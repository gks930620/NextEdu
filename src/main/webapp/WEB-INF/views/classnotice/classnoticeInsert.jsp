<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 공지사항 - 공지 등록</title>
<!-- 작성자: 김지원
	작성날짜: 9월 24일 오후 4시 38분 -->

<!-- 여기에 자바 스크립트 이벤트 코딩 필요 -->
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">공지사항 등록</h3>
		<form:form action="classnoticeInsertMessage.edu"
			modelAttribute="classnoticeVO" enctype="multipart/form-data">
			<form:hidden path="cnNo" />
			<table class="table table-primary table-bordered">
				<tbody>
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
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
							cssClass="form-control input-sm" /> <form:errors path="cnTitle" />
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><textarea rows="10" name="cnContent"
							class="form-control input-sm">${classnoticeVO.cnContent}</textarea>
						<%-- 	<form:errors path="cnContent" /> --%></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="classnoticeList.edu" class="btn btn-sm btn-default">목록으로</a>
						</div>
						<div class="button-right">
							<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
						</div>
					</td>
				</tr>

				</tbody>
			</table>
		</form:form>
	</div>


</body>
</html>