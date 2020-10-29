<%@page import="com.next.common.util.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/ login.jsp</title>
<%@include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%
    String id = "";
    String checked ="";
    
	String msg = request.getParameter("msg");
	/*브라우저 쿠기 긁어와서 객체에 저장*/
	CookieUtils cookieUtils = new CookieUtils(request);
	
	if(cookieUtils.getValue("SAVE_ID")!=null){
		id = cookieUtils.getValue("SAVE_ID");
		checked = "checked='checked'";
	}
%>
<%@include file="/WEB-INF/inc/top.jsp" %>
	<div class="mainContainer">
		<div class="loginView">
		<form action="login.edu" method="post" class="loginForm">
			
			<%
				if(msg != null && !msg.isEmpty()){
					%>
					<div>
						<p><%=msg%></p>
					</div>
					<%
				}
			%>
			<table class="table table-primary table-bordered">
				<tbody>
					<tr>
						<td colspan="2">
							<h3 class="h3-text">로그인</h3>
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">아이디</th>
						<td><input type="text" name="userId"
							class="form-control input-sm" value="<%=id%>"></td>
					</tr>
					<tr>
						<th class="text-center table-th">비밀번호</th>
						<td><input type="password" name="userPass"
							class="form-control input-sm"></td>
					</tr>
					<tr>
						<td colspan="2"><label>
						<input cl type="checkbox"
								name="rememberMe" value="Y" <%=checked%>> ID 기억하기</label>
							<button type="submit" class="btn btn-primary button-right">로그인</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		</div>
	</div>
</body>
</html>