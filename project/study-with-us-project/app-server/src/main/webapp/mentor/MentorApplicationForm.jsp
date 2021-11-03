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

<div id="main">
    <div id="input_form">      
        <form action='apply' target="../index.jsp">
            주제<br> 
        <textarea name="subject" id="textarea" cols="50" rows="10" placeholder="개설할 스터디 주제를 입력해주세요."></textarea><br><br><br>
            자기소개<br> 
        <textarea name="selfIntro" id="textarea" cols="50" rows="10" placeholder="스터디 설명에 들어갈 자기소개를 입력해주세요."></textarea><br><br><br>
        <input id=button type="submit" onclick="window.close()" value="등록">
        <input id=button type="button" onclick="window.close()" value="취소">
        </form>
    </div>
</div>
</body>
</html>