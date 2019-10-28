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
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
<div class="container-fluid" style="background-color: yellow;">
 
 <div class="row">
    <div class="col-md-1" style="background-color: yellow;">1列</div>
    <div class="col-md-3" style="background-color: red;">3列</div>
    <div class="col-md-6" style="background-color: black;">6列</div>
    <div class="col-md-2"style="background-color:green;">2列</div>
 
 </div>
 
  <div class="row">
    <div class="col-md-5" style="background-color: yellow;">1列</div>
    <div class="col-md-2" style="background-color: red;">3列</div>
    <div class="col-md-1" style="background-color: black;">6列</div>
    <div class="col-md-4"style="background-color:green;">2列</div>
 
 </div>
 

 </div>
<button type="button" class="btn btn-primary">Primary</button>
<button type="button" class="btn btn-secondary">Secondary</button>
<button type="button" class="btn btn-success">Success</button>
<button type="button" class="btn btn-danger">Danger</button>
<button type="button" class="btn btn-warning">Warning</button>
</body>
</html>