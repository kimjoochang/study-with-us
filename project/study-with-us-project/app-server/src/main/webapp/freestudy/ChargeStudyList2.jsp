<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>유료 스터디</title>
<link rel="stylesheet" href="../css/theme.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/study/StudyList.css"> 
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">

	<h1>멘토링 목록</h1>

	<br class="inputs">
    <input id="input1" type="button" value="모집중">
    <input id="input1" type="button" value="진행중">
    <input id="input1" type="button" value="진행완료">

	<a class=input2 href='form'>멘토링 작성</a>
	</br>

	<hr size="2" noshade color="gray">

	<br>
			
  <c:forEach items="${chargeStudyList}" var="chargeStudy">
  <c:choose>
  <c:when test="${chargeStudy.studyStatus eq 0}">
<c:set var="type" value="모집중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${chargeStudy.studyStatus eq 1}">
<c:set var="type" value="진행중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${chargeStudy.studyStatus eq 2}">
<c:set var="type" value="진행완료"/>
  </c:when>
  </c:choose>
  
  <div class="wrapper">
    <div class="main_main">

      <ul class="uldesign">
        <li class="lidesign"><a href='detail?no=${chargeStudy.no}'></a>
          <h1 class="studyTitle"> ${chargeStudy.title} </h1>
          <span>
            <img class="on_offline"
            src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/onlineIcon.png">
            <p class="on_offline_status">${chargeStudy.onOffLine},${chargeStudy.area}</p>
          </span>
          
          <section>
            <div class="info_item">

              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/fillingHeartIcon.png">
              <p class="icon_count">${chargeStudy.likes}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/eyeIcon copy.png">
              <p class="icon_count">${chargeStudy.viewCount}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/speechBalloonIcon copy.png">              
              <p class="icon_count"></p>
            </div>
          </section>
        </li> <!--lidesign-->
      </ul> <!-- uldesign -->
      
      </div> <!--main_main-->
      </div> <!--wrapper-->
  </c:forEach>
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



