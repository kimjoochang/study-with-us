<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>유료 스터디</title>
<link rel="stylesheet" href="../css/theme.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/study/StudyList.css"> 
</head>

<body>
	<div class="container">
	<jsp:include page="../header.jsp"></jsp:include>

	<h1>멘토링 스터디 목록</h1>

	<br class="inputs">
    <input id="input1" type="button" value="모집중">
    <input id="input1" type="button" value="진행중">
    <input id="input1" type="button" value="진행완료">

	<a href='form'>멘토링 스터디 작성</a>
	</br>

	<hr size="2" noshade color="gray">

	<br>
			
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
	</div> <!-- container -->
	</body>
	</html>



