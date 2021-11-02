package com.studywithus.servlet.freestudy;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/updateform")
public class FreeStudyUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudyDao freeStudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Study freeStudy = freeStudyDao.findByNo(Integer.parseInt(request.getParameter("no")));

			if (freeStudy == null) {
				throw new Exception("해당 번호의 무료 스터디가 없습니다.");
			} 

			request.setAttribute("freeStudy", freeStudy);
			request.getRequestDispatcher("FreeStudyUpdateForm.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}
}







