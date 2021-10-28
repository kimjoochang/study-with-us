<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.MemberDao"%>
<%@page import="com.studywithus.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Member member = new Member();

String email = request.getParameter("email");
String password = request.getParameter("password");
String phoneNumber = request.getParameter("phoneNumber");

member.setName(request.getParameter("name"));
member.setEmail(email);
member.setPassword(password);
member.setPhoneNumber(phoneNumber);

int type = memberDao.findByEmail(email);

int count = 0;

if (type == 1) {%>
  중복된 아이디가 있습니다.<br>

<%} else if (!email.contains("@") || !email.contains(".com")){%>
  이메일 형식의 아이디를 입력하세요.<br>

  <%} else if (password.length() < 8 || password.length() > 16) {%>
  비밀번호는 8자 이상 16자 이하로 설정 가능합니다.<br>

  <%} else if (!password.contains("!") && !password.contains("@") && !password.contains("$") && !password.contains("^")) { %>
  비밀번호는 다음의 특수문자를 하나 이상 포함해야 합니다.(!,@,$,^)<br>

  <%}  else if (password.contains(email) == true) {%>
  아이디를 포함한 비밀번호는 사용하실 수 없습니다.<br>

  <%}  else if (password.contains(phoneNumber) == true) {%>
  휴대폰 번호를 포함한 비밀번호는 사용하실 수 없습니다.<br>

  <%} else if (phoneNumber.length() != 11) {%>
  올바른 형식의 휴대폰 번호를 입력하세요.<br>

  <%}  else {
  count ++;
  memberDao.insert(member);
  sqlSession.commit();
}

if (count == 0) {
  response.sendRedirect("JoinForm.jsp");
  
} else {
  response.sendRedirect("../index.jsp");
  
}

%>

<%!
MemberDao memberDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext servletContext = config.getServletContext();
  sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  memberDao = (MemberDao) servletContext.getAttribute("memberDao");
}

%>