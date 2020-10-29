<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>


<input type="text" class="form-control" placeholder="학교이름을 입력하세요 (ex.OO초등학교, 중앙초등학교,버드내초등학교)" id="schoolSearch" onkeydown="schoolSearch(this.value)">
<div id="scoolId">    aa    </div>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	var v_div=document.getElementById("scoolId");
	
	//학교이름 검색하면 api에서 데이터받아서 넘기기  div에 표시해주기 
	function schoolSearch(schoolName) {
		if(event.keyCode==13){
			$.ajax({
				url : "schoolData.edu?schoolNm="+schoolName,
				type : "post",
				dataType : "json",
				data : {},
				success : function(data){
					console.log(data);
					console.log(data.response.body.items.item[0].schoolId);
					var v_arrays=data.response.body.items.item;
				
					
					var v_table="<table border=1><tr><th>구분</th><th>이름</th><th>지번주소</th><th>도로명주소</th><th>학교아이</th></tr>";
					
					for(var i=0;i<v_arrays.length;i++){
						v_table+="<tr onmouseover="+"\'this.style.background="+"\"red\"' onmouseout="+"\'this.style.background="+"\"white\"'  onclick='fn_setSchoolData(this)'>";
						v_table+="<td>"+v_arrays[i].fondType+"</td>";
						v_table+="<td>"+v_arrays[i].schoolNm+"</td>";
						v_table+="<td>"+v_arrays[i].lnmadr+"</td>";
						v_table+="<td>"+v_arrays[i].rdnmadr+"</td>";
						v_table+="<td>"+v_arrays[i].schoolId+"</td>";
						v_table+="</tr><br><br>";						
					}
					v_div.innerHTML=v_table;
					
					
				}
			})//ajax
		}//엔터키만
	}//funciont schoolSearch
	
	
	
	
	
	
	//학교 클릭했을 때 부모창에 데이터넘기기
	function fn_setSchoolData(p_this) {
		var v_postSchoolId=p_this.children[p_this.children.length-1].innerHTML;
		var v_postSchoolNm=p_this.children[1].innerHTML;
		
		var v_schoolId=opener.document.getElementById("schoolId");
		var v_schoolNm=opener.document.getElementById("schoolNm");
		
		v_schoolId.value=v_postSchoolId;
		v_schoolNm.value=v_postSchoolNm;
		window.close();
	}
	
	
	
	
	
	

</script>

</body>
</html>