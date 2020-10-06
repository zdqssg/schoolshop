let userRegFormApp = new Vue({
    el: '#userRegFormApp',
    data: {
        registerObject: {
            nickname: null,
            password: null,
            phone: null
        },
        rePassword: null,
        codeObject: {
            code: null,
            btn: '发送验证码',
            hint: '',
            timeOut: false
        },
        regResult: {
            msg: '',
            time: 3
        }
    },
    methods: {
        checkHaveName() {

        },
        checkPwd() {

        },
        checkRePwd() {

        },
        checkPhone() {

        },
        sendCode() {
            if (!this.codeObject.timeOut) {
                let phone = this.registerObject.phone;
                const reg = /^1[34578]\d{9}$/;
                if (!phone || !reg.test(phone)) {
                    this.codeObject.hint = '请输入正确的手机号';
                    return;
                }
                this.codeObject.hint = '';
                axios.get('/api-user/none/regCode/' + phone)
                    .then(r => {
                        if (r.data.state === 2000) {
                            this.codeObject.timeOut = 60
                            this.codeObject.btn = '已发送,' + this.codeObject.timeOut + '秒后可重新发送'
                            let codeInter = setInterval(f => {
                                --this.codeObject.timeOut
                                if (this.codeObject.timeOut === 0) {
                                    clearInterval(codeInter)
                                    this.codeObject.timeOut = false
                                    this.codeObject.btn = '重新发送'
                                }
                            }, 1000);
                        }else {
                            this.codeObject.btn = r.data.message
                        }
                    })
                    .catch(e=>{
                        console.log(e)
                    })
            } else {
                this.codeObject.hint = '请' + this.codeObject.timeOut + '秒后再次发送';
            }
        },
        registerUser() {
            let data = this.registerObject
            axios.post('/api-user/none/reg', data)
                .then(r => {
                    if (r.data.state === 2000) {
                        this.regResult = '注册成功,即将于<span id="time">' + this.regResult.time + '</span>秒后返回登录';
                        let regResultInter = setInterval(f => {
                            --this.regResult.time
                            if (this.regResult.time === 0) {
                                location.href='/person/none/login'
                            }
                        }, 1000);
                    } else {
                        this.regResult = r.data.message
                    }
                })
                .catch(e => {
                })
        }
    },
    created() {

    }
})