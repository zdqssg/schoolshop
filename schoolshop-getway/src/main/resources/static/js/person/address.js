let receiveAddressApp = new Vue({
    el: '#receiveAddressApp',
    data: {
        defaultAddr: [],
        userReceiveAddress: [],
        addReceiveAddress: {
            username: null,
            phone: null,
            state: null,
            provinceCode: null,
            cityCode: null,
            areaCode: null,
            streetCode: null,
            detail: null
        },
        province: [{provinceCode: 0}, {provinceName: "请选择"}],
        city: [{cityCode: 0}, {provinceName: "请先选择省份选择"}],
        area: [{areaCode: 0}, {provinceName: "请选择城市"}],
        street: [{streetCode: 0}, {provinceName: "请选择区域"}],

    },
    methods: {
        loadUserReceiveAddress: function () {
            let _this = this;
            axios.get('/api-user/receiveAddress')
                .then(function (response) {
                    let list = response.data.data;
                    for (let i = 0; i < list.length; i++) {
                        if (list[i].state == 2) {
                            _this.defaultAddr.splice(0, 1, list[i]);
                            list.splice(i, 1)
                        }
                    }
                    _this.userReceiveAddress = list;
                })
                .catch(function (error) {

                })
        },
        loadProvince: function () {
            let _this = this;
            axios.get("/home/provinceList")
                .then(function (response) {
                    _this.province = response.data.data;
                })

        },
        loadCity: function () {
            let _this = this;
            axios.get("/home/cityList")
                .then(function (response) {
                    let cityList = response.data.data;
                    let city = []
                    for (let i = 0; i < cityList.length; i++) {
                        if (cityList[i].provinceCode == _this.addReceiveAddress.provinceCode) {
                            city.push(cityList[i])
                        }
                    }
                    _this.city = city

                })
        },
        loadArea: function () {
            let _this = this;
            axios.get("/home/areaList")
                .then(function (response) {
                    let areaList = response.data.data;
                    let area = []
                    for (let i = 0; i < areaList.length; i++) {
                        if (areaList[i].cityCode == _this.addReceiveAddress.cityCode) {
                            area.push(areaList[i])
                        }
                    }
                    _this.area = area

                })
        },
        loadStreet: function () {
            let _this = this;
            axios.get("/home/streetList")
                .then(function (response) {
                    let streetList = response.data.data;
                    let street = []
                    for (let i = 0; i < streetList.length; i++) {
                        if (streetList[i].areaCode == _this.addReceiveAddress.areaCode) {
                            street.push(streetList[i])
                        }
                    }
                    _this.street = street

                })
        },
        postReceiveAddress: function () {
            let _this = this;
            // console.log(_this.addReceiveAddress)
            axios.post("/api-user/receiveAddress", _this.addReceiveAddress)
                .then(function (response) {
                    if (response.data.state === 2000) {
                        // alert(121)
                        history.go(0)
                    }
                })
                .catch(function (error) {

                })

        },
        deleteAddress: function (id, index) {
            let _this = this;
            console.log('删除' + id)
            axios.delete('/api-user/receiveAddress/' + id)
                .then(function (response) {
                    console.log('返回结果' + response)
                    if (response.data.state == 2000) {
                        if (index == undefined) {
                            _this.defaultAddr = [];
                        } else {
                            let addressList = _this.userReceiveAddress;
                            addressList.splice(index, 1)
                        }
                    }
                })
                .catch(function (error) {

                })

        },
        setDefault: function (id, index) {
            let _this = this;
            let data = {
                id: id,
                state: 2
            }
            axios.put("/api-user/receiveAddress/update", data)
                .then(function (response) {
                    if (response.data.state == 2000) {
                        // history.go(0)
                        let defaultAddrElement = _this.defaultAddr[0];
                        let userReceiveAddress = _this.userReceiveAddress[index];
                        defaultAddrElement.state = 1
                        userReceiveAddress.state = 2

                        _this.defaultAddr.splice(0, 1, userReceiveAddress)
                        _this.userReceiveAddress.splice(index, 1, defaultAddrElement)
                    }
                })
                .catch(function (error) {

                })
        },
        changeAddress: function (id, index) {

            let _this = this;
            if (id == undefined || index == undefined) {
                _this.addReceiveAddress = _this.defaultAddr[0]
            } else {
                _this.addReceiveAddress = _this.userReceiveAddress[index]
            }


        }
    },
    created: function () {
        this.loadUserReceiveAddress()
        this.loadProvince()
    }
})
 
 
 
 
 
 
 
 
 
 
 
 

