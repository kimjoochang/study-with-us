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
import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Member;

@WebServlet("/community/comment/delete")
public class CommentDeleteController extends HttpServlet {
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

    try {
      int commentNo = Integer.parseInt(request.getParameter("commentNo"));

      Comment comment = commentDao.findByNo(commentNo);

      Member loginUser = (Member)request.getSession().getAttribute("loginUser");

      if(loginUser.getNo() == comment.getWriter().getNo()) {
        commentDao.delete(commentNo);
        sqlSession.commit();
      }

      request.setAttribute("no", Integer.parseInt(request.getParameter("communityNo")));
      request.getRequestDispatcher("/community/detail").forward(request, response);

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
