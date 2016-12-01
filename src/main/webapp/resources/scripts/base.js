/**
 * Created by Jason on 2016/11/30.
 .................._oo0oo_
 .................088888880
 .................88" . "88
 .................(| -_- |)
 ..................0\ = /0
 ...............___/'---'\___
 ..............' \\|     |// '.
 ............/ \\|||  :  |||// \
 .........../_ ||||| -:- |||||- \
 ..........|   | \\\  -  /// |   |
 ..........| \_|  ''\---/''  |_/ |
 ..........\  .-\__  '-'  __/-.  /
 ........___'. .'  /--.--\  '. .'___
 ......"" '<  '.___\_<|>_/___.' >'  "".
 ....| | : '-  \'.;'\ _ /';.'/ - ' : | |
 ....\  \ '_.   \_ __\ /__ _/   .-' /  /
 ====='-.____'.___ \_____/___.-'____.-'=====
 ..................'=---='

 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 ..........佛祖保佑        永无BUG
 */
var Tools = function () {
    /**
     * 获取cookie
     */
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return decodeURIComponent(arr[2]);
        else
            return null;
    }

    /**
     * 设置cookie
     */
    function setCookie(name, value) {
        //获取当前时间
        var exdate = new Date();
        var expireDays = 365 * 3;
        exdate.setDate(exdate.getDate() + expireDays);
        var href = window.location.href.substr(8);
        var domain = href.substr(href.indexOf('.'), href.indexOf('/') - href.indexOf('.'));
        document.cookie = name + "=" + value + ";domain=" + domain + ";path=/;expires=" + exdate.toGMTString();
    }

    /**
     * 删除cookie
     * @param name
     */
    function deleteCookie(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        var href = window.location.href.substr(8);
        if (cval != null)
            var domain = href.substr(href.indexOf('.'), href.indexOf('/') - href.indexOf('.'));
        document.cookie = name + "=" + cval + ";domain=" + domain + ";path=/;expires=" + exp.toGMTString();
    }

    /**
     * ajax请求方法
     * @param myUrl API地址
     * @param myMethod POST OR GET
     * @param myData 参数
     * @param isasync 是否异步
     * @param myfunction 成功方法
     */
    function ajaxRequests(myUrl, myMethod, myData, isasync, myfunction) {
        $.ajax({
            url: myUrl,
            method: myMethod,
            async: isasync,
            data: myData,
            success: function (data, s, xhr) {
                if (data != null) {
                    if (typeof myfunction == "function")
                        myfunction(data);
                }
                else {
                    if (typeof myfunction == "function")
                        myfunction('null');
                }

            },
            error: function () {
            }
        });
    }

    /**
     * loading
     */
    function putLoading() {
        var loading = "<div class='loading'><div></div></div>";
        $("body").append(loading);
    }

    function deleteLoading() {
        if ($(".loading")) {
            $(".loading").remove();
        }
    }
    return {
        getCookie: getCookie,
        setCookie: setCookie,
        deleteCookie: deleteCookie,
        ajaxRequests: ajaxRequests,
        putLoading:putLoading,
        deleteLoading:deleteLoading
    }
}();