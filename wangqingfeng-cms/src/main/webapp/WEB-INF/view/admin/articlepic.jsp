<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title}</title>
</head>
<body>
	<div class="container">
		<div align="right">
			<button class="btn btn-success" onclick="check(1)">同意</button>
			<button class="btn btn-warning" onclick="check(-1)">驳回</button>
			<button class="btn btn-info" onclick="back()">返回</button>
		</div>
		<dl>
			<!-- 标题 -->
			<dt>
				<h2 align="center">
					<strong>${title}</strong>
				</h2>
			</dt>
			<hr>
			<dd>
				<div>
			     	<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
						<c:forEach items="${list}" var="a" varStatus="i">
							<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }" class="${i.index==0?"active":"" }"></li>
						</c:forEach>
						</ol>
						<div class="carousel-inner">
							<c:forEach items="${list}" var="a" varStatus="i">
								<div class="carousel-item ${i.index==0?"active":"" }">
									<div class="container">
										<img src="/png/${a.url}" alt="..." width="1100px" height="600px">
										<div class="carousel-caption d-none d-md-block">
											<h5>${a.descr }</h5>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</dd>
		</dl>
	</div>
	<div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
//返回上层
function back(){
	$("#content-wrapper").load("/admin/articles");
}
//审核
function check(status){
	var id='${article.id}';
	$.post("/admin/updateArticle",{id:id,status:status},function(flag){
		if(flag){
			alert("操作成功");
		}else{
			alert("操作失败");
		}
	})
}
</script>
</html>