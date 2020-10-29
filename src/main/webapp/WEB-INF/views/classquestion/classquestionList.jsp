<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 1:1문의 질문 리스트</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 -->
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<div class="page-header">
			<h3 class="h3-text">
				우리반 1:1문의 - <small>내 글 목록 조회</small>
			</h3>
		</div>

		<!-- 검색 폼 생성하기 -->
		<!-- 검색 폼 끝 -->

		<!-- 시작 -- 이제부터 게시판 라인  -->
		<table class="table table-primary table-bordered table-hover">
			<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col />
				<col width="10%" />
				<col width="15%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th class="text-center table-th">글번호</th>
					<th class="text-center table-th">분류</th>
					<th class="text-center table-th">제목</th>
					<th class="text-center table-th">작성자</th>
					<th class="text-center table-th">등록일</th>
					<th class="text-center table-th">답변 여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="classquestionVOList" items="${classquestionVOList}">
					<tr class="text-center">
						<td>${classquestionVOList.cqNo}</td>
						<td><a
							href="classquestionView.edu?cqNo=${classquestionVOList.cqNo}">${classquestionVOList.cqCategory}</a>
						</td>
						<td class="text-left"><a
							href="classquestionView.edu?cqNo=${classquestionVOList.cqNo}">${classquestionVOList.cqTitle}</a>
						</td>
						<td><a
							href="classquestionView.edu?cqNo=${classquestionVOList.cqNo}">${classquestionVOList.cqMemId}</a>
						</td>
						<td><c:choose>
								<c:when test="${classquestionVOList.cqModDate != null}">${classquestionVOList.cqModDate}</c:when>
								<c:when test="${classquestionVOList.cqModDate == null}">${classquestionVOList.cqRegDate}</c:when>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${classquestionVOList.cqAnswerYn == 'N'}">답변중</c:when>
								<c:when test="${classquestionVOList.cqAnswerYn == 'Y'}">답변완료</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- <!-- START : 페이지네이션  --> 
		<nav class="text-center">
			<mytag:paging pagingVO="${pagingVO}" linkPage="classquestionList.edu" />
		</nav> 
		<!-- END : 페이지네이션  -->  --%>
		<!-- 시작 -- 목록 건수 및 새글쓰기 버튼 // 진행도: 새글쓰기 버튼만 -->
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-sm-1 button-right">
				<a href="classquestionInsert.edu" class="btn btn-primary btn-sm">
					<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
					&nbsp;새글쓰기
				</a>
			</div>
		</div>
		<!-- 끝 -- 목록 건수 및 새글쓰기 버튼 // 진행도: 새글쓰기 버튼만 -->
	</div>
</body>
</html>