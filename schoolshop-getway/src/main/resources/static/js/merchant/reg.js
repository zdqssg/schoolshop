function gotoMerchantLogin() {
    location.href = "/merchant/none/login";
}

let merchantRegForm = new Vue({
    el: "#merchant-reg-form",
    data: {
        selectedCategory1: null,
        category1: [],
        category2: [{id: 1, name: "请先选择店铺性质"}]
    },
    methods: {
        register: function () {
            $.ajax({
                type: 'post',
                url: '/api-merchant/none/reg',
                data: $("#merchant-reg-form").serialize(),
                success: function (msg) {
                    let $inform = $('#inform');
                    if (msg.state === 2000) {
                        $inform.fadeIn(200)
                        $inform.html('<h4 class="spop-title">注册成功</h4>即将于<span id="time">3</span>秒后返回登录')
                        var second = 2;
                        var showPop = setInterval(function () {
                            if (second == 0) {
                                clearInterval(showPop);
                                location.href = '/merchant/none/login'
                            }
                            $('#time').html(second)
                            second--;
                        }, 1000);
                    } else {
                        $inform.fadeIn(200)
                        $inform.html('<h4 class="spop-title">' + msg.message + '</h4>')
                        setTimeout(function () {
                            $inform.fadeOut(100)
                        }, 3000)
                    }
                }
            })
        },
        loadCategory1: function () {
            $.ajax({
                url: '/home/category1',
                success: function (json) {
                    merchantRegForm.category1 = json.data
                }
            })
        },
        getCategory2: function () {
            $.ajax({
                url: '/home/category2/' + this.selectedCategory1,
                success: function (json) {
                    merchantRegForm.category2 = json.data
                }
            })
        }
    },
    created: function () {
        this.loadCategory1()
    }
});