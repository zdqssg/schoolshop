//头部用户信息
let navRightApp = new Vue({
    el: '#navRightApp',
    data: {
        user: false,
        nickname: '',
        carts: [],
        search: {
            msg: '',
            timeout: null,
            isNone: true,
            matchingArr: []
        }
    },
    methods: {
        //加载用户名
        loadInfo () {
            let _this = this;
            axios.get('/api-user/info/name')
                .then(r=> {
                    if (r.data.state === 2000) {
                        _this.user = true
                        _this.nickname = r.data.data;
                        _this.$options.methods.loadUserCart(_this);
                    }
                })
                .catch(e=>{
                    _this.user = false
                    _this.nickname = '';
                    _this.carts = [];
                })
        },
        loadUserCart (_this) {
            axios.get('/api-user/cart/headerCart')
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.carts = r.data.data;
                    }
                })
                .catch(e => {
                })
        },
        /**
         * 当input的值改变的事件  延迟400毫秒发送请求
         */
        sendMatching (_this) {
            if (_this === undefined) {
                _this = this;
            }
            // 实现input连续输入，只发一次请求
            clearTimeout(_this.timeout);
            _this.timeout = setTimeout(() => {
                if (!_this.search.msg || /^\s*$/.test(_this.search.msg)) {
                    _this.search.isNone = true
                    return false;
                }
                axios.get('/api-search/searchMatching/' + _this.search.msg)
                    .then(r => {
                        let result = r.data.data;
                        console.log(result)
                        if (result.length === 0) {
                            _this.search.isNone = true
                            return;
                        }
                        _this.search.isNone = false
                        _this.search.matchingArr = result;
                    })
                    .catch(e => {
                    })
            }, 400);
        },
        //以下都是事件
        /**
         *
         */
        toShopWord () {
            location.href = '/home/searchMsg/' + this.search.msg
        },
        toShopWordByMatching(msg) {
            location.href = '/home/searchMsg/' + msg;
        }
    },
    created () {
        this.loadInfo()
    }
});