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

@WebServlet("/user/delete")
public class MembershipWithdrawalController extends HttpServlet {
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
    /*
     * System.out.println("회원 탈퇴를 위해 회원 정보를 다시 한 번 입력해주세요.\n");
     * 
     * String email = Prompt.inputString("이메일: "); String password = Prompt.inputString("비밀번호: ");
     * 
     * Member loginUser = AuthLogInHandler.getLoginUser();
     * 
     * if (!loginUser.getEmail().equals(email) || !loginUser.getPassword().equals(password)) {
     * System.out.println("현재 로그인한 정보와 일치하지 않습니다.\n"); return; }
     */
    try {

      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberDao.findByNo(no);

      // Member loginUser = (Member)request.getSession().getAttribute("loginUser");
      // if(loginUser.getNo() == member.getNo()) {

      if (member == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      //  if(loginUser.getNo() == no) {
      memberDao.delete(no);
      sqlSession.commit();
      //    }

      response.sendRedirect("../");

    } catch (Exception e) {
      sqlSession.rollback();
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
} 

