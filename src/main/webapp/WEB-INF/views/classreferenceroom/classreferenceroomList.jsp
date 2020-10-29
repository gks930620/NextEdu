<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 자료실 - 목록 조회</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 -->
</head>


<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<div class="page-header">
			<h3 class="h3-text">
				우리반 자료실 - <small>목록 조회</small>
			</h3>
		</div>

		<!-- START : 검색 폼  -->
		<div class="collapse.in panel panel-default" id="id_search_area">
			<div class="panel-body">
				<form name="frm_search" action="classreferenceroomList.edu" 
					method="post" class="form-horizontal">
					<input type="hidden" name="curPage" value="${classreferenceroomSearchVO.curPage}">
					<input type="hidden" name="rowSizePerPage"
						value="${classreferenceroomSearchVO.rowSizePerPage}">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2 inline-block">
							<select id="id_searchType" name="searchType"
								class="form-control input-sm">
								<option value="T"
									${classreferenceroomSearchVO.searchType eq "T" ? "selected='selected'": "" }>제목</option>
								<option value="C"
									${classreferenceroomSearchVO.searchType eq "C" ? "selected='selected'": "" }>내용</option>
							</select>
						</div>
						<div class="col-sm-2 inline-block">
							<input type="text" name="searchWord"
								class="form-control input-sm" value="${classreferenceroomSearchVO.searchWord}"
								placeholder="검색어">
						</div>
						<div class="col-sm-1 button-right inline-block">
							<button type="submit" class="medium-button btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 검색 폼  -->


		<!-- 시작 -- 이제부터 게시판 라인  -->
		<table class="greed table table-bordered table-primary">
			<colgroup>
				<col width="15%" />
				<col width="15%" />
				<col />
				<col width="15%" />
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
				<c:forEach var="classreferenceroomVOList"
					items="${classreferenceroomVOList}">
					<tr class="text-center">
						<td>${classreferenceroomVOList.crNo}</td>
						<td><a
							href="classreferenceroomView.edu?crNo=${classreferenceroomVOList.crNo}">${classreferenceroomVOList.crCategory}</a>
						</td>
						<td class="text-left"><a
							href="classreferenceroomView.edu?crNo=${classreferenceroomVOList.crNo}">${classreferenceroomVOList.crTitle}</a>
						</td>

						<td><c:choose>
								<c:when test="${classreferenceroomVOList.crModDate != null}">${classreferenceroomVOList.crModDate}</c:when>
								<c:when test="${classreferenceroomVOList.crModDate == null}">${classreferenceroomVOList.crRegDate}</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- START : 페이지네이션  -->
		<nav class="text-center">
			<mytag:paging pagingVO="${classreferenceroomSearchVO}"
				linkPage="classreferenceroomList.edu" />
		</nav>

		<!-- 시작 -- 목록 건수 및 새글쓰기 버튼 // 진행도: 새글쓰기 버튼만 -->
		<mytag:sec hasRole="TEACHER">
			<div class="button-right" style="margin-bottom: 10px;">
				<div class="col-sm-1 text-right">
					<a href="classreferenceroomInsert.edu"
						class="btn btn-primary btn-sm"> <span
						class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
						&nbsp;새글쓰기
					</a>
				</div>
			</div>
		</mytag:sec>
		<!-- 끝 -- 목록 건수 및 새글쓰기 버튼 // 진행도: 새글쓰기 버튼만 -->
	</div>
</body>
</html>