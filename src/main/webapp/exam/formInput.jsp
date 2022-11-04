<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="formResult.jsp" method="get" >
이름 :  <input type="text" name="name"><br/>
나이 :  <input type="text" name="age"><br/>
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
 <input type="submit" value="전송" >

</form>
</body>
</html>