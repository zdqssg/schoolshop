let addGoodForm = new Vue({
    el: "#addGoodForm",
    data: {
        good: {
            goodsName: '',
            goodsPrice: '',
            goodsRepertory: '',
            goodsDescribe: '',
            category3Id: '',
            goodsImgPath: ''
        },
        category3: [],
        msg: ''
    },
    methods: {
        //初始化
        init() {
            //加载三级分类
            axios.get('/api-merchant/info/category3')
                .then(r => {
                    this.category3 = r.data.data
                })
                .catch(e => {
                })
        },
        // 上传图片
        upload() {
            let formData = new FormData();
            formData.append("file", $('#goodsImg')[0].files[0])
            if (formData.get("file") === undefined) {
                return;
            }
            axios.post('/img/upload', formData)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.good.goodsImgPath = r.data.data
                    }
                })
                .catch(e => {
                })
        },
        //点击添加商品
        addGood() {
            console.log(this.good)
            axios.post('/api-merchant/goods', this.good)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.msg = '添加成功'
                        this.$options.methods.empty(this)
                    } else {
                        this.msg = r.data.message
                    }
                })
                .catch(e => {
                })
        },
        //清空
        empty(_this) {
            _this.good = {
                goodsName: '',
                goodsPrice: '',
                goodsRepertory: '',
                goodsDescribe: '',
                category3Id: '',
                goodsImgPath: ''
            }
        }

    },
    created() {
        this.init()
    }
});

