package com.studywithus.servlet.FreeStudy;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/detail")
public class FreeStudyDetailController extends GenericServlet {
	private static final long serialVersionUID = 1L;

	StudyDao studyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		studyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		try {
			int no = Integer.parseInt(request.getParameter("no"));
			Study freeStudy = studyDao.findByNo(no);

			if (freeStudy == null) {
				throw new Exception("해당 번호의 무료 스터디가 없습니다.");
			} 

			request.setAttribute("freestudy", freeStudy);
			request.getRequestDispatcher("/freestudy/FreeStudyDetail.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}
}
