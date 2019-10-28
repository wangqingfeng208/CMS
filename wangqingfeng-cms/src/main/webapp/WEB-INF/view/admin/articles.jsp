<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员后台</title>
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
			<label for="title">文章标题:</label>
			<input id="title" type="text" name="title" value="${article.title}" class="form-control">
			&nbsp;
			文章状态:
			<select name="status" class="form-control" id="status">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
			</select>
			&nbsp;
			<button class="btn btn-success btn-sm" onclick="ss()" type="button">搜索</button>
		</div>
	</div>
	<table class="table table-bordered table-hover" style="text-align: center">
		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>作者</td>
			<td>昵称</td>
			<td>发布时间</td>
			<td>文章状态</td>
			<td>点击量</td>
			<td>是否热门</td>
		</tr>
		<c:forEach items="${articles}" var="a" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>
					<a href="javascript:detail(${a.id})">${a.title}</a>
				</td>
				<td>${a.user.username}</td>
				<td>${a.user.nickname}</td>
				<td><fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${a.status==0?"待审":a.status==1?"已审":"驳回"}</td>
				<td>${a.hits}</td>
				<td>
					<c:if test="${a.hot==0 }">
						<button class="btn btn-success" onclick="update(${a.id},this)">否</button>
					</c:if> <c:if test="${a.hot==1 }">
						<button class="btn btn-danger" onclick="update(${a.id},this)">是</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>${pages}</div>
</body>
<script type="text/javascript">
//文章详情
function detail(id){
	var url="/admin/article?id="+id;
	// alert(url)
	$("#content-wrapper").load(url);
}
function detailpic(id){
	var url="/admin/articlepic?id="+id;
	// alert(url)
	$("#content-wrapper").load(url);
}
//热门文章管理
function update(id,obj){
	//0:否 1:是
	var hot =$(obj).text()=="否"?"1":"0";
	$.post("/admin/updateArticle",{id:id,hot:hot},function(flag){
		if(flag){
			//改变按钮的内容
			$(obj).text(hot==1?"是":"否");
			//改变按钮的样式
			$(obj).attr("class",hot==1?"btn btn-danger":"btn btn-success");
		}else{
			alert("操作失败")
		}
	})
}
function ss(){
	//在框架的中间区域显示查询
	$("#content-wrapper").load("/admin/articles?title="+$("[name='title']").val()+"&status="+$("[name='status']").val());
}
//回显查询条件
$(function(){
	var status='${article.status}';//获取查询条件
	//让下拉框选中
	$("#status").each(function(){
		$(this).val(status);
	})
	
})
</script>
</html>