<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%	request.setCharacterEncoding("UTF-8");%>
	
	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채팅 서비스</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
      <meta charset="utf-8">
    <meta name="description" content="WebRTC code samples">
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1, maximum-scale=1">
    <meta itemprop="description" content="Client-side WebRTC code samples">
    <meta itemprop="image" content="../../../images/webrtc-icon-192x192.png">
    <meta itemprop="name" content="WebRTC code samples">
    <meta name="mobile-web-app-capable" content="yes">
    <meta id="theme-color" name="theme-color" content="#ffffff">

    <base target="_blank">

<%@include file="/WEB-INF/inc/header.jsp"%>
    <title>Multiple peer connections</title>

    <link rel="icon" sizes="192x192" href="../../../images/webrtc-icon-192x192.png">
    <link href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet" type="text/css">

<style type="text/css">
	.lecture{
	position:absolute;
	left:500px;
	top:0px;
	width: 1200px;
	height:800px;
	}


</style>    
</head>

<body>

<!-- Preloader Start -->
	<div id="preloader-active">
		<div
			class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
				<div class="preloader-circle"></div>
				<div class="preloader-img pere-text">
					<img
						src="<%=request.getContextPath()%>/resources/img/logo/logo.png">
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/inc/top.jsp"%>


<div class="mainContainer">
<!--  <input type="text" id="nickname" class="form-inline" placeholder="닉네임을 입력해주세요" required autofocus>-->
<!--  <button class = "btn btn-primary" id = "name">확인</button>-->
<label for="roomId" class="label label-default" hidden>방 번호</label>
<label  id="roomId" class="form-inline" hidden>${room.roomId}</label>

<br>
<h1><label for="roomName" class="label label-default">방 이름</label></h1>

<h1><label id="roomName" class="form-inline">${room.name}</label></h1>
<div id = "chatroom" style = "width:400px; height: 400px; border:1px solid; background-color : white; overflow: auto;"></div>
<h1><input type = "text" id = "message" style = "height : 30px; width : 340px;" placeholder="내용을 입력하세요" autofocus onkeydown="fn_sendMessage()">
<button class = "small-button" id = "send"  >전송</button></h1>
<br>
  <button type="button" class = "btn btn-primary" id = "btnclose" onclick="disconnect()">퇴장하기</button>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

    var webSocket;
    var nickname;

    var roomId = "${room.roomId}";
    nickname='${memberVO.memName}';
    connect();
   // document.getElementById("name").addEventListener("click",function(){
      	//nickname = document.getElementById("nickname").value;
        //document.getElementById("nickname").style.display = "none";
        //document.getElementById("name").style.display = "none";
   // });
    
    document.getElementById("send").addEventListener("click",function(){
        send();
    })//
    
    function fn_sendMessage() {
		if(event.keyCode==13){
			send();
		}
	}
  
    
    
    function connect(){
       webSocket = new WebSocket("wss://<%=request.getServerName()%>:8443<%=request.getContextPath()%>/chat");
	
      // request.getServerPort()
        
        webSocket.onopen = onOpen;
        webSocket.onclose = onClose;
        webSocket.onmessage = onMessage;
    }
    
    function disconnect(){
    	if('${memberVO.memRole}'=='TEACHER'){
    		//방폭파하고싶은데...
    	}
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'LEAVE',writer:nickname}));
        webSocket.close();
        history.go(-1);
        
        
    }
    function send(){
        msg = document.getElementById("message").value;
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'CHAT',writer:nickname,message : msg}));
        document.getElementById("message").value = "";
    }
    function onOpen(){
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'ENTER',writer:nickname}));
    }
    function onMessage(e){
        data = e.data;
        chatroom = document.getElementById("chatroom");
        chatroom.innerHTML = chatroom.innerHTML + "<br><h2>" + data+"</h2>";
        $('#chatroom').scrollTop($('#chatroom')[0].scrollHeight+20);
    }
    function onClose(){
        disconnect();
    }

</script>




