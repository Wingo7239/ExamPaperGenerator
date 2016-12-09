<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/tag.jsp"%>
<%@ page import="com.yw.dto.Cart"%>
<%@ page import="com.yw.domain.Question"%>
 <%@ page import="java.util.List"%>
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


	<div class="container" style="padding-top:15px">
                <div class="row clearfix">
                    <div class="col-md-1 column"></div>
                    <div class="col-md-10 column">
                        <div id="pui_head">
                            <div id="pui_title">
                                <div id="pui_maintitle" title="试卷主标题">曲靖市第一中学2016～2017学年度第一学期期中考试</div>
                                <div id="pui_subtitle" title="试卷副标题">高二年级物理试卷</div>
                            </div>
                            <div id="pui_testinfo" title="试卷信息栏" style="display: block;">
                                命题人：李昆华 审核人：董光顺 分值110分 考试时间：90分钟</div>
                        </div>
                        
                        
                        	<%final String[] no = {"一、","二、","三、","四、","五、","六、"};
                        	int i = 1,j=1;
                        	List<Cart> list =  (List<Cart>)request.getAttribute("cart");
                        	List<String> titleList =  (List<String>)request.getAttribute("titleList");
                         	for(int x = 0 ; x < list.size() ; x++){
                         		Cart c = list.get(x);%>
                         		
                         		<div id="ques_group_<%=i%>">
                                <div class="questypehead">
                                    <div class="questypeheadbox" id="questypeheadbox<%=i %>">
                                    <div class="questypetitle">
   										 	<span class="questypeindex"><b><%=no[x] %></b></span>
    										<span class="questypename" id="questypename<%=i %>"><%=c.getName() %></span>
    										<span class="questypenote" id="questypenote<%=i %>">（本题共<%=c.getCount()%>道小题，<%=titleList.get(x) %>）</span>
									</div>
									</div>
                            	</div>
                            	<% for(Question q : c.getQuesList()) {
                            	String[] str = q.getMemo().split(">", 2);%>
                            	
                            	
                            	
                            	<div class="ques" id="<%=q.getId() %>" >
                            	<%= str[0]+"><span><b>"+j+++".</b></span>"+str[1] %>
                            	</div>
                            	<%}%>
                            	</div>
                         	<%}%>
                        
                        
                        
                        
                        
                    </div>
                    <div class="col-md-1 column"></div>
                </div>
            </div>

</body>
</html>