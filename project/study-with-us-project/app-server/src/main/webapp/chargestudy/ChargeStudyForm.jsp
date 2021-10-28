<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>유료 스터디 생성</title>
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
<h1>유료 스터디 생성</h1>
<form action='StudyAdd.jsp'>
<label for='f-title'>제목</label> <input id='f-title' type='text' name='title'><br>
<label for='f-area'>지역</label> <input id='f-area' type='text' name='area'><br>
<label for='f-content'>내용</label> <input id='f-content' type='text' name='content'><br>
<label for='f-writer'>작성자</label> <input id='f-writer' type='text' name='name'><br>
<label for='f-maxMembers'>모집인원</label> <input id='f-maxMembers' type='text' name='maxMembers'><br>
<label for='f-price'>금액</label> <input id='f-price' type='text' name='price'><br>
<label for='f-startDate'>시작일</label> <input id='f-startDate' type='text' name='startDate'><br>
<label for='f-endDate'>종료일</label> <input id='f-endDate' type='text' name='endDate'><br>
<button>등록</button><br>
</form>
</body>
</html>









