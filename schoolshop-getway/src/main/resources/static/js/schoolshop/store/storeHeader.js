let storeLogoApp = new Vue({
    el: '#storeLogoApp',
    data: {
        store: {}
    },
    methods: {
        //加载店铺信息Id 店铺名 和头像
        loadStoreInfo() {
            let pathname = location.pathname;
            let split = pathname.split('/');
            // console.log(split)
            let merchandId = split[2];
            // console.log(merchandId)
            let _this = this;
            axios.get('/api-store/' + merchandId)
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.store = r.data.data
                    }
                })
                .catch(e => {
                })
        }
    },
    created() {
        this.loadStoreInfo()
    }
});
let storeNavApp = new Vue({
    el: '#storeNavApp',
    data: {
        store: {},
        category3: {},
        headerHotGoods: {}
    },
    methods: {
        //加载店铺信息Id 店铺名 和头像
        loadStoreInfo () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');
            // console.log(split)
            let merchandId = split[2];
            // console.log(merchandId)
            axios.get('/api-store/' + merchandId)
                .then(r=> {
                    if (r.data.state === 2000) {
                        _this.store = r.data.data
                        _this.$options.methods.loadStoreCategory3(_this)
                        _this.$options.methods.loadStoreHotGoods(_this)
                    }
                })
                .catch(e=>{

            })
        },
        //加载商家商品分类
        loadStoreCategory3(_this) {
            axios.get('/home/category3/' + _this.store.category2Id)
                .then(r=>{
                    _this.category3 = r.data.data

                })
                .catch(e=> {
                })
        },
        //加载商家热卖
        loadStoreHotGoods (_this) {
            axios.get('/api-good/' + _this.store.id + '/hotGoods')
                .then(r=> {
                    _this.headerHotGoods = r.data.data

                })
                .catch(e=> {
                })
        }
    },
    created () {
        this.loadStoreInfo()
    }
});