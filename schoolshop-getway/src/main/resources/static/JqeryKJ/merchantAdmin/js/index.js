$(function () {
    //菜单点击
    J_iframe
    let hrefArr = $("#historyHrefArr");
    let hrefArrIndex = $("#historyHrefArrIndex");


    $(".J_menuItem").on('click', function () {
        let url = $(this).attr('href');
        $("#J_iframe").attr('src', url);
        $("#fa-refresh").attr('href', url)

        let split = hrefArr.html().split('-');
        let index = parseInt(hrefArrIndex.html());
        split.splice(++index, 0, url);
        hrefArr.html(split.join('-'))
        hrefArrIndex.html(index);
        return false;
    });
    $("#fa-refresh").on('click', function () {
        let url = $(this).attr('href');
        $("#J_iframe").attr('src', url);
        return false;
    })
    $("#fa-chevron-circle-left").on('click', function () {
        let split = hrefArr.html().split('-');
        let index = parseInt(hrefArrIndex.html());

        index=index>0?--index:0;
        $('#historyHrefArrIndex').html(index)
        let url = split[index]

        $("#fa-refresh").attr('href', url)
        $("#J_iframe").attr('src', url);
        return false;
    })


});
