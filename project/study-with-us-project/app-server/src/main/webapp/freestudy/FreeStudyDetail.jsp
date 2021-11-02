<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>무료 스터디</title>
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
<h1>무료 스터디 상세보기</h1>
<form action='detail'>
    <label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${freeStudy.no}' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${freeStudy.title}' readonly><br>
    
    <!-- 팀장? 작성자? -->
    <label for='f-name'>작성자</label> 
    <input id='f-name' type='text' name='name' value='${freeStudy.writer.name}' readonly><br>
    
    <!-- 설명? 내용? -->
    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${freeStudy.content}' readonly><br>
    
    <label for='f-status'>스터디 진행상태</label> 
    <input id='f-status' type='text' name='status' value='${freeStudy.studyStatus}' readonly><br>
    
    <label for='f-onOffLine'>온오프라인</label> 
    <input id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}' readonly><br>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${freeStudy.startDate}' readonly><br>
     
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${freeStudy.endDate}' readonly><br>

    <!-- 11.02 선영 질문 모집인원 현재인원 type : number? text? -->
    <label for='f-maxMembers'>모집인원</label> 
    <input id='f-maxMembers' type='number' name='maxMembers' value='${freeStudy.maxMembers}' readonly><br>
     
    <label for='f-members'>현재인원</label> 
    <input id='f-members' type='number' name='members' value='${freeStudy.members}' readonly><br>
        
    <label for='f-viewCount'>조회수</label> 
    <input id='f-viewCount' type='text' name='viewCount' value='${freeStudy.viewCount}' readonly><br>
    
    <label for='f-likes'>좋아요</label> 
    <input id='f-likes' type='text' name='likes' value='${freeStudy.likes}' readonly><br>

    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${freeStudy.registeredDate}</span><br>
    
    
<c:choose>
<c:when test="${loginUser eq null}">
<a href='/swu/freestudy/list'>[목록]</a><br>
</c:when>

<c:when test="${checkWriter eq 1}">
<a href='updateform?no=${freeStudy.no}'>[변경]</a> 
<a href='delete?no=${freeStudy.no}'>[삭제]</a> 
<a href='/swu/freestudy/list'>[목록]</a><br>
</c:when>

<c:when test="${checkWriter eq 2}">
<c:if test="${result eq 0}">
<a href='/swu/freestudy/interest/add?no=${freeStudy.no}'>[관심목록 추가]</a>
</c:if>

<c:if test="${result eq 1}">
<a href='/swu/freestudy/interest/delete?no=${freeStudy.no}'>[관심목록 삭제]</a>
</c:if>

<a href='/swu/freestudy/list'>[목록]</a><br>
</c:when>
</c:choose>
</form>

</body>
</html>



