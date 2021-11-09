<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>무료 스터디</title>
    <link rel="stylesheet" href="../css/theme.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/study/StudyList.css">
  </head>

<body>
  <div class="container">
  <jsp:include page="../header.jsp"></jsp:include>

  <h1>스터디 목록
  </h1>
    
    <br class="inputs">
    <input id="input1" type="button" value="모집중">
    <input id="input1" type="button" value="진행중">
    <input id="input1" type="button" value="진행완료">

    <a class=input2 href='form'>무료 스터디 작성</a>
    </br>

    <hr size="2" noshade color="gray">

    <br>
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
  
<div class="wrapper">
  <main>
    <div class="main_main">

      <ul class="uldesign">
        <li class="lidesign">
          <h1 class="studyTitle"> ${freeStudy.title} </h1>
          <span>
            <img class="on_offline"
            src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/onlineIcon.png">
            <p class="on_offline_status">${freeStudy.onOffLine},${freeStudy.area}</p>
          </span>
          
          <section>
            <div class="info_item">

              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/fillingHeartIcon.png">
              <p class="icon_count">${freeStudy.likes}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/eyeIcon copy.png">
              <p class="icon_count">${freeStudy.viewCount}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/speechBalloonIcon copy.png">              
              <p class="icon_count">1</p>
            </div>
          </section>
        </li>
         
        <li class="lidesign">
          <h1 class="studyTitle"> 제목2 </h1>
          <span>
            <img class="on_offline"
            src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/offLineIcon2.png">
            <p class="on_offline_status">온라인,지명</p>
          </span>
          
          <section>
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/fillingHeartIcon.png">
              <p class="icon_count">1</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/eyeIcon copy.png">
              <p class="icon_count">1</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/speechBalloonIcon copy.png">              
              <p class="icon_count">1</p>
              
              
            </div>
          </section>
        </li>
        
</c:forEach>

</tbody>
</table>
</div> <!-- container -->
</body>
</html>









