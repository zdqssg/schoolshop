let paySystemApp = new Vue({
    el: '#paySystemApp',
    data: {
        sum: 0,//总价
        order: {},//订单
        defaultAddr: null,//地址
        toPayHint: {
            msg: ''
        }
    },
    methods: {
        /**
         *
         *
         */
        parseUrl () {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');
            let orderId = parseInt(split[3]);
            _this.$options.methods.loadOrder(_this, orderId)

        },
        loadOrder (_this, orderId) {
            axios.get('/api-user/order/toPay/' + orderId)
                .then(r=> {
                    if (r.data.state === 2000) {
                        let order = r.data.data
                        _this.order = order
                        let sum = 0
                        for (let i = 0; i < order.cart.length; i++) {
                            sum += (order.cart[i].good.goodsPrice) * (order.cart[i].number)
                        }
                        _this.sum = sum
                        _this.$options.methods.loadDefaultAddr(_this)
                    } else {
                        location.href = '/person/order'
                    }
                })
                .catch(e=>{

                })

        },
        loadDefaultAddr (_this) {
            axios.get('/api-user/receiveAddress/defaultAddr')
                .then(r=> {
                    if (r.data.state == 2000) {
                        _this.defaultAddr = r.data.data
                        _this.defaultAddr.has = true
                    } else {
                        _this.defaultAddr.has = false
                    }
                })
                .catch(e=> {

                })
        },
        toPay () {
            let _this = this;
            if (_this.defaultAddr == null) {
                _this.toPayHint.msg = '<a href="/person/address">请先设置默认收货地址</a>'
                setTimeout(function () {
                    _this.toPayHint.msg = ''
                }, 60 * 1000)
                return;
            }
            _this.toPayHint.msg = '后台正在处理[请勿关闭页面和其他操作]'
            let index = 0
            let dianArr = ['·', '··', '···', '····', '·····', '······']
            let interval = setInterval(function () {
                _this.toPayHint.msg = '后台正在处理[请勿关闭页面和其他操作]' + dianArr[index++]
                if (index > 5) {
                    index = 0
                }
            }, 500);
            let order = _this.order;
            order.username = _this.defaultAddr.username
            order.phone = _this.defaultAddr.phone
            order.provinceCode = _this.defaultAddr.provinceCode
            order.cityCode = _this.defaultAddr.cityCode
            order.areaCode = _this.defaultAddr.areaCode
            order.streetCode = _this.defaultAddr.streetCode
            order.detail = _this.defaultAddr.detail
            axios.post(
                '/api-user/order',
                order,
                {timeout: 1000 * 60 * 2})
                .then(r=> {
                    let state = r.data.state;

                    clearInterval(interval)
                    if (state == 2000) {
                        _this.toPayHint.msg = '支付成功'
                        setTimeout(function () {
                            location.href = '/person/order'
                        }, 3000)
                        return;
                    }
                    let message = r.data.message;
                    if (state == 5000) {
                        _this.toPayHint.msg = '请勿重复提交'
                    } else if (state == 5010 || state == 5050) {
                        _this.toPayHint.msg = message;
                        setTimeout(function () {
                            location.href = '/person/order'
                        }, 3000)

                    } else if (state == 5020) {
                        _this.toPayHint.msg = message;
                        let moneyNoFull = confirm(message);
                        if (moneyNoFull) {
                            let reason = prompt('请输入充值金额');
                            a(reason)
                            function a(reason){
                                if (reason || reason === "") {
                                    //填写内容并“确定”
                                    if (isNaN(reason) || reason <= 1 || reason > 99999999) {
                                        reason = prompt('请输入正确的金额');
                                        a(reason)
                                    } else {
                                        alert("正在充值")

                                    }
                                } else {

                                }
                            }


                        }
                    } else if (state == 5030 || state == 5040 || state == 3000) {
                        _this.toPayHint.msg = message;
                    } else {
                        _this.toPayHint.msg = '未知错误'
                    }
                })
                .catch(e=> {
                    clearInterval(interval)
                    _this.toPayHint.msg = '系统正忙请稍后再试'
                })


        },
        clickGood (merchantId, id) {
            location.href = '/store/' + merchantId + '/good/' + id
        }
    },
    created () {
        this.parseUrl()
    }
})


