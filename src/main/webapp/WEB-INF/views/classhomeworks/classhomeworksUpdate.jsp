<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>우리반 과제 - 글 수정</title>
<!-- 작성자: 김지원
	작성날짜: 9월 22일 오후 5시 38분 
	
	 작성자 : 김아름
	작성날짜: 9월 28일 ~  -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="mainContainer">
	<h3 class="h3-text">과제 - <small>글 수정</small></h3>
	<form:form action="classhomeworksUpdateMessage.edu" modelAttribute="classhomeworksVO" method="post" enctype="multipart/form-data">
	<form:hidden path="chNo"/>

	<!-- 시작: 글 수정 폼 -->
	<table class="table table-primary table-bordered">
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		<tr>
			<th class="text-center table-th">글번호</th>
			<td>${classhomeworksVO.chNo}</td>
		</tr>
		<tr>
			<th class="text-center table-th">제목</th>
			<td>
				<form:input path="chTitle" cssClass="form-control input-sm"/>
				<form:errors path="chTitle" />
			</td>
		</tr>
		<tr>
			<th class="text-center table-th">작성자</th>
			<td>${classhomeworksVO.chMemId}</td>
		</tr>
		<tr>
			<th class="text-center table-th">과목명</th>
			<td>
				<form:select path="chCategory" cssClass="form-control-mini  input-sm" required="required">
					<option value="" >과목을 선택하세요</option>
					<option value="국어" ${classhomeworksVO.chCategory eq "국어" ?  "selected='selected'" : "" }>국어</option>
   					<option value="수학" ${classhomeworksVO.chCategory eq "수학" ?  "selected='selected'" : "" }>수학</option>
   					<option value="영어" ${classhomeworksVO.chCategory eq "영어" ?  "selected='selected'" : "" }>영어</option>
   					<option value="과학" ${classhomeworksVO.chCategory eq "과학" ?  "selected='selected'" : "" }>과학</option>
   					<option value="사회" ${classhomeworksVO.chCategory eq "사회" ?  "selected='selected'" : "" }>사회</option>
   					<option value="미술" ${classhomeworksVO.chCategory eq "미술" ?  "selected='selected'" : "" }>미술</option>
   					<option value="음악" ${classhomeworksVO.chCategory eq "음악" ?  "selected='selected'" : "" }>음악</option>
   					<option value="체육" ${classhomeworksVO.chCategory eq "체육" ?  "selected='selected'" : "" }>체육</option>
				</form:select>
				<form:errors path="chCategory"/>
			</td>
		</tr>
		<tr>
			<th class="text-center table-th">제출기한</th>
			<td>
				<form:input path="chDeadline" cssClass="form-control input-sm"/>
				<form:errors path="chDeadline" />
			</td>
		</tr>
		<tr>
			<th class="text-center table-th">첨부파일
					<button type="button" id="id_btn_new_file" class="small-button">추가</button>
			</th>
			<td class="file_area">
				<c:forEach var="f" items="${classhomeworksVO.attaches}" varStatus="st">
					<div>
						# 파일 ${st.count}
							<a href="<c:url value='/attach/download/${f.atchNo}' />" target="_blank">
								<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
								${f.atchOriginalName}
							</a>
						Size : ${f.atchFancySize}
						Down : ${f.atchDownHit}
					</div>
				</c:forEach>
			
				<div class="form-inline">
				<input type="file" name="chFiles" class="form-control">
				<button type="button" class="small-button btn_delete">삭제</button>
				</div>
			
			</td>
		</tr>
		<tr>
			<th class="text-center table-th">내용</th>
			<td>
				<textarea rows="10" name="chContent" class="form-control input-sm">${classhomeworksVO.chContent}</textarea>
				<form:errors path="chContent" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="button-left">
					<a href="classhomeworksList.edu" class="btn btn-default btn-sm"> <span
						class="glyphicon glyphicon-list" aria-hidden="true"></span>
						&nbsp;&nbsp;목록
					</a>
				</div>
				<div class="button-right">
					<button type="submit" class="btn btn-sm btn-default">
						<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
						&nbsp;&nbsp;저장
					</button>
				</div>
			</td>
		</tr>
	</table>
	</form:form>
	<!-- END : 과제 수정 테이블 -->
	</div>
<script type="text/javascript">
$('#id_btn_new_file').click(function(){
	// 파일 추가버튼 클릭 이벤트
	$('.file_area').append('<div class="form-inline">'
						 + '<input type="file" name="chFiles" class="form-control">'
						 + '<button type="button" class="btn_delete btn btn-sm">삭제</button>'
						 + '</div>');
	});
	
	// 파일 삭제버튼 클릭 이벤트 (동적으로 추가된 객체의 이벤트)
	$('.file_area').on('click','.btn_delete', function(){
		$(this).closest('div').remove();
	});
</script>
</body>
</html>