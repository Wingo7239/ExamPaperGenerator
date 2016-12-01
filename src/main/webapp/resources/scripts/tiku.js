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
var ExeBank = function () {
    var allPage = '';
    var pageNow = '';
    var pageSize = '';
    var knowledge = '';
    var type = '';
    var search = '';

    var ExeList = avalon.define({
        $id: 'exeList',
        list: []
    });

    var PageList = avalon.define({
        $id: 'pageList',
        prev: '1',
        next: '',
        list: [],
        changeActive: function () {
            $('.list > [pageNum]').removeClass('active');
            var select = '.list > [pageNum=' + pageNow + ']';
            $(select).addClass('active');
        }
    });

    (function () {
        pageNow = 1;
        pageSize = 15;
        $('[pageNum="1"]').addClass('active');
        getExe();

        clickKnowledge();
        clickType();
        searchButton();
        clickPage();
    })();

    /**
     * 搜索方法
     */
    function getExe() {
        Tools.putLoading();
        var url = '//localhost:8080/tiku/question';
        var data = 'pageNow=' + pageNow
            + '&pageSize=' + pageSize
            + '&knowledge=' + knowledge
            + '&type=' + type
            + '&search=' + search;
        Tools.ajaxRequests(url, 'GET', data, true, function (data) {
            ExeList.list = data.list;
            allPage = data.pageCount;
            putPages();
            Tools.deleteLoading();
        });
    }

    /**
     * 点击知识点
     */
    function clickKnowledge() {
        $(document.body).on('click', '[knowledge]', function () {
            $('[knowledge]').removeClass('active');
            $('.dropdown >a').removeClass('active');
            $(this).parent('li').parent('ul').parent('li').children('a').addClass('active');
            $(this).addClass('active');
            knowledge = $(this).attr('knowledge');
            getExe();
        });
    }

    /**
     * 点击题型
     */
    function clickType() {
        $(document.body).on('click', '[key]', function () {
            $('[key]').removeClass('active');
            $(this).addClass('active');
            type = $(this).attr('key');
            getExe();
        });
    }

    function searchButton() {
        $(document.body).on('click', '#searchButton', function () {
            search = $('#searchInput').val();
            getExe();
        });
    }

    /**
     * 分页方法
     */
    function putPages() {
        var list = new Array();

        if (parseInt(allPage) == 0) {
            $(".pagination").hide();
        }
        else if (parseInt(allPage) < 8) {
            $(".pagination").show();
            for (var i = 1; i <= pageTotal; i++) {
                list.push(i.toString());
            }
        }
        else {
            if (parseInt(pageNow) <= 3 || parseInt(pageNow) > parseInt(allPage) - 3) {
                $(".pagination").show();
                list.push("1");
                list.push("2");
                list.push("3");
                list.push("...");
                list.push((parseInt(allPage) - 2).toString());
                list.push((parseInt(allPage) - 1).toString());
                list.push(parseInt(allPage).toString());
            }
            else {
                list.push("...");
                list.push((parseInt(pageNow) - 2).toString());
                list.push((parseInt(pageNow) - 1).toString());
                list.push((parseInt(pageNow) ).toString());
                list.push((parseInt(pageNow) + 1).toString());
                list.push((parseInt(pageNow) + 2).toString());
                list.push("...");
            }
        }

        PageList.list = list;

        if (pageNow != '1') {
            PageList.prev = (parseInt(pageNow) - 1).toString();
        }
        else {
            PageList.prev = '1';
        }
        if (pageNow != allPage) {
            PageList.next = (parseInt(pageNow) + 1).toString();
        }
        else {
            PageList.next = allPage;
        }
    }

    /**
     * 绑定分页
     */
    function clickPage() {
        $(document.body).on('click', '[pageNum]', function () {
            pageNow = $(this).attr('pageNum');
            getExe();
        });

    }
}();