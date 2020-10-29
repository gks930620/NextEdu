<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
.class-home-container {
	position: relative;
	/* border: 1px solid red; */
	width: 900px;
	height: 200px;
	left: 20%;
}

.class-home {
	position: relative;
	display: inline-block;
	/* border: 1px solid black; */
	margin-top: 0px; width : 200px;
	height: 200px;
	width: 200px;
	text-align: center;
	color: green;
}
</style>

<%@include file="/WEB-INF/inc/header.jsp"%>
<title>NextEdu</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<div class="class-home-container">
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/chat/roomView.edu?roomId=${roomId}"> <img
					src="<%=request.getContextPath()%>/resources/img/classhome/실시간수업.png">
					<br>실시간수업
				</a>
			</div>
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/classnotice/classnoticeList.edu">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/학급공지.png">
					<br>학급공지
				</a>
			</div>
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/classreferenceroom/classreferenceroomList.edu">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/학습자료실.png">
					<br>학습자료실
				</a>
			</div>
			<div class="class-home">
				<a href="#">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/학사일정.png">
					<br>학사일정
				</a>
			</div>
		</div>
		<div class="class-home-container">
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/classmember/classmemberList.edu">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/학생리스트.png">
					<br>학생리스트
				</a>
			</div>
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/classquestion/classquestionList.edu">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/1대1문의.png">
					<br>1:1문의
				</a>
			</div>
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/classhomeworks/classhomeworksList.edu">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/과제.png">
					<br>과제
				</a>
			</div>
			<div class="class-home">
				<a href="<%=request.getContextPath()%>/timetable/timetableView.edu">
					<img
					src="<%=request.getContextPath()%>/resources/img/classhome/시간표.png">
					<br>시간표
				</a>
			</div>
		</div>
		
		<script type="text/javascript">
		
		
		</script>
</body>
</html>