<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<%
	request.setCharacterEncoding("utf-8");
    AddressDAO dao = AddressDAO.getInstance();
    int num =Integer.parseInt(request.getParameter("num"));
    Address ad=  dao.getDetail(num);
%>
<script>
function del(){
	if(confirm("정말 삭제할까요?")){
		location.href="deletePro.jsp?num=<%=num%>";
	}
}

</script>
</head>
<body>
<h3>상세보기</h3>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="num" value=<%=ad.getNum() %> > 
	<table>
		<tr>
			<th>번호</th>
			<td><input type="text" name="num" value="<%=ad.getNum() %>" 
			 disabled="disabled"> </td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="<%=ad.getName() %>"> </td>
		</tr>
			<tr>
			<th>우편번호</th>
			<td><input type="text" name="zipcode" value="<%=ad.getZipcode() %>"  size=7></td>
		</tr>
			<tr>
			<th>주소</th>
			<td><input type="text" name="addr" value="<%=ad.getAddr() %>" size=50> </td>
		</tr>
			<tr>
			<th>전화번호</th>
			<td><input type="text" name="tel" value="<%=ad.getTel() %>"></td>
		</tr>
		<tr>
		<td colspan="2">
			<input type="submit"  value="수정">
			<input type="button"  value="삭제" onclick="del()">
		</td>
		</tr>
	</table>
</form>
</body>
</html>