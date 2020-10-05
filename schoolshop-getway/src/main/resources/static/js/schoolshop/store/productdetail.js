let titleApp = new Vue({
    el: '#titleApp',
    data: {
        good: {}
    },
    methods: {
        //加载店铺信息Id 店铺名 和头像
        loadStoreInfo () {
            let pathname = location.pathname;
            let split = pathname.split('/');
            // console.log(split)
            let goodId = split[4];
            let _this = this;
            axios.get('/api-good/info/' + goodId)
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.good = r.data.data
                    }
                })
                .catch(e => {
                })
        }
    },
    created() {
        this.loadStoreInfo()
    }
})

let goodDetailApp = new Vue({
    el: '#goodDetailApp',
    data: {
        user: false,
        message: null,
        userHasLikeHint: {
            hasLike: false,
            state: false,
            color: null,
            message: '',
            timeOut: null
        },
        addCartHint: {
            state: false,
            color: null,
            message: '',
            timeOut: null
        },
        data: null,

    },
    methods: {
        loadGoodDetail (pageNum) {
            let _this = this;
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            let pathname = location.pathname;
            let split = pathname.split('/');
            let goodId = parseInt(split[4]);

            axios.get('/api-good/' + goodId + '/' + pageNum)
                .then(function (json) {
                    if (json.data.state === 2000) {
                        let data = json.data.data;
                        json.data;
                        let good = data.list;

                        good.createTime = getTime(good.createTime);

                        let comments = good.comment;
                        for (let i = 0; i < comments.length; i++) {
                            comments[i].createTime = getTime(comments[i].createTime);
                        }
                        data.good = good
                        _this.data = data

                        _this.$options.methods.loadUserInfo(_this);
                    } else {
                        _this.message = json.data.message
                    }
                })
        },
        //加载用户
        loadUserInfo(_this) {
            axios.get('/api-user/info/name')
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.user = true
                        _this.$options.methods.loadUserHasLike(_this);
                    } else {
                        _this.user = false
                    }

                })
                .catch(e => {
                    _this.user = false
                })
        },
        loadUserHasLike(_this) {
            console.log(_this)
            let goodId = _this.data.good.id;
            axios.get('/api-user/likeGoods/' + goodId)
                .then(r => {
                    if (r.data.state === 2000) {
                        if (r.data.data.state === 1) {
                            _this.userHasLikeHint.hasLike = true
                        } else {
                            _this.userHasLikeHint.hasLike = false
                        }

                    } else {
                        _this.userHasLikeHint.hasLike = false
                    }
                })
                .catch(e => {
                    _this.userHasLikeHint.hasLike = false
                })
        },
        changeLike (goodId) {
            let _this = this;
            if (!_this.user) {
                _this.$options.methods.hint(_this.userHasLikeHint, '<span>请</span><a href="/person/none/login" class="alert-link">登录</a>!!!', 'danger')
                return;
            }
            axios.post('/api-user/likeGoods/' + goodId)
                .then(r => {
                    // console.log(response)
                    if (r.data.state === 2000) {
                        _this.userHasLikeHint.hasLike = !_this.userHasLikeHint.hasLike
                    } else {
                        _this.$options.methods.hint(_this.userHasLikeHint, r.data.message, 'danger')
                    }
                }).catch(e => {
                _this.$options.methods.hint(_this.userHasLikeHint, '异常，请稍后重试', 'danger')
            })
        },
        /**
         * 添加进购物车
         */
        addCart(goodId) {
            console.log("尝试添加进购物车", goodId)
            let _this = this;
            if (!_this.user) {
                _this.$options.methods.hint(_this.addCartHint, '<span>请</span><a href="/person/none/login" class="alert-link">登录</a>!!!', 'danger')
                return;
            }
            axios.post('/api-user/cart/' + goodId)
                .then(r => {
                    // console.log(response)
                    if (r.data.state === 2000) {
                        _this.$options.methods.hint(_this.addCartHint, '添加成功', 'success')
                    } else {
                        _this.$options.methods.hint(_this.addCartHint, r.data.message + '<a href="/person/cart">[购物车]</a>', 'danger')
                    }
                }).catch(e => {
                // console.log(error)
                _this.$options.methods.hint(_this.addCartHint, '异常，请稍后重试', 'danger')
            })
        },
        /**
         *添加购物车的警告
         */
        hint(_this, message, color) {
            _this.state = true
            _this.message = message
            _this.color = color
            clearTimeout(_this.timeOut)
            _this.timeOut = setTimeout(function () {
                _this.state = false
            }, 5000)
        },
        /**
         * 评价点赞
         * @param id
         */
        giveALike(id) {
            let _this = this;
            axios.put('/api-goodComment/addLike/' + id)
                .then(r => {
                    // console.log(_this.data.good.comment)
                    if (r.data.state === 2000) {
                        // console.log(_this.data.good.comment)
                        let comments = _this.data.good.comment;
                        for (let i = 0; i < comments.length; i++) {
                            // console.log(comments[i].id)
                            if (comments[i].id === id) {
                                comments[i].approval = r.data.data
                            }
                        }
                    }
                }).catch(e => {

            })
        }
    },
    created() {
        this.loadGoodDetail()
    }
});
let hotGoodsApp = new Vue({
    el: '#hotGoodsApp',
    data: {
        merchantId: null,
        hotGoods: []
    },
    methods: {
        loadStoreHotGoods() {
            let _this = this;
            let pathname = location.pathname;
            let split = pathname.split('/');
            let merchantId = split[2];
            _this.merchantId = merchantId
            axios.get('/api-good/' + merchantId + '/hotGoods')
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.hotGoods = r.data.data
                    }
                })
                .catch(e => {

                })

        },
        clickGood(id) {
            location.href = '/store/' + this.merchantId + '/good/' + id
        }
    },
    created() {
        this.loadStoreHotGoods()
    }
})