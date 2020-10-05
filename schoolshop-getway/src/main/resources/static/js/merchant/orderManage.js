let orderApp = new Vue({
    el: '#orderApp',
    data: {
        data: {}
    },
    methods: {
        load(pageNum) {
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            axios.get('/api-merchant/order/' + pageNum)
                .then(r => {
                    let data = r.data.data;
                    for (let i = 0; i < data.length; i++) {
                        data[i].createTime=getTime(data[i].createTime)
                    }
                    this.data =data;
                })
        },
        sendGood(id,index){
         axios.put('/api-merchant/order/sendGood/'+id)
             .then(r=>{
                 if (r.data.state===2000){
                     this.data[index].state=3
                     alert(发货成功)
                 }else {
                     alert(r.data.message)
                 }
             })
        }
    },
    created() {
        this.load()
    }

})