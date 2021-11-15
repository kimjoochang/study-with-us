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

@WebServlet("/user/resetpwd")
public class ResetPasswordController extends HttpServlet {
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

    try {
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");

      Member member = memberDao.findMember(name, email, phoneNumber);

      if (member == null) {
        throw new Exception("해당 정보의 회원이 없습니다.");
      } 

      member.setPassword(request.getParameter("password"));

      memberDao.update(member);
      sqlSession.commit();

      response.sendRedirect("/swu");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("Error.jsp").forward(request, response);

    }
  }
}