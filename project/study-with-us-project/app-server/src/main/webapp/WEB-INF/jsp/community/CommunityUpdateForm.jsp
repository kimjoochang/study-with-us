<%@page import="com.studywithus.domain.Comment"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.CommentDao"%>
<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 수정</title>
</head>
<body>

<form action='update' method='post'>
    <label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${community.no}' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${community.title}'><br>
    
    <label for='f-content'>내용</label> 
    <textarea name="content" id="textarea" cols="50" rows="5">${community.content}'></textarea><br>
    
    <label for='f-writer'>작성자</label> 
    <input id='f-writer' type='email' name='writer' value='${community.writer.email}' readonly><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${community.registeredDate}</span><br>
<button>변경</button> <a href='/swu/community/detail?no=${community.no}'>[취소]</a>
</form>
 
</body>
</html>

