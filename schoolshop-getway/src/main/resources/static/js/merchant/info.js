let infoApp = new Vue({
    el: '#infoApp',
    data: {
        info: {}
    },
    methods: {
        init() {
            axios.get('/api-merchant/info')
                .then(r => {
                    this.info = r.data.data;
                })
        },
        // 上传图片
        upload() {
            let formData = new FormData();
            formData.append("file", $('#storeHeadPhoto')[0].files[0])
            if (formData.get("file") === undefined) {
                return;
            }
            axios.post('/img/upload', formData)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.$options.methods.update(r.data.data)
                    }
                })
                .catch(e => {
                })
        },
        update(storeHeadPhoto) {
            axios.put('/api-merchant/info/changeHeadPhoto/' + storeHeadPhoto)
                .then(r => {
                    if (r.data.state===2000){
                        this.info.storeHeadPhoto =storeHeadPhoto
                    }else {
                        alert(r.data.message)
                    }
                })
        }
    },
    created() {
        this.init()
    }
})