<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(){
	//이름, 나이 공백인지 검사
/* 	 if(document.getElementById("name").value==""){
		 alert("이름 입력")
		 return
	 } */
	 if(document.querySelector("#name").value==""){
		 alert("이름 입력")
		 return
	 }
	 if(document.getElementById("age").value==""){
		 alert("나이 입력")
		 return
	 }
	
	//관심 분야 선택 안하면 선택하라고  alert
	var chk_obj = document.getElementsByName("hobby")
	var checked = false
	//var checked =0
    for(i =0; i<chk_obj.length;i++){
    	if(chk_obj[i].checked){
    		checked= true
    	}
    }
	if(checked == false){
		alert("관심분야 선택")
		return
	}
	document.getElementById("frm").submit()
}
</script>
</head>
<body>
<form action="formResult.jsp" method="get" id="frm" >
이름 :  <input type="text" name="name" id="name"><br/>
나이 :  <input type="text" name="age" id="age"><br/>
성별 :
<label for="m">남자</label>
<input type="radio"  value="남" id="m" name="gender" checked>
<label for="f"> 여자</label>
<input type="radio"  value="여" id="f"  name="gender"><br/>
관심분야 :
  <label for="h1">운동</label>
  <input type="checkbox" name="hobby" value="운동" id="h1">
    <label for="h2">게임</label>
  <input type="checkbox" name="hobby" value="게임" id="h2">
  <label for="h3">등산</label>
  <input type="checkbox" name="hobby" value="등산" id="h3">
  <label for="h4">영화</label>
  <input type="checkbox" name="hobby" value="영화" id="h4">
  <br/>
  직업 :
  <select name="job">
	  <option value="학생">학생</option>
	  <option value="공무원">공무원</option>
	  <option value="회사원">회사원</option>
	   <option value="기타">기타</option>   
</select><br>
 <input type="button" value="button전송"  onclick="check()">

</form>
</body>
</html>