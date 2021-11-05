<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>무료 스터디</title>

<style>
  div {
    xdisplay: inline-block;
    border: 1px solid black;
    margin: 5px;
    padding: 10px;
    width: 140px;
    height: 180px;
    text-align: center;
    float: left;
  }

  #input1 {
    
    width: 80px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(117, 109, 170);
    color: rgb(246, 245, 252);
    border: 2px solid rgb(117, 109, 170);
    box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
    margin: 5px;
  }

  #input2 {
    float: right;
    width: 110px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(246, 245, 252);
    color: rgb(117, 109, 170);
    border: 2px solid rgb(117, 109, 170);
    box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
    margin: 5px;
  }

  #menu {
    background-attachment: scroll;
background-clip: border-box;
background-color: rgb(255, 255, 255);
background-image: none;
background-origin: padding-box;
background-position-x: 0%;
background-position-y: 0%;
background-size: auto;
border-bottom-left-radius: 24px;
border-bottom-right-radius: 24px;
border-top-left-radius: 24px;
border-top-right-radius: 24px;
box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 25px 0px;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: list-item;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
height: 240px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
margin-bottom: 10px;
margin-left: 10px;
margin-right: 10px;
margin-top: 10px;
opacity: 1;
padding-bottom: 24px;
padding-left: 24px;
padding-right: 24px;
padding-top: 24px;
position: relative;
text-align: left;
transition-delay: 0s;
transition-duration: 0.2s;
transition-property: transform;
transition-timing-function: ease-in;
width: 244px;
  }
xbody {
  border: solid 3px color(756daa);
  padding: 10px;
  line-height: 20px
}

xdiv {
  border: solid 3px rgb(117, 109, 170);
  margin: 10px;
  color: white;
  width: 150px;
  display: inline-block;
}

.title {
  font-size: 40px;
}
 
@media (max-width: 600px) {
  .title {
    font-size: 20px;
  }
}

</style>

</head>
<body>
  <h1>무료 스터디 조회</h1>
  <p>
    
    <br>
    <input id="input1" type="submit" value="모집중">
    <input id="input1" type="submit" value="진행중">
    <input id="input1" type="submit" value="진행완료">
    
    <input id="input2" type="submit" value="무료 스터디 작성">
    <a href='form'>무료 스터디 작성</a>
    
    <hr size="2" noshade color="gray">
    <div id="menu">스터디 진행상태</div>
    <div id="menu">스터디 진행상태</div>
    <div id="menu">스터디 진행상태</div>
    <div id="menu">스터디 진행상태</div>
    <div id="menu">스터디 진행상태</div>
    <div id="menu">스터디 진행상태</div>

  </div>

</p>

  <br>
  <table border='1'>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>시작일</th>
        <th>종료일</th>
        <th>온오프라인</th>
        <!-- 오프라인일 경우만 -->
        <th>지역</th> 
        <th>모집인원</th>
        <th>등록일</th>
        <th>조회수</th>
        <th>좋아요수</th>
    </tr>
  </thead>
  <tbody>

<c:forEach items="${freeStudyList}" var="freeStudy">

  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 0}">
<c:set var="type" value="모집중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 1}">
<c:set var="type" value="진행중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 2}">
<c:set var="type" value="진행완료"/>
  </c:when>
  </c:choose>
  
  <tr>
    <td>${freeStudy.no}</td>
    <td><a href='detail?no=${freeStudy.no}'>${freeStudy.title}</a></td> 
    <td>${freeStudy.writer.name}</td> 
    <td>${freeStudy.startDate}</td>
    <td>${freeStudy.endDate}</td>
    <td>${freeStudy.onOffLine}</td>
    <td>${freeStudy.area}</td>
    <td>${freeStudy.members}/${freeStudy.maxMembers}</td>
    <td>${type}</td>
    <td>${freeStudy.registeredDate}</td>
    <td>${freeStudy.viewCount}</td>
    <td>${freeStudy.likes}</td>
  </tr>
</c:forEach>

</tbody>
</table>
</body>
</html>









