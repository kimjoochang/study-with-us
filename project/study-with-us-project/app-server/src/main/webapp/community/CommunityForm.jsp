<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>커뮤니티</title>
</head>

<style>
#menu_bar{
  float:left;
}


ul{
  list-style:none;
}

#main{
  width:100%;
}
</style>
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
카테고리 : ${type}<br>

<div id="main">
    <div id="input_form">
        <span>카테고리</span>
        <div id= category_menu>
            <a href='form?categoryNo=0'>정보</a>
            <a href='form?categoryNo=1'>질문</a>
            <a href='form?categoryNo=2'>스몰톡</a>
        </div>
        
        <form action='add'>
            카테고리 : ${type}<br>
            <input type='hidden' name='categoryNo' value='${categoryNo}'><br>
            제목 <br><input type='text' name='title' size=48 maxlength=30><br><br>
            내용<br> 
            <textarea name="content" id="textarea" cols="50" rows="10"></textarea><br><br><br>
        </form>
        <input id=button type="button" onclick="location.href='add'" value="등록">
        <input id=button type="button" onclick="location.href='list?no=0'" value="취소">
    </div>
</div>
</body>
</html>