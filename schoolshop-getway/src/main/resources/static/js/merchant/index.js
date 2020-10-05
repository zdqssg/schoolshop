let navHeader = new Vue({
    el: "#nav-header",
    data: {
        info: []
    },
    methods: {
        loadInfo: function () {
            $.ajax({
                url: '/api-merchant/info',
                success: function (json) {
                    navHeader.info = json.data;
                }
            })
        }
    },
    created: function () {
        this.loadInfo()
    }
});