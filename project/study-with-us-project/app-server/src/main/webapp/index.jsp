<%@page import="com.studywithus.servlet.user.AuthLogInHandler"%>
<%@page import="com.studywithus.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STUDY WITH US</title>
</head>
<body>
<h1>STUDY WITH US</h1>

<% 
Member member = AuthLogInHandler.getLoginUser();

if (member == null) {
%>
<a href='user/JoinForm.jsp'>회원가입</a><br>
<a href='user/LoginForm.jsp'>로그인</a><br>
<% } else { %>
<br><h2>나의 정보</h2>
<%=member.getName()%> 님 환영합니다!
<br><a href='user/AuthLogout.jsp'>[로그아웃]</a>
</body>
</html>
<% } %>