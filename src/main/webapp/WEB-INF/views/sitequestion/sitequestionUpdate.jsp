<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>sitequestionUpdate.jsp</title>
<!-- 작성자 : 김아름
	작성일자 : 2020.09.22 -->
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<div class="mainContainer">
	<h3>1:1 문의글 수정</h3>	
	<form:form action="sitequestionUpdateMessage.edu" id="frm_sitequestion" modelAttribute="sitequestionVO" method="post" enctype="multipart/form-data">
		<form:hidden path="sqNo"/>
	<!-- START : 1:1 문의사항 테이블 -->
	<table class="table table-primary table-bordered">
		<tbody>
			<tr>
				<th class="text-center table-th" width="20%">글번호</th>
				<td>${sitequestionVO.sqNo }</td>
			</tr>
			<tr>
				<th class="text-center table-th" width="20%">분류</th>
				<td>
					<form:select path="sqCategory" required="required" cssClass="form-control input-sm">
						<option value="">-- 선택하세요 -- </option>
						<option value="화상수업" 	${sitequestionVO.sqCategory eq "화상수업" ?  "selected='selected'" : "" }>화상수업</option>
						<option value="로그인"  	${sitequestionVO.sqCategory eq "로그인" ?  "selected='selected'" : "" }>로그인</option>
						<option value="사이트 이용" ${sitequestionVO.sqCategory eq "사이트 이용" ?  "selected='selected'" : "" }>사이트 이용</option>
						<option value="기타" 		${sitequestionVO.sqCategory eq "기타" ?  "selected='selected'" : "" }>기타</option>
					</form:select>
					<form:errors path="sqCategory" />				
				</td>
			</tr>
			<tr>
				<th class="text-center table-th" width="20%">제목</th>
				<td><form:input path="sqTitle" cssClass="form-control input-sm" />
					<form:errors path="sqTitle" />
				</td>
			</tr>
			<tr>
				<th class="text-center table-th" width="20%">작성자</th>
				<td><form:input path="sqWriter" value="${sessionScope.USER_INFO.userName}" readonly="true" cssClass="form-control input-sm" />
					<form:errors path="sqWriter" /></td>
			</tr>
			<tr>
				<th class="text-center table-th" width="20%">등록일자</th>
				<td>${sitequestionVO.sqRegDate }</td>
			</tr>
			<tr>
				<th class="text-center table-th">첨부파일<br>
					<button type="button" id="id_btn_new_file" class="small-button">추가</button>
				</th>
				<td class="file_area">
					<c:forEach var="f" items="${sitequestionVO.attaches}" varStatus="st">
						<div>
							<%-- <input type="checkbox" id="id_file_all_change" name="atchNo" value="${f.atchNo }"> --%>
							# 파일 ${st.count}
								<a>	<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									${f.atchOriginalName}
								</a>
							Size : ${f.atchFancySize}
							<button type="button" class="small-button btn_delete" data-atch-no='${f.atchNo }' >삭제</button>
						</div>
					</c:forEach>
					<div class="form-inline">
						<input type="file" name="sqFiles" class="form-control">
						<button type="button" class="small-button btn_delete">삭제</button>
					</div>
				</td>
			</tr>
			<tr>
				<th class="text-center table-th" width="20%">내용</th>
				<td>
					<textarea rows="10" name="sqContent" class="form-control">${sitequestionVO.sqContent}</textarea>
					<form:errors path="sqContent" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
		          <div class="button-left">
		              <a href="sitequestionList.edu" class="btn btn-default btn-sm"> 
		              	<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		                &nbsp;&nbsp;목록
		              </a>
		          </div>
		            <div class="button-right">
		              <button type="button" formaction="sitequestionDeleteMessage.edu" class="btn btn-sm btn-danger" id="deletesitequestion"> 
		                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		                &nbsp;&nbsp;삭제
		              </button>
		              <button type="submit" class="btn btn-sm btn-primary" > 
		                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
		                &nbsp;&nbsp;저장
		              </button>
		             </div>
				</td>
			</tr>
		</tbody>	
	</table>
	</form:form>
	<!-- END : 1:1 문의사항 테이블 -->
</div>
<script type="text/javascript">
$('#id_btn_new_file').click(function(){
	// 파일 추가버튼 클릭 이벤트
	$('.file_area').append('<div class="form-inline">'
						 + '<input type="file" name="sqFiles" class="form-control">'
						 + '<button type="button" class="small-button btn_delete">삭제</button>'
						 + '</div>');
	});
	
	// 파일 삭제버튼 클릭 이벤트 (동적으로 추가된 객체의 이벤트)
	$('.file_area').on('click','.btn_delete', function(){
		$(this).closest('div').remove();
	});
	
	// 수정할 때 기존에 있던 파일 삭제 클릭 이벤트(hidden으로 숨겨서 삭제된것처럼하고 DB에는 삭제여부가 N → Y로 변경)
	$('.btn_attach_delete').click(function() {
		$btn =  $(this)
		atchNo = $btn.data('atch-no')
		$div = $btn.closest('div')
		$div.html('<input type="hidden" name="delAtchNos" value="'+ atchNo +'">')
	}) // #btn_attach_delete.click
	
	$(document).ready(function(e){
		$('#deletesitequestion').click(function(){
			var result = confirm("정말 삭제하시겠습니까?");
			if (result) {
				$('form#frm_sitequestion').prop('action', "sitequestionDeleteMessage.edu")
				$('form#frm_sitequestion').submit();
			} else {
				$('form#frm_sitequestion').prop('action', "sitequestionUpdate.edu")
				$('form#frm_sitequestion').submit();
			}
		});
	});
	

</script>
</body>
</html>