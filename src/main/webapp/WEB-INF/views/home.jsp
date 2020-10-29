<%@page import="com.next.common.util.CookieUtils"%>
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
.firstContainer{
	display: inline-block;
	width: 30%;
	height: 500px;
}

.classContainer {
	position: relative;
	vertical-align: top;
	margin-left: 5%;
	height: 230px;
	overflow: hidden;
	border: 1px solid black;
}
.classContainer img{
	position: absolute;
	top:0;
	left: 0;
	width: 100%;
	height: 100%;
}

.youtubeContainer {
	position: relative;
	vertical-align: top;
	margin-left: 5%;
	margin-top: 10px;
	height: 260px;
	overflow: hidden;
	border: 1px solid black;
}


.noticeContainer {
	position: relative;
	vertical-align: top;
	display: inline-block;
	width: 30%;
	height: 500px;
	overflow: hidden;
	border: 1px solid black;
}

.rightContainer {
	position: relative;
	vertical-align: top;
	display: inline-block;
	width: 30%;
	height: 500px;
	overflow: hidden;
	border: 1px solid black;
}

.loginContainer {
	position: relative;
	vertical-align: top;
	height: 210px;
	overflow: hidden;
	border: 1px solid black;
}

.logoutCantainer {
	position: relative;
	vertical-align: top;
	height: 210px;
	text-align: center;
	background-color: #ffe8a1;
	overflow: hidden;
	border: 1px solid black;
}

.bannerContainer {
	position: relative;
	vertical-align: top;
	height: 280px;
	margin-top: 10px;
	overflow: hidden;
	border: 1px solid black;
}

.mypageBox {
	position: relative;
	margin-top: 20px;
	font-size: 1.5em;
	color: #1c7430;
}

.cl_child {
    display: inline-block;
}

#id_parent {
    position: relative;
    width: 700px;
    height: 700px;
                            /* 부모 div보다 자식 div 크기가 더 클 때 .. */
}

#id_row1{
    position: absolute; /* id_parent 의 왼쪽 모서리 기준으로 움직임 */
    width: 2820px;
    height: 700px;
}

#id_row2{
    position: absolute;
    width: 2820px;
    height: 700px;
}

.etc {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
}
</style>

