<%@page import="com.studywithus.servlet.user.AuthLogInController"%>
<%@page import="com.studywithus.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STUDY WITH US</title>
</head>
<body>
<h1>STUDY WITH US</h1>

<c:if test="${loginUser eq null}">
<ul>
<li><a href='user/joinform'>회원가입</a><br>
<li><a href='user/loginform'>로그인</a><br>
<li><a href='community/list?categoryNo=0'>커뮤니티</a><br>
<li><a href='freestudy/list'>무료 스터디</a><br>
<li><a href='chargestudy/list'>유료 스터디</a><br>
</ul>
</c:if>

<c:if test="${loginUser ne null}">
<br><h2>나의 정보</h2>
<c:out value="${loginUser.name}"/>님 환영합니다!
<ul>
<c:if test="${loginUser.userAccessLevel | 16}">
<li><a href='mypage'>마이페이지</a><br>
</c:if>
<c:if test="${loginUser.userAccessLevel ne 32}">
<li><a href='mypage'>마이페이지</a><br>
</c:if>
<c:if test="${loginUser.userAccessLevel eq 32}">
<li><a href='adminpage'>관리자페이지</a><br>
</c:if>
<li><a href='community/list?categoryNo=0'>커뮤니티</a><br>
<li><a href='freestudy/list'>무료 스터디</a><br>
<li><a href='chargestudy/list'>유료 스터디</a><br>
<li><a href='user/logout'>로그아웃</a><br>
</ul>
</c:if>
</body>
</html>
