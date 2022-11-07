<%@page import="com.address.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체보기</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<%
  AddressDAO dao = AddressDAO.getInstance();
  ArrayList<Address> arr =  dao.addressList();
  int count = dao.getCount();
%>
</head>
<body>
<div class="container">
	<div align="right">
		<a href="insert.jsp">추가하기</a><br/><br/>
	</div>

	<h3>전체보기(<%=count %>)</h3>
	<%-- <%
		for(int i = 0; i<arr.size(); i++){
	%>		
		  <%=arr.get(i).getNum() %>	
		   <%=arr.get(i).getName() %>	
		   <%=arr.get(i).getZipcode() %>	
		   <%=arr.get(i).getAddr() %>	
		   <%=arr.get(i).getTel() %>	
		   <br/>
	<% 	
		}
	%>	 
	<hr/> --%>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
				<th>전화번호</th>
			</tr>
		</thead>
		<tbody>
		<%
			for(Address ad  : arr){
		%>		
			<tr>
			  <td><%=ad.getNum() %>	</td>
			  <td>
			   <a href="detail.jsp?num=<%=ad.getNum() %>"><%=ad.getName() %></a>
			   </td>
		       <td><%=ad.getAddr() %></td>	
			   <td><%=ad.getTel() %></td>	
		   </tr>
		<% 	
			}
		%>	 
		</tbody>
	</table>
</div>
</body>
</html>



