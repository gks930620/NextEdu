<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>우리반 과제 상세 보기</title>
<!-- 작성자 : 김아름
	작성일자 : 2020.09.28 ~ -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="mainContainer">
	<h3 class="h3-text"> 우리반 과제 - <small>상세 보기</small></h3>
	<!-- 교사가 등록한 과제 상세보기 화면 -->
	<form:form action="../classhomeworksanswer/classhomeworksanswerInsert.edu"
			   modelAttribute="classhomeworksVO" method="post"
			   enctype="multipart/form-data">
		<form:hidden path="chNo"/>
		<form:hidden path="chTitle"/>
		<form:hidden path="chCategory"/>
		<form:hidden path="chDeadline"/>
		<form:hidden path="chMemId"/>
	
	<table class="table table-primary table-bordered">
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th class="text-center table-th">글번호</th>
				<td>${classhomeworksVO.chNo}</td>
			</tr>
			<tr>
				<th class="text-center table-th">과목명</th>
				<td>${classhomeworksVO.chCategory}</td>
			</tr>
			<tr>
				<th class="text-center table-th">과제 제목</th>
				<td>${classhomeworksVO.chTitle}</td>
			</tr>
			<tr>
				<th class="text-center table-th">작성자명</th>
				<td>${classhomeworksVO.chMemName}</td>
			</tr>
			<tr>
				<th class="text-center table-th">첨부파일</th>
				<td>
					<c:forEach var="f" items="${classhomeworksVO.attaches}" varStatus="st">
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
				<td><pre>${classhomeworksVO.chContent}</pre></td>
			</tr>
			<tr>
				<th class="text-center table-th">제출기한</th>
				<td>${classhomeworksVO.chDeadline}</td>
			</tr>
			<tr>
				<td colspan="2" style="background-color: white;">
					<div class="button-left">
						<a href="classhomeworksList.edu" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;&nbsp;목록
						</a>
					</div>
					<!-- START : 교사 화면 -->
					<mytag:sec hasRole="TEACHER,MANAGER">
					<div class="button-right">
						<a href="classhomeworksUpdate.edu?chNo=${classhomeworksVO.chNo}" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							&nbsp;&nbsp;수정
						</a>
						<form:form action="classhomeworksDelete.edu" modelAttribute="classhomeworksVO">
						<form:hidden path="chNo"/>
							<button type="submit" class="btn btn-sm btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							&nbsp;&nbsp;삭제
							</button>
						</form:form>
					</div>
					</mytag:sec>
					<!-- END : 교사 화면 -->
					
					<!-- START : 학생 화면 -->
					<mytag:sec hasRole="STUDENT">
					<div class="button-right">
						<button type="submit" class="btn btn-sm btn-default">과제 작성</button>
						<a href="../classhomeworksanswer/classhomeworksanswerViewS.edu?chaChNo=${classhomeworksVO.chNo}" class="btn btn-default btn-sm"> 
							<span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>
								  &nbsp;&nbsp;내 과제 보기
						</a>
					
					<!-- classhomeworks.chNo과 classhomeworksanswer.chaChNo가 같고, chaMemId와 userId가 같다면 내과제보기
						otherwise 과제작성 -->
					<%-- 
					<c:forEach var="classhomeworksanswerVOList" items="${classhomeworksanswerVOList}">
					<c:choose>
						
					<c:when test="${(classhomeworks.chNo == classhomeworksanswerVOList.chaChNo)&&(classhomeworksanswerVOList.chaMemId=='student1')}">
						<a href="../classhomeworksanswer/classhomeworksanswerViewS.edu?chaChNo=${classhomeworksVO.chNo}" class="btn btn-default btn-sm"> 
							<span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>
								  &nbsp;&nbsp;내 과제 보기
						</a>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-sm btn-default">과제 작성</button>
					
					</c:otherwise>
					</c:choose>
					</c:forEach>
					 --%>
					</div>
					</mytag:sec>
					<!-- END : 학생 화면 -->
				</td>
			</tr>
		</tbody>
	</table>
	</form:form>
</div>
<!-- 끝 - 본문 영역 -->

</body>
</html>