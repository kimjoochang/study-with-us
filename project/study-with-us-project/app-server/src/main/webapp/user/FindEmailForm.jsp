<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>스터디 위더스</title>
</head>

<body>
<form method = "post" action="ShowEmail.jsp">
  <table border = "1"  align = "center">
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
  
</div>
</form>     

</body>
</html>
