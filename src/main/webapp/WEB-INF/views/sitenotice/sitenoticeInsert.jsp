<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitenoticeForm.jsp</title>
<!-- 작성자 : 김아름        한창희,
	작성일자 : 2020.09.23 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<h3 class="h3-text">공지사항 등록</h3>	
	<form:form action="sitenoticeInsertMessage.edu" modelAttribute="sitenoticeVO">
	<form:hidden path="snNo"/>
	<!-- START : 공지사항 테이블 -->
	<table class="table table-primary table-bordered">
		<tbody>
		<colgroup>
			<col width="20%" />
			<col/>
		</colgroup>
			<tr>
			<th class="text-center table-th">중요도 분류</th>
			<td>
				<form:select path="snTopYn" cssClass="form-control input-sm">
					<option value="" >공지 구분을 선택하세요</option>
					<form:option value="일반공지">일반공지</form:option>
   					<form:option value="중요공지">중요공지</form:option>
				</form:select>
				<form:errors path="snTopYn"/>
			</td>
			</tr>
			<tr>
				<th class="text-center table-th">제목</th>
				<td>
					<form:input path="snTitle" cssClass="form-control input-sm"/>
					<form:errors path="snTitle" />
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">내용</th>
				<td>
					<form:textarea path="snContent" cssClass="form-control" rows="10"/>
				 	<form:errors path="snContent" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="button-left">
						<a href="sitenoticeList.edu" class="btn btn-sm btn-default">목록으로</a>
					</div>
					<div class="button-right">
						<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
					</div>
				</td>
			</tr>
		</tbody>	
	</table>
	</form:form>
	<!-- END : 공지사항 테이블 -->
</div> <!-- mainContainer -->
</body>
</html>