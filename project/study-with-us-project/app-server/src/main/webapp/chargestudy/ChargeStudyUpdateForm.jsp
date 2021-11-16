<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>스터디위더스 : 멘토링수정</title>
  <link rel="stylesheet" href="../css/theme.css">
  <link rel="stylesheet" href="../css/bootstrap.css">
  <link rel="stylesheet" href="../css/study/StudyUpdateForm.css">
</head>

<body>
  <div class="container">
    <jsp:include page="../header.jsp"></jsp:include>
      
<header class="freepagetop">

  <form action='update'>
    <fieldset class="menu">

    <h1 class="study-content-category">멘토링 수정</h1>
    <hr>

	<label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${chargeStudy.no}' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${chargeStudy.title}'><br>
    
    <label for='f-writer'>작성자</label> 
    <input id='f-writer' type='email' name='writer' value='${chargeStudy.writer.email}' readonly><br>
    
    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${chargeStudy.content}'><br>

    <label for='f-category'>카테고리</label> 
    <input id='f-category' type='text' name='category' value='${chargeStudy.category}'><br>
    
    <label for='f-maxMembers'>모집인원</label> 
    <input id='f-maxMembers' type='number' name='maxMembers' value='${chargeStudy.maxMembers}'><br>
     
    <label for='f-members'>현재인원</label> 
    <input id='f-members' type='number' name='members' value='${chargeStudy.members}' readonly><br>
    
    <label for='f-price'>금액</label> 
    <input id='f-price' type='text' name='price' value='${chargeStudy.price}' readonly><br>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${chargeStudy.startDate}'><br>
    
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${chargeStudy.endDate}'><br>
    
    <label for='f-likes'>좋아요</label> 
    <input id='f-likes' type='text' name='likes' value='${chargeStudy.likes}' readonly><br>
    
    <label for='f-viewCount'>조회수</label> 
    <input id='f-viewCount' type='text' name='viewCount' value='${chargeStudy.viewCount}' readonly><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${chargeStudy.registeredDate}</span><br>
	
    <div class="study-bottom-button">
	    <button class="input3">변경</button>
	    <a href='list'>목록</a><br>
    </div> 

  </fieldset>

	</form>

  </header>
</div> <!-- container -->
</body>
</html>
