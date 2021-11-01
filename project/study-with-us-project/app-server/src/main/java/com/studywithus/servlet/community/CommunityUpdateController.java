package com.studywithus.servlet.community;

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

@WebServlet("/community/update")
public class CommunityUpdateController extends HttpServlet{

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
    int communityNo = Integer.parseInt(request.getParameter("no"));

    try {
      Community community = communityDao.findByNo(communityNo);

      if (community == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      community.setCategory(community.getCategory());
      community.setTitle(request.getParameter("title"));
      community.setContent(request.getParameter("content"));
      community.setWriter(community.getWriter());

      communityDao.update(community);

      sqlSession.commit();

      request.getRequestDispatcher("detail").forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
