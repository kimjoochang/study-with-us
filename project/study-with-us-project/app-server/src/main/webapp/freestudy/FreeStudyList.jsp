<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>무료 스터디</title>

    <base target="_self"/>

    <link rel="stylesheet" href="../css/theme.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/study/StudyList.css">
  </head>

<body>
  <div class="container">
  <jsp:include page="../header.jsp"></jsp:include>

  <h1 class="study-content-category">스터디 목록</h1>
  <hr>
    
  <div class="study-input-collection">
    <input id="input1" type="button" value="모집중">
    <input id="input1" type="button" value="진행중">
    <input id="input1" type="button" value="진행완료">
    <a class=input2 href='form'>스터디 작성</a>
  </div>
  
  <br>

<div class="wrapper">
    <div class="main_main">

      <ul class="uldesign">

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
  
        <li class="lidesign"> <a href='detail?no=${freeStudy.no}'></a>
          <div class="study-package">
            <h1 class="studyTitle"> ${freeStudy.title} </h1>
            <span>
              <img class="on_offline"
              src="../img/onlineIcon.png">
              <p class="on_offline_status">${freeStudy.onOffLine},${freeStudy.area}</p>
            </span>
          </div>
          
          <section>
            <div class="info_item">

              <img class="icon"
              src="../img/fillingHeartIcon.png">
              <p class="icon_count">${freeStudy.likes}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="../img/eyeIcon.png">
              <p class="icon_count">${freeStudy.viewCount}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="../img/speechBalloon.png">              
              <p class="icon_count">1</p>
            </div>
          </section>
        </li>
</c:forEach>
</ul> <!-- uldesign -->
</div> <!-- main_main -->
</div> <!-- wrapper -->

</div> <!-- container -->

<script>
  var trList = document.querySelectorAll("li"); // 리턴 객체는 HTMLCollection 타입 객체이다.
  trList.forEach(function(trTag) {
    trTag.onclick = (e) => {
      window.location.href = e.currentTarget.querySelector("a").href;
    };
  });
  </script>
</body>
</html>









