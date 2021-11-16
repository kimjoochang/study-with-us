package com.studywithus.web.freestudy;

import java.io.IOException;
import java.sql.Date;

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

@WebServlet("/freestudy/update")
public class FreeStudyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SqlSession sqlSession;
	StudyDao freeStudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
		freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));

		try {
			Study freeStudy = freeStudyDao.findByNo(no);

			if (freeStudy == null) {
				throw new Exception("해당 번호의 무료 스터디가 없습니다.");
			} 

			//			freeStudy.setOnOffLine(Integer.parseInt(request.getParameter("onOffLine")));
			//			freeStudy.setArea(request.getParameter("area"));
			freeStudy.setTitle(request.getParameter("title"));
			freeStudy.setContent(request.getParameter("content"));
			freeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));
			freeStudy.setStartDate(Date.valueOf(request.getParameter("startDate")));
			freeStudy.setEndDate(Date.valueOf(request.getParameter("endDate")));

			freeStudy.setWriter(freeStudy.getWriter());
			freeStudy.setOnOffLine(freeStudy.getOnOffLine());
			freeStudy.setArea(freeStudy.getArea());
			freeStudy.setRegisteredDate(freeStudy.getRegisteredDate());

			freeStudyDao.update(freeStudy);

			sqlSession.commit();

			request.getRequestDispatcher("detail").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}
}







