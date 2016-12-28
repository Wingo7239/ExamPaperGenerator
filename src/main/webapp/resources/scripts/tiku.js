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

    var basket = [
        {
            "count": 0,
            "name": "填空题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "选择题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "实验题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "计算题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "简答题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "作图题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "辨析题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "估算题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "判断题",
            "queslist": []
        },
        {
            "count": 0,
            "name": "证明题",
            "queslist": []
        },
    ];
    var basketCount = 0;

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

    var Basket = avalon.define({
        $id: 'basket',
        list: [],
        num: 0
    });

    (function () {
        pageNow = 1;
        pageSize = 15;

        var url = '//localhost:8080/tiku/cart';
        var data = '';
        Tools.ajaxRequests(url, 'GET', data, true, function (data) {
        	
        	$.each(data,function(n,dv){
        		$.each(basket,function(index,bv){
        			if(dv.name == bv.name){
        				console.log(dv.name);
        				console.log(bv.name);
        				basket[index] = dv;
        				basketCount += dv.count;
        				return false;
        			}
        		});
        		
        	});
        	
            Basket.list = basket;
            Basket.num = basketCount;
        });

        $('[pageNum="1"]').addClass('active');
        getExe();

        clickKnowledge();
        clickType();
        searchButton();
        clickPage();
        addBasket();
        updataBasket();
        init();
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
        	$.each(data.list,function(i,el){
        		if($.inArray(""+el.id,basket[el.type-1].queslist) != -1){
        			data.list[i].isadd = true;
        		}
        		else{
        			data.list[i].isadd = false;
        		}
        	});
        	
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

            $('.list > [pageNum]').removeClass('active');
            $('[pageNum="1"]').addClass('active');

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

            $('.list > [pageNum]').removeClass('active');
            $('[pageNum="1"]').addClass('active');

            getExe();
        });
    }

    function searchButton() {
        $(document.body).on('click', '#searchButton', function () {
            search = $('#searchInput').val();

            $('.list > [pageNum]').removeClass('active');
            $('[pageNum="1"]').addClass('active');

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
            for (var i = 1; i <= allPage; i++) {
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
            if (pageNow != '...') {
                getExe();
            }
        });

    }

    /**
     * 绑定添加到购物篮
     */
    function addBasket() {
        $(document.body).on('click', '.addBasket', function () {
            var quesID = $(this).attr('questionid');
            var quesType = $(this).attr('type');

            if ($(this).attr('is-add') == 'false') {
                basket[quesType - 1].queslist[basket[quesType - 1].queslist.length] = quesID;
                Basket.list[quesType - 1].queslist[basket[quesType - 1].queslist.length] = quesID;

                basket[quesType - 1].count += 1;
                Basket.list[quesType - 1].count += 1;

                basketCount += 1;

                $(this).attr('is-add', 'true').text('已在试题篮');
            }
            else {
                basket[quesType - 1].count -= 1;
                Basket.list[quesType - 1].count -= 1;
                $.each(basket[quesType - 1].queslist, function (i, el) {
                    if (el == quesID) {
                        basket[i].queslist.splice(i, 1);
                        Basket.list[i].queslist.splice(i, 1);
                    }
                });
                basketCount -= 1;
                $(this).attr('is-add', 'false').text('添加到试题篮');
            }
            // Basket.list = basket;
            Basket.num = basketCount;

            console.log(basket[0].queslist);
        });
    }

    /**
     * 确定出题
     */
    function updataBasket() {
        $(document.body).on('click', '#updata', function () {
            var url = '//localhost:8080/tiku/cart';
            var data = JSON.stringify(basket);
            console.log(data);
            Tools.ajaxRequests(url, 'POST', data, true, function (data) {
                if (data == true || data == 'true') {
                    alert('成功');
                }
                location.href = "/tiku/preview";
            });
        });
    }
    
    function init(){
    	$(document.body).on('mouseenter','.nav-link',function(){
			$('.basket').show();
    	});
    }
}();