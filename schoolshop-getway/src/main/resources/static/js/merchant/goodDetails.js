let goodDetailApp = new Vue({
    el: '#goodDetailApp',
    data: {
        goodId: 0,
        good: {},
        goodState: {
            show: false,
            value: 0,
            msg: '',
            text: ['下架中', '已上架', '商品违规'],
            option: ['下架', '上架']
        },
        updateInfo: {
            show: false,
            msg: ''
        },
        styleSale: {
            width: 0,
            minWidth: '3em'
        },
        styleNoSale: {
            width: 0
        }
    },
    methods: {
        //初始化
        loadGoodDetail(pageNum) {
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            this.goodId = parseInt($("#goodId").html());
            axios.get('/api-good/' + this.goodId + '/' + pageNum)
                .then(r => {
                    let data = r.data.data;
                    let good = data.list;

                    good.createTime = getTime(good.createTime);
                    good.updateTime = getTime(good.updateTime);
                    let sale = good.goodsSale;
                    let repertory = good.goodsRepertory;
                    if (repertory <= 0) {
                        this.styleSale.width = '0%';
                        this.styleNoSale.width = '100%'
                    } else {
                        if (sale > repertory) {
                            this.styleSale.width = numFloat((sale / (repertory + sale)) * 100) + '%';
                            this.styleNoSale.width = numFloat((1 - sale / (repertory + sale)) * 100) + '%';
                        } else {
                            this.styleSale.width = numFloat((sale / repertory) * 100) + '%';
                            this.styleNoSale.width = numFloat((1 - sale / repertory) * 100) + '%';
                        }
                    }
                    let comments = good.comment;
                    for (let i = 0; i < comments.length; i++) {
                        comments[i].createTime = getTime(comments[i].createTime);
                    }
                    data.list = good
                    this.good = data
                })

        },
        /**
         * 删除商品
         */
        deleteGood() {
            let b = confirm("是否确定删除该商品");
            if (b) {
                alert("正在删除，并跳到展示所有商品页面");
                location.href = '/merchant/goods'
            }
        },
        /**
         * 上传商品大图
         */
        getFile() {
            let formData = new FormData();
            formData.append("file", $('#goodBaseImgFile')[0].files[0])
            if (formData.get("file") === undefined) {
                return;
            }
            axios.post('/img/upload', formData)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.good.list.goodsImgPath = r.data.data
                        this.$options.methods.changeGoodImgPath(this)
                    }
                })
                .catch(e => {
                })
        },
        /**
         * 修改商品图片
         */
        changeGoodImgPath(_this) {
            let data = {
                id: _this.good.list.id,
                goodsImgPath: _this.good.list.goodsImgPath
            }
            axios.put('/api-merchant/goods', data)
        },
        /**
         *修改状态按钮单机事件
         */
        changeGoodStateBtn() {
            this.goodState.show = !this.goodState.show
        }, /**
         * 发送恢复商品的请求
         */
        sendRecoverRequest() {
            console.log('sendRecoverRequest')
        },
        /**
         *确定修改的提示
         */
        confirmChangeGoodState() {
            let _this = this;
            if (_this.good.list.goodsState === _this.goodState.value) {
                _this.goodState.msg = '未改动'
                setTimeout(function () {
                    _this.goodState.msg = ''
                }, 3000)
            } else {
                let data = {
                    id: _this.good.list.id,
                    goodsState: _this.goodState.value
                }
                console.log(data)
                axios.put('/api-merchant/goods', data)
                    .then(r => {
                        if (r.data.state == 2000) {
                            _this.good.list.goodsState = _this.goodState.value
                            _this.goodState.show = false
                        } else {
                            _this.goodState.msg = '修改异常'
                            setTimeout(function () {
                                _this.goodState.msg = ''
                            }, 3000)
                        }
                    })
            }
        },
        /**
         * 创建广告
         */
        aKeyToCreate(goodId) {
            axios.post('/api-advertising/' + goodId)
                .then(r => {
                    if (r.data.state === 2000) {
                        alert("创建成功")
                    } else {
                        alert(r.data.message)
                    }
                })
                .catch(e => {
                })

        },
        /**
         * 监听修改商品属性
         */
        changeInfo() {
            this.updateInfo.show = true
        },
        /**
         * 上传商品属性
         */
        saveInfo() {
            let good = this.good.list;
            let data = {
                id: good.id,
                goodsName: good.goodsName,
                goodsDescribe: good.goodsDescribe,
                goodsPrice: good.goodsPrice,
                goodsRepertory: good.goodsRepertory,
                isDiscount: good.isDiscount
            }
            axios.put('/api-merchant/goods', data)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.updateInfo.msg = '修改成功'
                        setTimeout(function () {
                            history.go(0)
                        }, 3000)

                    } else {
                        this.updateInfo.msg = r.data.message
                    }
                })
        },
        /**
         * 评论点赞
         * @param commentId
         */
        giveALike(commentId, index) {
            axios.put('/api-goodComment/addLike/' + commentId)
                .then(r => {
                    if (r.data.state == 2000) {
                        this.good.list.comment[index].approval = r.data.data
                    }
                })
        },
        /**
         *评论
         */
        newForOld() {
            let r = prompt("请输入评论信息...(临时实现效果)");
            if (r === '' || r) {
                alert('你提交了评论:' + r)
            }

        }
    },
    created() {
        this.loadGoodDetail()
    }
})


