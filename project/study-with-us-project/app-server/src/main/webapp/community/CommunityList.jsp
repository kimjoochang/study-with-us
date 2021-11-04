<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 조회</title>
<style type="text/css">
ul{
    list-style:none;
}

li{
    height: 30px;
}

a{  
    text-align: center;
    height:20px;
    width: 50px;
    border-radius: 20%;
    margin-top: 2%;
    margin-right: 20px;
    text-decoration: none;
}

#main{
    display: flex;

}

#input_form{
    padding-top: 5%;
    margin: 0 auto;
}

.modal_content{
        display: none;
        width: 500px;
        height: 500px;
        position: absolute;
        top:50%;
        left: 50%;
        margin: -250px 0 0 -250px;
        background:#eee;
        z-index: 2;
    }
    .modal_background{
        display: none;
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        background-color:rgba(0, 0,0, 0.5);
        top:0;
        left: 0;
        z-index: 1;
    }
    .modal_close{
        width: 26px;
        height: 26px;
        position: absolute;
        top: -30px;
        right: 0;
    }
    .modal_close> a{
        display: block;
        width: 100%;
        height: 100%;
        background:url(https://img.icons8.com/metro/26/000000/close-window.png);
        text-indent: -9999px;
    }
</style>

<script>
    window.onload = function() {
 
    function onClick() {
        document.querySelector('.modal_content').style.display ='block';
        document.querySelector('.modal_background').style.display ='block';
    }   
    function offClick() {
        document.querySelector('.modal_content').style.display ='none';
        document.querySelector('.modal_background').style.display ='none';
    }
 
    document.getElementById('write_modal').addEventListener('click', onClick);
    document.querySelector('.modal_close').addEventListener('click', offClick);
};
</script>
</head>

<body>
<c:choose>
  <c:when test="${categoryNo eq 0}">
<c:set var="type" value="정보"/>
<ul>
<li><a href='list?categoryNo=1'>질문 커뮤니티</a><br>
<li><a href='list?categoryNo=2'>스몰톡 커뮤니티</a><br>
</ul>
  </c:when>
  
  <c:when test="${categoryNo eq 1}">
<c:set var="type" value="질문"/>
<ul>
<li><a href='list?categoryNo=0'>정보 커뮤니티</a><br>
<li><a href='list?categoryNo=2'>스몰톡 커뮤니티</a><br>
</ul>
  </c:when>

  <c:when test="${categoryNo eq 2}">
<c:set var="type" value="스몰톡"/>
<ul>
<li><a href='list?categoryNo=0'>정보 커뮤니티</a><br>
<li><a href='list?categoryNo=1'>질문 커뮤니티</a><br>
</ul>
  </c:when>
</c:choose>

<button type='button' id="write_modal">글쓰기</button>
<div class="modal_background"></div>
  <div class="modal_content">
    <div class="modal_close"><a href="#">닫기</a></div>
    <div>
    
    <c:choose>
<c:when test="${categoryNo eq 0}">
<c:set var="type" value="정보"/>
</c:when>

<c:when test="${categoryNo eq 1}">
<c:set var="type" value="질문"/>
</c:when>

<c:when test="${categoryNo eq 2}">
<c:set var="type" value="스몰톡"/>
</c:when>
</c:choose>

<div id="main">
    <div id="input_form">
        <span>카테고리</span>
        <div id= category_menu>
            <a href='form?categoryNo=0'>정보</a>
            <a href='form?categoryNo=1'>질문</a>
            <a href='form?categoryNo=2'>스몰톡</a>
        </div>
        
        <form action='add' target="CommunityList.jsp">
            카테고리 : ${type}<br>
            <input type='hidden' name='categoryNo' value='${categoryNo}'><br>
            제목 <br><input type='text' name='title' size=48 maxlength=30><br><br>
            내용<br> 
            <textarea name="content" id="textarea" cols="50" rows="10"></textarea><br><br><br>
        <input id=button type="submit" onclick="offClick()" value="등록">
        <input id=button type="button" onclick="offClick()" value="취소">
        </form>
    </div>
</div>
    </div>
  </div>



<!--<input type="button" value="글쓰기" onclick="addCommunity();"/> -->
<br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>카테고리</th>
    <th>제목</th>
    <th>작성자</th>
    <th>조회수</th>
    <th>좋아요</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${communityList}" var="community">
<tr>
    <td>${community.no}</td>
    <td>${type}</td>
    <td><a href='detail?no=${community.no}'>${community.title}</a></td> 
    <td>${community.writer.email}</td> 
    <td>${community.viewCount}</td> 
    <td>${community.like}</td> 
    <td>${community.registeredDate}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>

