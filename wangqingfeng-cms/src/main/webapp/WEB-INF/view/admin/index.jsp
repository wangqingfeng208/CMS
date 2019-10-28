<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入  css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 后台页面样式模板 -->
<link rel="stylesheet" href="/resource/css/sb-admin.css">
<link rel="stylesheet" href="/resource/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body class="bg-light">
<!--top -->
	<div>
		<jsp:include page="top.jsp"></jsp:include>
	</div>
	<div id="wrapper">
		<!-- 后台管理系统左册菜单 -->
		<jsp:include page="left.jsp" />
		<!-- 中间内容显示区域 -->
		<div id="content-wrapper" class="bg-light">
			<div align="center" >
				<img alt="" src="/resource/images/bg_admin.jpg" class="rounded-circle">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
//文档就绪函数
$(function(){
	//为左侧菜单添加点击事件
	$(".nav-link").click(function(){
		$("ul li").removeClass("list-group-item-info");
		$(this).parent().addClass("list-group-item-info");
		//获取点击URL
      	var url = $(this).attr("data");
		//在当前页面的中间区域执行url
		$("#content-wrapper").load(url);
	})
})
</script>
</html>