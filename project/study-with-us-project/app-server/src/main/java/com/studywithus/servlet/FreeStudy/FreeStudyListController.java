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
			// 클라이언트 요청을 처리하는데 필요한 데이터 준비
			Collection<Study> freeStudyList = studyDao.findAll();

			// 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
			request.setAttribute("freeStudyList", freeStudyList); // ?
			request.getRequestDispatcher("freeStudy.jsp").forward(request, response); // ?

		} catch (Exception e) {
			// 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
			request.setAttribute("error", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}
}
