package com.studywithus.servlet.FreeStudy;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/add")
public class FreeStudyAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SqlSession sqlSession;
	StudyDao StudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
		StudyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession Session = request.getSession(false);

		if (Session.getAttribute("loginUser") == null) {
			response.sendRedirect("list");
			return;
		}

		//		response.setContentType("text/html;charset=UTF-8");
		//		PrintWriter out = response.getWriter();

		Study freeStudy = new Study();
		Member writer = (Member) request.getSession(false).getAttribute("logginUser");

		//		freeStudy.setWriter(AuthLogInController.getLoginUser());
		freeStudy.setOnOffLine(Integer.parseInt(request.getParameter("onOffLine")));
		freeStudy.setArea(request.getParameter("area"));
		freeStudy.setTitle(request.getParameter("title"));
		freeStudy.setContent(request.getParameter("content"));
		freeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));
		freeStudy.setStartDate(Date.valueOf(request.getParameter("startDate")));
		freeStudy.setEndDate(Date.valueOf(request.getParameter("endDate")));

		try {
			StudyDao.insert(freeStudy);
			sqlSession.commit();

			response.sendRedirect("list");

		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);

		}
		//		response.setHeader("Refresh", "1;url=list");
	}
}


