<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 1:1문의 - 질문 등록</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 38분 -->

<!-- 여기에 자바 스크립트 이벤트 코딩 필요 -->



</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3>글 등록</h3>
		<form:form action="classquestionInsertMessage.edu"
			modelAttribute="classquestionVO" enctype="multipart/form-data">
			<form:hidden path="cqNo" />
			<table class="table table-striped table-bordered">
				<tbody>
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th>제목</th>
					<td><form:input path="cqTitle"
							cssClass="form-control input-sm" /> <form:errors path="cqTitle" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${classquestionVO.cqMemId}</td>
				</tr>
				<tr>
					<th>분류</th>
					<td><form:select path="cqCategory"
							cssClass="form-control input-sm">
							<option value="">게시글을 분류하세요.</option>
							<form:option value="수업">수업</form:option>
							<form:option value="과제">과제</form:option>
							<form:option value="고민상담">고민상담</form:option>
							<form:option value="기타">기타</form:option>
						</form:select> <form:errors path="cqCategory" /></td>
				</tr>
				<tr>
					<th>첨부파일
						<button type="button" id="id_btn_new_file">추가</button>
					</th>
					<td class="file_area">
						<div class="form-inline">
							<input type="file" name="" class="form-control">
							<button type="button" class="btn_delete btn btn-sm">삭제</button>
						</div>
					</td>
				</tr>

				<tr>
					<th>내용</th>
					<td><textarea rows="10" name="cqContent"
							class="form-control input-sm">${classquestionVO.cqContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pull-left">
							<a href="classquestionList.edu" class="btn btn-sm btn-default">목록으로</a>
						</div>
						<div class="pull-right">
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