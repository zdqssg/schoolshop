let user = new Vue({
    el: '#user',
    data: {
        info: {}
    },
    methods: {
        loadInfo: function () {
            $.ajax({
                url: '/api-user/info/info',
                success: function (json) {
                    user.info = json.data
                }
            })
        }
    },
    created: function () {
        this.loadInfo()
    }
});
let newGoodApp = new Vue({
    el: '#newGoodApp',
    data: {
        hasLike: false,
        good: {}
    },
    methods: {
        loadNewGood: function () {
            let _this = this;
            axios.get('/api-good/newGood')
                .then(function (response) {
                    _this.good = response.data.data
                    _this.$options.methods.loadUserHasLike(_this);
                })
                .catch(function (error) {

                })
        },
        loadUserHasLike: function (_this) {
            console.log(_this)
            let goodId = _this.good.id;
            axios.get('/api-user/likeGoods/' + goodId)
                .then(function (response) {
                    if (response.data.state === 2000) {
                        if (response.data.data.state == 1) {
                            _this.hasLike = true
                        } else {
                            _this.hasLike = false
                        }

                    } else {
                        _this.hasLike = false
                    }
                })
                .catch(function (error) {
                    _this.hasLike = false
                })
        },
        likeGood: function (goodId) {
            let _this = this;
            axios.post('/api-user/likeGoods/' + goodId).then(function (response) {
                // console.log(response)
                if (response.data.state === 2000) {
                    _this.hasLike = !_this.hasLike
                }
            }).catch(function (error) {
            })
        }
    },
    created: function () {
        this.loadNewGood()
    }
})
let recommendGoodApp = new Vue({
    el: '#recommendGoodApp',
    data: {
        good: []
    },
    methods: {
        loadRecommendGoods: function () {
            let _this = this;
            axios.get('/home/headerProducts')
                .then(function (response) {
                    _this.good = response.data.data
                })
                .catch(function (error) {

                })
        }
    }, created: function () {
        this.loadRecommendGoods()
    }
})

