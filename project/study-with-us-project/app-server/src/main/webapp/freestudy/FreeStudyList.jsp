<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>무료 스터디</title>
</head>
<body>
  <h1>무료 스터디 등록</h1>
  <a href='form'>무료 스터디</a>
  <br>
  <table border='1'>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>시작일</th>
        <th>종료일</th>
        <th>온오프라인</th>
        <!-- 오프라인일 경우만 -->
        <th>지역</th> 
        <th>모집인원</th>
        <th>스터디 진행상태</th>
        <th>등록일</th>
        <th>조회수</th>
        <th>좋아요수</th>
    </tr>
  </thead>
  <tbody>

<c:forEach items="${freeStudyList}" var="freeStudy">

  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 0}">
<c:set var="type" value="모집중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 1}">
<c:set var="type" value="진행중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 2}">
<c:set var="type" value="진행완료"/>
  </c:when>
  </c:choose>
  
  <tr>
    <td>${freeStudy.no}</td>
    <td><a href='detail?no=${freeStudy.no}'>${freeStudy.title}</a></td> 
    <td>${freeStudy.writer.name}</td> 
    <td>${freeStudy.startDate}</td>
    <td>${freeStudy.endDate}</td>
    <td>${freeStudy.onOffLine}</td>
    <td>${freeStudy.area}</td>
    <td>${freeStudy.members}/${freeStudy.maxMembers}</td>
    <td>${type}</td>
    <td>${freeStudy.registeredDate}</td>
    <td>${freeStudy.viewCount}</td>
    <td>${freeStudy.likes}</td>
  </tr>
</c:forEach>

</tbody>
</table>
</body>
</html>









