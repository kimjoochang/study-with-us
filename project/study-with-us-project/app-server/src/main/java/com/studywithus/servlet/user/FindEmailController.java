package com.studywithus.servlet.user;

import java.io.IOException;
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

@WebServlet("/user/findemail")
public class FindEmailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String name = request.getParameter("name");
    String phoneNumber = request.getParameter("phoneNumber");
    //  String email = null;

    try {
      Member member =  memberDao.findMemberByNamePhoneNumber(name, phoneNumber);

      if (member != null) {

        String email = member.getEmail();

        int index = email.length()-3; // 
        String tempEmail = email.substring(0,3); //0~3번 문자까지 보여주고

        for(int i = 0; i < index; i++) {
          tempEmail += "*";} // 나머지 *처리

        email = tempEmail; // 별표처리된 이메일 대입
        //request.setAttribute("email", email);
        request.setAttribute("email", member.getEmail());
        request.getRequestDispatcher("ShowEmail.jsp").forward(request, response);

        sqlSession.commit();

        // 얘를 뷰에 넣어야하는건가?....
        // System.out.println(name + " 회원님의 아이디는 " + member.getEmail() + " 입니다.\n");

      }
      response.sendRedirect("/swu");

    } catch (Exception e) {
      sqlSession.rollback();
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/../Error.jsp").forward(request, response);
    }
  }
}
