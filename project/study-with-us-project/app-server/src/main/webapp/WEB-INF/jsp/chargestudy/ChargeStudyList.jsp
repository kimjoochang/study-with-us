<%@page import="com.studywithus.domain.Member"%>
<%@page import="com.studywithus.domain.Study"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.Date"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>스터디위더스 : 멘토링목록</title>
  <base target="_self"/>

  <link rel="stylesheet" href="${contextPath}/css/theme.css">
  <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/css/study/StudyList.css"> 
  <link rel="stylesheet" href="${contextPath}/css/footer.css">

</head>

<body>
  <div class="container">
	<jsp:include page="../header.jsp"></jsp:include>

<!-- 검색창 -->
<div class="search-view">
  <form class="search-form" action="search">
    <input type="search" name="keyword" placeholder="키워드를 검색해주세요." class="search-input">
    <button type="submit" class="search-button">
        <img class="reading-glasses-icon" src="${contextPath}/img/search icon.png"></button>
      <!--
    </button>
      <div class="search-option">
        <div>
          <input name="type" type="radio" value="type-users" id="type-users">
          <label for="type-users">
            <svg class="edit-pen-title">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use>
            </svg>
            <span>Users</span>
          </label>
        </div>
        
        <div>
          <input name="type" type="radio" value="type-posts" id="type-posts">
          <label for="type-posts">
            <svg class="edit-pen-title">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#post"></use>
            </svg>
            <span>Posts</span>
          </label>
        </div>
        <div>
          <input name="type" type="radio" value="type-images" id="type-images">
          <label for="type-images">
            <svg class="edit-pen-title">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#images"></use>
            </svg>
            <span>Images</span>
          </label>
        </div>
        <div>
          <input name="type" type="radio" value="type-special" id="type-special" checked="">
          <label for="type-special">
            <svg class="edit-pen-title">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#special"></use>
            </svg>
            <span>Special</span>
          </label>
        </div>
      </div>
    -->
    </form> 
  </div> <!--검색창-->

  <h1 class="study-content-category">멘토링 목록</h1>
  <hr>

  <div class="study-input-collection">
    <input id="input1" type="button" onclick="location.href='listrecruit'" value="모집중">
    <input id="input1" type="button" onclick="location.href='listongoing'" value="진행중">
    <input id="input1" type="button" onclick="location.href='listfinish'" value="진행완료">
	  <a class=input2 href='#' onclick="auth();">멘토링 작성</a>
  </div>

	<br>
		
  <div class="wrapper">
    <div class="main_main">

      <ul class="uldesign">
        
  <c:forEach items="${chargeStudyList}" var="chargeStudy">
  <!-- 
  <%
  Study chargeStudy = (Study)pageContext.getAttribute("chargeStudy");
  
  if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == -1) {
    chargeStudy.setStudyStatus(0); // 모집중

  } else if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == -1) {
    chargeStudy.setStudyStatus(1); // 진행중

  } else {
    chargeStudy.setStudyStatus(2); // 진행완료
  } %>
   -->
  
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
  
        <li class="lidesign"><a href='detail?no=${chargeStudy.no}'></a>
          <h1 class="studyTitle"> ${chargeStudy.title} </h1>
 
          <span>
            <img class="on_offline"
            src="${contextPath}/img/offLineIcon2.png">
            <p class="on_offline_status">${chargeStudy.area}</p>
          </span>
          
          <section>
            <div class="info_item">

              <img class="icon"
              src="${contextPath}/img/fillingHeartIcon.png">
              <p class="icon_count">${chargeStudy.likes}</p>
            </div>
            
            <div class="info_item">
              <img class="icon"
              src="${contextPath}/img/eyeIcon.png">
              <p class="icon_count">${chargeStudy.viewCount}</p>
            </div>
          </section>

        </li> <!--lidesign-->
    </c:forEach>
    </ul> <!-- uldesign -->
      
  </div> <!--wrapper-->
  </div> <!--main_main-->
  
  
  <jsp:include page="../footer.jsp"></jsp:include>
	</div> <!-- container -->
	
	<script>
	const auth = () => {
		
		 var uid = '<%=session.getAttribute("loginUser")%>';
		
		if(uid == 'null') {
			alert("로그인이 필요합니다.");
			
			
		} else {
			location.href="form";
			
		}
	}
	</script>
	
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



