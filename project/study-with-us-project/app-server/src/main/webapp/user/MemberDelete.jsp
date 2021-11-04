<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.studywithus.dao.MemberDao" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
</head>
<body>
  <%
        String id= (String)session.getAttribute("email"); 
        String pw = request.getParameter("password");
        
        
        MemberDao memberDao = MemberDao.findByNo();
        int check = memberDao.delete();
        
        if(check == 1){
            session.invalidate(); 
    %>
        <br><br>
        <b><font size="4" color="gray">회원정보가 삭제되었습니다.</font></b>
        <br><br><br>
    
        <input type="button" value="확인" onclick="index.jsp'">
    
    <%    
  
        }else{
    %>
        <script>
            alert("비밀번호가 맞지 않습니다.");
            history.go(-1);
        </script>
    <%
        } 
    %>

</body>
</html>