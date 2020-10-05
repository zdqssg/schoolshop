let orderApp = new Vue({
    el: '#orderApp',
    data: {
        navTabs: [
            {active: true, text: '所有订单'},
            {active: false, text: '待付款'},
            {active: false, text: '待发货'},
            {active: false, text: '待收货'},
            {active: false, text: '待评价'}],
        order: []
    },
    methods: {

        //加载订单
        loadOrder: function (state) {
            //active
            this.navTabs.forEach(f => (f.active = false))
            this.navTabs[state].active = true

            if (state == 0) {
                axios.get('/api-user/order')
                    .then(r => {
                        this.order = r.data.data;
                        this.$options.methods.handleOrder(this);
                    })
                    .catch(e=>{})
            } else {
                axios.get('/api-user/order/state/' + state)
                    .then(r => {
                        this.order = r.data.data
                        this.$options.methods.handleOrder(this);
                    })
                    .catch(e=>{})
            }

        },
        //处理订单
        handleOrder: function (_this) {
            for (let i = 0; i < _this.order.length; i++) {
                //转换时间格式
                _this.order[i].createTime = getTime(_this.order[i].createTime);
                //计算总价
                let cart = _this.order[i].cart;
                for (let j = 0; j < cart.length; j++) {
                    _this.order[i].sum = cart[j].number * cart[j].good.goodsPrice
                }
            }
        }

    },
    created: function () {
        this.loadOrder(0)
    }
})