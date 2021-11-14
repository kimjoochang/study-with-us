package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/list")
public class ChargeStudyListController extends GenericServlet {
	private static final long serialVersionUID = 1L;

	StudyDao chargeStudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		try {
			Collection<Study> chargeStudyList = chargeStudyDao.findAll(1,10000000);

			request.setAttribute("chargeStudyList", chargeStudyList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChargeStudyList.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
