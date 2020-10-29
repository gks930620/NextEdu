<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitenoticeList.jsp</title>
<!-- 작성자 : 김아름
	작성일자 : 2020.09.23 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<h3 class="h3-text">NextEdu 공지사항 - <small>목록조회</small></h3>
	<form name="frm_search" action="sitenoticeList.edu" method="post" class="form-horizontal">
        <input type="hidden" name="curPage" value="${sitequestionSearchVO.curPage }">
        <input type="hidden" name="rowSizePerPage" value="${sitequestionSearchVO.rowSizePerPage }">
    </form>
	<div class="row" style="margin-bottom: 10px;">
	    <div class="col-sm-3 form-inline"  >
	        전체 ${sitequestionSearchVO.totalRowCount } 건 조회
	        <select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm" >
	            <option value="10" >10</option>
	            <option value="20" >20</option>
	            <option value="30" >30</option>
	            <option value="50" >50</option>
	        </select>
	    </div>
	    <%-- 
	    <div class="col-sm-2 col-sm-offset-6 text-right" >
	        <a id="id_seach_show" href="#id_search_area" class="btn btn-info btn-sm"  data-toggle="collapse"  aria-expanded="false" aria-controls="collapseExample"> 
	        <i id="searchTap" class="fas fa-chevron-up"></i>
	            <span>&nbsp;검색닫기</span>
	            
			</a>
	    </div> --%>
	    
	</div>
	
	<!-- START : 공지사항 테이블 -->
	<table class="greed table table-bordered table-primary">
	<colgroup>
		<col width="15%" />
		<col />
		<col width="25%" />
	</colgroup>
	<thead>
		<tr>
			<th class="text-center table-th">글번호</th>
			<th class="text-center table-th">제목</th>
			<th class="text-center table-th">등록일</th>
		</tr>
	</thead>	
	<tbody>
		<!-- 중요공지 상단 고정 -->
		<c:forEach var="sitenoticeVOTopList" items="${sitenoticeVOTopList}">
			<tr class="text-center"> 
			<c:choose>
			<c:when test="${sitenoticeVOTopList.snTopYn == '중요공지'}">
				<td class="important-notice"><span>!!필독!!</span></td>
				<td class="text-left important-notice">
					<a class="important-notice" href="sitenoticeView.edu?snNo=${sitenoticeVOTopList.snNo}">${sitenoticeVOTopList.snTitle}</a>
				</td>
				<td class="important-notice">
				<c:choose>
				<c:when test="${sitenoticeVOTopList.snModDate != null}">${sitenoticeVOTopList.snModDate}</c:when>
				<c:when test="${sitenoticeVOTopList.snModDate == null}">${sitenoticeVOTopList.snRegDate}</c:when>
				</c:choose>
				</td>
			</c:when>
			</c:choose>
			</tr>
		</c:forEach>
		
		<c:forEach var="sitenoticeVOList" items="${sitenoticeVOList}">
		<tr class="text-center">
			<td>${sitenoticeVOList.snNo}</td>
			<td class="text-left">
				<a href="sitenoticeView.edu?snNo=${sitenoticeVOList.snNo}">${sitenoticeVOList.snTitle}</a>
			</td>
			
			<td>
			<c:choose>
			<c:when test="${sitenoticeVOList.snModDate != null}">${sitenoticeVOList.snModDate}</c:when>
			<c:when test="${sitenoticeVOList.snModDate == null}">${sitenoticeVOList.snRegDate}</c:when>
			</c:choose>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<form action="ExcelPoi.edu" name="excelForm" id="excelForm" method="post">
		<input type="text" name="filename" placeholder="파일명을 입력해주세요" class="inline-block" style="width:200px " />
		<input type="submit" class="medium-button" value="EXCEL 다운로드"  />
	</form>
	
	<!-- START : 페이지네이션  --> 
	<nav class="text-center">
		<mytag:paging pagingVO="${sitequestionSearchVO}" linkPage="sitenoticeList.edu" />
	</nav>
	<!-- END : 페이지네이션  --> 
	
	<mytag:sec hasRole="MANAGER">	
	<div class="button-right" >
        <a href="sitenoticeInsert.edu" class="btn btn-primary btn-sm"> 
            <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
            &nbsp;공지 등록
		</a>
    </div>
    </mytag:sec>
	<!-- END : 공지사항 테이블 -->
	
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