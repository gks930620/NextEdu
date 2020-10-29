<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>우리반 1:1문의 상세 보기</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3>
			우리반 1:1문의 - <small>상세 보기</small>
		</h3>
		<form action="classanswerInsertMessage.edu" METHOD="POST">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>글번호</th>
						<td>${classquestionVO.cqNo}</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td>${classquestionVO.cqTitle}</td>
					</tr>
					<tr>
						<th>글분류</th>
						<td>${classquestionVO.cqCategory}</td>
					</tr>
					<tr>
						<th>작성자명</th>
						<td>${classquestionVO.cqMemId}</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><pre>${classquestionVO.cqContent}</pre></td>
					</tr>
					<tr>
						<th>등록일자</th>
						<td>${classquestionVO.cqRegDate}</td>
					</tr>

					<c:if test="${classquestionVO.cqAnswerYn == 'Y'}">
						<tr>
							<th>답글</th>
							<td>${classquestionVO.caContent}</td>
						</tr>
					</c:if>
					
					<c:if test="${classquestionVO.cqAnswerYn == 'N'}">
						<mytag:sec hasRole="TEACHER">
							<tr>
								<th>답글</th>
								<td><input type="hidden" name="cqNo"
									value="${classquestionVO.cqNo}"> <textarea rows="10"
										name="caContent" class="form-control input-sm">${classquestionVO.caContent}</textarea></td>
							</tr>
						</mytag:sec>
					</c:if>

					<tr>
						<td colspan="3">
							<div class="pull-left">
								<a href="classquestionList.edu" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;&nbsp;목록
								</a>
							</div>
							<mytag:sec hasRole="STUDENT">
							<div class="pull-right">
								<a href="classquestionUpdate.edu?cqNo=${classquestionVO.cqNo}"
									class="btn btn-success btn-sm"> <span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									&nbsp;&nbsp;수정
								</a>
							</div>
							</mytag:sec>
							<div class="pull-right">
								<mytag:sec hasRole="TEACHER">
									<button type="submit" class="btn btn-success btn-sm">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										&nbsp;&nbsp;답글 저장
									</button>
								</mytag:sec>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- 끝 - 본문 영역 -->


</body>
</html>