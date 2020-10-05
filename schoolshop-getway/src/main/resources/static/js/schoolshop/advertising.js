let advertisingApp = new Vue({
    el: '#advertisingApp',
    data: {
        titleShow: false,
        titleADV: {},
        allADV: []
    },
    methods: {
        parseURL() {
            let pathname = location.pathname;
            let split = pathname.split('/');
            let element = split[3];
            if (element !== undefined && element > 0 && !isNaN(element)) {
                this.$options.methods.loadTitleADV(this, element)
            }
        },
        loadTitleADV(_this, element) {
            axios.get('/api-advertising/' + element)
                .then(r => {
                    if (r.data.state === 2000) {
                        _this.titleShow = true
                        let advertising = r.data.data;
                        let time = advertising.createTime;
                        advertising.month = getMonthToString(time)
                        advertising.date = getDate(time);
                        _this.titleADV = advertising
                    } else {
                        _this.titleShow = false
                    }
                })
        },
        loadAllADV(pageNum) {
            if (pageNum == "" || isNaN(pageNum) || pageNum < 1) {
                pageNum = 1;
            }
            axios.get('/api-advertising/all/'+pageNum)
                .then(r => {
                    let advertising = r.data.data;
                    advertising = this.$options.methods.handleADV(advertising);
                    this.allADV = advertising

                })
        }
        ,
        handleADV(advertising) {
            for (let i = 0; i < advertising.length; i++) {
                let time = advertising[i].createTime;
                advertising[i].month = getMonthToString(time)
                advertising[i].date = getDate(time);
            }
            return advertising;
        }
    },
    created() {
        this.parseURL()
        this.loadAllADV()
    }
})