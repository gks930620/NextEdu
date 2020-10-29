<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 공지사항 - 공지 수정</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 38분 -->
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">
			우리반 공지사항 - <small>공지 수정</small>
		</h3>
		<form:form action="classnoticeUpdate.edu" modelAttribute="classnoticeVO">
			<form:hidden path="cnNo" />

			<!-- 시작: 글 수정 폼 -->
			<table class="table table-primary table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th class="text-center table-th">글번호</th>
					<td>${classnoticeVO.cnNo}</td>
				</tr>
				<tr>
					<th class="text-center table-th">중요도 분류</th>
					<td><form:select path="cnTopYn"
							cssClass="form-control-mini input-sm">
							<option value="">공지 구분을 선택하세요</option>
							<form:option value="N">일반공지</form:option>
							<form:option value="Y">중요공지</form:option>
						</form:select> <form:errors path="cnTopYn" /></td>
				</tr>
				<tr>
					<th class="text-center table-th">분류</th>
					<td><form:select path="cnCategory"
							cssClass="form-control-mini input-sm">
							<option value="">게시글을 분류하세요.</option>
							<form:option value="시험">시험</form:option>
							<form:option value="행사">행사</form:option>
							<form:option value="설문조사">설문조사</form:option>
							<form:option value="가정통신문">가정통신문</form:option>
							<form:option value="기타">기타</form:option>
						</form:select> <form:errors path="cnCategory" /></td>
				</tr>
				<tr>
					<th class="text-center table-th">제목</th>
					<td><form:input path="cnTitle"
							cssClass="form-control input-sm" /> <form:errors path="cnTitle" /></td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><textarea rows="10" name="cnContent"
							class="form-control input-sm">${classnoticeVO.cnContent}</textarea>
						<%-- 	<form:errors path="cnContent" /> --%></td>
				</tr>
				<tr>
					<th class="text-center table-th">등록일자</th>
					<td>${classnoticeVO.cnRegDate}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="classnoticeList.edu" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div>
						<div class="button-right">
							<button type="submit"
								class="btn btn-sm btn-danger" formaction="classnoticeDeleteMessage.edu">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								&nbsp;&nbsp;삭제
							</button>
							<button type="submit" formaction="classnoticeUpdateMessage.edu" class="btn btn-sm btn-primary">
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