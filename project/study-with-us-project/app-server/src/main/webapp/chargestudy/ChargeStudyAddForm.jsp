<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<%@page import="com.studywithus.domain.Member"%>
<% Member loginUser = (Member) session.getAttribute("loginUser"); %>


<!DOCTYPE html>
<html lang="ko">

<head>
  <title>유료 스터디</title>
  <link rel="stylesheet" href="../css/theme.css">
  <link rel="stylesheet" href="../css/bootstrap.css">
  <link rel="stylesheet" href="../css/study/StudyAddForm.css">
</head>
  
<body>
  <div class="container">
    <jsp:include page="../header.jsp"></jsp:include>

<header class="freepagetop">

<h1>멘토링 등록</h1>
<form action='add'>

<fieldset class="menu">

<div class="menu_box1">

<label for='title'>제목</label> <input id='title' type='text' name='title' placeholder="제목을 입력해주세요.">
<br>
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

<label for='content'>내용</label>
<textarea name ="content" cols="50" rows="15" placeholder="등록할 스터디 내용을 입력해주세요.">
</textarea>
<br>
<label for='startDate'>시작일</label> <input id='startDate' type='date' name='startDate'><br>
<label for='endDate'>종료일</label> <input id='endDate' type='date' name='endDate'><br>
<label for='category'>카테고리</label> <input id='category' type='text' name='category'><br>
<label for='maxMembers'>정원</label> <input id='maxMembers' type='number' name='maxMembers' placeholder="ex) 6명"><br>
<label for='price'>금액</label> <input id='price' type='text' name='price' placeholder="ex) 10000"><br>
<label for='writer'>작성자</label> 
<input id='writer' type='email' name='writer' value='${loginUser.email}' readonly><br>

</div>

<br>
<div style='text-align: center;'>
<input class="input5" type="reset" value="초기화">
<!--<input type="submit" value="등록">-->
<button class="input5">등록</button>
<button class="input5" type="button" onclick="location.href='list'">취소</button><br>
</div>

</fieldset> <!-- menu-->
</form>
</header> <!-- -->
</div> <!-- container -->
</body>
</html>


