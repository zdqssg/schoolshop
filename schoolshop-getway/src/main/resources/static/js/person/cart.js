let userCartApp = new Vue({
    el: '#userCartApp',
    data: {
        isCheckedAll: false,
        sum: 0,
        cartId: [],
        cart: [],
        soldCart: []
    },
    methods: {
        /**
         * 加载购物车
         */
        loadCart () {
            let _this = this;
            axios.get('/api-user/cart')
                .then(r=> {
                    let cartAll = r.data.data;
                    let cart = []
                    let soldCart = []
                    for (let i = 0; i < cartAll.length; i++) {
                        if (cartAll[i].good.goodsState == 1) {
                            cartAll[i].checked = false
                            // console.log(cartAll[i])
                            cart.push(cartAll[i])
                        } else {
                            soldCart.push(cartAll[i])
                        }
                    }
                    _this.cart = cart
                    _this.$options.methods.calculateSum(_this)
                    // console.log(_this.cart)
                    _this.soldCart = soldCart
                })
                .catch(e=> {

                })
        },
        /**
         * 删除购物车的商品
         * @param id
         * @param index
         */
        deleteCart (id, index) {
            if (confirm("你确定要删除吗？")) {
                let _this = this;
                axios.delete('/api-user/cart/' + id)
                    .then(r=> {
                        if (r.data.state == 2000) {
                            for (let i = 0; i < _this.cart.length; i++) {
                                if (_this.cart[i].id == id) {
                                    _this.cart.splice(index, 1)
                                }
                            }
                            for (let i = 0; i < _this.soldCart.length; i++) {
                                if (_this.soldCart[i].id == id) {
                                    _this.soldCart.splice(index, 1)
                                }
                            }

                        } else {
                            alert('删除失败:' + r.data.message)
                        }
                    })
                    .catch(e=>{
                        alert('异常:' + e)
                    })
            }

        },
        /**
         * 选中购物车的商品
         * @param id
         * @param index
         */
        checkedCart (id, index) {
            let _this = this;
            // console.log(index)
            // console.log(_this.cart[index].checked)
            _this.cart[index].checked = !_this.cart[index].checked


            // console.log(_this.cart[index].checked)
            //计算总价
            _this.$options.methods.calculateSum(_this)
        },
        /**
         * 全选
         */
        checkedAll () {
            let _this = this;
            _this.isCheckedAll = !_this.isCheckedAll
            let cart = _this.cart;
            for (let i = 0; i < cart.length; i++) {
                cart[i].checked = _this.isCheckedAll
            }
            _this.$options.methods.calculateSum(_this)
        },
        /**
         * 改变数量
         * @param n
         * @param index
         */
        changNumber (n, index) {
            let _this = this;
            let cartElement = _this.cart[index];
            let cartNumber = cartElement.number;

            let number = 0;
            if (n) {
                number = ++cartNumber;
            } else {
                number = --cartNumber;
            }
            if (number < 1) {
                return;
            }
            let params = {
                id: cartElement.id,
                number: number
            }
            axios.put("/api-user/cart", params)
                .then(r=> {
                    if (r.data.state === 2000) {
                        cartElement.number = number
                        //计算总价
                        _this.$options.methods.calculateSum(_this)
                    }
                })
                .catch(e=> {

                })
        },
        /**
         * 计算总价
         * @param _this
         */
        calculateSum (_this) {
            let sum = 0
            let cart = _this.cart;
            let count=0;
            for (let i = 0; i < cart.length; i++) {
                let goodSum = (cart[i].good.goodsPrice) * (cart[i].number)
                cart[i].sum = goodSum
                if (cart[i].checked) {
                    sum += cart[i].sum
                    count+=1;
                    // console.log(_this.cart[i].sum)
                }

            }
            if (count==cart.length){
                _this.isCheckedAll=true
            }else {
                _this.isCheckedAll=false
            }
            _this.sum = sum
        },
        gotoPay () {
            let _this = this;
            let cart = _this.cart;
            let idArr = []
            for (let i = 0; i < cart.length; i++) {
                if (cart[i].checked) {
                    idArr.push(cart[i].id)
                }
            }
            if (idArr.length <= 0) {
                alert("请选择要购买的商品")
                return;
            }
            axios.post('/api-user/order/addOrder/'+idArr)
                .then(r=> {
                    if (r.data.state==2000){
                       let oderId = parseInt(r.data.data);
                        location.href = '/person/checkout/'+oderId
                    }else {
                        alert("系统异常")
                    }
                })
                .catch(e=>{
                   alert("系统异常"+e)
                })
        }
    },
    created () {
        this.loadCart()
    }
})