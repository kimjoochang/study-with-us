package com.studywithus.servlet.FreeStudy;

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
import com.studywithus.domain.Study;

@WebServlet("/freestudy/delete")
public class FreeStudyDeleteController  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudyDao freeStudyDao;
	SqlSession sqlSession;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
		freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int no = Integer.parseInt(request.getParameter("no"));
			Study chargeStudy = freeStudyDao.findByNo(no);

			if (chargeStudy == null) {
				throw new Exception("해당 번호의 무료 스터디가 존재하지 않습니다.");
			}

			freeStudyDao.delete(no);
			sqlSession.commit();

			response.sendRedirect("list");

		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}
}