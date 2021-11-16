<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>스터디위더스 : 내가개설한멘토링목록</title>
</head>
<body>
	<h1>내가 개설한 멘토링 목록</h1>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>지역</th>
				<th>카테고리</th>
				<th>작성자</th>
				<th>금액</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>모집인원</th>
				<th>스터디 진행상태</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>좋아요수</th>
			</tr>
		</thead>
		<tbody>
		
  <c:forEach items="${chargeStudyList}" var="chargeStudy">
  
  <c:choose>
  <c:when test="${chargeStudy.studyStatus eq 0}">
<c:set var="type" value="모집중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${chargeStudy.studyStatus eq 1}">
<c:set var="type" value="진행중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${chargeStudy.studyStatus eq 2}">
<c:set var="type" value="진행완료"/>
  </c:when>
  </c:choose>
  
			<tr>
				<td>${chargeStudy.no}</td>
				<td><a href='detail?no=${chargeStudy.no}'>${chargeStudy.title}</a></td> 
        <td>${chargeStudy.area}</td>
        <td>${chargeStudy.category}</td>
        <td>${chargeStudy.writer.name}</td>
        <td>${chargeStudy.price}</td>
        <td>${chargeStudy.startDate}</td>
        <td>${chargeStudy.endDate}</td>
        <td>${chargeStudy.members}/${chargeStudy.maxMembers}</td>
        <td>${type}</td> 
        <td>${chargeStudy.registeredDate}</td>
        <td>${chargeStudy.viewCount}</td>
        <td>${chargeStudy.likes}</td>
	</tr>
  </c:forEach>
 
	</tbody>
	</table>
	</body>
	</html>



