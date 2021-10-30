<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>유료 스터디</title>
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>
<body>
<h1>유료 스터디 상세보기</h1>
	<form action='update'>
	<label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${chargeStudy.no}' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${chargeStudy.title}'><br>
    
    <label for='f-writer'>작성자</label> 
    <input id='f-writer' type='email' name='writer' value='${chargeStudy.writer}' readonly><br>
    
    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${chargeStudy.content}'><br>
    
    <label for='f-maxMembers'>모집인원</label> 
    <input id='f-maxMembers' type='text' name='maxMembers' value='${chargeStudy.maxMembers}'><br>
     
    <label for='f-members'>현재인원</label> 
    <input id='f-members' type='text' name='members' value='${chargeStudy.members}'><br>
    
    <label for='f-price'>금액</label> 
    <input id='f-price' type='text' name='price' value='${chargeStudy.price}'><br>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${chargeStudy.startDate}'><br>
    
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${chargeStudy.endDate}'><br>
    
    <label for='f-likes'>좋아요</label> 
    <input id='f-likes' type='text' name='likes' value='${chargeStudy.likes}'><br>
    
    <label for='f-viewCount'>조회수</label> 
    <input id='f-viewCount' type='text' name='viewCount' value='${chargeStudy.viewCount}'><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${chargeStudy.registeredDate}</span><br>
	
	<button>변경</button>
	 <a href='delete?no=${chargeStudy.no}'>[삭제]</a> <a href='list'>[목록]</a><br>
	</form>

</body>
</html>
