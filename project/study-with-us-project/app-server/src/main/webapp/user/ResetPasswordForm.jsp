<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>스터디위더스</title>
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

<form action='user/resetpwd' method="post">

  <h1>비밀번호 변경</h1>
  <table border = 2  align = center>
      <caption><h3>회원정보 확인</h3></caption>
      <tr>
      <th>
        이름
      </th>
      <td>
        <input type = "text" name ="name" placeholder="회원 이름을 입력하세요" required>
      </td>
      <th>
        이메일
      </th>
    <td>
      <input type = "email" name ="email" placeholder="가입 시 사용한 이메일을 입력하세요" required>
    </td>
    <th>
        휴대폰번호
      </th>
    <td>
      <input type = "text" name ="phoneNumber" placeholder="010-1234-5678" required>
    </td>
    <br>
  </tr> 
  </table>
  
 <button>비밀번호 변경하러 가기</button> 
 <button><a href='/swu/'>취소</a></button> 
</form>

</body>
</html>









