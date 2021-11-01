package com.studywithus.servlet.community;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommentDao;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

@WebServlet("/community/detail")
public class CommunityDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommunityDao communityDao;
  CommentDao commentDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
    commentDao = (CommentDao) servletContext.getAttribute("commentDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int communityNo;

      // 요청이 detail.jsp에서 왔다면
      if (request.getAttribute("no") == null) {
        communityNo = Integer.parseInt(request.getParameter("no"));
        communityDao.updateCount(communityNo);

        // 댓글 추가 서블릿에서 왔다면
      } else {
        communityNo = (int) request.getAttribute("no");
      }

      Community community = communityDao.findByNo(communityNo);
      List<Comment> comments= commentDao.findAll(communityNo);

      //      jsp에서 커뮤 작성자랑 로그인한 사람이 같은지 다른지에 따라 메뉴 다르게 출력하기 위해
      int num = checkWriter((Member) request.getSession(false).getAttribute("loginUser"),
          community.getWriter().getNo());

      request.setAttribute("checkWriter", num);
      request.setAttribute("community", community);
      request.setAttribute("comments", comments);

      sqlSession.commit();

      request.getRequestDispatcher("CommunityDetail.jsp").forward(request, response);

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }

  private int checkWriter(Member writer, int no) {
    // 로그인을 안했다면
    if (writer == null) {
      return 0;

      // 게시글 작성자라면
    } else if (writer.getNo() == no) {
      return 1;

      // 작성자가 아니라면
    } else {
      return 2;
    }
  }
}