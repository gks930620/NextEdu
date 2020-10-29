 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>우리반 자료실 상세 보기</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3 class="h3-text">
			우리반 자료실 - <small>상세 보기</small>
		</h3>
		<table class="table table-primary table-bordered">
			<colgroup>
				<col width="20%" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th class="text-center table-th">글번호</th>
					<td>${classreferenceroomVO.crNo}</td>
				</tr>
				<tr>
					<th class="text-center table-th">글제목</th>
					<td>${classreferenceroomVO.crTitle}</td>
				</tr>
				<tr>
					<th class="text-center table-th">글분류</th>
					<td>${classreferenceroomVO.crCategory}</td>
				</tr>
				<tr>
					<th class="text-center table-th">첨부파일</th>
					
					<td><c:forEach var="f" items="${classreferenceroomVO.attaches}" varStatus="st">
							<div># 파일 ${st.count} <a href="<c:url value='/attach/download/${f.atchNo}' />"
									target="_blank"> <span class="glyphicon glyphicon-save"
									aria-hidden="true"> </span> ${f.atchOriginalName}
								</a> Size : ${f.atchFancySize} Down : ${f.atchDownHit}
							</div>
						</c:forEach></td>
				</tr>
				<tr>
					<th class="text-center table-th">내용</th>
					<td><pre>${classreferenceroomVO.crContent}</pre></td>
				</tr>
				<tr>
					<th class="text-center table-th">등록일자</th>
					<td>${classreferenceroomVO.crRegDate}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="button-left">
							<a href="classreferenceroomList.edu"
								class="btn btn-default btn-sm"> <span
								class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div> <mytag:sec hasRole="TEACHER">
							<div class="button-right">
								<a
									href="classreferenceroomUpdate.edu?crNo=${classreferenceroomVO.crNo}"
									class="btn btn-success btn-sm"> <span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									&nbsp;&nbsp;수정
								</a>
							</div>
						</mytag:sec>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 끝 - 본문 영역 -->

	<!-- 시작 - 댓글 등록 영역 -->
	<!--  끝 - 댓글 등록 영역 -->

	<!-- 시작 - 댓글 목록 영역 -->
	<!--  끝 - 댓글 목록 영역 -->
</body>
</html>