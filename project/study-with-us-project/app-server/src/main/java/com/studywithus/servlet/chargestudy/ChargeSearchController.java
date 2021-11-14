package com.studywithus.servlet.chargestudy;

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

@WebServlet("/chargestudy/search")
public class ChargeSearchController extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	StudyDao chargeStudyDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		chargeStudyDao = (StudyDao) servletContext.getAttribute("chargeStudyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");

		try {
			Collection <Study> chargeStudyList = chargeStudyDao.findByKeyword(keyword);

			request.setAttribute("chargeStudyList", chargeStudyList);

			request.getRequestDispatcher("ChargeStudyList.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}  
}
