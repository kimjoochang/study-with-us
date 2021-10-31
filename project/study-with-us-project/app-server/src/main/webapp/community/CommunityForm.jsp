<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>커뮤니티</title>
</head>

<body>
<c:choose>
<c:when test="${categoryNo eq 0}">
<c:set var="type" value="정보"/>
<a href='form?categoryNo=1'>질문</a>
&nbsp &nbsp
<a href='form?categoryNo=2'>스몰톡</a>
</c:when>

<c:when test="${categoryNo eq 1}">
<c:set var="type" value="질문"/>
<a href='form?categoryNo=0'>정보</a>
&nbsp &nbsp
<a href='form?categoryNo=2'>스몰톡</a>
</c:when>

<c:when test="${categoryNo eq 2}">
<c:set var="type" value="스몰톡"/>
<a href='form?categoryNo=0'>정보</a>
&nbsp &nbsp
<a href='form?categoryNo=1'>질문</a>
</c:when>
</c:choose>

<h1>커뮤니티 등록</h1>
<form action='add'>
카테고리 : ${type}<br>
<input type='hidden' name='categoryNo' value='${categoryNo}'><br>
커뮤니티 제목: <br><input type='text' name='title'><br>
커뮤니티 내용<br> 
<textarea name="content" id="textarea" cols="50" rows="5"></textarea><br>
<button>등록</button><br>
</form>
</body>
</html>