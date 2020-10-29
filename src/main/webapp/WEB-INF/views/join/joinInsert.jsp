R<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>joinInsert.jsp</title>
<!-- 작성자 : 한창희
	작성일자 : 2020.09.25 -->
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="mainContainer">
		<div class="joinContainer">
			<!-- START : 회원가입 테이블 -->
			<form:form action="joinInsertMessage.edu" modelAttribute="memberVO"
				method="POST" id="regForm">
				<table class="table table-primary table-bordered">
					<tbody>
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<td colspan="2">
							<h3 class="h3-text">회원가입</h3>
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">아이디</th>
						<td><form:input path="memId" cssClass="form-control input-sm" />
							<form:errors path="memId" cssClass="" />
							<form:button	class="idCheck" id="" onclick="fn_idCheck();" path="">중복확인</form:button>
							<!-- 중복체크했는지안했는지 Y,N -->
							<form:input id="idCheck" path="idCheck"	cssClass="form-control input-sm" type="hidden" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">비밀번호</th>
						<td>
							<form:input path="memPass" cssClass="form-control input-sm" id="pwd1" value="" />
							<form:errors path="memPass" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">비밀번호 확인</th>
						<td>
							<form:input path="" cssClass="form-control input-sm" id="pwd2" value="" />
							<form:errors path="" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div class="alert-success" id="alert-success">비밀번호가 일치합니다.</div>
							<div class="alert-danger" id="alert-danger">비밀번호가 일치하지않습니다.</div>
						</td>
					</tr>

					<tr>
						<th class="text-center table-th">이름</th>
						<td>
							<form:input path="memName" cssClass="form-control input-sm" /> 
							<form:errors path="memName" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">성별</th>
						<td>남<form:radiobutton path="memSex" value="M" /> 여<form:radiobutton
								path="memSex" value="F" /> <%-- 	 	<form:input path="memSex" cssClass="form-control input-sm" />
					<form:errors path="memSex"></form:errors>   --%>
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">생년월일</th>
						<td>
							<form:input path="memBir" cssClass="form-control input-sm" />
							<form:errors path="memBir" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">이메일</th>
						<td>
							<form:input path="memMail" cssClass="form-control input-sm" id="email" />
							<form:errors	path="memMail" />
							<form:button class="emailCheck" onclick="fn_emailSend();" path="">인증메일발송</form:button>
							
							<!-- 인증했는지안했는지 Y,N -->
							<form:input path="emailCheck" id="emailCheck"   type="hidden"/>
							
							<input type="text" id="inputRandom"  >
							
							<!-- 이메일확인 난수 저장필드 -->
							<form:hidden path="" value="" id="sendedRandom" />
							
							<!-- 이메일확인버튼 -->
							<input type="button" onclick="fn_emailCheck()" id="emailCheckButton" value="확인"> 
							
							
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">휴대전화</th>
						<td><form:input path="memHp" cssClass="form-control input-sm" />
							<form:errors path="memHp" /></td>
					</tr>
					<tr>
						<th class="text-center table-th">우편번호</th>
						<td>
							<input class="btn btn-primary btn-sm" style="position: relative; display: inline-block;" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
							<form:input type="text" id="sample6_postcode" placeholder="우편번호" path="memZip" cssClass="form-control input-sm" />
							<form:errors path="memZip"/>
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">주소</th>
						<td>
							<form:input type="text" id="sample6_address" placeholder="주소" path="memAdd1" cssClass="form-control input-sm" />
							<form:errors path="memAdd1" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">상세주소</th>
						<td>
							<form:input type="text" id="sample6_detailAddress" 	placeholder="상세주소" path="memAdd2" cssClass="form-control input-sm" />
							<form:errors path="memAdd2" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">학교ID</th>
						<td>
							<input class="btn btn-primary btn-sm" style="position: relative; display: inline-block;" type="button" onclick="schoolCode()" value="학교ID찾기"><br>
							<form:input type="text" id="schoolNm" path="memScnm" cssClass="form-control input-sm" value="" placeholder="학교이름" />
							<form:errors path="memScnm" />
							<form:input type="text" id="schoolId" path="memScid" cssClass="form-control input-sm" value="" placeholder="학교ID" />
							<form:errors path="memScid" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">학년</th>
						<td>
							<form:input path="memGrade" cssClass="form-control input-sm" />
							<form:errors path="memGrade" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">반</th>
						<td>
							<form:input path="memClass"	cssClass="form-control input-sm" />
							<form:errors path="memClass" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">번호</th>
						<td>
							<form:input path="memStuno"	cssClass="form-control input-sm" />
						 	<form:errors path="memStuno" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">부모님 성함</th>
						<td>
							<form:input path="memPnm" cssClass="form-control input-sm" />
							<form:errors path="memPnm" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">부모님 연락처</th>
						<td>
							<form:input path="memPhp" cssClass="form-control input-sm" />
							<form:errors path="memPhp" />
						</td>
					</tr>
					<tr>
						<th class="text-center table-th">교원자격증</th>
						<td>
							<form:input path="memTli"	cssClass="form-control input-sm" />
							<form:errors path="memTli" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="button-left">
								<a href="/edu" class="btn btn-sm btn-default">Home</a>
							</div>
							<div class="button-right">
								<button type="submit" class="btn btn-sm btn-primary" id="submit">저장하기</button>
							</div>
						</td>
					</tr>
					</tbody>
				</table>
			</form:form>
			<!-- END : 회원가입 테이블 -->
		</div>
	</div>
	<!-- mainContainer -->


	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<input type="text" id="sample6_extraAddress" placeholder="참고항목" hidden>

	<script>
