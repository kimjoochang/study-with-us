package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.DeleteRequestFormDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/deletecancel")

public class ChargeStudyDeleteCancelController  extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  SqlSession sqlSession;
  DeleteRequestFormDao deleteRequestFormDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    deleteRequestFormDao = (DeleteRequestFormDao) servletContext.getAttribute("deleteRequestFormDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Study chargeStudy = chargeStudyDao.findByNo(no);

      Member member = (Member) request.getSession().getAttribute("loginUser");

      if (member == null) {
        response.sendRedirect("/swu/user/loginform");

      } else if (member.getNo() != chargeStudy.getWriter().getNo()) {
        throw new Exception("삭제권한이 없습니다.");
      }

      if (chargeStudy == null) {
        throw new Exception("해당 번호의 유료 스터디가 존재하지 않습니다.");
      }

      chargeStudy.setDeleteStatus(0);

      chargeStudyDao.update(chargeStudy);
      deleteRequestFormDao.delete(no);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      System.out.println(e.getMessage());
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}