let userInfo = new Vue({
    el: '#user-info',
    data: {
        info: {}
    },
    methods: {
        loadInfo: function () {
            $.ajax({
                url: '/api-user/info/info',
                success: function (json) {
                    userInfo.info = json.data
                }
            })
        },
        changeHeaderPhoto: function () {
            $.ajax({
                type: 'POST',
                url: '/api-user/upload/changeHeaderPhoto',
                data: new FormData($("#user-header-photo")[0]),
                contentType: false,
                processData: false,
                success: function (json) {
                    if (json.state == 2000) {
                        userInfo.info.headPhoto=json.data;
                    } else {
                        console.log(json.data);
                    }
                }
            });
        },
        updateGender: function () {
            $.ajax({
                type: 'POST',
                url: '/api-user/info/changeGender',
                data:{gender:this.info.gender},
                success: function (json) {
                    if (json.state == 2000) {

                    }else {

                    }
                }
            })
        },
        updateBirth: function () {
            $.ajax({
                type: 'POST',
                url: '/api-user/info/updateBirth',
                data:{birth:this.info.birth},
                success: function (json) {
                    if (json.state == 2000) {

                    }else {

                    }
                }
            })
        }
    },
    created: function () {
        this.loadInfo()
    }
});