package com.studywithus.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

@WebServlet("/member/add")
public class SignUpHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>회원가입</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원가입 결과</h1>");

    Member member = new Member();

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String phoneNumber = request.getParameter("phoneNumber");

    member.setName(request.getParameter("name"));
    member.setEmail(email);
    member.setPassword(password);
    member.setPhoneNumber(phoneNumber);
    try {
      int type = memberDao.findByEmail(email);

      if (type == 1) {
        out.println("중복된 아이디가 있습니다.<br>");

      } else if (!email.contains("@") || !email.contains(".com")){
        out.println("이메일 형식의 아이디를 입력하세요.<br>");

      } else if (password.length() < 8 || password.length() > 16) {
        out.println("비밀번호는 8자 이상 16자 이하로 설정 가능합니다.<br>");

      } else if (!password.contains("!") && !password.contains("@") && !password.contains("$") && !password.contains("^")) {
        out.println("비밀번호는 다음의 특수문자를 하나 이상 포함해야 합니다.(!,@,$,^)<br>");

      }  else if (password.contains(email) == true) {
        out.println("아이디를 포함한 비밀번호는 사용하실 수 없습니다.<br>");

      }  else if (password.contains(phoneNumber) == true) {
        out.println("휴대폰 번호를 포함한 비밀번호는 사용하실 수 없습니다.<br>");

      } else if (phoneNumber.length() != 11) {
        out.println("올바른 형식의 휴대폰 번호를 입력하세요.<br>");

      }  else {

        memberDao.insert(member);
        sqlSession.commit();

        out.println("회원가입이 완료되었습니다.\n");
        out.println("<br> <a href='/swu'>[메인]</a><br>");
      } 
      out.println("<br> <a href='/swu'>[메인]</a><br>");
      out.println("<br> <a href='/swu/member/form'>[회원가입]</a><br>");
    } catch (Exception e) {
      out.println("<br> <a href='/swu'>[메인]</a><br>");
      throw new ServletException();
    } 

    out.println("</body>");
    out.println("</html>");
  }

}
