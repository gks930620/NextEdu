<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitequestionList.jsp</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
	<mytag:sec hasRole="STUDENT, TEACHER">
		<h3 class="h3-text">
			1:1 문의사항 - <small>목록조회</small>
		</h3>
		<div class="panel-body">
			<form name="frm_search" action="sitequestionList.edu" method="post" class="form-horizontal">
				<input type="hidden" name="curPage" value="${userVO.curPage }">
				<input type="hidden" name="rowSizePerPage" value="${userVO.rowSizePerPage }">
			</form>
		</div>
		<div class="row pull-right" style="margin-bottom: 10px;">
			<div class="col-sm-3 form-inline">
				전체 ${userVO.totalRowCount } 건 조회
				<select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm">
					<option value="10" ${userVO.rowSizePerPage eq "10" ? "selected='selected'" : "" }>10</option>
					<option value="20" ${userVO.rowSizePerPage eq "20" ? "selected='selected'" : "" }>20</option>
					<option value="30" ${userVO.rowSizePerPage eq "30" ? "selected='selected'" : "" }>30</option>
					<option value="50" ${userVO.rowSizePerPage eq "50" ? "selected='selected'" : "" }>50</option>
				</select>
			</div>
		</div>

		<!-- START : 1:1 문의사항 테이블 -->
		<table class="table table-primary table-bordered">
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
					<th class="text-center table-th">답변여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sitequestionVOEachList"	items="${sitequestionVOEachList}">
					<tr class="text-center">
						<td>${sitequestionVOEachList.sqNo }</td>
						<td>${sitequestionVOEachList.sqCategory }</td>
						<td class="text-left"><a
							href="sitequestionView.edu?sqNo=${sitequestionVOEachList.sqNo}">${sitequestionVOEachList.sqTitle }</a>
						</td>
						<td>${sitequestionVOEachList.sqWriter }</td>
						<td>${sitequestionVOEachList.sqRegDate }</td>
						<td>${sitequestionVOEachList.sqAnswerYn }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			<div class="button-right">
			<a href="sitequestionInsert.edu" class="btn btn-success btn-sm">
				<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
				&nbsp;문의하기
			</a>
		</div>
		<!-- END : 1:1 문의사항 테이블 -->
		<nav class="text-center">
			<mytag:paging pagingVO="${userVO }" linkPage="sitequestionList.edu" />
		</nav>
		</mytag:sec>
		
		<mytag:sec hasRole="MANAGER">
		<div class="panel-body">
			<form name="frm_search" action="sitequestionList.edu" method="post"
				class="form-horizontal">
				<input type="hidden" name="curPage" value="${pagingVO.curPage }">
				<input type="hidden" name="rowSizePerPage" value="${pagingVO.rowSizePerPage }">
			</form>
		</div>
		<div class="row pull-right" style="margin-bottom: 10px;">
			<div class="col-sm-3 form-inline">
				전체 ${pagingVO.totalRowCount } 건 조회
				<select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm">
					<option value="10" ${pagingVO.rowSizePerPage eq "10" ? "selected='selected'" : "" }>10</option>
					<option value="20" ${pagingVO.rowSizePerPage eq "20" ? "selected='selected'" : "" }>20</option>
					<option value="30" ${pagingVO.rowSizePerPage eq "30" ? "selected='selected'" : "" }>30</option>
					<option value="50" ${pagingVO.rowSizePerPage eq "50" ? "selected='selected'" : "" }>50</option>
				</select>
			</div>
		</div>

		<!-- START : 1:1 문의사항 테이블 -->
		<table class="table table-primary table-bordered">
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
					<th class="text-center table-th">답변여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sitequestionVOList" items="${sitequestionVOList}">
					<tr class="text-center">
						<td>${sitequestionVOList.sqNo }</td>
						<td>${sitequestionVOList.sqCategory }</td>
						<td class="text-left"><a
							href="sitequestionView.edu?sqNo=${sitequestionVOList.sqNo}">${sitequestionVOList.sqTitle }</a>
						</td>
						<td>${sitequestionVOList.sqWriter }</td>
						<td>${sitequestionVOList.sqRegDate }</td>
						<td>${sitequestionVOList.sqAnswerYn }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="button-right">
			<a href="sitequestionInsert.edu" class="btn btn-success btn-sm">
				<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
				&nbsp;문의하기
			</a>
		</div>
		<!-- END : 1:1 문의사항 테이블 -->
		<nav class="text-center">
			<mytag:paging pagingVO="${pagingVO }" linkPage="sitequestionList.edu" />
		</nav>
		</mytag:sec>
	</div>

	<script type="text/javascript">
		// 변수 정의
		var f = document.forms['frm_search'];

		// 함수 정의 

		// 각 이벤트 등록

		// 페이지 링크 클릭
		$('ul.pagination > li > a[data-page]').click(function(e) {
			e.preventDefault(); // 이벤트 전파방지, a의 href로 이동금지
			// data-page에 있는 값을 f.curPage.value에 설정, 서브밋한다.
			f.curPage.value = $(this).data('page');
			f.submit();
		}); // ul.pagination > li > a[data-page] 표시

		// 목록갯수 변경
		// id_rowSizePerPage 변경되었을 때 
		// 페이지 1로 설정, 목록수 = 현재 값으로 변경 후 submit
		$('#id_rowSizePerPage').change(function(e) {
			f.curPage.value = 1;
			f.rowSizePerPage.value = this.value;
			f.submit();
		}); // '#id_rowSizePerPage'.change
	</script>
</body>
</html>