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
	<form:form action="classhomeworksanswerUpdateMessage.edu" modelAttribute="classhomeworksanswerVO">
	<form:hidden path="chaChNo"/>
	<form:hidden path="chaNo"/>

	<!-- 시작: 글 수정 폼 -->
	<table class="table table-primary table-bordered">
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		<tr>
			<th class="text-center table-th">글번호</th>
			<td>${classhomeworksanswerVO.chaNo}</td>
		</tr>
		<tr>
			<th class="text-center table-th">제목</th>
			<td>
				<form:input path="chaTitle" cssClass="form-control input-sm"/>
				<form:errors path="chaTitle" />
			</td>
		</tr>
		<tr>
			<th class="text-center table-th">작성자</th>
			<td>${classhomeworksanswerVO.chaMemId}</td>
		</tr>
		<tr>
			<th class="text-center table-th">과목명</th>
			<td>${classhomeworksanswerVO.chaCategory}</td>
		</tr>
		<tr>
			<th class="text-center table-th">첨부파일
				<button type="button" id="id_btn_new_file" class="small-button">추가</button>
			</th>
			<td class="file_area">
				<c:forEach var="f" items="${classhomeworksanswerVO.attaches}" varStatus="st">
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
				<input type="file" name="chaFiles" class="form-control">
				<button type="button" class="small-button btn_delete">삭제</button>
				</div>
			
			</td>
		</tr>
		<tr>
			<th class="text-center table-th">내용</th>
			<td>
				<form:textarea path="chaContent" rows="10" name="chaContent" class="form-control input-sm" />
				<form:errors path="chaContent" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="button-left">
					<a href="../classhomeworks/classhomeworksList.edu" class="btn btn-default btn-sm"> <span
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
</body>
<script type="text/javascript">

	//파일 추가 버튼 클릭 이벤트
	$('#id_btn_new_file').click(function(){
		$('.file_area').append('<div class="form-inline">'
			+ '<input type="file" name="chaFiles" class="form-control">'
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