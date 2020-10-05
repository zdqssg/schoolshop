let resultRoomApp = new Vue({
    el: '#resultRoomApp',
    data: {
        //价格区间下标
        priceIndex: 0,
        //价格区间
        priceSection: [
            {priceStart: 0, priceEnd: 100},
            {priceStart: 100, priceEnd: 300},
            {priceStart: 300, priceEnd: 1000},
            {priceStart: 1000, priceEnd: 5000},
            {priceStart: 5000, priceEnd: 99999999}],
        //过滤器对象
        filter: {
            msg: null,//模糊关键字
            category: null,//类型
            pageNum: 0,//指定页
            orderBy: null,//通过什么排序
            orderSort: 1, //升序降序
            betweenBy: null,//通过什么搜索范围
            betweenStart: null,//范围
            betweenEnd: null//范围
        },
        // //分类数组
        // category: [],
        //搜索内容
        room: {},
        //分类
        category1List: [],
        //分类显示的状态
        categoryHiden: false,
        //推荐商品
        recommendGoods: []
    },
    methods: {
        //初始化
        init () {
            //获取一级分类
            axios.get('/home/category1')
                .then(r => {
                    this.category1List = r.data.data;
                })
            //加载推荐商品
            axios.get('/home/headerProducts')
                .then(r => {
                    this.recommendGoods = r.data.data;
                })
        },
        /**
         * 得到当前搜索的是按关键字搜索还是类型搜索
         */
        parseUrl () {
            let pathname = location.pathname;
            let split = pathname.split('/');
            if (split[2] === 'searchMsg') {
                this.filter.msg = decodeURI(split[3]);
            } else {
                this.filter.category = decodeURI(split[3]);
            }
            this.$options.methods.load(this);
        },
        load (_this) {
            if (_this === undefined) {
                _this = this
                _this.filter.pageNum = 0
            }
            axios.post('/api-search', _this.filter)
                .then(r => {
                    _this.room = r.data.data;
                    console.log(_this.room)
                })
        },
        //分页
        loadPaging (pageNum) {
            if (this.room.list.size === 0) {
                return;
            }
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 0;
            }
            this.filter.pageNum = pageNum
            this.$options.methods.load(this);
            //锚点
            location.href = '#resultRoomApp'
        },

        //改变分类列表的显示状态
        changeCategoryState () {
            resultRoomApp.categoryHiden = !resultRoomApp.categoryHiden
        },
        /**
         *  //按设置价格范围过滤
         * @param betweenBy 范围查询的字段价格
         */
        loadByPrice (betweenBy) {
            if (this.room.total === 0) {
                return;
            }
            if (betweenBy === undefined) betweenBy = null
            //获取价格区间下标
            let priceIndex = this.priceIndex;
            this.filter.betweenBy = betweenBy
            //设置范围起点和终点
            this.filter.betweenStart = this.priceSection[priceIndex].priceStart
            this.filter.betweenEnd = this.priceSection[priceIndex].priceEnd

            this.$options.methods.load(this);
        },
        clickGood (merchantId, id) {
            location.href = '/store/' + merchantId + '/good/' + id
        }
    },
    created () {
        //解析Url
        this.parseUrl()
        //初始化
        this.init()
    }

})
let hotGoodsApp = new Vue({
    el: '#hotGoodsApp',
    data: {
        hotGoods: []
    },
    methods: {
        loadStoreHotGoods () {
            let _this = this;
            axios.get('/home/hotProducts')
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.hotGoods = r.data.data
                    }
                })
                .catch(e => {

                })

        },
        clickGood(merchantId, id) {
            location.href = '/store/' + merchantId + '/good/' + id
        }
    },
    created () {
        this.loadStoreHotGoods()
    }
})


