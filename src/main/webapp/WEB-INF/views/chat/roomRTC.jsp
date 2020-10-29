	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
  <head>
  <%@include file="/WEB-INF/inc/header.jsp"%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta name="apple-mobile-web-app-capable" content="yes">
  </head>
  
 <body>
 








<hr><input type="text" id="roomId"><button id="joinConference">토론수업방들어가기</button><hr>
<br><br><br><br><br><br><br><br>
<div id="roomIdInfo">   </div>


</div>
<!--  <script src="https://rtcmulticonnection.herokuapp.com/dist/RTCMultiConnection.min.js"></script>-->
 <script src="https://rtcmulticonnection.herokuapp.com/dist/RTCMultiConnection.js"></script>
<script src="https://rtcmulticonnection.herokuapp.com/socket.io/socket.io.js"></script>


<script>
var connection = new RTCMultiConnection();

// this line is VERY_important
connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

// if you want audio+video conferencing
connection.session = {
    audio: true,
    video: true
};


document.getElementById("joinConference").onclick=function(){
	this.style="display:none";
	var roomId=document.getElementById('roomId');
	var roomIdInfo=document.getElementById('roomIdInfo');
	
	
	roomIdInfo.innerHTML="방이름 : "+roomId.value;

	
	roomId.style="display:none";
connection.openOrJoin(roomId.value);
	
}


</script>

	
</body>
</html>


