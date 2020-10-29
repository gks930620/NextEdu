<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<meta charset="UTF-8">
<title>${resultMessageVO.title}</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="mainContainer">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-page-header">
					&nbsp;&nbsp;${resultMessageVO.title}
				</div>
				<div class="panel-heading">
					<p>${resultMessageVO.message}</p>
				</div>
				<div class="panel-body">
					<div class="button-left">
					<a href="${pageContext.request.contextPath}" class="btn btn-primary">
					 <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						&nbsp;Home
					</a> 
					</div>
					&nbsp;&nbsp;
					<div class="button-right">
						<c:if test="${not empty resultMessageVO.url}">
						<a href="<c:url value='${resultMessageVO.url}' />"class="btn btn-warning"> 
							<span class="glyphicon glyphicon-new-window aria-hidden="true"></span>
							&nbsp;${resultMessageVO.urlTitle}
						</a> 
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>