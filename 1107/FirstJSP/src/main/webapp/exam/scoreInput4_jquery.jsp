<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.6.1.min.js" ></script>
  
<script>
$(document).ready(function(){
	$("#btn").click(function(){
		if($("#name").val() == ""){
			alert("이름 입력")
			return 
		}
		if($("#kor").val()=="" || isNaN($("#kor").val() )){
			alert("국어 점수  입력")
			$("#kor").val("")
			return 
		}
		if($("#eng").val()=="" ||!$.isNumeric($("#eng").val() )){
			alert("영어 점수  입력")
			$("#eng").val("")
			return 
		}
		if($("#math").val()=="" ||!$.isNumeric($("#math").val() )){
			alert("수학 점수  입력")
			$("#math").val("")
			return 
		}
		$("#frm").submit()
	})
})

</script>
</head>
<body>
<form action="scoreResult4.jsp"  id ="frm" method="post">
이름: <input type="text" name ="name"  id="name"><br/>
국어: <input type="text" name ="kor" id="kor"><br/>
영어:<input type="text" name ="eng" id="eng" ><br/>
수학:<input type="text" name ="math" id="math"><br/>
<input type="button" value="jquery전송"  id="btn"/>
</form>
</body>
</html>



