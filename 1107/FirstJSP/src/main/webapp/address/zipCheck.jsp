<%@page import="com.address.ZipCode"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a:link      {text-decoration: none;color:#000}
a:hover  {text-decoration: none;color:#000}
a:visited {text-decoration: none;color:#000}
</style>
<%
 	request.setCharacterEncoding("utf-8");
	String dong = request.getParameter("dong");
	AddressDAO dao  = AddressDAO.getInstance();
	ArrayList<ZipCode> zarr =dao.zipcodeRead(dong);
	
%>
<script>
	function dongCheck(){
		if(document.getElementById('dong').value==""){
			alert("동이름을 입력하세요")
			document.getElementById('dong').focus()
			return
		}
		document.getElementById('frm').submit()
	}
	
	function send(code, sido, gugun,dong, bunji){
		const address = sido+" " + gugun +" "+dong+" "+bunji
		opener.document.getElementById("zipcode").value = code;
		opener.document.getElementById("addr").value = address;
		self.close()
	}
</script>
</head>
<b>우편번호 찾기</b>
<form action="zipCheck.jsp" id="frm">
	<table>
		<tr>
			<td> 동이름 입력 : <input type="text" name="dong"  id="dong"/>
			<input type="button" value="검색"  onclick="dongCheck()"/>
		</tr>
		<tr>
<%
	if(zarr.isEmpty()){
%>		
		<td>검색 결과 없습니다.</td>
<% 
	}else{
%>				
		<td>* 검색 후, 아래 우편번호를 클릭하면 자동 입력됩니다.</td>
<%		
	}
%>		
		</tr>
<%
	for ( ZipCode z  : zarr){
		String zip  = z.getZipcode();
		String sido = z.getSido();
		String gugun = z.getGugun();
		String d = z.getDong();
		String bunji = z.getBunji();
%>
<tr> 
	<td> <a href="javascript:send('<%=zip %>','<%=sido %>','<%=gugun %>','<%=d%>','<%=bunji %>')">
	<%=zip %><%=sido %><%=gugun %>	<%=d%><%=bunji %></a>
	</td>
</tr>	
<%		
	}
%>
	</table>
</form>
</body>
</html>



