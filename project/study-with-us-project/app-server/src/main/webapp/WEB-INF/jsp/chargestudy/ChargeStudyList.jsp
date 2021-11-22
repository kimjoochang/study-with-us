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
    <input id="input1" type="button" value="모집중">
    <input id="input1" type="button" value="진행중">
    <input id="input1" type="button" value="진행완료">
	  <a class=input2 href='form'>멘토링 작성</a>
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
  
       <!-- ***** 로그인/회원가입 시 모달창 *****-->
      <div class="modal1 hidden">
      <!--모달 활성화 시 흐린 배경 표현-->
      <div class="modal1_overlay">
        <!--모달 화면-->
        <div class="modal1_content">
     <button type="button" class="close" data-dismiss="modal" aria-lable="close" onclick="closeModal();">&times;</button>
          <div class="sign-buttons">
              <div class="d-flex justify-content-center">
          
              <!--회원가입 버튼 -->
              <div class="text-center">
                <a href="" class="btn btn-primary1" data-toggle="modal" data-target="#signupPage" onclick="closeModal();">&nbsp;Sign up&nbsp;<i class="fas fa-user-plus ml-3"></i></a>
              </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          
              <!--로그인 버튼-->
              <div class="text-center">
                <a href="" class="btn btn-primary1" data-toggle="modal" data-target="#signinPage" onclick="closeModal();">&nbsp;&nbsp;&nbsp;&nbsp;Sign in&nbsp;<i class="fas fa-sign-in-alt ml-3"></i></a>
              </div>
              
            </div>
          </div>



        </div> <!-- modal_content -->
      </div> <!-- modal_overlay -->
    </div> <!-- modal_hidden -->
    </div>
  </div>


<div class="modal fade" id="signupPage">
    <div class="modal-dialog">
      <div class="modal-content">
        
        <div class="modal-header text-center">
          <h5 class="modal-title w-100 dark-grey-text font-weight-bold">Sign Up</h5>
          <button type="button" class="close" data-dismiss="modal" aria-lable="close">&times;</button>
        </div>

        <div class="title_img"><img src="${contextPath}/img/스터디위더스.png"></img></div>

        <div class="modal-body mx-3">

          <form action="user/join" method="post"><!-- 추가-->
            
            <div class="md-form mb-5">
            <label class ="sign_label" data-error="wrong" data-success="right">&nbsp;이름
            <input type="text" name="name" class="form-control validate" placeholder="이름을 입력하세요" required>
          </label></div>

          <!--  --><div class="md-form mb-5">
           <div class="check_font" id="id_check">
            <label data-error="wrong" data-success="right">&nbsp;이메일
              <input type="email" id="user_id" name="email" class="form-control validate" 
              placeholder="이메일을 입력하세요" required oninput = "checkEmail()" />
            </label></div>
         <!--  -->     <span class="email_already">이미 사용중인 이메일입니다.</span>
           <!--  -->   </div>
            
            <!-- 얘를 빼보자 // 원본소스
              <div class="check_font" id="id_check">
             <input type="email" id = "email" name='email' class="form-control validate" autocomplete="username" required oninput = "checkEmail()" />
            <span class="email_ok">사용 가능한 이메일입니다.</span>
            <span class="email_already">이미 사용중인 이메일입니다.</span>
          
            <span class="email_ok">사용 가능한 이메일입니다.</span>
            <span class="email_already">이미 사용중인 이메일입니다.</span>
            </div>  --> 
          

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">&nbsp;닉네임
            <input type="text" name="nickname" class="form-control validate" placeholder="닉네임을 입력하세요" required>
          </label></div>

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">&nbsp;비밀번호
            <input type="password" name="password" class="form-control validate" placeholder="비밀번호를 입력하세요" required>
          </label></div>
<!-- 
          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">비밀번호 확인</label>
            <input type="password" name="password_confirm" class="form-control validate">
          </div>
 -->

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">&nbsp;&nbsp;휴대폰 번호
            <input type="text" name="phoneNumber" class="form-control validate" placeholder="010-1234-5678" required>
          </label></div>
        </div>
          
        <div class="modal-footer d-flex justify-content-center">
          <button class="btn btn-primary1">스터디위더스 시작하기</button><br><hr>
        </div>
      </form>

      </div>
    </div>
  </div>
  
<!--LOG IN 누르면 나오는 모달 창 내용들--> 

<div class="modal fade" id="signinPage">
  <div class="modal-dialog">
    <div class="modal-content">
      
      <div class="modal-header text-center">
        <h5 class="modal-title w-100 dark-grey-text font-weight-bold">Sign In</h5>
        <button type="button" class="close" data-dismiss="modal" aria-lable="close">&times;</button>
      </div>

      <div class="title_img"><img src="${contextPath}/img/스터디위더스.png"></img></div>
      <!-- 추가 -->
      <div class="modal-body mx-4">

        <form action='user/login' method="post">
        <div class="md-form">
       <label data-error="wrong" data-success="right"> 이메일 
   <!--  얘를 빼보자 <input type="email" id = "email" name='email' class="form-control validate" autocomplete="username" required oninput = "checkEmail()" />
         -->
        <input type="email" id = "email" name='email' class="form-control validate" autocomplete="" /></label> 
<!--         <span class="email_ok">사용 가능한 이메일입니다.</span>
        <span class="email_already">이미 사용중인 이메일입니다.</span> -->
          
        </div>

        <div class="md-form">
          <label data-error="wrong" data-success="right">비밀번호
          <input type="password" name='password' class="form-control validate"></label>
        </div><br>
        <div class="text-center mb-3">
           <button class="btn btn-primary1">스터디위더스 시작하기</button><br>
           
           
         <!--   <button type="button" class="btn btn-primary1 btn-block z-depth-1a">스터디위더스 시작하기</button><br><hr> -->
          <!--<button type="button" class="btn btn-primary btn-block z-depth-1a">스터디위더스 시작하기</button>-->
        </div>
     </form>

     <br>
         <p class="font-small blue-text d-flex justify-content-end">
         <a href="user/findemail" class="blue-text sl-1">Forgot ID</a> &nbsp;/&nbsp;
         <a href="user/resetpwd" class="blue-text sl-1">PWD</a></p>
         <br><hr>

        <div class="st-sns">
        <p class="font-large dark-grey-text d-flex justify-content-center">or</p>
        <p class="font-large dark-grey-text d-flex justify-content-center">SNS로 시작하기</p>
      </div>

        <div class="row my-3 justify-content-center">
          <div class="sns_container">
            <div class="sns_icon"><a><img src="${contextPath}/img/facebook.png"></img></a></div>&nbsp;&nbsp;
            <div class="sns_icon"><a><img src="${contextPath}/img/twitter.png"></img></a></div>&nbsp;&nbsp;
            <div class="sns_icon"><a><img src="${contextPath}/img/google.png"></img></a></div>&nbsp;&nbsp;
            <div class="sns_icon"><a><img src="${contextPath}/img/instagram.png"></img></a></div>&nbsp;&nbsp;
        </div><!--sns_container-->
        </div><!--row my-3 justify-content-center-->
        
  
  <jsp:include page="../footer.jsp"></jsp:include>
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



