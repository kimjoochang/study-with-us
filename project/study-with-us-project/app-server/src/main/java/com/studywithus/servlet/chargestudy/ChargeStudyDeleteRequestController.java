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
import com.studywithus.domain.Study;

@WebServlet("/deleteRequest")
public class ChargeStudyDeleteRequestController  extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Study chargeStudy = chargeStudyDao.findByNo(no);

      if (chargeStudy == null) {
        throw new Exception("해당 번호의 유료 스터디 회원이 없습니다.");
      }

      chargeStudyDao.delete(no);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
//}
//
//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println("[유료 스터디 / 삭제 요청]\n");
//    int no = (int) request.getAttribute("chargeNo");
//
//    Study chargeStudy = chargeStudyDao.findByNo(no);
//
//    String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
//    System.out.println();
//    while (true) {
//      if (input.equalsIgnoreCase("n") || input.length() == 0) {
//        System.out.println("유료 스터디 삭제 요청을 취소하였습니다.\n");
//        return;
//      } else if (!input.equalsIgnoreCase("y")) {
//        System.out.println("다시 입력하세요.\n");
//        continue;
//      } else {
//        break;
//      }
//    }
//    chargeStudy.setDeleteStatus(1);
//
//    chargeStudyDao.update(chargeStudy);
//    sqlSession.commit();
//
//    System.out.println("삭제 요청이 완료되었습니다.\n");
//  }
//}