//우편번호 주소 함수
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
   //아이디중복체크확인
    function fn_idCheck(){
    	event.preventDefault();
    	event.stopPropagation();
		$.ajax({
			url : "idCheck.edu",
			type : "post",
			dataType : "json",
			data : {"memId" : $("#memId").val()},
			success : function(data){
				console.log($("#memId").val());
				if(data == 1){
					alert("중복된 아이디입니다.");
				}else if(data == 0){
					$("#idCheck").attr("value", "Y");
					alert("사용가능한 아이디입니다.");
				}else if(data==2){
					alert("아이디는 빈 칸 사용 불가");
				}
			}
		})
	}
   
 
   //이메일인증하기  인증메일보내기 
	function fn_emailSend() {
		event.preventDefault(); //form으로인해 insertMessage넘어가는거 패스 
		var v_email = document.getElementById("email").value;
		alert("메일을 보냈습니다. 확인해주세요");
		$.ajax({
			url : "signUp.edu?email=" + v_email,
			type : "post",
			//dataType : "json",
			//data : {},
			success : function(data) {
				console.log(data);  //data는 난수 6자리
				$("#sendedRandom").attr("value",data);	
			},
			error : function(request, status, error) {
				console.log(request);
				console.log(status);
				console.log(error);
			}
		})
	}
   
   
	//이메일인증하기  인증번호확인누르기
   function fn_emailCheck(){
		var sendedRandom=document.getElementById("sendedRandom");
		var inputRandom=document.getElementById("inputRandom");
		if(sendedRandom.value==inputRandom.value &&sendedRandom.value!=""){
			document.getElementById("emailCheck").value="Y";
			$('#inputRandom').hide();
			$('#emailCheckButton').val("확인완료");
			document.getElementById("emailCheckButton").disabled=true;
			alert("확인완료");
		} else{
			alert("인증번호가 다릅니다.");
		}
	}
   
	if(document.getElementById("emailCheck").value=="Y"){
		$('#inputRandom').hide();
		$('#emailCheckButton').val("확인완료");
		document.getElementById("emailCheckButton").disabled=true;
		}
	

	//저장하기 누를 때 부족한것들 조사 (중복체크,비밀번호확인 등 )
	$(document).ready(function(){
		$("#submit").on("click", function() {
			$.ajax({
				url : "signUpEmailRandomCode.edu",
				type : "post",
				//dataType : "json",
				//data : {},
				success : function(data) {
					var v_emailCookie=data;
					var v_emailChecked=$("#emailChecked").val();
					
					if(v_emailCookie==v_emailChecked&&v_emailCookie!=""){
						console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
						$("#emailCheck").attr("value","Y");
					}
					
					var idChkVal = $("#idCheck").val();
					var pwd1 = $("#pwd1").val();
					var pwd2 = $("#pwd2").val();
					var email = $("#emailCheck").val();
				
					
					if (pwd1 != pwd2 || pwd1 == "" || pwd2 == ""	) {
						alert("비밀번호가 다릅니다");
					}
					
					if (email != "Y") {
						alert("이메일 인증해 주세요")
					}
					
					if (idChkVal != "Y") {
						alert("중복확인 해 주세요.");
					} else if (idChkVal == "Y") {
						$("#regForm").submit();
					}	
				}
			})
		});
	});

	//비밀번호 다르면...보이냐 안보이냐 정도 ?
	$(function() {
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("input").keyup(function() {
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			if (pwd1 != "" || pwd2 != "") {
				if (pwd1 == pwd2) {
					$("#alert-success").show();
					$("#alert-danger").hide();

				} else {
					$("#alert-success").hide();
					$("#alert-danger").show();

				}
			}
		});
	});

	//학교찾기 
	function schoolCode() {
		window.open("school.edu", "ddd",
						"width = 800, height = 800, top = 100, left = 200, location = no");
	}
	
	
	
	
	
	
</script>














</body>
</html>