<%@include file="/WEB-INF/inc/header.jsp"%>
<title>NextEdu</title>
</head>
<body>
	<%
		String id = "";
		String checked = "";

		String msg = request.getParameter("msg");
		/*브라우저 쿠기 긁어와서 객체에 저장*/
		CookieUtils cookieUtils = new CookieUtils(request);

		if (cookieUtils.getValue("SAVE_ID") != null) {
			id = cookieUtils.getValue("SAVE_ID");
			checked = "checked='checked'";
		}
	%>
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
		<div class="firstContainer">
			<div class="classContainer">
				<!-- classroom 이미지로 넣어보기 -->
				<a href="<%=request.getContextPath()%>/classhome/classhome.edu">
					<img src="<%=request.getContextPath()%>/resources/img/classroom.png">
				</a>
				<%-- <a href="<%=request.getContextPath()%>/classhome/classhome.edu" class="btn btn-primary"> 
				&nbsp;클래스 홈 입장하기
			</a> --%>
			</div>
			
			<div class="youtubeContainer">
				<iframe width="100%" height="100%" src="https://www.youtube.com/embed/_T6TiXN0_OU"
				 		 frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
				 		 allowfullscreen>
				</iframe>
			</div>
		</div>
		<div class="noticeContainer">
			<a href="<%=request.getContextPath()%>/sitenotice/sitenoticeList.edu">
				<%-- <img src="<%=request.getContextPath()%>/resources/img/5004-512.png" height="30" width="30" title="공지사항 더보기"> --%>
				<c:forEach begin="1" end="54">
					&nbsp;
				</c:forEach>
				더보기</a>
			<table style="TABLE-layout:fixed">
				<colgroup>
					<col width="10%" />
					<col />
					<col width="20%" />
				</colgroup>
				
			<thead>
				<tr>
					<th class="text-center table-th">글번호</th>
					<th class="text-center table-th">제목</th>
					<th class="text-center table-th">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sitenoticeVOList" items="${sitenoticeVOList}">
				<tr class="text-center">
					<td>${sitenoticeVOList.snNo }</td>
					<td class="text-left" >
						<a href="sitenotice/sitenoticeView.edu?snNo=${sitenoticeVOList.snNo}">${sitenoticeVOList.snTitle}</a>
					</td>
					<td>${sitenoticeVOList.snRegDate}</td>
				</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
		<div class="rightContainer">
			<c:choose>
				<c:when test="${empty USER_INFO}">
					<div class="loginContainer">
						<form action="login/login.edu" method="post" class="loginForm">

							<%
								if (msg != null && !msg.isEmpty()) {
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
										<td colspan="2"><label> <input type="checkbox"
												name="rememberMe" value="Y" <%=checked%>> ID 기억하기
										</label>
											<button type="submit" class="btn btn-primary button-right">로그인</button>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="logoutCantainer">
						<p class="mypageBox">
							<span
								style="color: #0077b5; font-style: italic; font-weight: bold;">${USER_INFO.userName}
							</span> 님 환영합니다!
						</p>
						<a href="<%=request.getContextPath()%>/mypage/mypageUpdate.edu"
							class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							&nbsp;&nbsp;개인정보 수정
						</a>
						<div style="height: 5px"></div>
						<a href="<%=request.getContextPath()%>/login/logout.edu"
							class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							&nbsp;&nbsp;로그아웃
						</a>
					</div>
				</c:otherwise>
			</c:choose>
			<!--  로그인화면 -->

			<div class="bannerContainer">
					<div id="id_row1">
						<!-- 여기에 style을 줘야 인식하는데..
                                   	귀찮으니까 함수에서 inline 스타일로 초기화-->
						<div class="cl_child">
							<a href="https://www.cyber1388.kr:447/" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너1.png" height="280">
							</a>
						</div>
						<div class="cl_child">
							<a href="https://www.kyci.or.kr/userSite/index.asp" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너2.png" height="280">
							</a>
						</div>
						<div class="cl_child">
							<a href="https://www.facebook.com/KCPC2019/" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너3.png" height="280">
							</a>
						</div>
						<div class="cl_child">
							<a href="http://stopbullying.re.kr/" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너4.png" height="280">
							</a>
						</div>
					</div>
					<div id="id_row2">
						<!-- 여기에 style을 줘야 인식하는데..
                                                     	귀찮으니까 함수에서 inline 스타일로 초기화-->
						<div class="cl_child">
							<a href="http://buseo.sen.go.kr/web/services/bbs/bbsView.action?bbsBean.bbsCd=94&bbsBean.bbsSeq=7841&ctgCd=209" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너5.png" height="280">
							</a>
						</div>
						<div class="cl_child">
							<a href="http://uri-i.kr/v3/" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너6.png" height="280">
							</a>
						</div>
						<div class="cl_child">
							<a href="http://doran.edunet.net/main/mainForm.do" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너7.jpg" height="280">
							</a>
						</div>
						<div class="cl_child">
							<a href="http://www.chungbuk.go.kr/www/contents.do?key=93156" target="blank">
								<img src="<%=request.getContextPath()%>/resources/img/banner/배너8.png" height="280">
							</a>
						</div>
					</div>
			</div>
		</div>
	</div>
	<!-- container -->
	<script>
        
        var v_row1 = document.getElementById("id_row1");
        var v_row2 = document.getElementById("id_row2");
        var v_mvW = 5;         // 한 번 클릭에 움직일 값
        function f_mv() {
            if(!v_row1.style.left) {        // 초기화되지 않았다면
                v_row1.style.left = "0px";  // 강제로 초기화 해줌
                v_row2.style.left = "2128px"     // id_row1의 길이가 2108px 이므로 그 이후에 붙도록
            }
            v_row1.style.left = parseInt(v_row1.style.left) - v_mvW + "px";
            v_row2.style.left = parseInt(v_row2.style.left) - v_mvW + "px";

            var v_row1Left = parseInt(v_row1.style.left);
            var v_row2Left = parseInt(v_row2.style.left);
            if (v_row1Left <= -2128) {
                v_row1.style.left = "2128px";
            } else if (v_row2Left <= -2128) {
                 v_row2.style.left = "2128px";
            }
            setTimeout(f_mv,100);
        }
        f_mv();
    </script>
</body>
</html>