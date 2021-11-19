<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<%@page import="com.studywithus.domain.Member"%>
<%@ page import="java.sql.Date"%>

<% Member loginUser = (Member) session.getAttribute("loginUser"); %>


<!DOCTYPE html>
<html lang="ko">

<head>
  <title>스터디위더스 : 멘토링등록</title>
  <link rel="stylesheet" href="${contextPath}/css/theme.css">
  <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/css/study/StudyAddForm.css">
</head>
  
<body>
  <div class="container">
    <jsp:include page="../header.jsp"></jsp:include>

<header class="freepagetop">

  <form action='add' method="post">

    <div class="study-box">
      <fieldset class="menu">
    
        <div class="study-form-content">    
  <h1 class="study-content-category">멘토링 등록</h1>
  <hr>

<div class="study-content-line">
  <label for='area'>지역</label>
    <select name = "area">
      <option value="" selected="selected">시/도</option>
      <option value="서울" >서울</option>
      <option value="경기">경기</option>
    </select>

<select name = "area">
  <option value="" selected="selected">구/군</option>
  <option value="강남" >강남</option>
  <option value="서초">분당</option>
</select>
<br>
</div> <!--study-area-div-->

<div class="study-content-line">
  <label for='title'>제목</label>
  <input id='title' type='text' name='title' placeholder="제목을 입력해주세요.">
</div>

<div class="study-content-line">
  <label for='category'>카테고리</label> 
  <select name = category>
    <option value="" selected="selected">---카테고리---</option>
    <option value="category">일반기업</option>
    <option value="category" >공기업/공무원</option>
    <option value="category" >교육</option>
  </select>
</div>

<div class="study-content-line">
  <label for='maxMembers'>정원</label> 
  <input id='maxMembers' type='number' name='maxMembers' placeholder="ex) 4">
</div>

<div class="study-content-line">
  <label class="study-content-label" for='content'>내용</label>
  <textarea class = "study-textarea" name ="content" cols="69" rows="10" placeholder="등록할 스터디 내용을 입력해주세요."> </textarea>
</div>

<%Date nowDate = new Date(System.currentTimeMillis()); %>

<div class="study-content-line">
  <label for='startDate'>시작일</label> 
  <input id='startDate' type='date' min ="<%=nowDate%>" name='startDate'>
</div>

<div class="study-content-line">
  <label for='endDate'>종료일</label> 
  <input id='endDate' type='date' min ="<%=nowDate%>" name='endDate'>
</div>

<div class="study-content-line">
  <label for='price'>금액</label> 
  <input id='price' type='text' name='price' placeholder="ex) 10000">
</div>

</div><!--study-form-content-->

<br>
<div class="study-bottom-button">
  <input class="input5" type="reset" value="초기화">
  <!--<input type="submit" value="등록">-->
  <button class="input5">등록</button>
  <button class="input5" type="button" onclick="location.href='list'">취소</button><br>
</div>

</fieldset> <!-- menu-->
</form>
</header> <!-- -->

<jsp:include page="../footer.jsp"></jsp:include>
</div> <!-- container -->

</body>
</html>


