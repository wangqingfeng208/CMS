<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	<div class="container">
		<div class="form-group form-inline">
			<label for="username">用户名:</label>
			<input id="username" type="text" name="username" value="${username}" class="form-control">
			&nbsp;
			<button class="btn btn-success btn-sm" id="ss" type="button">搜索</button>
		</div>
	</div>
	<table class="table table-bordered table-hover" style="text-align: center">
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>状态</td>
			<td>角色</td>
			<td>注册日期</td>
			<td>修改日期</td>
		</tr>

		<c:forEach items="${user}" var="u" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>${u.username}</td>
				<td>${u.nickname}</td>
				<td><fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"/></td>
				<td><c:if test="${u.locked==0}">
						<button class="btn btn-success" onclick="update(${u.id},this)">正常</button>
					</c:if> 
					<c:if test="${u.locked==1}">
						<button class="btn btn-danger" onclick="update(${u.id},this)">停用</button>
					</c:if>
				</td>
				<td>${u.role==0?"普通用户":"管理员"}</td>
				<td><fmt:formatDate value="${u.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${u.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</table>
	<div>${pages}</div>
	<div class="alert alert-primary" role="alert">
  		A simple primary alert—check it out!
  	</div>
</body>
<script type="text/javascript">
//改变用户状态
function update(id,obj){
	//0:正常 1:停用
	var locked =$(obj).text()=="正常"?"1":"0";
	$.post("/admin/updateUser",{id:id,locked:locked},function(flag){
		if(flag){
			alert("alert alert-success");
			//改变按钮的内容
			$(obj).text(locked==1?"停用":"正常");
			//改变按钮的样式
			$(obj).attr("class",locked==1?"btn btn-danger":"btn btn-success");
		}else{
			alert("操作失败")
		}
	})
}
$(function(){
	$("#ss").click(function(){
		$("#content-wrapper").load("/admin/user?username="+$("[name='username']").val());
	})
})
</script>
</html>