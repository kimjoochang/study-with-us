<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디위더스 / 회원탈퇴</title>
<h1>회원탈퇴</h1>
<script type="text/javascript">
        // 비밀번호 미입력시 경고창
        function checkValue(){
            if(!document.deleteform.password.value){
                alert("비밀번호를 입력하지 않았습니다.");
                return false;
            }
        }
    </script>
    
</head>
<body>
 
    <br><br>
    <b><font size="6" color="gray">내 정보</font></b>
    <br><br><br>
 
    <form name="membershipWithdrawalForm" method="post" action="/DeleteMember.jsp"
        onsubmit="return checkValue()">
 
        <table>
            <tr>
                <td bgcolor="skyblue">비밀번호</td>
                <td><input type="password" name="password" maxlength="50"></td>
            </tr>
        </table>
        
        <br> 
        <input type="button" value="취소" onclick="javascript:window.location='MainForm.jsp'">
        <input type="submit" value="탈퇴" /> 
    </form>
</head>
<body>

</body>
</html>