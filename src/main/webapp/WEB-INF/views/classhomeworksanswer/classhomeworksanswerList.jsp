<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>내 과제 - 목록 조회</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 
	
	9/25 ~ : 김아름 -->
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
	<!-- START : 교사에게만 접근 권한 있는 페이지 -->
	<mytag:sec hasRole="TEACHER">
		<div class="page-header">
			<h3 class="h3-text">
				우리반 과제 - <small>제출 목록</small>
			</h3>
		</div>

		<!-- 시작 -- 목록 건수  -->
		<form name="frm_search" action="classhomworksanswerList.edu" method="post" class="form-horizontal">
	        <input type="hidden" name="curPage" value="${pagingVO.curPage }">
	        <input type="hidden" name="rowSizePerPage" value="${pagingVO.rowSizePerPage }">
	    </form>
	    <div class="row" style="margin-bottom: 10px;">
		    <div class="col-sm-3 form-inline"  >
		        전체 ${pagingVO.totalRowCount } 건 조회
		        <select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm" >
		            <option value="10" >10</option>
		            <option value="20" >20</option>
		            <option value="30" >30</option>
		            <option value="50" >50</option>
		        </select>
		    </div>
		</div>
		<!-- 끝 -- 목록 건수 -->

		<!-- 시작 -- 이제부터 게시판 라인  -->
		<table class="table table-primary table-bordered table-hover">
			<colgroup>
				<col width="10%" />
				<col />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th class="text-center table-th">과목</th>
					<th class="text-center table-th">제목</th>
					<th class="text-center table-th">작성자</th>
					<th class="text-center table-th">제출기한</th>
					<th class="text-center table-th">제출일</th>
					<th class="text-center table-th">점수</th>
				</tr>
			</thead>
			<tbody> 
			
		<c:forEach var="classhomeworksanswerVOList" items="${classhomeworksanswerVOList}">
				<tr class="text-center">
					<td>${classhomeworksanswerVOList.chaCategory}</td>
					<td class="text-left">
						<a href="classhomeworksanswerViewT.edu?chaNo=${classhomeworksanswerVOList.chaNo}&chaChNo=${classhomeworksanswerVOList.chaChNo}">${classhomeworksanswerVOList.chaTitle}</a>
					</td>
					<td>${classhomeworksanswerVOList.chaMemName}</td>
					<td>${classhomeworksanswerVOList.chaDeadline}</td>
					
					<td>
					<c:choose>
						<c:when test="${classhomeworksanswerVOList.chaModDate != null}">${classhomeworksanswerVOList.chaModDate}</c:when>
						<c:when test="${classhomeworksanswerVOList.chaModDate == null}">${classhomeworksanswerVOList.chaRegDate}</c:when>
					</c:choose>
					</td>
				
					<td>${classhomeworksanswerVOList.chaScore}</td>
				</tr>
			</c:forEach> 
			</tbody>
		</table>
		<!-- START : 페이지네이션  --> 
		<nav class="text-center">
			<mytag:paging pagingVO="${pagingVO}" linkPage="classhomeworksanswerList.edu" />
		</nav>
		<!-- END : 페이지네이션  -->
		</mytag:sec>
	</div>
<script type="text/javascript">
	// 변수 정의
	var f = document.forms['frm_search'];
	
	// 페이지 링크 클릭
	$('ul.pagination > li > a[data-page]').click(function (e) {
		e.preventDefault();	// 이벤트 전파방지, a의 href로 이동금지
		// data-page에 있는 값을 f.curPage.value에 설정, 서브밋한다.
		f.curPage.value = $(this).data('page');
		f.submit();
	});	// ul.pagination > li > a[data-page] 표시
	
	$('#id_rowSizePerPage').change(function(e){
		f.curPage.value = 1;
		f.rowSizePerPage.value = this.value;
		f.submit();
	});	// '#id_rowSizePerPage'.change
	
</script>
</body>
</html>