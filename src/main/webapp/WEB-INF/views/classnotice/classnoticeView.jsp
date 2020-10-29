<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>우리반 공지사항 상세 보기</title>
<!-- 작성자: 김지원
	작성일: 9월 24일 오후 6시 16분 -->
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">
			우리반 공지사항 - <small>상세 보기</small>
		</h3>
		<table class="table table-primary table-bordered">
			<colgroup>
				<col width="20%" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th class="text-center table-th">글번호</th>
					<td>${classnoticeVO.cnNo}</td>
				</tr>
				<tr>
					<th class="text-center table-th">글제목</th>
					<td>${classnoticeVO.cnTitle}</td>
				</tr>
				<tr>
					<th class="text-center table-th">글분류</th>
					<td>${classnoticeVO.cnCategory}</td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><pre>${classnoticeVO.cnContent}</pre></td>
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
						</div> <mytag:sec hasRole="TEACHER">
							<div class="button-right">
								<a href="classnoticeUpdate.edu?cnNo=${classnoticeVO.cnNo}"
									class="btn btn-success btn-sm"> <span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									&nbsp;&nbsp;수정
								</a>
							</div>
						</mytag:sec>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 끝 - 본문 영역 -->

	<!-- 시작 - 댓글 등록 영역 -->
	<!--  끝 - 댓글 등록 영역 -->

	<!-- 시작 - 댓글 목록 영역 -->
	<!--  끝 - 댓글 목록 영역 -->
</body>
</html>