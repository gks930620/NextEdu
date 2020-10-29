<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>우리반 학생 - ${classmemberVO.memName}</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<h3>회원 상세 정보</h3>
		<table class="table table-striped ">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${classmemberVO.memId}</td>
				</tr>
				<tr>
					<th>회원명</th>
					<td>${classmemberVO.memName}</td>
				</tr>
				<tr>
					<th>학번</th>
					<td>${classmemberVO.memStuno}</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>${classmemberVO.memSex}</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${classmemberVO.memZip}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${classmemberVO.memAdd1}</td>
				</tr>
				<tr>
					<th>생일</th>
					<td>${classmemberVO.memBir}</td>
				</tr>
				<tr>
					<th>메일</th>
					<td>${classmemberVO.memMail}</td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td>${classmemberVO.memHp}</td>
				</tr>
				<tr>

					<td colspan="2"><a href="classmemberList.edu"
						class="btn btn-info"> <span class="glyphicon glyphicon-list"
							aria-hidden="true"></span> &nbsp;목록
					</a> <mytag:sec hasRole="TEACHER">
							<a href="classmemberUpdate.edu?memId=${classmemberVO.memId}"
								class="btn btn-info btn-sm"> <span
								class="glyphicon glyphicon-knight" aria-hidden="true"></span>
								&nbsp;수정
							</a>
						</mytag:sec></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>