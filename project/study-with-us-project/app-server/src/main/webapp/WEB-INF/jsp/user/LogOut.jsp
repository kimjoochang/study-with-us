<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디위더스</title>
<h1>로그아웃</h1>
</head>
<body>
<%
HttpSession loginUser = request.getSession();
loginUser.invalidate();

%>
</body>
</html>