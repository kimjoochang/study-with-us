<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 조회</title>
</head>

<body>
<c:choose>
  <c:when test="${categoryNo eq 0}">
<c:set var="type" value="정보"/>
<ul>
<li><a href='list?no=1'>질문 커뮤니티</a><br>
<li><a href='list?no=2'>스몰톡 커뮤니티</a><br>
</ul>
  </c:when>
  
  <c:when test="${categoryNo eq 1}">
<c:set var="type" value="질문"/>
<ul>
<li><a href='list?no=0'>정보 커뮤니티</a><br>
<li><a href='list?no=2'>스몰톡 커뮤니티</a><br>
</ul>
  </c:when>

  <c:when test="${categoryNo eq 2}">
<c:set var="type" value="스몰톡"/>
<ul>
<li><a href='list?no=0'>정보 커뮤니티</a><br>
<li><a href='list?no=1'>질문 커뮤니티</a><br>
</ul>
  </c:when>
</c:choose>

<a href='form?no=${categoryNo}'>글쓰기</a><br>
<br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>카테고리</th>
    <th>제목</th>
    <th>작성자</th>
    <th>조회수</th>
    <th>좋아요</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${communityList}" var="community">
<tr>
    <td>${community.no}</td>
    <td>${type}</td>
    <td><a href='Detail.jsp?no=${community.no}'>${community.title}</a></td> 
    <td>${community.writer.email}</td> 
    <td>${community.viewCount}</td> 
    <td>${community.like}</td> 
    <td>${community.registeredDate}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>

