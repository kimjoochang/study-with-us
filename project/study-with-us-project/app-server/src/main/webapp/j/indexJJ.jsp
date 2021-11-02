<%@page import="com.studywithus.servlet.user.AuthLogInController"%>
<%@page import="com.studywithus.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>STUDY WITH US</title>
<header> 
  

    <h1>STUDY WITH US</h1>
    <div class="logo"> <h1><a href="링크"></a></h1></div>
     <div class="search"> 
         <s_sidebar_element> <s_search> 
         <input type="text" name="serach_keyword" value="키워드를 입력하세요" 
        onkeypress="if (event.keyCode == 13) { [##_search_onclick_submit_##] }" placeholder="Search Keyword" /> <button type="button" onclick="[##_search_onclick_submit_##]" class="submit"> <i class="material-icons">search</i>
         </button> </s_search> </s_sidebar_element> 
    </div> 

   
</header>

<style>
    h1{
        font-size: 40px;
        margin-top: 40px;
        text-align: center;
    }
   .non-member-menu{
    font-size: 20px;
    text-align: center;
    margin-top: 20px;
    margin-bottom: 20px;
   }
   .member-menu{
    background-color: tomato;
    font-size: 20px;
    text-align: center;
    margin-top: 20px;
    margin-bottom: 20px;
   }
   .search{
    text-align: center;
   }
.sticky_nav{
    text-align: right;
}
</style>

<nav class="sticky_nav"> <ul class="menu"> 
    <a href="[##_rss_url_##]">RSS</a>
    <a href="[##_taglog_link_##]">Tag</a>
    <a href="[##_guestbook_link_##]">GuestBook</a></ul> 
</nav>
</head>
<body>
<%
Member loginUser = (Member) session.getAttribute("loginUser");
if (loginUser == null) {
%>

<div class="non-member-menu">
<ul>
<a href='user/joinform'>회원가입</a><br>
<a href='user/loginform'>로그인</a><br>
<a href='community/list?categoryNo=0'>커뮤니티</a><br>
<a href='freestudy/list'>무료 스터디</a><br>
<a href='chargestudy/list'>유료 스터디</a><br>
</ul>
</div>

<% } else { %>
<br><h2>나의 정보</h2>
<%=loginUser.getName()%> 님 환영합니다!

<div class="non-member-menu">
<ul>
<a href='community/list?categoryNo=0'>커뮤니티</a><br>
<a href='freestudy/list'>무료 스터디</a><br>
<a href='chargestudy/list'>유료 스터디</a><br>
<a href='logout'>[로그아웃]</a><br>
</ul>
</div>

</body>
</html>
<% } %>
