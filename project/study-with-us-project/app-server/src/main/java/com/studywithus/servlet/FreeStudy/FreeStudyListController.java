package com.studywithus.servlet.FreeStudy;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/freeStudy/list")
public class FreeStudyListController extends GenericServlet {
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
			// 클라이언트한테 요청받을 때 쓸 변수
			int freeStudyNo = Integer.parseInt(request.getParameter("no"));

			Collection<Study> freeStudyList = studyDao.findAll();

			request.setAttribute("freeStudyNo", freeStudyNo); 

			if (freeStudyList == null) {
				request.getRequestDispatcher("FreeStudyList.jsp").forward(request, response);
			}

			request.setAttribute("freeStudyList", freeStudyList);
			request.getRequestDispatcher("FreeStudyList.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}
}
