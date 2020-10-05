function getTime(createTime) {
    let timestamp = new Date(createTime).getTime();
    let t = (new Date().getTime() - timestamp) / 1000;
    let parseTime;
    if (t < 60) {
        parseTime = '刚刚'
    } else if (t < 60 * 60) {
        parseTime = parseInt(t / 60) + '分钟前'
    } else if (t < 60 * 60 * 24) {
        parseTime = parseInt(t / 60 / 60) + '小时前'
    } else if (t < 60 * 60 * 24 * 7) {
        parseTime = parseInt(t / 60 / 60 / 24) + '天前'
    } else {
        parseTime = formatDateTime(timestamp)
    }
    return parseTime;
}

function formatDateTime(timestamp) {
    let date = new Date(timestamp);
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = date.getDate() + ' ';
    let h = date.getHours();
    h = (h <= 12 ? '上午' : '下午') + h + '点'
    // let m = date.getMinutes() + ':';
    // let s = date.getSeconds();
    return Y + M + D + h;
};

/**
 * 得到
 * @param n
 * @returns {string}
 */
function getBeforeDate(n) {
    let date = new Date();
    let year, month, day;
    date.setDate(date.getDate() + n);
    year = date.getFullYear();
    month = date.getMonth() + 1;
    day = date.getDate();
    s = year + '-' + (month < 10 ? ('0' + month) : month) + '-' + (day < 10 ? ('0' + day) : day);
    $("#day").html(day)
    $("#year-month").html(year + '.' + month)
    return s;
}

/**
 * 得到星期
 * @param day
 * @returns {string}
 */
function getWeekDay(day) {
    let weekDay = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
    let myDate = new Date(Date.parse(day));
    let week = weekDay[myDate.getDay()];
    return week;
}

/**
 * 得到时间
 * @returns {string}
 */
function time() {
    let date = new Date();
    let h = date.getHours();          //获取小时
    let m = date.getMinutes();        //获取分钟
    let s = date.getSeconds();        //获取秒
    return (h < 10 ? '0' + h : h) + ':' + (m < 10 ? '0' + m : m) + ':' + (s < 10 ? '0' + s : s);
}

/**
 *保留一位小数
 * @param num
 * @returns {string}
 */
function numFloat(num) {

    num = num.toFixed(2);
    num = num.substr(0, num.length - 1);
    var lastStr = num.charAt(num.length - 1);
    if (lastStr % 2 == 0) {
        return num;
    } else {
        return (num - 0.1).toFixed(1);
    }

}

function getMonthToString(createTime) {
    let month = ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二'];
    let date = new Date(createTime);
    return month[date.getMonth()]+'月';
}

function getDate(createTime) {
    let date = new Date(createTime);
    return date.getDate();
}

/*
date.getFullYear();  // 获取完整的年份(4位,1970)
date.getMonth();  // 获取月份(0-11,0代表1月,用的时候记得加上1)
date.getDate();  // 获取日(1-31)
date.getTime();  // 获取时间(从1970.1.1开始的毫秒数)
date.getHours();  // 获取小时数(0-23)
date.getMinutes();  // 获取分钟数(0-59)
date.getSeconds();  // 获取秒数(0-59)



// 比如需要这样的格式 yyyy-MM-dd hh:mm:ss
var date = new Date(1398250549490);
Y = date.getFullYear() + '-';
M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
D = date.getDate() + ' ';
h = date.getHours() + ':';
m = date.getMinutes() + ':';
s = date.getSeconds();
console.log(Y+M+D+h+m+s);
// 输出结果：2014-04-23 18:55:49
 */