<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="header-area">
	<div class="main-header ">
		<div class="header-mid gray-bg">
			<div class="container">
				<div class="row d-flex align-items-center">
					<!-- Logo -->
					<div class="col-xl-5 col-lg-5 col-md-5 d-none d-md-block">
						<div class="logo">
							<a href="<%=request.getContextPath()%>"><img
								src="<%=request.getContextPath()%>/resources/img/logo/logo.png"
								width=60% height=60% alt=""></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="header-bottom header-sticky">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-xl-8 col-lg-8 col-md-12 header-flex">
						<!-- sticky -->
						<div class="sticky-logo">
							<a href="<%=request.getContextPath()%>"><img
								src="<%=request.getContextPath()%>/resources/img/logo/logo.png"
								alt=""></a>
						</div>
						<!-- Main-menu -->
						<div class="main-menu d-none d-md-block">
							<nav>
								<ul id="navigation">
									<li><a href="<%=request.getContextPath()%>"><span
											class="mainText">Home</span></a></li>
									<li><a
										href="<%=request.getContextPath()%>/classhome/classhome.edu"><span
											class="mainText">우리반</span></a>
										<ul class="submenu">
											<li><a
												href="<%=request.getContextPath()%>/classhome/classhome.edu"></a></li>
											<li><a
												href="<%=request.getContextPath()%>/classreferenceroom/classreferenceroomList.edu">학습자료실</a></li>
											<li><a
												href="<%=request.getContextPath()%>/classhomeworks/classhomeworksList.edu">과제</a></li>
											<li><a
												href="<%=request.getContextPath()%>/classnotice/classnoticeList.edu">학급
													공지사항</a></li>
											<li><a
												href="<%=request.getContextPath()%>/classquestion/classquestionList.edu">1:1
													문의</a></li>
											<li><a
												href="<%=request.getContextPath()%>/classmember/classmemberList.edu">우리반
													친구들</a></li>
										</ul></li>
									<li><a href=""><span class="mainText">이용안내</span></a>
										<ul class="submenu">
											<li><a
												href="<%=request.getContextPath()%>/sitenotice/sitenoticeList.edu">공지사항</a></li>
											<li><a
												href="<%=request.getContextPath()%>/sitequestion/sitequestionList.edu">1:1
													문의</a></li>
										</ul></li>
									<li><a href="#"><span class="mainText">마이페이지</span></a>
										<ul class="submenu">
											<c:choose>
												<c:when test="${empty USER_INFO}">
													<li><a
														href="<%=request.getContextPath()%>/join/joinInsert.edu">회원가입</a></li>
												</c:when>
												<c:otherwise>
													<li><a
														href="<%=request.getContextPath()%>/mypage/mypageUpdate.edu">개인정보수정</a></li>
													<li><a
														href="<%=request.getContextPath()%>/login/logout.edu">로그아웃</a></li>
												</c:otherwise>
											</c:choose>
										</ul></li>
							</nav>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-4">
						<div class="header-right f-right d-none d-lg-block">
							<!-- Heder social -->
							<ul class="header-social">
								<li><a href="https://www.fb.com/sai4ull"><i
										class="fab fa-facebook-f"></i></a></li>
								<li><a href="#"><i class="fab fa-twitter"></i></a></li>
								<li><a href="#"><i class="fab fa-instagram"></i></a></li>
								<li><a href="#"><i class="fab fa-youtube"></i></a></li>
							</ul>
							<!-- Search Nav -->
							<div class="nav-search search-switch">
								<i class="fa fa-search"></i>
							</div>
						</div>
					</div>
					<!-- Mobile Menu -->
					<div class="col-12">
						<div class="mobile_menu d-block d-md-none"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Header End -->

<!-- JS here -->