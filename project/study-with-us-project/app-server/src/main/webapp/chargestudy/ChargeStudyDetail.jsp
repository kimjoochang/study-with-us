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
    
    <label for='f-name'>제목</label>
    <input id='f-name' type='text' name='name' value='${chargeStudy.title}'><br>
    
    <label for='f-no'>작성자</label> 
    <input id='f-no' type='text' name='no' value='${chargeStudy.writer}' readonly><br>
    
    <label for='f-email'>내용</label> 
    <input id='f-email' type='text' name='email' value='${chargeStudy.content}'><br>
    
    <label for='f-password'>모집인원</label> 
    <input id='f-photo' type='text' name='photo' value='${chargeStudy.maxMembers}'><br>
     
    <label for='f-password'>현재인원</label> 
    <input id='f-photo' type='text' name='photo' value='${chargeStudy.members}'><br>
    
    <label for='f-photo'>금액</label> 
    <input id='f-photo' type='text' name='photo' value='${chargeStudy.price}'><br>
    
    <label for='f-tel'>시작일</label> 
    <input id='f-tel' type='text' name='tel' value='${chargeStudy.startDate}'><br>
    
    <label for='f-tel'>종료일</label> 
    <input id='f-tel' type='text' name='tel' value='${chargeStudy.endDate}'><br>
    
    <label for='f-tel'>좋아요</label> 
    <input id='f-tel' type='text' name='tel' value='${chargeStudy.likes}'><br>
    
    <label for='f-tel'>조회수</label> 
    <input id='f-tel' type='text' name='tel' value='${chargeStudy.viewCount}'><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${chargeStudy.registeredDate}</span><br>
	
	<button>변경</button>
	 <a href='delete?no=${chargeStudy.no}'>[삭제]</a> <a href='list'>[목록]</a><br>
	</form>

</body>
</html>
