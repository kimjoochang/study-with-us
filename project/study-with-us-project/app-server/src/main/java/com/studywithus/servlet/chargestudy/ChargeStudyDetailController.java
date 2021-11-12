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
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/detail")
public class ChargeStudyDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int chargStudyNo;
      int result;
      // 요청이 detail.jsp에서 왔다면
      if (request.getAttribute("no") == null) {
        chargStudyNo = Integer.parseInt(request.getParameter("no"));
        chargeStudyDao.updateCount(chargStudyNo);

        // 다른 서블릿에서 왔다면
      } else {
        chargStudyNo = (int) request.getAttribute("no");
      }

      Member member = (Member) request.getSession().getAttribute("loginUser");

      if (member != null) {
        result =  chargeStudyDao.checkLikesByMember(member.getNo(), chargStudyNo);
      } else {
        result = 0;
      }

      Study chargeStudy = chargeStudyDao.findByNo(chargStudyNo);

      //    jsp에서 무료 스터디 작성자랑 로그인한 사람이 같은지 다른지에 따라 메뉴 다르게 출력하기 위해
      int num = checkWriter(member,chargeStudy.getWriter().getNo());

      request.setAttribute("chargeStudy", chargeStudy);
      request.setAttribute("result", result);
      request.setAttribute("checkWriter", num);

      sqlSession.commit();

      request.getRequestDispatcher("/chargestudy/ChargeStudyDetail.jsp").forward(request, response);

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

