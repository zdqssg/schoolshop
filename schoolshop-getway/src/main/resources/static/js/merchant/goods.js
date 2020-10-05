let vGoods = new Vue({
    el: "#v-goods",
    data: {
        category3Id: 0,
        goods: {}
    },
    methods: {
        loadGoods: function (pageNum) {
            loadGoodsByType(this.category3Id, pageNum)
        }
    },
    created: function () {
        this.loadGoods()
    }
});
let vManager = new Vue({
    el: "#v-manager",
    data: {
        initIndex: 0,
        category3: []
    },
    methods: {
        loadGoods: function (category3Id) {
            vGoods.category3Id = category3Id;
            loadGoodsByType(category3Id, 1)
        },
        active(index) {
            this.initIndex = index
        },
        loadCategory3: function () {
            $.ajax({
                url: '/api-merchant/info/category3',
                success: function (json) {
                    vManager.category3 = json.data
                }
            })
        }
    },
    created: function () {
        this.loadCategory3()
    }
});

function loadGoodsByType(category3Id, pageNum) {
    if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
        pageNum = 1;
    }
    $.ajax({
        url: '/api-merchant/goods/' + category3Id + '/' + pageNum,
        success: function (json) {
            let list = json.data.list;
            for (let i = 0; i < list.length; i++) {
                list[i].createTime = getTime(list[i].createTime);
            }
            vGoods.goods = json.data;
        }
    })
}