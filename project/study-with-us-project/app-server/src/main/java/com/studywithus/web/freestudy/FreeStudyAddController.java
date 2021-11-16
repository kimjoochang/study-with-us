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
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/add")
public class FreeStudyAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SqlSession sqlSession;
	StudyMemberDao studyMemberDao;
	StudyDao StudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
		StudyDao = (StudyDao) servletContext.getAttribute("studyDao");
		studyMemberDao = (StudyMemberDao) servletContext.getAttribute("studyMemberDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Study freeStudy = new Study();
		Member writer = (Member) request.getSession(false).getAttribute("loginUser");

		freeStudy.setWriter(writer);
		freeStudy.setOnOffLine(Integer.parseInt(request.getParameter("onOffLine")));
		freeStudy.setArea(request.getParameter("h_area1")+request.getParameter("h_area2"));
		freeStudy.setCategory(request.getParameter("category"));
		freeStudy.setTitle(request.getParameter("title"));
		freeStudy.setContent(request.getParameter("content"));
		freeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));
		freeStudy.setStartDate(Date.valueOf(request.getParameter("startDate")));
		freeStudy.setEndDate(Date.valueOf(request.getParameter("endDate")));

		try {
			StudyDao.insert(freeStudy);
			studyMemberDao.insert(writer.getNo(), freeStudy.getNo(), Study.OWNER_STATUS);
			sqlSession.commit();

			response.sendRedirect("list");

		} catch (Exception e) {
			sqlSession.rollback();
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}
}


