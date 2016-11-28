<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="common/head.jsp"%>
</head>
<body>

	<div class="top-nav-wrap">
		<div class="tn-header">
			<div class="tn-nav">
				<div class="tn-logo">
					<a href=><img src="/tiku/resources/images/sign.png"></a>
				</div>
			</div>

			<div class="tn-person-r">
				<div class="tn-title-login">
					<div>
						<a class="nav-link"> 注册 </a> <a class="nav-link"> 登陆 </a>

					</div>

				</div>
			</div>
		</div>
	</div>


	<div class="container" style="padding-top: 15px">
		<div class="row clearfix">
			<div class="col-md-1 column"></div>
			<div class="col-md-10 column">
				<div id="pui_head">
					<div id="pui_title">
						<div id="pui_maintitle" title="试卷主标题" style="display: block;">xxx学校2016-2017学年度11月同步练习</div>
						<div id="pui_subtitle" title="试卷副标题" style="display: block;">物理试卷</div>
					</div>
					<div id="pui_testinfo" title="试卷信息栏" style="display: block;">考试范围：xxx；考试时间：100分钟；命题人：xxx</div>
					<div id="pui_studentinput" title="考生信息填写栏" style="display: none;">学校:___________姓名：___________班级：___________考号：___________</div>
					<div id="pui_notice" title="考生注意事项栏" style="display: block;">
						<div id="pui_noticetip">注意事项：</div>
						<div id="pui_noticetext">
							1．答题前填写好自己的姓名、班级、考号等信息<br>2．请将答案正确填写在答题卡上
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-1 column"></div>
		</div>
	</div>

</body>
</html>