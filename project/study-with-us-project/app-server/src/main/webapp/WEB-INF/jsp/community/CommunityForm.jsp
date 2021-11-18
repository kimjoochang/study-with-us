<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>커뮤니티</title>
</head>

<style>
body {
    margin: 0 auto;
    width:1000px;
}

#header{
    margin-top: 50px;
    display: flex;
    height: 50px;
}

#logo{
    margin-left: 50px;
    width: 60%;
}

#category_menu{
    display: flex;
    height: 15%;
}

#menu {
    width:10%;
}

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
    border : solid 2px green;
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

#button{
    float:right;
    margin-right: 10px;
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

<div id="main">
    <div id="input_form">
        <span>카테고리</span>
        <div id= category_menu>
            <a href='form?categoryNo=0'>정보</a>
            <a href='form?categoryNo=1'>질문</a>
            <a href='form?categoryNo=2'>스몰톡</a>
        </div>
        
        <form action='add' method='post'">
            카테고리 : ${type}<br>
            <input type='hidden' name='categoryNo' value='${categoryNo}'><br>
            제목<br><input type='text' name='title' size=48 maxlength=30><br><br>
            내용<br> 
            <textarea name="content" id="textarea" cols="50" rows="10"></textarea><br><br><br>
        <input id=button type="submit" onclick="window.close()" value="등록">
        <input id=button type="button" onclick="window.close()" value="취소">
        </form>
    </div>
</div>
</body>
</html>