<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytage" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>​
<meta charset="UTF-8">
<title>우리반 학생 목록</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">우리반 학생 목록</h3>

		<!-- START : 검색 폼  -->
		<div class="collapse.in panel panel-default" id="id_search_area">
			<div class="panel-body">
				<form name="frm_search" action="classmemberList.edu" method="post" class="form-horizontal">
					<input type="hidden" name="curPage"	value="${classmemberSearchVO.curPage}">
					 <input	 type="hidden" name="rowSizePerPage" value="${classmemberSearchVO.rowSizePerPage}">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2 inline-block">
							<select id="id_searchType" name="searchType" class="form-control input-sm">
								<option value="MemName" ${classmemberSearchVO.searchType eq "MemName" ? "selected='selected'": "" }>이름</option>
								<option value="MemId" ${classmemberSearchVO.searchType eq "MemId" ? "selected='selected'": "" }>아이디</option>
								<option value="MemHP" ${classmemberSearchVO.searchType eq "MemHP" ? "selected='selected'": "" }>핸드폰</option>
								<option value="MemAdd1" ${classmemberSearchVO.searchType eq "MemAdd1" ? "selected='selected'": "" }>주소</option>
							</select>
						</div>
						<div class="col-sm-2 inline-block">
							<input type="text" name="searchWord" class="form-control input-sm" value="${classmemberSearchVO.searchWord}" placeholder="검색어">
						</div>
						<!-- 
						<div class="form-group">
							<div class="col-sm-2 col-sm-offset-9 text-right">
								<button type="button" id="id_btn_reset"
									class="btn btn-sm btn-default">
									<i class="fa fa-sync"></i> &nbsp;&nbsp;초기화
								</button>
							</div>
							 -->
	
						<div class="button-right inline-block">
							<button type="submit" class="medium-button btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>

				</form>
			</div>
		</div>
		<!-- END : 검색 폼  -->

		<!-- START : 목록건수 및 새글쓰기 버튼  -->
			<div class="col-sm-3 form-inline inline-block">
				전체 ${classmemberSearchVO.totalRowCount} 건 조회 <select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm">
					<option value="10" ${classmemberSearchVO.rowSizePerPage eq "10" ? "selected='selected'": "" }>10</option>
					<option value="20" ${classmemberSearchVO.rowSizePerPage eq "20" ? "selected='selected'": "" }>20</option>
					<option value="30" ${classmemberSearchVO.rowSizePerPage eq "30" ? "selected='selected'": "" }>30</option>
					<option value="50" ${classmemberSearchVO.rowSizePerPage eq "50" ? "selected='selected'": "" }>50</option>
				</select>
			</div>
			<!-- END : 목록건수 및 회원등록 버튼  -->
			<div class="col-sm-offset-6 button-right">
				<a href="#" class="medium-button" id="id_search_show"
					data-toggle="collapse" data-target="#id_search_area"> <i
					class="fas fa-chevron-up"></i> <span>&nbsp;검색닫기</span>
				</a>
			</div>
			<table class="greed table table-primary table-bordered">
				<caption class="hidden">회원목록 조회</caption>
				<colgroup>
					<col style="width: 3%;" />
					<col />
					<col style="width: 20%;" />
					<col style="width: 20%;" />
					<col style="width: 15%;" />
					<col style="width: 15%;" />
				</colgroup>
				<thead>
					<tr>
						<th class="text-center table-th"><input type="checkbox" id="id_member_all_change" )></th>
						<th class="text-center table-th">ID</th>
						<th class="text-center table-th">이름</th>
						<th class="text-center table-th">휴대폰</th>
						<th class="text-center table-th">성별</th>
						<th class="text-center table-th">학번</th>
					</tr>
				</thead>
				<tbody>
					<!-- 아래의 스크립트 코드를 EL/JSTL 사용해서 변경 -->
					<c:forEach var="classmemberVO" items="${classmemberVO}">
						<tr class="text-center">
							<td><input type="checkbox" name="memId"
								value="${classmemberVO.memId}"></td>

							<td class="text-left">${classmemberVO.memId}</td>
							<td><a
								href="classmemberView.edu?memId=${classmemberVO.memId}">${classmemberVO.memName}</a></td>
							<td>${classmemberVO.memHp}</td>
							<td>${classmemberVO.memSex}</td>
							<td>${classmemberVO.memStuno}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<form action="ExcelPoi.edu" name="excelForm" id="excelForm" method="post">
				<input type="text" name="fileName" placeholder="파일명을 입력해주세요" class="inline-block" style="width:200px " />
				<input type="submit" class="medium-button" value="EXCEL 다운로드"  />
			</form>

			<!-- START : 페이지네이션  -->
			<nav class="text-center">
				<mytage:paging pagingVO="${classmemberSearchVO}" linkPage="classmemberList.edu" />
			</nav>
			<!-- END : 페이지네이션  -->
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