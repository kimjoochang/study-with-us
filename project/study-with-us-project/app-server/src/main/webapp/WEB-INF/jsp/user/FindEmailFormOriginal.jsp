<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>스터디위더스</title>
</head>

<body>
<!-- <form method ="post" action="findemail"> 테스트용 주석처리-->
  <table border = "2"  align = "center">
      <caption><h1>아이디 찾기</h1></caption>
      <tr>
      <th>
        이름
      </th>
      <td>
        <input type = "text" name ="name" placeholder="홍길동" required>
      </td>
      <th>
        휴대폰번호
      </th>
    <td>
      <input type = "text" name ="phoneNumber" placeholder="010-1234-5678" required>
    </td>
    <br>
    <td>
      <th colspan="3">
        <input type="submit" value="찾기"> 
      </th>
    </td>
  </tr>
  
  </table>
  <div class="button">
  
  <c:if test="${email ne null}">
  <c:if test="${email ne 0}">
     회원님의 아이디는 <c:out value="${email}"/> 입니다.
     <a>비밀번호 찾기</a> <br><br><br><br><br><br> <a>메인</a> 
  </c:if>
  
  <c:if test="${email eq 0}">
    일치하는 정보를 찾을 수 없습니다.
  </c:if>
</c:if>  
</div>
</form>     

</body>
</html>
