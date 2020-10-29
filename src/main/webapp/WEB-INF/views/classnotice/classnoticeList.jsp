<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 공지사항</title>

<!-- 작성자: 김지원
	작성날짜: 9월 25일 오후 5시 -->
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<div class="page-header">
			<h3 class="h3-text">
				우리반 공지사항 - <small>목록 조회</small>
			</h3>
		</div>

		<!-- 검색 폼 생성하기 -->
		<!-- 검색 폼 끝 -->

		<!-- 시작 -- 이제부터 게시판 라인  -->
		<table class="greed table table-bordered table-primary">
			<colgroup>
				<col width="15%" />
				<col width="20%" />
				<col />
				<col width="20%" />
			</colgroup>
			<thead>
				<tr>
					<th class="text-center table-th">글번호</th>
					<th class="text-center table-th">분류</th>
					<th class="text-center table-th">제목</th>
					<th class="text-center table-th">등록일</th>
				</tr>
			</thead>
			<tbody>
				<!-- 중요공지 상단 고정 -->
				<c:forEach var="classnoticeVOList" items="${classnoticeVOList}">
					<tr class="text-center">
						<c:choose>
							<c:when test="${classnoticeVOList.cnTopYn == 'Y'}">
								<td class="important-notice"><span>!!중요공지!!</span></td>
								<td class="important-notice"><a class="important-notice"
									href="classnoticeView.edu?cnNo=${classnoticeVOList.cnNo}">${classnoticeVOList.cnCategory}</a>
								</td>
								<td class="text-left important-notice"><a class="important-notice"
									href="classnoticeView.edu?cnNo=${classnoticeVOList.cnNo}">${classnoticeVOList.cnTitle}</a>
								</td>
								<td class="important-notice"><c:choose>
										<c:when test="${classnoticeVOList.cnModDate != null}">${classnoticeVOList.cnModDate}</c:when>
										<c:when test="${classnoticeVOList.cnModDate == null}">${classnoticeVOList.cnRegDate}</c:when>
									</c:choose></td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>

				<c:forEach var="classnoticeVOList" items="${classnoticeVOList}">
					<tr class="text-center">
						<td>${classnoticeVOList.cnNo}</td>
						<td><a
							href="classnoticeView.edu?cnNo=${classnoticeVOList.cnNo}">${classnoticeVOList.cnCategory}</a>
						</td>
						<td class="text-left"><a
							href="classnoticeView.edu?cnNo=${classnoticeVOList.cnNo}">${classnoticeVOList.cnTitle}</a>
						</td>

						<td><c:choose>
								<c:when test="${classnoticeVOList.cnModDate != null}">${classnoticeVOList.cnModDate}</c:when>
								<c:when test="${classnoticeVOList.cnModDate == null}">${classnoticeVOList.cnRegDate}</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- START : 페이지네이션  --> 
		<nav class="text-center">
			<mytag:paging pagingVO="${pagingVO}" linkPage="classnoticeList.edu" />
		</nav>
		<!-- END : 페이지네이션  --> 
		<!-- 시작 -- 목록 건수 및 새글쓰기 버튼 // 진행도: 새글쓰기 버튼만 -->
		<!-- 관리자에게만 새글쓰기 버튼 보이기 -->
		<mytag:sec hasRole="TEACHER">
			<div class="button-right" style="margin-bottom: 10px;">
				<div class="col-sm-1 text-right">
					<a href="classnoticeInsert.edu" class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
						&nbsp;새글쓰기
					</a>
				</div>
			</div>
		</mytag:sec>
		<!-- 끝 -- 목록 건수 및 새글쓰기 버튼 // 진행도: 새글쓰기 버튼만 -->
		
	</div>
</body>
</html>