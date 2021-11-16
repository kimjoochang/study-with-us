package com.studywithus.web.community;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Member;

@WebServlet("/community/comment/add")
public class CommentAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommentDao commentDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    commentDao = (CommentDao) servletContext.getAttribute("commentDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/swu/user/loginform");
      return;
    }

    try {
      Member writer = (Member) request.getSession(false).getAttribute("loginUser");
      int communityNo = Integer.parseInt(request.getParameter("communityNo"));

      Comment comment = new Comment();
      comment.setCommunityNo(communityNo);
      comment.setContent(request.getParameter("content"));
      comment.setWriter(writer);

      commentDao.insert(comment);

      sqlSession.commit();

      response.sendRedirect("../detail?no="+ communityNo);

      //      request.setAttribute("no", Integer.parseInt(request.getParameter("communityNo")));
      //      request.getRequestDispatcher("/community/detail").forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
