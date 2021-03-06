package com.studywithus.web.freestudy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/freestudy/form")

public class FreeStudyAddFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession Session = request.getSession();

		if (Session.getAttribute("loginUser") == null) {
			response.sendRedirect("/swu/user/loginform");
			return;
		}

		request.getRequestDispatcher("/freestudy/FreeStudyAddForm.jsp").forward(request, response);
	}
}
