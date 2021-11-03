<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 상세</title>
</head>
<body>

<form>
    <label for='f-applicant_name'>신청자 이름</label>
    <span id='f-applicant_name'>${application.applicant.name}</span><br>
    
    <label for='f-applicant_email'>신청자 이메일</label> 
    <span id='f-applicant_email'>${application.applicant.email}</span><br>
    
    <label for='f-selfIntro'>자기소개</label> 
    <span id='f-selfIntro'>${application.selfIntroduction}</span><br>
    
    <label for='f-subject'>스터디 주제설명</label> 
    <span id='f-subject'>${application.subject}</span><br>
    
    <label for='f-registeredDate'>신청일</label> 
    <span id='f-registeredDate'>${community.registeredDate}</span><br>
    
<a href='/swu/community/list?categoryNo=${community.category}'>[목록]</a><br>
<a href='approve?no=${application.applicant.no}'>[승인]</a> 
<a href='reject?no=${application.applicant.no}'>[거절]</a> 
</form>

</body>
</html>
