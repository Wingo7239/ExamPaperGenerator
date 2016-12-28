/**
 * Created by Jason on 2016/12/10.
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
var preview = function () {
    var numToOne = ['一、', '二、', '三、', '四、', '五、', '六、', '七、', '八、', '九、', '十、'];

    var typeList = avalon.define({
        $id: 'typeList',
        list: [],
        num: 0,
        getExe: function () {
            var num = 1;
            $.each($('.typeList'), function (i, el) {
                var $_nowContent = $(el);

                var idList = $_nowContent.attr('id-list');
                var url = '//localhost:8080//tiku/queslist';
                var data = 'list=' + idList;

                Tools.ajaxRequests(url, 'GET', data, false, function (data) {
                    var $_content = '';
                    $.each(data, function (i, el) {
                        $_content += '<div num="' + (i + 1) + '">' +
                            '<span class="num">' + (num) + '　</span>' +
                            '<div class="body" exeid="' + el.id + '">' +
                            el.memo +
                            '</div>' +
                            '<div class="option">' +
                            '<a class="up">上移</a>' +
                            '<a class="delete">删除</a>' +
                            '<a class="down">下移</a>' +
                            '</div>' +
                            '</div>';
                        num += 1;
                    });
                    $_nowContent.append($_content);
                });
            });
            up_down_delete();
        }
    });

    (function () {
        init();
    })();

    function init() {
        getExe();
        done();
    }

    /**
     * 获取习题
     */
    function getExe() {
        var url = '//localhost:8080/tiku/cart';
        var data = '';
        Tools.ajaxRequests(url, 'GET', data, true, function (data) {
            $.each(data, function (i, el) {
                el.num = numToOne[i];
            });

            typeList.list = data;
        });

    }

    /**
     * 上移，下移，删除
     */
    function up_down_delete() {
        $(document.body).on('click', '.option .up', function () {
            var $_now_exe = $(this).parent('.option').parent('div');
            var $_now_body = $_now_exe.children('.body');
            var $_type = $_now_exe.parent('.typeList');

            var now_num = parseInt($_now_exe.attr('num'));
            console.log(now_num);
            if (now_num != 1) {
                var $_up_exe = $_now_exe.parent('.typeList').find('[num="' + (now_num - 1) + '"]');
                var $_up_body = $_up_exe.children('.body');

                $_now_exe.append($_up_body);
                $_up_exe.append($_now_body)
            }
        });
        $(document.body).on('click', '.option .down', function () {
            var $_now_exe = $(this).parent('.option').parent('div');
            var $_now_body = $_now_exe.children('.body');
            var $_type = $_now_exe.parent('.typeList');

            var now_num = parseInt($_now_exe.attr('num'));
            console.log(now_num);
            if (now_num != $_type.children('div').size()) {
                var $_down_exe = $_now_exe.parent('.typeList').find('[num="' + (now_num + 1) + '"]');
                var $_down_body = $_down_exe.children('.body');

                $_now_exe.append($_down_body);
                $_down_exe.append($_now_body)
            }
        });
        $(document.body).on('click', '.option .delete', function () {
            var $_now_exe = $(this).parent('.option').parent('div');
            var $_type = $_now_exe.parent('.typeList');
            
            var now_num = parseInt($_now_exe.children('span').text());
            $_now_exe.remove();
            if ($_type.children('div').size() == 0) {
                $_type.parent('div').remove();
            }
            //
            $('[num]').each(function(){
            	var tnum = +$(this).children('span').text();
            	if(tnum > now_num){
            		$(this).children('span').text(tnum-1);
            	}
            });
            
            //
        });
    }

    function done() {
        $(document.body).on('click', '#ok', function () {
            var url = 'http://localhost:8080/tiku/paper';
            var data = JSON.stringify(packageDATA());
            console.log(data);
            Tools.ajaxRequests(url, 'POST', data, true, function (data) {
                if (data == true || data == 'true') {
                    alert('成功');
                }
                location.href = "/tiku/paper?pid="+data;
            });
        });
    }

    function packageDATA() {
        var data = {
            maintitle: "",
            subtitle: "",
            testinfo: "",
            grouplist: []
        };

        data.maintitle = $('#maintitle').val();
        data.subtitle = $('#subtitle').val();
        data.testinfo = '命题人：' + $('#testinfo1').val()
            + ' 审核人：' + $('#testinfo2').val()
            + ' 分值：' + $('#testinfo3').val()
            + '分 考试时间：' + $('#testinfo4').val()
            + '分钟';

        var grouplist = new Array();

        $.each($('.content>.type'), function (i, el) {
            var oneType = {
                title: "",
                idlist: []
            };
            var type = $(el).children('.title').children('.name').text();
            if (type === "填空题" || type === "选择题" || type === "实验题" || type === "判断题") {
                oneType.title = $(el).children('.title').children('.name').text()
                    + '（'
                    + '本题共'
                    + $(el).children('.title').children('.remark').children('.num').text()
                    + '道小题，每小题'
                    + $(el).children('.title').children('.remark').children('.score').val()
                    + '分）';
            }
            else {
                oneType.title = $(el).children('.title').children('.name').text()
                    + '（'
                    + '本题共'
                    + $(el).children('.title').children('.remark').children('.num').text()
                    + '道小题';
                $.each($(el).children('.title').children('.remark').children('span').children('.oneExeScore'), function (i, el) {
                    var score = $(el).val();
                    oneType.title += '，第' + (i + 1) + '题'
                        + score
                        + '分）'
                })
            }
            $.each($(el).children('.typeList').find('[exeid]'), function (i, el) {
                oneType.idlist.push($(el).attr('exeid'));
            });
            grouplist.push(oneType);
        });
        data.grouplist = grouplist;
        console.log(data);
        return data;
    }

}();