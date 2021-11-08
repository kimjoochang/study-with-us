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
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<style>
ul{
    list-style:none;
  }
  
  .categorys li {
    margin-bottom: 20px;
  border-bottom: solid 2px gray;
}

.side_menu .li{
    margin-top : 4px;
    border:solid 1px;
    border-radius: 20%;
    height: 30px;
}

tr {
  text-align: center;
}

body {
  width : 1000px;
  margin : 0 auto;
}

.side_menu {
  margin-right: 30px;
  float: left;
}
.wrapper_wrapper{
  display:flex;
margin:0 auto;
height:10px;

}

.wrapper {
margin-top :50px;
  display:flex;
}


.list {
  width : 70%;
  position : fixed;
}
</style>

</head>

<body>
<jsp:include page="../header.jsp"></jsp:include>

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
<div class="wrapper_wrapper">
<div>글쓰기</div>
<div class="wrapper">
<div class="side_menu">
  <ul class="categorys">
    <li><a href='list?categoryNo=0'>정보 커뮤니티</a><br>
      <li><a href='list?categoryNo=1'>질문 커뮤니티</a><br>
        <li><a href='list?categoryNo=2'>스몰톡 커뮤니티</a><br>
  </ul>
</div>

  <div><button type='button' id="write_modal">글쓰기</button></div>

			<div class="lists">
			 <table class="table">
			    <thead>
			      <tr>
			        <th scope="col">번호</th>
			        <th scope="col">카테고리</th>
			        <th scope="col">제목</th>
			        <th scope="col">작성자</th>
			        <th scope="col">등록일</th>
			        <th scope="col"></th>
			      </tr>
			    </thead>
			    
          <c:forEach items="${communityList}" var="community">
						<c:choose>
						<c:when test="${community.category eq 0}">
						<c:set var="type" value="정보"/>
						</c:when>
						
						<c:when test="${community.category eq 1}">
						<c:set var="type" value="질문"/>
						</c:when>
						
						<c:when test="${community.category eq 2}">
						<c:set var="type" value="스몰톡"/>
						</c:when>
						</c:choose>
				     <tbody>
				        <tr>
	                <th scope="row">${community.no}</th>
						        <td>${type}</td>
						        <td><a href='detail?no=${community.no}'>${community.title}</a></td>
						        <td>${community.writer.nickname}</td>
						        <td>${community.registeredDate}</td>
						        <td>
						        
		                <ul>
		                  <li>
		                    조회수사진
		                    <span>${community.viewCount}</span>
		                  </li>
		                  <li>
		                    좋아요사진
		                    <span>${community.like}</span>
		                  </li>
		                </ul>
	              </tr>
	           </tbody>
            </c:forEach>
          </table>
         </div>
      </div>
    </div>
</body>
</html>

