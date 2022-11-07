<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function zipfinder(){
	window.open("zipCheck.jsp","","width=700 height=400" );
}

</script>
</head>
<body>
<a href="list.jsp">전체보기</a><br/><br/>
<form action="insertPro.jsp" method="post">
<table border="1">
		<tr>
				<td colspan="2">주소록 등록하기</td>
		</tr>
		<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
		</tr>
		<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode"  id="zipcode"  size=5>
						<input type="button" value="검색"  onclick="zipfinder()">
				</td>
		</tr>
		<tr>
				<td>주소</td>
				<td><input type="text" name="addr" id="addr"  size="50"></td>
		</tr>
		<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel"></td>
		</tr>
		<tr>
		<td colspan="2">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
		</td>
		</tr>
</table>

</form>

</body>
</html>