package com.studywithus.servlet.freestudy;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/search")
public class FreeStudySearchController extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	StudyDao freeStudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		freeStudyDao = (StudyDao) servletContext.getAttribute("freeStudyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");

		int freeStudyNo = 0;

		try {
			Collection<Study> freeStudyList = freeStudyDao.findByKeyword(keyword, freeStudyNo);

			request.setAttribute("freeStudyList", freeStudyList);
			request.setAttribute("freeStudyNo", freeStudyNo);

			request.getRequestDispatcher("freeStudyList.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}  
}
