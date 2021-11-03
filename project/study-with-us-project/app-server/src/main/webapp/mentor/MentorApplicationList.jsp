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
<title>멘토 신청내역 조회</title>
</head>


<body>
<table border='1'>
<thead>
  <tr>
    <th>신청자 번호</th>
    <th>신청자 이름</th>
    <th>신청자 이메일</th>
    <th>신청일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${applicationList}" var="application">
<tr>
    <td>${application.applicant.no}</td>
    <td><a href='detail?no=${application.applicant.no}'>${application.applicant.name}</a></td> 
    <td>${application.applicant.no}</td> 
    <td>${application.registeredDate}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>

