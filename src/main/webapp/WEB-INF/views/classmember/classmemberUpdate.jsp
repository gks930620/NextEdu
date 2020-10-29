<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>우리반 학생 - ${classmemberVO.memName} 수정</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp" %>
 <div class="mainContainer" >	
	<h3>학생 정보 수정 </h3>		
	<form:form action="classmemberUpdate.edu" modelAttribute="classmemberVO">
		<form:hidden path="memId"/>
	<table class="table table-striped table-bordered">
		<tbody>
			<tr>
				<th>회원명</th>
				<td> <form:input path="memName" cssClass="form-control input-sm"/>
					<form:errors path="memName"/>      
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><form:input path="memZip" cssClass="form-control input-sm"/>
					<form:errors path="memZip"/>      
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><form:input path="memAdd1" cssClass="form-control input-sm"/>
					<form:errors path="memAdd1"/> 
					<form:input path="memAdd2" cssClass="form-control input-sm"/>
					<form:errors path="memAdd2"/> 
				</td>
			</tr>			
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir" class="form-control input-sm" value="${classmemberVO.memBir}"></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><form:input path="memMail" cssClass="form-control input-sm"/>
					<form:errors path="memMail"/> 
				</td>
			</tr>
			<tr>
				<th>핸드폰</th>
				<td><input type="tel" name="memHp" class="form-control input-sm" value="${classmemberVO.memHp}"></td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="classmemberList.edu" class="btn btn-info btn-sm" >
						<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
						&nbsp; 목록 
					</a>					
					<button type="submit" class="btn btn-primary" formaction="classmemberUpdateMessage.edu">
					  <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						&nbsp; 저장  
					</button>
				</td>
			</tr>
		</tbody>	
	</table>	
	</form:form>
</div>

</body>
</html>