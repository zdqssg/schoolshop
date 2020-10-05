//首页轮播图
let slideList = new Vue({
    el: '#slideList',
    data: {
        List: []
    },
    methods: {
        loadSlideList() {
            axios.get('/home/slideList')
                .then(r => {
                    this.List = r.data.data;
                })
        }
    },
    created() {
        this.loadSlideList()
    }
});
//首页推荐商品
let recommendGoods = new Vue({
    el: '#recommendGoods',
    data: {
        List: []
    },
    methods: {
        loadRecommendGoods () {
            axios.get('/home/recommendList')
                .then(r => {
                    this.List = r.data.data;
                })
        },
        clickGood (merchantId, id) {
            location.href = '/store/' + merchantId + '/good/' + id
        }
    },
    created () {
        this.loadRecommendGoods()
    }
});
//首页热门商品
let hotGoodsApp = new Vue({
    el: '#hotGoodsApp',
    data: {
        List: []
    },
    methods: {
        loadHotGood() {
            axios.get('/home/hotProducts')
                .then(r => {
                    this.List = r.data.data
                })
        }
        ,
        clickGood (merchantId, id) {
            location.href = '/store/' + merchantId + '/good/' + id
        }
    },
    created() {
        this.loadHotGood();
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
            axios.get('/api-advertising/random')
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