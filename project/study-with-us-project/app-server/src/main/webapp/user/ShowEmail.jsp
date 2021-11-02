<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.studywithus.dao.MemberDao"%>  
<%@page import="com.studywithus.domain.Member"%>  


<!DOCTYPE html>
<html>
<head>
  <title>스터디 위더스</title>
</head>

<body>
  <h1>아이디 찾기 결과</h1>
<form action='showemail'  method="post">
 
<% String email = (String)request.getAttribute("email");%>
 
 <!--${member.name}님의 아이디는 ${member.email} 입니다.-->
 
 <br><button>로그인</button> <button>비밀번호 찾기</button>
</form>
</body>
</html>
