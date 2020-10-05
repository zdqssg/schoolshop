let storeLogoApp = new Vue({
    el: '#storeLogoApp',
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
});
let storeNavApp = new Vue({
    el: '#storeNavApp',
    data: {
        store: {},
        category3:{},
        headerHotGoods:{}
    },
    methods: {
        //加载店铺信息Id 店铺名 和头像
        loadStoreInfo: function () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');
            // console.log(split)
            let merchandId = split[2];
            // console.log(merchandId)
            axios.get('/api-store/' + merchandId)
                .then(function (response) {
                    if (response.data.state === 2000) {
                        _this.store = response.data.data
                        _this.$options.methods.loadStoreCategory3(_this)
                        _this.$options.methods.loadStoreHotGoods(_this)
                    }
                }).catch(function (error) {

            })
        },
        //加载商家商品分类
        loadStoreCategory3: function (_this) {
            axios.get('/home/category3/' + _this.store.category2Id)
                .then(function (response) {
                    _this.category3=response.data.data

                })
                .catch(function (error) {
                })
        },
        //加载商家热卖
        loadStoreHotGoods:function (_this) {
            axios.get('/api-good/'+_this.store.id+'/hotGoods')
                .then(function (response) {
                    _this.headerHotGoods=response.data.data

                })
                .catch(function (error) {
                })
        }
    },
    created: function () {
        this.loadStoreInfo()
    }
});