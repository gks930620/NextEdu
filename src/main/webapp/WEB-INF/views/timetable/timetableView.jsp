<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>Insert title here</title>

<style type="text/css">
.timetable tr > th , .timetable tr > td {
	width: 100px;
	height: 100px;
	text-align: center;
	font-size: 2em;
}
</style>

</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<table class="table table-primary table-bordered timetable">
		<tr>
			<th class="text-center table-th"></th>
			<th class="text-center table-th">월</th>
			<th class="text-center table-th">화</th>
			<th class="text-center table-th">수</th>
			<th class="text-center table-th">목</th>
			<th class="text-center table-th">금</th>
		</tr>
		<tr>
			<th class="text-center table-th">1교시</th>
			<td>수학</td>
			<td>수학</td>
			<td>수학</td>
			<td>수학</td>
			<td>국어</td>
		</tr>
		<tr>
			<th class="text-center table-th">2교시</th>
			<td>국어</td>
			<td>국어</td>
			<td>미술</td>
			<td>과학</td>
			<td>국어</td>
		</tr>
		<tr>
			<th class="text-center table-th">3교시</th>
			<td>사회</td>
			<td>과학</td>
			<td>미술</td>
			<td>음악</td>
			<td>도덕</td>
		</tr>
		<tr>
			<th class="text-center table-th">4교시</th>
			<td>영어</td>
			<td>음악</td>
			<td>영어</td>
			<td>국어</td>
			<td>창체</td>
		</tr>
		<tr>
			<th class="text-center table-th">5교시</th>
			<td>체육</td>
			<td>체육</td>
			<td>국어</td>
			<td>사회</td>
			<td>과학</td>
		</tr>
	</table>
</div> <!-- container -->
</body>
</html>