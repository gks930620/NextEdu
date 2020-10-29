<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitenoticeView.jsp</title>
<!-- 작성자 : 김아름
	작성일자 : 2020.09.23 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<h3 class="h3-text">공지사항</h3>
	
	<!-- START : 공지사항 테이블 -->
	<table class="table table-primary table-bordered">
			<tbody>
				<tr>
					<th class="text-center table-th">글번호</th>
					<td>${sitenoticeVO.snNo}</td>
				</tr>
				<tr>
					<th class="text-center table-th">제목</th>
					<td>${sitenoticeVO.snTitle}</td>
				</tr>
				<tr>
					<th class="text-center table-th">등록일자</th>
					<td>
					<c:choose>
					<c:when test="${sitenoticeVO.snModDate != null}">${sitenoticeVO.snModDate}</c:when>
					<c:when test="${sitenoticeVO.snModDate == null}">${sitenoticeVO.snRegDate}</c:when>
					</c:choose>
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><textarea rows="10" name="snContent" class="form-control input-sm" disabled="disabled">${sitenoticeVO.snContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
					  <div class="button-left">
							<a href="sitenoticeList.edu" class="btn btn-default btn-sm"> 
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div>
						
						<mytag:sec hasRole="MANAGER">	
						<div class="button-right">
						<form:form action="sitenoticeDelete.edu" modelAttribute="sitenoticeVO">
						<form:hidden path="snNo"/>
						<a href="sitenoticeUpdate.edu?snNo=${sitenoticeVO.snNo}" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							&nbsp;&nbsp;수정
						</a>
							<button type="submit" class="btn btn-sm btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							&nbsp;&nbsp;삭제
							</button>
						</form:form>
					</div>
						</mytag:sec>
					</td>					  
				</tr>
			</tbody>
		</table>	
	<!-- END : 공지사항 테이블 -->
</div> <!-- mainContainer -->
</body>
</html>