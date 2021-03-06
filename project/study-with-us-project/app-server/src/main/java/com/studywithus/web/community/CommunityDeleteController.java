package com.studywithus.web.community;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

@WebServlet("/community/delete")
public class CommunityDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommunityDao communityDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Community community = communityDao.findByNo(no);

      Member loginUser = (Member)request.getSession().getAttribute("loginUser");

      if(loginUser.getNo() == community.getWriter().getNo()) {

        communityDao.delete(no);
        sqlSession.commit();

        response.sendRedirect("list");
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
