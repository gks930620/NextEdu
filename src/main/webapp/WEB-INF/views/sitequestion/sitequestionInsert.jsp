<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitequestionInsert.jsp</title>
<!-- 작성자 : 김영훈
	작성일자 : 2020.09.24 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<h3 class="h3-text">1:1 문의글 등록</h3>	
	<form:form action="sitequestionInsertMessage.edu" modelAttribute="sitequestionVO" method="post" enctype="multipart/form-data">
<%-- 		<form:hidden path="sqNo"/> --%>
	<!-- START : 1:1 문의사항 테이블 -->
	<table class="table table-primary table-bordered">
		<tbody>
		<colgroup>
			<col width="20%" />
			<col/>
		</colgroup>
			<tr>
				<th class="text-center table-th">제목</th>
				<td>
					<form:input path="sqTitle" cssClass="form-control input-sm"/>
					<form:errors path="sqTitle" />
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">분류</th>
				<td>
					<form:select path="sqCategory" required="required" cssClass="form-control input-sm">
						<option value="">-- 선택하세요 -- </option>
						<option value="화상수업" 	${sitequestionVO.sqCategory eq "화상수업" ?  "selected='selected'" : "" }>화상수업</option>
						<option value="로그인"  	${sitequestionVO.sqCategory eq "로그인" ?  "selected='selected'" : "" }>로그인</option>
						<option value="사이트 이용" ${sitequestionVO.sqCategory eq "사이트 이용" ?  "selected='selected'" : "" }>사이트 이용</option>
						<option value="기타" 		${sitequestionVO.sqCategory eq "기타" ?  "selected='selected'" : "" }>기타</option>
					</form:select>
					<form:errors path="sqCategory" />
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">작성자</th>
				<td>
					<form:input path="sqWriter" value="${sessionScope.USER_INFO.userName}" readonly="true" cssClass="form-control input-sm" />
					<form:errors path="sqWriter" />
					<form:hidden path="sqMemId" value="${sessionScope.USER_INFO.userId}" readonly="true" cssClass="form-control input-sm" />
					<form:errors path="sqMemId" />
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">첨부파일
					<button type="button" id="id_btn_new_file" class="small-button">추가</button>
				</th>
				<td class="file_area">
					<div class="form-inline">
					<input type="file" name="sqFiles" class="form-control">
					<button type="button" class="small-button btn_delete">삭제</button>
					</div>
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">내용</th>
				<td><textarea rows="10" name="sqContent" class="form-control input-sm"></textarea>
				<form:errors path="sqContent" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="button-left">
						<a href="sitequestionList.edu" class="btn btn-sm btn-default">목록으로</a>
					</div>
					<div class="button-right">
						<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
					</div>
				</td>
			</tr>
		</tbody>	
	</table>
	</form:form>
	<!-- END : 1:1 문의사항 테이블 -->
</div> <!-- mainContainer -->
<script type="text/javascript">
$('#id_btn_new_file').click(function(){
	// 파일 추가버튼 클릭 이벤트
	$('.file_area').append('<div class="form-inline">'
						 + '<input type="file" name="sqFiles" class="form-control">'
						 + '<button type="button" class="small-button btn_delete">삭제</button>'
						 + '</div>');
	});
	
	// 파일 삭제버튼 클릭 이벤트 (동적으로 추가된 객체의 이벤트)
	$('.file_area').on('click','.btn_delete', function(){
		$(this).closest('div').remove();
	});
</script>
</body>
</html>