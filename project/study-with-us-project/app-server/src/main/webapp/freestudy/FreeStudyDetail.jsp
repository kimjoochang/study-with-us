<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
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
    <input id='f-title' type='text' name='title' value='${freeStudy.title}'><br>
    
    <!-- 팀장? 작성자? -->
    <label for='f-name'>팀장</label> 
    <input id='f-name' type='text' name='name' value='${freeStudy.writer.name}'><br>
    
    <!-- 설명? 내용? -->
    <label for='f-content'>설명</label> 
    <input id='f-content' type='text' name='content' value='${freeStudy.content}'><br>
    
    <label for='f-status'>스터디 진행상태</label> 
    <input id='f-status' type='text' name='status' value='${freeStudy.studyStatus}'><br>
    
    <label for='f-onOffLine'>온오프라인</label> 
    <input id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}'><br>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${freeStudy.startDate}'><br>
     
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${freeStudy.endDate}'><br>

    <label for='f-maxMembers'>모집인원</label> 
    <input id='f-maxMembers' type='number' name='maxMembers' value='${freeStudy.maxMembers}'><br>
     
    <label for='f-maxMembers'>현재인원</label> 
    <input id='f-maxMembers' type='number' name='members' value='${freeStudy.members}'><br>
        
    <label for='f-viewCount'>조회수</label> 
    <input id='f-viewCount' type='text' name='viewCount' value='${freeStudy.viewCount}'><br>
    
    <label for='f-likes'>좋아요</label> 
    <input id='f-likes' type='text' name='likes' value='${freeStudy.likes}'><br>

    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate' ${freeStudy.registeredDate}></span><br>
    
<button>수정</button>
 <a href='delete.jsp?no=${freeStudy.no}'>[삭제]</a> <a href='list.jsp'>[목록]</a><br>
</form>

</body>
</html>









