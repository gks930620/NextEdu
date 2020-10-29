<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitequestionView.jsp</title>

</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3>1:1 문의사항 - <small>상세보기</small></h3>
		
		<!-- START : 1:1 문의사항 테이블 -->
		<form name="frm_answer"	action="<c:url value='/siteanswer/siteanswerInsert.edu' />"	method="post">				
			<input type="hidden" name="saSqNo" value="${sitequestionVO.sqNo}">				

			<table class="table table-primary table-bordered">
				<tbody>
					<tr>
						<th class="text-center table-th" width="20%">글번호</th>
						<td>${sitequestionVO.sqNo }</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">분류</th>
						<td>${sitequestionVO.sqCategory }</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">제목</th>
						<td>${sitequestionVO.sqTitle }</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">작성자</th>
						<td>${sitequestionVO.sqWriter }</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">등록일자</th>
						<td>${sitequestionVO.sqRegDate }</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">첨부파일</th>
						<td>
							<c:forEach var="f" items="${sitequestionVO.attaches}" varStatus="st">
								<div>
									# 파일 ${st.count}
										<a href="<c:url value='/attach/download/${f.atchNo}' />" target="_blank">
											<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
											${f.atchOriginalName}
										</a>
									Size : ${f.atchFancySize}
									Down : ${f.atchDownHit}
								</div>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">질문내용</th>
						<td><pre>${sitequestionVO.sqContent }</pre>
							<%-- <textarea rows="10" class="form-control input-sm" >${sitequestionVO.sqContent }</textarea> --%>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="button-left">
								<a href="sitequestionList.edu" class="btn btn-success btn-sm">
									<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;&nbsp;목록으로
								</a>
							</div>
							<div class="button-right">
							<c:if test="${sitequestionVO.sqMemId eq sessionScope.USER_INFO.userId }">
								<a href="sitequestionUpdate.edu?sqNo=${sitequestionVO.sqNo}" class="btn btn-success btn-sm">
									<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;&nbsp;수정하기
								</a>
							</c:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<table class="table table-primary table-bordered">
				<tbody>
					<tr>
						<th class="text-center table-th" width="20%">답변자</th>
						<td>관리자</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">답변내용</th>
						<td><textarea name="saContent" rows="3" class="form-control input-sm">${sitequestionVO.siteanswerVO.saContent} </textarea>
						<form:errors path="siteanswerVO.saContent" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th" width="20%">답변일자</th>
						<td>${sitequestionVO.siteanswerVO.saRegDate }</td>
					</tr>
					<tr>
						<mytag:sec hasRole="MANAGER">
							<td colspan="2">
								<div class="button-right">
									<button type="submit" class="btn btn-success btn-sm">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										&nbsp;&nbsp;저장
									</button>
								</div>
								
								
							</td>
						</mytag:sec>
					</tr>
				</tbody>
			</table>
		</form>
		<!-- END : 1:1 문의사항 테이블 -->
	</div>
</body>
</html>