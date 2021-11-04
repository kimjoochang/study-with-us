<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
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
<h1>유료 스터디 상세보기</h1>
	<form action='updateform'>
	<label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${chargeStudy.no}' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${chargeStudy.title}' readonly><br>
    
    <label for='f-writer'>작성자</label> 
    <input id='f-writer' type='email' name='writer' value='${chargeStudy.writer.email}' readonly><br>
    
    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${chargeStudy.content}' readonly><br>
    
    <label for='f-category'>카테고리</label> 
    <input id='f-category' type='text' name='category' value='${chargeStudy.category}' readonly><br>
    
    <label for='f-maxMembers'>모집인원</label> 
    <input id='f-maxMembers' type='number' name='maxMembers' value='${chargeStudy.maxMembers}' readonly><br>
     
    <label for='f-members'>현재인원</label> 
    <input id='f-members' type='number' name='members' value='${chargeStudy.members}' readonly><br>
    
    <label for='f-price'>금액</label> 
    <input id='f-price' type='text' name='price' value='${chargeStudy.price}' readonly><br>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${chargeStudy.startDate}' readonly><br>
    
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${chargeStudy.endDate}' readonly><br>
    
    <label for='f-likes'>좋아요</label> 
    <input id='f-likes' type='text' name='likes' value='${chargeStudy.likes}' readonly><br>
    
    <label for='f-viewCount'>조회수</label> 
    <input id='f-viewCount' type='text' name='viewCount' value='${chargeStudy.viewCount}' readonly><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${chargeStudy.registeredDate }</span><br>
    
    <c:choose>
<c:when test="${loginUser eq null}">
<a href='/swu/chargerstudy/list'>[목록]</a><br>
</c:when>

<c:when test="${checkWriter eq 1}">
<a href='updateform?no=${chargeStudy.no}'>[수정]</a> 
<c:if test="${chargeStudy.deleteStatus eq 0}">
<a href='deleterequest?no=${chargeStudy.no}'>[삭제요청]</a> 
</c:if>
<c:if test="${chargeStudy.deleteStatus eq 1}">
<a href='deletecancel?no=${chargeStudy.no}'>[삭제요청 취소]</a> 
</c:if>
<a href='/swu/chargestudy/list'>[목록]</a><br>
</c:when>

<c:when test="${checkWriter eq 2}">
<c:if test="${result eq 0}">
<a href='/swu/chargestudy/interest/add?no=${chargeStudy.no}'>[관심목록 추가]</a>
</c:if>

<c:if test="${result eq 1}">
<a href='/swu/chargestudy/interest/delete?no=${chargeStudy.no}'>[관심목록 삭제]</a>
</c:if>

<a href='/swu/chargestudy/list'>[목록]</a><br>
</c:when>
</c:choose>
	</form>

</body>
</html>
