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
        loadUserReceiveAddress () {
            let _this = this;
            axios.get('/api-user/receiveAddress')
                .then(r=> {
                    let list = r.data.data;
                    for (let i = 0; i < list.length; i++) {
                        if (list[i].state == 2) {
                            _this.defaultAddr.splice(0, 1, list[i]);
                            list.splice(i, 1)
                        }
                    }
                    _this.userReceiveAddress = list;
                })
                .catch(e=> {

                })
        },
        loadProvince () {
            let _this = this;
            axios.get("/home/provinceList")
                .then(r=> {
                    _this.province = r.data.data;
                })

        },
        loadCity() {
            let _this = this;
            axios.get("/home/cityList")
                .then(r=> {
                    let cityList = r.data.data;
                    let city = []
                    for (let i = 0; i < cityList.length; i++) {
                        if (cityList[i].provinceCode == _this.addReceiveAddress.provinceCode) {
                            city.push(cityList[i])
                        }
                    }
                    _this.city = city

                })
        },
        loadArea () {
            let _this = this;
            axios.get("/home/areaList")
                .then(r=> {
                    let areaList = r.data.data;
                    let area = []
                    for (let i = 0; i < areaList.length; i++) {
                        if (areaList[i].cityCode == _this.addReceiveAddress.cityCode) {
                            area.push(areaList[i])
                        }
                    }
                    _this.area = area

                })
        },
        loadStreet () {
            let _this = this;
            axios.get("/home/streetList")
                .then(r=> {
                    let streetList = r.data.data;
                    let street = []
                    for (let i = 0; i < streetList.length; i++) {
                        if (streetList[i].areaCode == _this.addReceiveAddress.areaCode) {
                            street.push(streetList[i])
                        }
                    }
                    _this.street = street

                })
        },
        postReceiveAddress () {
            let _this = this;
            // console.log(_this.addReceiveAddress)
            axios.post("/api-user/receiveAddress", _this.addReceiveAddress)
                .then(r=> {
                    if (r.data.state === 2000) {
                        // alert(121)
                        history.go(0)
                    }
                })
                .catch(e=> {

                })

        },
        deleteAddress (id, index) {
            let _this = this;
            console.log('删除' + id)
            axios.delete('/api-user/receiveAddress/' + id)
                .then(r=> {
                    console.log('返回结果' + r)
                    if (r.data.state == 2000) {
                        if (index == undefined) {
                            _this.defaultAddr = [];
                        } else {
                            let addressList = _this.userReceiveAddress;
                            addressList.splice(index, 1)
                        }
                    }
                })
                .catch(e=> {

                })

        },
        setDefault (id, index) {
            let _this = this;
            let data = {
                id: id,
                state: 2
            }
            axios.put("/api-user/receiveAddress/update", data)
                .then(r=>{
                    if (r.data.state == 2000) {
                        // history.go(0)
                        let defaultAddrElement = _this.defaultAddr[0];
                        let userReceiveAddress = _this.userReceiveAddress[index];
                        defaultAddrElement.state = 1
                        userReceiveAddress.state = 2

                        _this.defaultAddr.splice(0, 1, userReceiveAddress)
                        _this.userReceiveAddress.splice(index, 1, defaultAddrElement)
                    }
                })
                .catch(e=> {

                })
        },
        changeAddress(id, index) {

            let _this = this;
            if (id == undefined || index == undefined) {
                _this.addReceiveAddress = _this.defaultAddr[0]
            } else {
                _this.addReceiveAddress = _this.userReceiveAddress[index]
            }


        }
    },
    created () {
        this.loadUserReceiveAddress()
        this.loadProvince()
    }
})
 
 
 
 
 
 
 
 
 
 
 
 

