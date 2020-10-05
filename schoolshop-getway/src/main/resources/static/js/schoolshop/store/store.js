let titleApp = new Vue({
    el: '#titleApp',
    data: {
        store: {}
    },
    methods: {
        //加载店铺信息Id 店铺名 和头像
        loadStoreInfo: function () {
            let pathname = location.pathname;
            let split = pathname.split('/');
            // console.log(split)
            let merchandId = split[2];
            // console.log(merchandId)
            let _this = this;
            axios.get('/api-store/' + merchandId)
                .then(function (response) {
                    if (response.data.state === 2000) {
                        _this.store = response.data.data
                    }
                }).catch(function (error) {

            })
        }
    },
    created: function () {
        this.loadStoreInfo()
    }
})

let slideApp = new Vue({
    el: '#slideApp',
    data: {
        merchantId: null,
        slide: []
    },
    methods: {
        //加载店铺轮播图
        loadStoreSlide: function () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');

            let merchantId = split[2];
            _this.merchantId = merchantId
            axios.get('/api-store/' + merchantId + '/slide')
                .then(function (response) {
                    if (response.data.state === 2000) {
                        _this.slide = response.data.data
                    }
                }).catch(function (error) {

            })
        },
    },
    created: function () {
        this.loadStoreSlide()
    }
});
let newGoodsApp = new Vue({
    el: '#newGoodsApp',
    data: {
        merchantId: null,
        newGoods: []
    },
    methods: {
        loadStoreNewGoods: function () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');

            let merchantId = split[2];
            _this.merchantId = merchantId
            axios.get('/api-good/' + merchantId + '/newGoods')
                .then(function (response) {
                    if (response.data.state === 2000) {
                        _this.newGoods = response.data.data
                    }
                })
                .catch(function (error) {

                })
        },
        clickGood: function (id) {
            location.href = '/store/' + this.merchantId + '/good/' + id
        }
    },
    created: function () {
        this.loadStoreNewGoods()
    }
})
let hotGoodsApp = new Vue({
    el: '#hotGoodsApp',
    data: {
        merchantId: null,
        hotGoods: []
    },
    methods: {
        loadStoreHotGoods: function () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');
            let merchantId = split[2];
            _this.merchantId = merchantId
            axios.get('/api-good/' + merchantId + '/hotGoods')
                .then(function (response) {
                    if (response.data.state === 2000) {
                        _this.hotGoods = response.data.data
                    }
                })
                .catch(function (error) {

                })

        },
        clickGood: function (id) {
            location.href = '/store/' + this.merchantId + '/good/' + id
        }
    },
    created: function () {
        this.loadStoreHotGoods()
    }
})


let advertisingApp = new Vue({
    el: '#advertisingApp',
    data: {
        advertising: []
    },
    methods: {
        init(_this) {
            if (_this === undefined) {
                _this = this;
            }
            let pathname = location.pathname;
            let split = pathname.split('/');

            let merchantId = split[2];
            axios.get('/api-advertising/random/'+merchantId)
                .then(r => {
                    let advertising = r.data.data;
                    advertising = this.$options.methods.handleADV(advertising);
                    console.log(advertising)
                    _this.advertising = advertising
                })
                .catch(e => {
                })


        },
        setIntervalF() {
            setInterval(d => {
                this.$options.methods.init(this);
            }, 1000 * 30)
        },
        handleADV(advertising) {
            for (let i = 0; i < advertising.length; i++) {
                let time = advertising[i].createTime;
                advertising[i].month = getMonthToString(time)
                advertising[i].date = getDate(time);
            }
            return advertising;
        }
    },
    created() {
        this.init()
        this.setIntervalF()
    }

})