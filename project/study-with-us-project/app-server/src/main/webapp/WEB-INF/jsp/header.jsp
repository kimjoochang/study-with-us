<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="copyright" content="MACode ID, https://macodeid.com/">

  <title>스터디위더스 : 메인</title>

  <base target="_self"/>
 <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
   -->

 <!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet"> -->

 <link rel="stylesheet" href="${contextPath}/css/theme.css">

</head>

<body>

  <!-- Back to top button -->
  <div class="back-to-top"></div>

  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
      <div class="container">
        <!--<a href="#" class="navbar-brand">스터디<span class="text-primary">위더스</span></a>-->
        <a href="/swu/app/index"><img src="${contextPath}/img/swu_text.png" alt="LOGO"></a>

        <div class="navbar-collapse collapse" id="navbarContent">
          <ul class="navbar-nav ml-auto">
          
            <li class="nav-item active">
              <c:if test="${loginUser eq null}">
              <a id="open1" class="nav-link" href="#">Sign Up / In</a>
              </c:if>
              <c:if test="${loginUser ne null}">
              <a class="nav-link" href="${contextPath}/app/user/logout">Logout</a>
              </c:if>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${contextPath}/app/freestudy/list">Study</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${contextPath}/app/chargestudy/list">Mentoring</a>
              </li>
              
              <c:if test="${loginUser ne null}">
              <c:if test="${loginUser.userAccessLevel eq 32}">
                <a class="nav-link" href="${contextPath}/app/adminpage/deleterequestlist">Admin Page</a>
              </c:if>
              <c:if test="${loginUser.userAccessLevel ne 32}">
                <a class="nav-link" href="${contextPath}/app/mypage/chargeregisterlist">My Page</a>
              </c:if>
              <li class="nav-item">
              </li>
              </c:if>
            </ul>
           </div>
      </div><!-- container -->
    </nav>
    </header>
    
    
</body>
</html>
