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

<!-- 검색창 -->
<div class="search-view">
  <form class="search-form" action="search">
    <input type="search" name="keyword" placeholder="키워드를 검색해주세요." class="search-input">
    <button type="submit" class="search-button">
        <img class="reading-glasses-icon" src="../img/search icon.png"></button>
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
          <h1 class="studyTitle"> ${freeStudy.title} </h1>
          <span>
            <img class="on_offline"
            src="../img/onlineIcon.png">
            <p class="on_offline_status">${freeStudy.onOffLine},${freeStudy.area}</p>
          </span>
          
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
          </section>
        </li>
</c:forEach>
</ul> <!-- uldesign -->
</div> <!-- main_main -->
</div> <!-- wrapper -->

</div> <!-- container -->

<script>
let type;
let box;
let text;

const changeColor = () => {
  if (${categoryNo} === 0) {
    text = document.querySelector('.info_text');
    box =  document.querySelector('.info_box');

    text.classList.add('ctry_text_color');
    box.classList.add('ctry_box_color');
    
  } else if (${categoryNo} === 1) {
    text = document.querySelector('.qa_text');
    box =  document.querySelector('.qa_box');
    text.classList.add('ctry_text_color');
    box.classList.add('ctry_box_color');
    
  } else {
    text = document.querySelector('.talk_text');
    box =  document.querySelector('.talk_box');

    text.classList.add('ctry_text_color');
    box.classList.add('ctry_box_color');
  }
}

changeColor();

const openBtn = document.getElementById('open');
//onModal button

const closeBtn = document.getElementById('close');
//offModal button

const modal = document.querySelector('.modal');
//HTML에서의 모달 최상위 요소

const overlay = document.querySelector('.modal_overlay');
//모달창이 활성화되면 흐린 배경을 표현하는 요소

const openModal = () => {
modal.classList.remove('hidden');
}

const closeModal = () => {
modal.classList.add('hidden');
}

openBtn.addEventListener('click', openModal);
//onModal

closeBtn.addEventListener('click', closeModal);
//모달창 내부의 닫기 버튼

//overlay.addEventListener('click', closeModal);
//모달창 영역 밖

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
trTag.onclick = (e) => {
  window.location.href = e.currentTarget.querySelector("a").href;
};
});

  </script>
</body>
</html>









