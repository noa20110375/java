<%@page import="com.exam.DateBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
  DateBean bean = new DateBean();
 %>   
 <%= bean.getToday() %>
