
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yw.domain.Knowledge"%>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>题库</title>
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
	<nav class="navbar navbar-default" role="navigation"
		style="text-align: center;">
		<ul class="nav navbar-nav navbar-right" style="padding-right: 60px">
			<li><a href="#" class="link active" knowledge="${subjectId}">全部</a></li>

			<%
			String id = (String)request.getAttribute("subjectId");
			for(Knowledge c : (List<Knowledge>)request.getAttribute(id)){%>
			<li class="dropdown"><a href="#" class="dropdown-toggle link"
				data-toggle="dropdown"> <%=c.getName() %> <b class="caret"></b>
			</a> <%if(c.isIsFolder()){%>
				<ul class="dropdown-menu">
					<li><a class="link" href="#" knowledge="<%=c.getId() %>">全部</a></li>
					<%
				for(Knowledge k : (List<Knowledge>)request.getAttribute(c.getId())){
				%>
					<li><a class="link" href="#" knowledge="<%=k.getId()%>"><%=k.getName() %></a></li>
				<%} %>
				</ul></li>
			<%}} %>

		</ul>
	</nav>

	<nav style="text-align: center">
		<ul class="pagination" ms-controller="pageList">
			<li class="NextAndPrev"><a href="#" ms-attr-pageNum="prev">Prev</a>
			</li>
			<li class="list" ms-repeat="list" data-repeat-rendered="changeActive">
				<a href="#" ms-attr-pageNum="el" ms-text="el"></a>
			</li>
			<li class="NextAndPrev"><a href="#" ms-attr-pageNum="next">Next</a>
			</li>
		</ul>
	</nav>

	<div class="container" style="padding-top: 15px">

		<div class="row clearfix">
			<div class="col-md-2 column">
				<h4>搜索</h4>
				<div class="input-group">
					<input id="searchInput" type="text" class="form-control"> <span
						class="input-group-btn">
						<button id="searchButton" class="btn btn-default" type="button">Go!</button>
					</span>
				</div>
				<h4>题型</h4>
				<a href="javascript:;" class="list-group-item active" key="0">全部</a>


				<a href="javascript:;" class="list-group-item" key="1">填空题</a> <a
					href="javascript:;" class="list-group-item" key="2">选择题</a> <a
					href="javascript:;" class="list-group-item" key="3">实验题</a> <a
					href="javascript:;" class="list-group-item" key="4">计算题</a> <a
					href="javascript:;" class="list-group-item" key="5">简答题</a> <a
					href="javascript:;" class="list-group-item" key="6">作图题</a> <a
					href="javascript:;" class="list-group-item" key="7">辨析题</a> <a
					href="javascript:;" class="list-group-item" key="8">估算题</a> <a
					href="javascript:;" class="list-group-item" key="9">判断题</a> <a
					href="javascript:;" class="list-group-item" key="10">证明题</a>


			</div>
			<div class="col-md-10 column">
				<div class="row">
					<div class="list-group" ms-controller="exeList">
						<div ms-repeat="list">
							<a class="list-group-item active">

								<h4 class="list-group-item-heading" ms-text="el.source"></h4>
								<p class="list-group-item-text"
									ms-text="'知识点：'+el.knowledge.name"></p>
							</a>
							<div class="list-group-item" ms-html="el.memo"></div>
							<div class="list-group-item" ms-html="el.answer"></div>
						</div>
					</div>
				</div>
				<div class="center">
					<nav style="text-align: center">
						<ul class="pagination" ms-controller="pageList">
							<li class="NextAndPrev"><a href="#" ms-attr-pageNum="prev">Prev</a>
							</li>
							<li class="list" ms-repeat="list"
								data-repeat-rendered="changeActive"><a href="#"
								ms-attr-pageNum="el" ms-text="el"></a></li>
							<li class="NextAndPrev"><a href="#" ms-attr-pageNum="next">Next</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>

		</div>
	</div>
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/tiku/resources/scripts/tiku.js"></script>
</body>
</html>