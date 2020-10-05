let commentListApp = new Vue({
    el: '#commentListApp',
    data: {
        data: []
    },
    methods: {
        load: function (pageNum) {
            let _this = this;
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            axios.get("/api-user/userGoodsComment/" + pageNum)
                .then(function (response) {
                    _this.data = response.data.data
                })
                .catch(function (error) {

                })

        },
        clickGood:function (merchantId,id) {
            location.href='/store/'+merchantId+'/good/'+id
        }
    },
    created: function () {
        this.load()
    }
})