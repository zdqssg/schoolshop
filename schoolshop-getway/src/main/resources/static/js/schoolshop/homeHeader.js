new Vue({
    el: '#schoolshopNavApp',
    data: {
        category1List: [],
        goods1: [],
        goods2: [],
        stores: [],
        Goods: [],
        store: []
    },
    methods: {

        init() {
            //头部商品分类
            axios.get('/home/category1')
                .then(r => {
                    this.category1List = r.data.data
                })
                .catch(e => {
                })
            //头部精选热搜
            axios.get('/home/selectHotSearch')
                .then(r => {
                    let data = r.data.data
                    this.goods1 = data.slice(0, 4)
                    this.goods2 = data.slice(5, 9)
                })
                .catch(e => {
                })
            //头部推荐商家们
            axios.get('/home/headerStores')
                .then(r => {
                    this.stores = r.data.data;
                })
                .catch(e => {
                })
            //头部精选商品
            axios.get('/home/headerProducts')
                .then(r => {
                    this.Goods = r.data.data;
                })
                .catch(e => {
                })
            //头部精选商店
            axios.get('/home/headerStore')
                .then(r => {
                    this.store = r.data.data;
                })
                .catch(e => {
                })
        },
        clickGoods(merchantId, id) {
            location.href = '/store/' + merchantId + '/good/' + id
        }
    },
    created() {
        this.init()
    }
})




