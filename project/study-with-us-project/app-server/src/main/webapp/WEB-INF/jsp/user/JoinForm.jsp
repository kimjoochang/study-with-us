<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
</head>

<body>
<h1>회원가입</h1>
<form action='join'  method="post">
이름: <input type='text' name='name'><br>
이메일: <input type='email' name='email'><br>
닉네임: <input type='text' name='nickname'><br>
암호: <input type='password' name='password'><br>
전화: <input type='text' placeholder="010-1234-1234" name='phoneNumber'><br>
<button>등록</button><br>
</form>
</body>
</html>
