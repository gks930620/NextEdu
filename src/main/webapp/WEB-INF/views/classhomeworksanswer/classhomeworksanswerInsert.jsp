<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>우리반 과제 - 글 등록</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 38분 
	
	 작성자 : 김아름
	작성날짜: 9월 28일 ~ 
	 -->

<!-- 여기에 자바 스크립트 이벤트 코딩 필요 -->

<!-- 학생이 과제 '제출'하는 화면 -->
<style type="text/css">
.input-style {
	border: 0px;
	background-color: #69a25c;
}
.font-white {
	color: white;
	font-size: 1.3em;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="mainContainer">
	<h3 class="h3-text">과제 제출</h3>
		<form:form action="classhomeworksanswerInsertMessage.edu"
			modelAttribute="classhomeworksanswerVO" method="post"
			enctype="multipart/form-data">
			<form:hidden path="chaMemId"/>
			<form:hidden path="chaMemName"/>
			<form:hidden path="chaChNo"/>
			<!-- view에서 부모글 번호, 과목, 제목 끌어와야.. -->
			<!-- START : 과제 테이블 -->
			<table class="table table-primary table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<td colspan="2" class="text-center table-th font-white">
					[
						<form:input path="chaCategory" 
									cssClass="input-style text-center font-white"
									size="2"/>
						<form:errors path="chaCategory" />
					]
						<form:input path="chaTitle" cssClass="input-sm input-style font-white"/>
						<form:errors path="chaTitle" />
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">학생 이름</th>
						<%-- <c:if test="${classhomeworksanswerVO.chaMemId != memberVO.memId}">
							<td>${memberVO.memName}</td>
						</c:if> --%>
						<td>${USER_INFO.userName}</td>
				</tr>
				<tr>
					<th class="text-center table-th">마감일</th>
					<td>
						<form:hidden path="chaDeadline" cssClass="form-control input-sm"/>
						<form:errors path="chaDeadline" />
						${classhomeworksanswerVO.chaDeadline}
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">첨부파일
						<button type="button" id="id_btn_new_file" class="small-button">추가</button>
					</th>
					<td class="file_area">
						<div class="form-inline">
							<input type="file" name="chaFiles" class="form-control">
							<button type="button" class="btn_delete small-button">삭제</button>
						</div>
					</td>
				</tr>

				<tr>
					<th class="text-center table-th">내용</th>
					<td>
						<form:textarea path="chaContent" cssClass="form-control" rows="10"/>
				 		<form:errors path="chaContent" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="../classhomeworks/classhomeworksList.edu" class="btn btn-sm btn-default">목록으로</a>
						</div>
						<div class="button-right">
							<button type="submit" class="btn btn-sm btn-default">저장하기</button>
						</div>
					</td>
				</tr>

			</table>
		</form:form>
</div>

</body>
<script type="text/javascript">

	//파일 추가 버튼 클릭 이벤트
	$('#id_btn_new_file').click(function(){
		$('.file_area').append('<div class="form-inline">'
			+ '<input type="file" name="classhomeworksFiles" class="form-control">'
			+ ' <button type="button" class="btn_delete small-button" >삭제</button>'
			+ '</div>');
	});		// #id_btn_new_file 클릭 이벤트
	
	// 파일 삭제 버튼 클릭 이벤트 (동적으로 추가된 객체의 이벤트 등록시 on 사용)
	$('.file_area').on('click','.btn_delete', function(){	// 문서 로딩 할 당시에 존재하지 않아서
															// (.btn_delete).click(function	사용 불가
							// 문서 로딩 할 때부터 존재하던.. btn_delete 상위의 .file_area를 이용해서 이벤트를 거는 것
		$(this).closest('div').remove();
	});		// btn_delete 클릭 이벤트
	
</script>
</html>