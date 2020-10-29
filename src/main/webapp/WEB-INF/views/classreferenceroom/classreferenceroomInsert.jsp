<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<meta charset="UTF-8">
<title>학급 자료실 - 글 등록</title>
<!-- 작성자: 김지원
	작성날짜: 9월 24일 오후 4시 38분 -->

<!-- 여기에 자바 스크립트 이벤트 코딩 필요 -->
<script type="text/javascript">
$('#id_btn_new_file').click(function(){
	// 파일 추가버튼 클릭 이벤트
	$('.file_area').append('<div class="form-inline">'
						 + '<input type="file" name="crFiles" class="form-control">'
						 + '<button type="button" class="btn_delete btn btn-sm">삭제</button>'
						 + '</div>');
	});
	
	// 파일 삭제버튼 클릭 이벤트 (동적으로 추가된 객체의 이벤트)
	$('.file_area').on('click','.btn_delete', function(){
		$(this).closest('div').remove();
	});
</script>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">자료 등록</h3>
		<form:form action="classreferenceroomInsertMessage.edu"
			modelAttribute="classreferenceroomVO" enctype="multipart/form-data"  method="post">
			<form:hidden path="crNo" />
			<table class="table table-primary table-bordered">
				<tbody>
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th class="text-center table-th">제목</th>
					<td><form:input path="crTitle"
							cssClass="form-control input-sm" /> <form:errors path="crTitle" />
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">분류</th>
					<td><form:select path="crCategory"
							cssClass="form-control-mini input-sm">
							<option value="">게시글을 분류하세요.</option>
							<form:option value="국어">국어</form:option>
							<form:option value="수학">수학</form:option>
							<form:option value="영어">영어</form:option>
							<form:option value="과학">과학</form:option>
							<form:option value="사회">사회</form:option>
							<form:option value="미술">미술</form:option>
							<form:option value="음악">음악</form:option>
							<form:option value="체육">체육</form:option>
						</form:select> <form:errors path="crCategory" /></td>
				</tr>
				<tr>
					<th class="text-center table-th">첨부파일
						<button type="button" id="id_btn_new_file" class="small-button">추가</button>
					</th>
					<td class="file_area">
						<div class="form-inline">
							<input type="file" name="crFiles" class="form-control">
							<button type="button" class="btn_delete btn btn-sm">삭제</button>
						</div>
					</td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><textarea rows="10" name="crContent"
							class="form-control input-sm">${classreferenceroomVO.crContent}</textarea>
						<%-- 	<form:errors path="crContent" /> --%></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="classreferenceroomList.edu"
								class="btn btn-sm btn-default">목록으로</a>
						</div>
						<div class="button-right">
							<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
		</form:form>
	</div>


</body>
</html>