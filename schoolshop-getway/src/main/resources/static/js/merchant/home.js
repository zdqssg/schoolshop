let vNumber = new Vue({
    el: "#v-number",
    data: {
        orderNumber: 0,
        visitNumber: 0,
        likeNumber: 0,
        incomeNumber: 0
    },
    methods: {
        loadOrderNumber: function () {
            $.ajax({
                url: '/api-merchant/order/orderNumber',
                success: function (json) {
                    vNumber.orderNumber = json.data;
                }
            })
        },
        loadClickCountAndSumMoney: function () {
            $.ajax({
                url: '/api-merchant/info/clickCountAndSumMoney',
                success: function (json) {
                    vNumber.visitNumber = json.data.clickCount;
                    vNumber.incomeNumber = json.data.sumMoney;
                }
            })
        },
        loadLikeNumber: function () {
            $.ajax({
                url: '/api-merchant/info/likeNumber',
                success: function (json) {
                    vNumber.likeNumber = json.data;
                }
            })
        }
    },
    created: function () {
        this.loadOrderNumber()
        this.loadClickCountAndSumMoney()
        this.loadLikeNumber()
    }

});