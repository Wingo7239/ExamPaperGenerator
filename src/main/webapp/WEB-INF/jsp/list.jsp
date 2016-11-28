 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
 <%@ page import="java.util.List"%>
 <%@ page import="com.yw.domain.Category"%>
<!-- 引入jstl -->
<%@ include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
   <head>
      <title>题库</title>
	   <%@include file="common/head.jsp" %> 
   </head>
   <body>
 
<div class="top-nav-wrap">
    <div class="tn-header">
        <div class="tn-nav">
            <div class="tn-logo"><a href=><img src="/tiku/resources/images/sign.png"></a></div>     
        </div>
    
        <div class="tn-person-r">
            <div class="tn-title-login">
                <div>
                    <a class="nav-link" >
                        注册
                    </a>
                    <a class="nav-link" >
                      登陆
                    </a>
                    
                </div>

            </div>
        </div>
    </div>
</div>


<nav class="navbar navbar-default" role="navigation" style="text-align: center;">
		<ul class="nav navbar-nav navbar-right">
			<li class="active"><a href="#">全部</a></li>
			<%
			String id = (String)request.getAttribute("subjectId");
			for(Category c : (List<Category>)request.getAttribute(id)){
				if(c.isIsFolder()){%>
				
				<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<%=c.getName() %>
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
				
					<li><a href="#">全部</a></li>
					<% for(Category ca : (List<Category>)request.getAttribute(c.getId())){%>
						<li><a href="#"><%=ca.getName() %></a></li>
					<%}%>
					</ul>
					</li>
				<%}
				else{%>
					<li><a href="#"><%=c.getName() %></a></li>
				<%}
			}%>
			
			<li><a></a></li>
			
			
		</ul>
	</div>
	</div>
</nav>


   		<div class="container" style="padding-top:15px">

	<div class="row clearfix">
	<div class="col-md-2 column">
	<h4>搜索</h4>
		<div class="input-group">
                    <input type="text" class="form-control">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>
                </div>
		<h4> 题型 </h4>
			<a href ="javascript:;" class="list-group-item active" key="0">全部</a>
			
			<c:forEach var="tp" items="${type}">
			<a href ="javascript:;" class="list-group-item" key="${tp.id}">${tp.name}</a>
			</c:forEach>

		</div>
		<div class="col-md-10 column">
			<div class="row">
			<div class="list-group">
				 <c:forEach var="qes" items="${list}">
				<a class="list-group-item active">${qes.source}</a>
				<div class="list-group-item">
					${qes.memo} 
				</div>
				<div class="list-group-item">
					${qes.answer}
				</div>
				
			</c:forEach>
			</div>
		</div>
		<div class="center">
			<nav style="text-align: center">
			<ul class="pagination">
				<li>
					 <a href="#">Prev</a>
				</li>
				<li>
					 <a href="#">1</a>
				</li>
				<li>
					 <a href="#">2</a>
				</li>
				<li>
					 <a href="#">3</a>
				</li>
				<li>
					 <a href="#">4</a>
				</li>
				<li>
					 <a href="#">5</a>
				</li>
				<li>
					 <a href="#">Next</a>
				</li>
			</ul>
		</nav>
		</div>
	</div>
		
	</div>
</div>
   </body>
   <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</html>