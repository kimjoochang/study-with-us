<%@page import="com.studywithus.domain.Member"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="copyright" content="MACode ID, https://macodeid.com/">

  <title>MY PAGE</title>

  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="../assets/css/bootstrap.css">
  
  <link rel="stylesheet" href="../assets/vendor/animate/animate.css">

  <link rel="stylesheet" href="../assets/css/theme.css">
  
  <link rel="stylesheet" href="../assets/css/my_page_info.css">
  
</head>

<body>

  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
      <div class="container">
        <!--<a href="#" class="navbar-brand">ì¤í°ë<span class="text-primary">ìëì¤</span></a>-->
        <img src="../assets/img/swu_text.png" alt="">

        <div class="navbar-collapse collapse" id="navbarContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="index.html">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="service.html">Study</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="blog.html">Mentoring</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="about.html">Community</a>
            </li>
            <li class="nav-item">
            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation"></button>
            <span class="navbar-toggler-icon"></span>
      </div>
    </nav>


 <!-- <div class="page-section" id="myinfo"> -->
   <div class="container">
       <div class="col-lg-6 py-3 wow fadeInUp">
         
         <span class="subhead">my page</span>
         <!--<h2 class="title-section">ëì ì ë³´</h2>-->
         <div class="divider-sub"></div>
         

         <div class ="both">
          <ul class="my-menu-list">
            <div class="profile-icon">
            
            <img src="../assets/img/profile.png">
          </div>
            <li class ="userid">jeje2kim ë</li>
            <ul class=sub>
            <li><a href="#">ëì ì ë³´</a></li>
            <li><a href="#">ê²°ì  ë´ì­</a></li>
            <li><a href="#">ê´ì¬ ëª©ë¡</a></li>
            <li><a href="#">ëì íë</a></li>
          </ul>
          </ul>
          <div class="content-section"> 

<form>
  <section class="all-contents">

    <div class="subject"> ê°ì¸ ì ë³´ </div>

    <div class="box1">

      <div class="attribute">
      ì¬ì§ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input class="photo" type="text" value="ì¬ì§ì ë±ë¡íì¬ ê³ì  ë§ì¶¤ ì¤ì "></input>
      <!---->
      <input class="file" type="file" name="fileName"></div>
      <hr>
      <div class="box">

      ëë¤ì &nbsp;&nbsp;&nbsp;&nbsp;
      <input class="nickname" value="ì«ì§ë§"></input>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" class="btn btn-primary btn-sm">ë³ê²½</button><br>
      ì´ë©ì¼ &nbsp;&nbsp;&nbsp;&nbsp;
      <input class="email" type="email" name="email" id="email" value="studywithus@gmail.com"></input> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      <button type="button" class="btn btn-primary btn-sm">ë³ê²½</button><br>
      ë¹ë°ë²í¸ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input class="pwd" type="password" name="my_password" id="my_password" value="11111111"></input>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      <button type="button" class="btn btn-primary btn-sm">ë³ê²½</button> 
      </div></div>


      <div class="subject"> ì°ë½ì² ì ë³´ </div>
      <div class="box2">
        
      í´ëí°  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input class="ph" type="text" value="010-1234-5678"></input>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      <button type="button" class="btn btn-primary btn-sm">ë³ê²½</button> 
    </div>
    <div class="box3">
      <span class="with"><button type="button" class="btn btn-secondary btn-sm">íì íí´</button></div></span>
    </div>
  </div>


</section>
</form>



  </div>







        </div>
      </div>
          
        </div>
      </div>
    </div> <!-- .container -->
 <!-- </div> <!-- .page-section -->
    
    
    


<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script src="../assets/js/bootstrap.bundle.min.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>
  
</body>
</html>