<!-- 화상 -->
<!-- 화상 -->
<!-- 화상 -->
<!-- 화상 -->
<!-- 화상 -->
<!-- 화상 -->


 <script src="https://rtcmulticonnection.herokuapp.com/demos/menu.js"></script>

  <section class="make-center lecture">
  <h1>
	<button type="button" onclick="location.href='<%=request.getContextPath()%>/chat/roomRTC.edu?roomId=${room.roomId}' ">토론수업</button>
	</h1>
    <h1><input type="text" id="room-id" value="${room.roomId}" autocorrect=off autocapitalize=off size=20 style="disply:none" readonly="readonly">
    
    <mytag:sec hasRole="TEACHER">
   	 <button id="open-room">Open Room</button></h1>
    	<h1><button id="join-room" hidden>Join Room</button></h1>
  </mytag:sec>
        <mytag:sec hasRole="STUDENT">
    	<button id="join-room" >Join Room</button></h1>
   	<h1> <button id="open-room" hidden>Open Room</button></h1>
  </mytag:sec>
	
    <button id="open-or-join-room" hidden>Auto Open Or Join Room</button>
    <div id="videos-container" style="width:1000px; height:1000px"></div>
    <div id="room-urls" style="text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>
  </section>

<script src="https://rtcmulticonnection.herokuapp.com/dist/RTCMultiConnection.js"></script>
<script src="https://rtcmulticonnection.herokuapp.com/node_modules/webrtc-adapter/out/adapter.js"></script>
<script src="https://rtcmulticonnection.herokuapp.com/socket.io/socket.io.js"></script>

<!-- custom layout for HTML5 audio/video elements -->
<link rel="stylesheet" href="https://rtcmulticonnection.herokuapp.com/dev/getHTMLMediaElement.css">
<script src="https://rtcmulticonnection.herokuapp.com/dev/getHTMLMediaElement.js"></script>

<script>
// ......................................................
// .......................UI Code........................
// ......................................................
document.getElementById('open-room').onclick = function() {
    disableInputButtons();
    connection.open(document.getElementById('room-id').value, function() {
        showRoomURL(connection.sessionid);
    });
};

document.getElementById('join-room').onclick = function() {
    disableInputButtons();

    connection.sdpConstraints.mandatory = {
        OfferToReceiveAudio: true,
        OfferToReceiveVideo: true
    };
    connection.join(document.getElementById('room-id').value);
};

document.getElementById('open-or-join-room').onclick = function() {
    disableInputButtons();
    connection.openOrJoin(document.getElementById('room-id').value, function(isRoomExist, roomid) {
        if (isRoomExist === false && connection.isInitiator === true) {
            // if room doesn't exist, it means that current user will create the room
            showRoomURL(roomid);
        }

        if(isRoomExist) {
          connection.sdpConstraints.mandatory = {
              OfferToReceiveAudio: true,
              OfferToReceiveVideo: true
          };
        }
    });
};

// ......................................................
// ..................RTCMultiConnection Code.............
// ......................................................

var connection = new RTCMultiConnection();

// by default, socket.io server is assumed to be deployed on your own URL
connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

// comment-out below line if you do not have your own socket.io server
// connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

connection.socketMessageEvent = 'video-broadcast-demo';

connection.session = {
    audio: true,
    video: true,
    oneway: true
};

connection.sdpConstraints.mandatory = {
    OfferToReceiveAudio: false,
    OfferToReceiveVideo: false
};

// https://www.rtcmulticonnection.org/docs/iceServers/
// use your own TURN-server here!
connection.iceServers = [{
    'urls': [
        'stun:stun.l.google.com:19302',
        'stun:stun1.l.google.com:19302',
        'stun:stun2.l.google.com:19302',
        'stun:stun.l.google.com:19302?transport=udp',
    ]
}];

connection.videosContainer = document.getElementById('videos-container');
connection.onstream = function(event) {
    var existing = document.getElementById(event.streamid);
    if(existing && existing.parentNode) {
      existing.parentNode.removeChild(existing);
    }

    event.mediaElement.removeAttribute('src');
    event.mediaElement.removeAttribute('srcObject');
    event.mediaElement.muted = true;
    event.mediaElement.volume = 0;

    var video = document.createElement('video');

    try {
        video.setAttributeNode(document.createAttribute('autoplay'));
        video.setAttributeNode(document.createAttribute('playsinline'));
    } catch (e) {
        video.setAttribute('autoplay', true);
        video.setAttribute('playsinline', true);
    }

    if(event.type === 'local') {
      video.volume = 0;
      try {
          video.setAttributeNode(document.createAttribute('muted'));
      } catch (e) {
          video.setAttribute('muted', true);
      }
    }
    video.srcObject = event.stream;

    
    //화면크기조정
    var width = parseInt(connection.videosContainer.clientWidth / 3) - 20;
    var mediaElement = getHTMLMediaElement(video, {
        title: event.userid,
        buttons: ['full-screen'],
        width: 800,
        height:300,
        showOnMouseEnter: false
    });

    connection.videosContainer.appendChild(mediaElement);

    setTimeout(function() {
        mediaElement.media.play();
    }, 5000);

    mediaElement.id = event.streamid;
};

