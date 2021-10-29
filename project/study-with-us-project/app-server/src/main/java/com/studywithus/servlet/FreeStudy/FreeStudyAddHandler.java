package com.studywithus.servlet.FreeStudy;

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
import com.studywithus.servlet.user.AuthLogInController;

public class FreeStudyAddHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudyDao StudyDao;
	SqlSession sqlSession;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
		StudyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("  <title>무료스터디 등록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>무료스터디 등록결과</h1>");

		Study freeStudy = new Study();

		freeStudy.setWriter(AuthLogInController.getLoginUser());
		freeStudy.setOnOffLine(Integer.parseInt(request.getParameter("onOffLine")));
		freeStudy.setArea(request.getParameter("area"));
		freeStudy.setTitle(request.getParameter("title"));
		freeStudy.setContent(request.getParameter("content"));
		freeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));

		while (true) {
			try {
				freeStudy.setStartDate(request.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));
			} catch (IllegalArgumentException e) {
				System.out.println("다시 입력하세요.\n");
				continue;
			}

			break;
		}

		while (true) {
			try {
				freeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

			} catch (IllegalArgumentException e) {
				System.out.println("다시 입력하세요.\n");
				continue;
			}

			break;
		}
		try {

			StudyDao.insert(freeStudy);
			sqlSession.commit();

			out.println("무료스터디 등록이 완료되었습니다.\n");

			out.println("<a href='list'>[목록]</a><br>");

		} catch (Exception e) {
			out.println("목록 조회 오류!");
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");

		// 리프래시(refresh)
		// 웹브라우저에게 서버가 보내준 HTML을 출력한 후 
		// 1초가 경과하면 지정한 URL을 다시 요청하도록 명령한다.
		response.setHeader("Refresh", "1;url=list");
	}
}


