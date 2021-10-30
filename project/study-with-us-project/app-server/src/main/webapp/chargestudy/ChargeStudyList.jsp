<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>유료 스터디</title>
</head>
<body>
	<h1>유료 스터디 목록</h1>
	<a href='Form.jsp'>유료 스터디</a>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>제목</th>
				<th>지역</th>
				<th>내용</th>
				<th>금액</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
		
  <c:forEach items="${chargeStudyList}" var="chargeStudy">
			<tr>
				<td>${chargeStudy.no}</td>
				<td><a href='Detail.jsp?no='>${chargeStudy.name}</a></td> 
        <td>${chargeStudy.getTitle()}</td>
        <td>${chargeStudy.getContent()}</td>
        <td>${chargeStudy.getArea()}</td>
        <td>${chargeStudy.getPrice()}</td>
        <td>${chargeStudy.getWriter().getName()}</td>
        <td>${chargeStudy.getStartDate()}</td>
        <td>${chargeStudy.getEndDate()}</td>
        <td>${chargeStudy.getStudyStatus()}</td>
        <td>${chargeStudy.getRegisteredDate()}</td>
        <td>${chargeStudy.getMembers()}</td>
        <td>${chargeStudy.getMaxMembers()}</td>
        <td>${chargeStudy.getViewCount()}</td>
        <td>${chargeStudy.getLikes()}</td>
        <!-- status 리스트에 출력되게 할/말 -->
	</tr>
  </c:forEach>
 
	</tbody>
	</table>
	</body>
	</html>


