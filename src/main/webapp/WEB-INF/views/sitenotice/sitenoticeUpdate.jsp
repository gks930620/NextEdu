<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitenoticeUpdate.jsp</title>
<!-- 작성자 : 김아름
	작성일자 : 2020.09.23 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<h3 class="h3-text">공지사항 수정</h3>	
	<form:form action="sitenoticeUpdateMessage.edu" modelAttribute="sitenoticeVO">
		<form:hidden path="snNo"/>
	<!-- START : 공지사항 테이블 -->
	<table class="table table-primary table-bordered">
		<tbody>
			<tr>
				<th class="text-center table-th">글번호</th>
				<td>${sitenoticeVO.snNo}
				
					
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">중요도 분류</th>
				<td>
					<form:select path="snTopYn" cssClass="form-control input-sm">
						<option value="" >공지 구분을 선택하세요</option>
						<form:option value="일반공지">일반공지</form:option>
	   					<form:option value="중요공지">중요공지</form:option>
					</form:select>
					<form:errors path="snTopYn"/>
				</td>
			</tr>
			<tr>
				<th class="text-center table-th">제목</th>
				<td>
				<form:input path="snTitle" cssClass="form-control input-sm"/>
				<form:errors path="snTitle" />
				</td>
			</tr>	
			<tr>
				<th class="text-center table-th">등록일자</th>
				<td>${sitenoticeVO.snRegDate}</td>
			</tr>
			<tr>
				<th class="text-center table-th">내용</th>
				<td>
				
				<textarea rows="10" name="snContent" class="form-control input-sm">${sitenoticeVO.snContent}</textarea>
				<form:errors path="snContent" />	
				</td>
			</tr>
			<tr>
				<td colspan="2">
		          <div class="button-left">
		              <a href="sitenoticeList.edu" class="btn btn-default btn-sm"> 
		                <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		                &nbsp;&nbsp;목록
		              </a>
		            </div>
		            <div class="button-right">
		              <button type="submit" class="btn btn-sm btn-default" > 
		                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
		                &nbsp;&nbsp;저장
		              </button>
		             </div>
				</td>
			</tr>
		</tbody>	
	</table>
	</form:form>
	<!-- END : 공지사항 테이블 -->
	
</div>
</body>
</html>