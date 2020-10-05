let commentListApp = new Vue({
    el: '#commentListApp',
    data: {
        data: []
    },
    methods: {
        load (pageNum) {
            let _this = this;
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            axios.get("/api-user/userGoodsComment/" + pageNum)
                .then(r=> {
                    _this.data = r.data.data
                })
                .catch(e=> {

                })

        },
        clickGood (merchantId,id) {
            location.href='/store/'+merchantId+'/good/'+id
        }
    },
    created () {
        this.load()
    }
})