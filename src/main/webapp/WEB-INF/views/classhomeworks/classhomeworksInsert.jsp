<%@page import="com.next.classhomeworks.vo.ClasshomeworksVO"%>
<%@page import="com.next.login.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<!-- 담임교사가 과제 '등록'하는 화면 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="mainContainer">
<%-- <%UserVO vo = (UserVO)session.getAttribute("USER_INFO"); 
String memId = vo.getUserId(); 
ClasshomeworksVO classhomeworksVO = new ClasshomeworksVO();
classhomeworksVO.setChMemId(memId);%>
<%="아이디"+classhomeworksVO.getChMemId() %>
 --%>
	<h3 class="h3-text">과제 등록</h3>
		<form:form action="classhomeworksInsertMessage.edu"
			modelAttribute="classhomeworksVO" method="post"
			enctype="multipart/form-data">
			<form:hidden path="chMemId"/>
			<form:hidden path="chMemName"/>
			<!-- START : 과제 테이블 -->
			<table class="table table-primary table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th class="text-center table-th">제목</th>
					<td>
						<form:input path="chTitle" cssClass="form-control input-sm"/>
						<form:errors path="chTitle" />
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">분류</th>
					<td>
						<form:select path="chCategory" cssClass="form-control-mini input-sm">
							<option value="" >과목을 선택하세요</option>
							<form:option value="국어">국어</form:option>
		   					<form:option value="수학">수학</form:option>
		   					<form:option value="영어">영어</form:option>
		   					<form:option value="과학">과학</form:option>
		   					<form:option value="사회">사회</form:option>
		   					<form:option value="미술">미술</form:option>
		   					<form:option value="음악">음악</form:option>
		   					<form:option value="체육">체육</form:option>
						</form:select>
						<form:errors path="chCategory"/>
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">첨부파일
						<br><button type="button" id="id_btn_new_file" class="small-button">추가</button>
					</th>
					<td class="file_area">
						<div class="form-inline">
							<input type="file" name="chFiles" class="form-control">
							<button type="button" class="small-button btn_delete">삭제</button>
						</div>
					</td>


				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td>
						<form:textarea path="chContent" cssClass="form-control" rows="10"/>
				 		<form:errors path="chContent" />
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">마감일</th>
					<td>
						<form:input path="chDeadline" cssClass="form-control-mini input-sm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="classhomeworksList.edu" class="btn btn-sm btn-default">목록으로</a>
						</div>
						<div class="button-right">
							<button type="submit" class="btn btn-sm btn-default">과제 작성</button>
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
			+ '<input type="file" name="chFiles" class="form-control">'
			+ ' <button type="button" class="small-button btn_delete" >삭제</button>'
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