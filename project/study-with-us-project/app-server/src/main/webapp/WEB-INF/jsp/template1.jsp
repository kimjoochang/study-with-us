<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle}</title>
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="stylesheet" href="${contextRoot}/css/bootstrap.css">

<link rel="stylesheet" href="${contextRoot}/css/theme.css">

<link rel="stylesheet" href="${contextRoot}/css/footer.css">

<link rel="stylesheet" href="${contextRoot}/css/community/CommunityList.css">

</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<div class="container">

<div id="content">
<jsp:include page="${contentUrl}"/>
</div><!-- #content --> 


<jsp:include page="/footer.jsp"></jsp:include>
</div><!-- .container -->
</body>
</html>