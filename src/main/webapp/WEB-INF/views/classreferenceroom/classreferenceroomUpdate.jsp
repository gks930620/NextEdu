<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 자료실 - 글 수정</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 38분 -->
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">
			우리반 자료실 - <small>글 수정</small>
		</h3>
		<form:form action="classreferenceroomUpdate.edu" modelAttribute="classreferenceroomVO">
			<form:hidden path="crNo" />

			<!-- 시작: 글 수정 폼 -->
			<table class="table table-primary table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th class="text-center table-th">글번호</th>
					<td>${classreferenceroomVO.crNo}</td>
				</tr>
				<tr>
					<th class="text-center table-th">제목</th>
					<td><form:input path="crTitle"
							cssClass="form-control input-sm" /> <form:errors path="crTitle" /></td>
				</tr>
				<tr>
					<th class="text-center table-th">분류</th>
					<td><form:select path="crCategory"
							cssClass="form-control-mini input-sm">
							<option value="">게시글을 분류하세요.</option>
							<form:option value="국어">국어</form:option>
							<form:option value="수학">수학</form:option>
							<form:option value="영어">영어</form:option>
							<form:option value="과학">과학</form:option>
							<form:option value="사회">사회</form:option>
							<form:option value="미술">미술</form:option>
							<form:option value="음악">음악</form:option>
							<form:option value="체육">체육</form:option>
						</form:select> <form:errors path="crCategory" /></td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><textarea rows="10" name="crContent"
							class="form-control input-sm">${classreferenceroomVO.crContent}</textarea>
						<%-- 	<form:errors path="cnContent" /> --%></td>
				</tr>
				<tr>
					<th class="text-center table-th">등록일자</th>
					<td>${classreferenceroomVO.crRegDate}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="classreferenceroomList.edu" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div>
						<div class="button-right">
							<button type="submit" formaction="classreferenceroomDeleteMessage.edu"
								class="btn btn-sm btn-danger">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								&nbsp;&nbsp;삭제
							</button>
							<button type="submit" formaction="classreferenceroomUpdateMessage.edu" class="btn btn-sm btn-primary">
								<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
								&nbsp;&nbsp;저장
							</button>
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>