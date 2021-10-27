package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyAddHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    chargeStudyDao = (StudyDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>유료스터디 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>유료스터디 등록결과</h1>");


    Study chargeStudy = new Study();

    chargeStudy.setWriter(AuthLogInHandler.getLoginUser());
    chargeStudy.setArea(Prompt.inputString("지역: "));
    chargeStudy.setTitle(Prompt.inputString("스터디 제목: "));
    chargeStudy.setContent(Prompt.inputString("스터디 설명: "));
    chargeStudy.setMaxMembers(Prompt.inputInt("모집 인원: "));
    chargeStudy.setPrice(Prompt.inputInt("가격: " ));

    while (true) {
      try {
        chargeStudy.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

      } catch (IllegalArgumentException e) {
        System.out.println("다시 입력하세요.\n");
        continue;
      }

      break;
    }

    while (true) {
      try {
        chargeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      } catch (IllegalArgumentException e) {
        System.out.println("다시 입력하세요.\n");
        continue;
      }

      break;
    }

    chargeStudyDao.insert(chargeStudy);
    sqlSession.commit();

    System.out.println();
    System.out.println("유료스터디 등록이 완료되었습니다.\n");
  }

}

