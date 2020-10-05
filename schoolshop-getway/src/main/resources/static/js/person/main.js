getUserBgImg();

function getUserBgImg() {
    $.ajax({
        url: '/api-user/info/bgImg',
        success: function (json) {

            $("body").css({
                background: 'url(' + json.data + ')'
            });
        }
    })
}

let asideApp = new Vue({
    el: '#asideApp',
    data: {
        checked: null,
        aside:
            [
                {html: '<a href="/person/index" >个人中心</a>'},
                {
                    html: '<a href="#.">个人资料</a><ul>\n' +
                        '                <li><a href="/person/info">个人信息</a></li>\n' +
                        '                <li><a href="/person/safety">安全设置</a></li>\n' +
                        '                <li><a href="/person/address">收货地址</a></li>\n' +
                        '            </ul>'
                },
                {
                    html: '<a href="#.">我的交易</a>\n' +
                        '            <ul>\n' +
                        '                <li><a href="/person/cart">购物车</a></li>\n' +
                        '                <li><a href="/person/order">订单管理</a></li>\n' +
                        '                <li><a href="/person/change">退款售后</a></li>\n' +
                        '            </ul>'
                },
                {
                    html: '<a href="#.">我的小窝</a>\n' +
                        '            <ul>\n' +
                        '                <li><a href="/person/collection">收藏</a></li>\n' +
                        '                <li><a href="/person/comment">评价</a></li>\n' +
                        '                <li><a href="/person/news">消息</a></li>\n' +
                        '            </ul>'
                }
            ]
    },
    methods: {
        loadAsideChecked: function () {
            let checked = sessionStorage.getItem('asideChecked');
            if(checked==null){
                this.checked=0
                return
            }
            this.checked = parseInt(checked);
        },
        singleCheck: function (index) {
            sessionStorage.setItem('asideChecked', index);
        }
    },
    created: function () {
        this.loadAsideChecked()
    }

})