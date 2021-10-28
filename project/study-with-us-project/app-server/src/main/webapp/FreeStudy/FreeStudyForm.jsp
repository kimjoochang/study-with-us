<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 무료 스터디</title>
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
<h1>새회원</h1>
<form action='FreeStudyAdd.jsp'>

<select name = "onOffLine">
    <option value="">직업선택</option>
      <option value="온라인">온라인</option>
      <option value="오프라인" selected="selected">오프라인</option>
</select>

  <select name="sido1" id="sido1"></select>
  <select name="gugun1" id="gugun1"></select>
        
<label for='f-title'>제목</label> <input id='f-title' type='text' name='photo'><br>
<label for='f-content'>내용</label> <input id='f-content' type='text' name='tel'><br>
<label for='f-maxMembers'>모집인원</label> <input id='f-tel' type='number' name='tel'><br>
<label for='f-maxMembers'>시작일</label> <input id='f-tel' type='date' name='tel'><br>
<label for='f-maxMembers'>종료일</label> <input id='f-tel' type='date' name='tel'><br>
<button>등록</button><br>
</form>
</body>
</html>









