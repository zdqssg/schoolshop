let userInfo = new Vue({
    el: '#user-info',
    data: {
        info: {}
    },
    methods: {
        loadInfo () {
            axios.get('/api-user/info/info')
                .then(r=>{
                    this.info = r.data.data
                })
        },
        getFile () {
            let formData = new FormData();
            formData.append("file", $('#user-header-photo')[0].files[0])
            if (formData.get("file") === undefined) {
                return;
            }
            axios.post('/img/upload', formData)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.info.headPhoto = r.data.data
                        this.$options.methods.changeHeaderPhoto(this)
                    }
                })
                .catch(e => {
                })
        },
        changeHeaderPhoto(_this){
            let data = {
                headPhoto: _this.info.headPhoto
            }
            axios.put('/api-user/info/headPhoto', data)
        },
        updateGender () {
            let data = {
                gender:this.info.gender
            }
            axios.put('/api-user/info/changeGender',data)
        },
        updateBirth () {
            let data = {
                birth:this.info.birth
            }
            axios.put('/api-user/info/updateBirth',data)

        }
    },
    created () {
        this.loadInfo()
    }
});