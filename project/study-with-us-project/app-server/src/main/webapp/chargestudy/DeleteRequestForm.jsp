<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<%@page import="com.studywithus.domain.Member"%>
<% Member loginUser = (Member) session.getAttribute("loginUser"); %>


<!DOCTYPE html>
<html lang="ko">

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
<h1>삭제요청서 등록</h1>
<form action='deleterequest'>
<input type ="hidden" name="no" value="${no}"/>
<label for='reason'>내용<br></label>
<textarea name ="reason" cols="50" rows="15" placeholder="삭제 사유를 입력해주세요.">
</textarea>
<br>
<div style='text-align: center;'>
<input type="reset" value="초기화">
<!--<input type="submit" value="등록">-->
<button>등록</button>
<button type="button" onclick="location.href='list'">취소</button><br>
</div>
</form>
</body>
</html>


