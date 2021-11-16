package com.studywithus.web.freestudy;

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

@WebServlet("/freestudy/interest/delete")
public class FreeStudyInterestDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudyDao freeStudyDao;
	SqlSession sqlSession;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int freeStudyNo = Integer.parseInt(request.getParameter("no"));

			Member member = (Member) request.getSession().getAttribute("loginUser");

			if (member == null) {
				response.sendRedirect("list");
			}

			freeStudyDao.deleteInterest(member.getNo(), freeStudyNo);

			sqlSession.commit();

			response.sendRedirect("../detail?no=" + freeStudyNo);

			//      request.setAttribute("no", chargeStudyNo);
			//      request.getRequestDispatcher("/chargestudy/detail").forward(request, response);

		} catch (Exception e) {
			sqlSession.rollback();
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}
}
