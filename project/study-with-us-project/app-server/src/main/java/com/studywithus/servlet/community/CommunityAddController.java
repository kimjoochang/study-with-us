package com.studywithus.servlet.community;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.user.AuthLogInHandler;

public class CommunityAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommunityDao communityDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

    request.getSess
    Community community = new Community();

    community.setCategory(categoryNo);
    community.setTitle(request.getParameter("title"));
    community.setContent(request.getParameter("content"));
    community.setWriter(AuthLogInHandler.getLoginUser());

    communityDao.insert(community);

    sqlSession.commit();

    System.out.println();
    System.out.println("커뮤니티 등록이 완료되었습니다.");
  }
}
