<%@page import="com.studywithus.servlet.user.AuthLogInController"%>
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
Member loginUser = (Member) session.getAttribute("loginUser");
if (loginUser == null) {
%>
<ul>
<li><a href='user/joinform'>회원가입</a><br>
<li><a href='user/loginform'>로그인</a><br>
<li><a href='community/list?categoryNo=0'>커뮤니티</a><br>
<li><a href='freestudy/list'>무료 스터디</a><br>
<li><a href='chargestudy/list'>유료 스터디</a><br>
</ul>
<% } else { %>
<br><h2>나의 정보</h2>
<%=loginUser.getName()%> 님 환영합니다!
<ul>
<li><a href='community/list?categoryNo=0'>커뮤니티</a><br>
<li><a href='freestudy/list'>무료 스터디</a><br>
<li><a href='chargestudy/list'>유료 스터디</a><br>
<li><a href='logout'>[로그아웃]</a><br>
</ul>
</body>
</html>
<% } %>