connection.onstreamended = function(event) {
    var mediaElement = document.getElementById(event.streamid);
    if (mediaElement) {
        mediaElement.parentNode.removeChild(mediaElement);

        if(event.userid === connection.sessionid && !connection.isInitiator) {
          alert('Broadcast is ended. We will reload this page to clear the cache.');
          location.reload();
        }
    }
};

connection.onMediaError = function(e) {
    if (e.message === 'Concurrent mic process limit.') {
        if (DetectRTC.audioInputDevices.length <= 1) {
            alert('Please select external microphone. Check github issue number 483.');
            return;
        }

        var secondaryMic = DetectRTC.audioInputDevices[1].deviceId;
        connection.mediaConstraints.audio = {
            deviceId: secondaryMic
        };

        connection.join(connection.sessionid);
    }
};

// ..................................
// ALL below scripts are redundant!!!
// ..................................

function disableInputButtons() {
    document.getElementById('room-id').onkeyup();

    document.getElementById('open-or-join-room').disabled = true;
    document.getElementById('open-room').disabled = true;
    document.getElementById('join-room').disabled = true;
    document.getElementById('room-id').disabled = true;
}

// ......................................................
// ......................Handling Room-ID................
// ......................................................

function showRoomURL(roomid) {
    var roomHashURL = '#' + roomid;
    var roomQueryStringURL = '?roomid=' + roomid;

    var html = '<h2>Unique URL for your room:</h2><br>';

    html += 'Hash URL: <a href="' + roomHashURL + '" target="_blank">' + roomHashURL + '</a>';
    html += '<br>';
    html += 'QueryString URL: <a href="' + roomQueryStringURL + '" target="_blank">' + roomQueryStringURL + '</a>';

    var roomURLsDiv = document.getElementById('room-urls');
    roomURLsDiv.innerHTML = html;

    roomURLsDiv.style.display = 'block';
}

(function() {
    var params = {},
        r = /([^&=]+)=?([^&]*)/g;

    function d(s) {
        return decodeURIComponent(s.replace(/\+/g, ' '));
    }
    var match, search = window.location.search;
    while (match = r.exec(search.substring(1)))
        params[d(match[1])] = d(match[2]);
    window.params = params;
})();

//var roomid = '';
//if (localStorage.getItem(connection.socketMessageEvent)) {
//    roomid = localStorage.getItem(connection.socketMessageEvent);
//} else {
//    roomid = connection.token();    
//}
//document.getElementById('room-id').value = roomid;
document.getElementById('room-id').onkeyup = function() {
    localStorage.setItem(connection.socketMessageEvent, document.getElementById('room-id').value);
};

var hashString = location.hash.replace('#', '');
if (hashString.length && hashString.indexOf('comment-') == 0) {
    hashString = '';
}

var roomid = params.roomid;
if (!roomid && hashString.length) {
    roomid = hashString;
}

if (roomid && roomid.length) {
    document.getElementById('room-id').value = roomid;
    localStorage.setItem(connection.socketMessageEvent, roomid);

    // auto-join-room
    (function reCheckRoomPresence() {
        connection.checkPresence(roomid, function(isRoomExist) {
            if (isRoomExist) {
                connection.join(roomid);
                return;
            }

            setTimeout(reCheckRoomPresence, 5000);
        });
    })();

    disableInputButtons();
}

// detect 2G
if(navigator.connection &&
   navigator.connection.type === 'cellular' &&
   navigator.connection.downlinkMax <= 0.115) {
  alert('2G is not supported. Please use a better internet service.');
}
</script>

  <footer>
    <small id="send-message"></small>
  </footer>

  <script src="https://www.webrtc-experiment.com/common.js"></script>


</div>
</body>
</html>