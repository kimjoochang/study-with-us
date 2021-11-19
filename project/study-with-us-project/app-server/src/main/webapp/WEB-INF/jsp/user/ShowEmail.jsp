<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.studywithus.dao.MemberDao"%>  
<%@page import="com.studywithus.domain.Member"%>  


<!DOCTYPE html>
<html>
<head>
  <title>스터디위더스 : 아이디찾기결과</title>
</head>

<body>
  <!--
    <h1>아이디 찾기 결과</h1>
    
    <c:if test="${email ne null}">
      회원님의 아이디는 <c:out value="${email}"/> 입니다.
    </c:if>
  -->

  <div class="alert alert-success" role="alert">
    <h4 class="alert-heading">아이디 찾기 결과</h4>
    <c:if test="${email ne null}">

    <p>회원님의 아이디는 <c:out value="${email}"/> 입니다.</p>
    </c:if>
    
    <c:if test="${email eq null}">
      일치하는 정보를 찾을 수 없습니다.
    </c:if>   
  </div>
  
  <hr>
  
  <br><button>로그인</button> <button>비밀번호 찾기</button>

</body>
</html>
