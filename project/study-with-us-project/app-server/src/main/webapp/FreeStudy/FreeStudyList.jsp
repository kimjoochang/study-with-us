<%@page import="com.studywithus.dao.StudyDao"%>
<%@page import="com.studywithus.domain.Study"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>무료 스터디</title>
</head>
<body>
<h1>무료 스터디 목록</h1>
<a href='form.jsp'>무료 스터디 등록</a><br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>온오프라인</th>
    <th>지역</th>
    <th>제목</th>
    <th>내용</th>
    <th>모집인원</th>
    <th>시작일</th>
    <th>종료일</th>
    <th>등록일</th>
    <th>조회수</th>
<!--  <th>상태</th> -->  
  </tr>
</thead>
<tbody>

<c:forEach items="${freeStudyList}" var="freeStudy">
<tr>
    <td>${freeStudy.no}</td>
    <td>${freeStudy.title}</td> 
    <td>${freeStudy.content}</td> 
    <td>${freeStudy.area}</td>
    <td>${freeStudy.onOffLine}</td>
    <td>${freeStudy.registeredDate}</td>
    <td>${freeStudy.viewCount}</td>
    <td>${freeStudy.maxMembers}</td>
    <td>${freeStudy.startDate}</td>
    <td>${freeStudy.endDate}</td>
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>









