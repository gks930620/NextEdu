<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>제출 과제 상세 보기</title>
<!-- 작성자 : 김아름
	작성일자 : 2020.09.28 ~ -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="mainContainer">
	<!-- START : 교사 화면 -->
	<mytag:sec hasRole="TEACHER">
	<h3 class="h3-text"> 학생 과제 - <small>상세 보기</small></h3>
	<table class="table table-primary table-bordered">
		<tbody>
			<tr>
				<td colspan="2" class="text-center table-th">
					${classhomeworksanswerVO.chaTitle}
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">작성자명</th>
				<td>${classhomeworksanswerVO.chaMemName}</td>
			</tr>
			<tr>
				<th class="text-center table-th">등록일</th>
				<td>${classhomeworksanswerVO.chaRegDate}</td>
			</tr>
			<tr>
				<th class="text-center table-th" width="20%">첨부파일</th>
				<td>
					<c:forEach var="f" items="${classhomeworksanswerVO.attaches}" varStatus="st">
						<div>
							# 파일 ${st.count}
								<a href="<c:url value='/attach/download/${f.atchNo}' />" target="_blank">
									<u style="color: blue"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									${f.atchOriginalName}
									</u>
								</a>
							&nbsp;&nbsp;|&nbsp;&nbsp; Size : ${f.atchFancySize}
							&nbsp;&nbsp;|&nbsp;&nbsp; Down : ${f.atchDownHit}
						</div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">내용</th>
				<td><pre>${classhomeworksanswerVO.chaContent}</pre></td>
			</tr>
			<tr>
				<td colspan="2" style="background-color: white;">
					<div class="button-left">
						<a href="javascript:history.back(-1)" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;&nbsp;목록
						</a>
					</div>
					<div class="button-right">
					<form:form action="classhomeworksScoreUpdate.edu"
							   modelAttribute="classhomeworksanswerVO" method="post"
							   enctype="multipart/form-data">
					<form:input path="chaScore" id="score" cssClass="form-control input-score"/>
					<form:hidden path="chaNo"/>
					<form:hidden path="chaChNo"/>
						<button type="submit" class="btn btn-sm btn-default">
						<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
						&nbsp;&nbsp;점수주기
						</button>
					</form:form>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	</mytag:sec>
	<!-- END : 교사 화면 -->
	
	<!-- START : 학생 화면 -->
	<mytag:sec hasRole="STUDENT">
	<h3 class="h3-text"> 내 과제 - <small>상세 보기</small></h3>
	<!--  과제 상세보기 화면 -->
	<table class="table table-primary table-bordered">
		<tbody>
			<tr>
				<td colspan="2" class="text-center table-th">
					${classhomeworksanswerVO.chaTitle}
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">작성자명</th>
				<td>${classhomeworksanswerVO.chaMemId}</td>
			</tr>
			<tr>
				<th class="text-center table-th">등록일</th>
				<td>${classhomeworksanswerVO.chaRegDate}</td>
			</tr>
			<tr>
				<th class="text-center table-th">첨부파일</th>
				<td>
					<c:forEach var="f" items="${classhomeworksanswerVO.attaches}" varStatus="st">
						<div>
							# 파일 ${st.count}
								<a href="<c:url value='/attach/download/${f.atchNo}' />" target="_blank">
									<u style="color: blue"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									${f.atchOriginalName}
									</u>
								</a>
							&nbsp;&nbsp;|&nbsp;&nbsp; Size : ${f.atchFancySize}
							&nbsp;&nbsp;|&nbsp;&nbsp; Down : ${f.atchDownHit}
						</div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">내용</th>
				<td><pre>${classhomeworksanswerVO.chaContent}</pre></td>
			</tr>
			<tr>
				<td colspan="2" style="background-color: white;">
					<form:form action="classhomeworksanswerDelete.edu" modelAttribute="classhomeworksanswerVO">
					<form:hidden path="chaNo"/>
					<div class="button-left">
						<a href="../classhomeworks/classhomeworksList.edu" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;&nbsp;목록
						</a>
					</div>
					<div class="button-right">
						<a href="classhomeworksanswerUpdate.edu?chaNo=${classhomeworksanswerVO.chaNo}" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							&nbsp;&nbsp;수정
						</a>
							<button type="submit" class="btn btn-sm btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							&nbsp;&nbsp;삭제
							</button>
					</div>
					</form:form>
				</td>
			</tr>
		</tbody>
	</table>
	</mytag:sec>
	<!-- END : 학생 화면 -->
</div>
<!-- 끝 - 본문 영역 -->
<script type="text/javascript">
$(document).on("keyup", "input[id^=score]", function() {
    var val= $(this).val();

    if(val.replace(/[0-9]/g, "").length > 0) {
        alert("숫자만 입력해 주십시오.");
        $(this).val('');
    }

    if(val < 0 || val > 100) {
        alert("0~100 범위로 입력해 주십시오.");
        $(this).val('');
    }
});
</script>
</body>
</html>