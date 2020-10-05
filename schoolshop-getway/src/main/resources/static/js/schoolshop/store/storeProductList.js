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

let resultRoomApp = new Vue({
    el: '#resultRoomApp',
    data: {
        //过滤器对象
        filter: {
            merchantId: null,
            msg: '',
            type: 0,//商品(0)  店铺(1)
            category: null,//类型
            pageNum: 1,//指定页
            orderBy: 0,//通过什么排序
            orderSort: 1, //升序降序
        },
        room: []
    },
    methods: {
        loadStoreGoods: function () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');
            _this.filter.merchantId = split[2]
            _this.filter.category = decodeURI(split[4]);
            _this.$options.methods.load(_this);
        },
        load: function (_this) {
            if (_this == undefined) {
                _this = this
                _this.filter.pageNum = 1
            }
            axios.get('/api-search', {
                params: _this.filter
            }).then(function (response) {
                if (response.data.state === 2000) {
                    _this.room = response.data.data
                }
            }).catch(function (error) {

            })
        },
        //分页
        loadPaging: function (pageNum) {
            let _this = this;
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            _this.filter.pageNum = pageNum
            _this.$options.methods.load(_this);
            location.href = '#resultRoomApp'
        },
        clickGood: function (id) {
            location.href = '/store/' + this.filter.merchantId + '/good/' + id
        }
    },
    created: function () {
        this.loadStoreGoods()
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