<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 1:1문의 - 질문 수정</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 38분 -->
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3>
			우리반 1:1문의 - <small>질문 수정</small>
		</h3>

		<form:form action="classquestionUpdate.edu"
			modelAttribute="classquestionVO">
			<form:hidden path="cqNo" />

			<!-- 시작: 글 수정 폼 -->
			<table class="table table-striped table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th>글번호</th>
					<td>${classquestionVO.cqNo}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><form:input path="cqTitle"
							cssClass="form-control input-sm" /></td>
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
					<th>내용</th>
					<td><textarea rows="10" name="cqContent"
							class="form-control input-sm">${classquestionVO.cqContent}</textarea></td>
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
					<th>등록일자</th>
					<td>${classquestionVO.cqRegDate}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pull-left">
							<a href="classquestionList.edu" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div> <c:if test="${classquestionVO.cqAnswerYn == 'N'}">
							<div class="pull-right">
								<button type="submit"
									formaction="classquestionDeleteMessage.edu"
									class="btn btn-sm btn-danger">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									&nbsp;&nbsp;삭제
								</button>
							</div>
						</c:if>
						<button type="submit" class="btn btn-sm btn-primary"
							formaction="classquestionUpdateMessage.edu">
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