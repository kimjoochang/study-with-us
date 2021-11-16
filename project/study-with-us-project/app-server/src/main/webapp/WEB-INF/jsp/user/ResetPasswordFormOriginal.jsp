<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>스터디 위더스</title>
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>

<body>
<form method="post" action='resetpwd'>
<h1>비밀번호 변경 확인</h1>
  <table border = "2  align = "center">
      <caption><h1>비밀번호 변경 확인</h1></caption>
      <tr>
      <th>
        이름
      </th>
      <td>
        <input type = "text" name ="name" placeholder="홍길동" required>
      </td>
      <th>
        이메일
      </th>
    <td>
      <input type = "email" name ="email" placeholder="study@swu.com" required>
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
    
 <button>변경</button> <a href='/swu/index'>[취소]</a> 
</form>

</body>
</html>









