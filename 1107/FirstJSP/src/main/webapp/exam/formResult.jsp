<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	
	String[] hobby = request.getParameterValues("hobby");
	String tmp = "";
	if(hobby != null) {
		for(int i=0; i < hobby.length; i++){
			tmp += hobby[i]+"  ";
		}
	}

	String job = request.getParameter("job");
	%>
이름 : <%=name %>  <br/>
나이:   <%=age %><br/>
성별:  <%=gender %> <br/>
관심분야:   <%=tmp %><br/>
직업 :  <%=job %><br/>
<hr>
직업 : <%=request.getParameter("job")  %>
</body>
</html>





