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
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/detail")
public class FreeStudyDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudyDao freeStudyDao;
	StudyMemberDao studyMemberDao;
	SqlSession sqlSession;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
		studyMemberDao = (StudyMemberDao) servletContext.getAttribute("studyMemberDao");
		sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int freeStudyNo;
			int likeResult; // 좋아요 여부
			int participateResult = 0; // 스터디 참여 여부

			// 요청이 detail.jsp에서 왔다면
			if (request.getAttribute("no") == null) {
				freeStudyNo = Integer.parseInt(request.getParameter("no"));
				freeStudyDao.updateCount(freeStudyNo);

				// 다른 서블릿에서 왔다면
			} else {
				freeStudyNo = (int) request.getAttribute("no");
			}

			Member member = (Member) request.getSession().getAttribute("loginUser");

			if (member == null) {
				likeResult = 0;

			} else {
				// 좋아요 여부
				likeResult =  freeStudyDao.checkLikesByMember(member.getNo(), freeStudyNo);

				Member participant = studyMemberDao.findByNoMember(member.getNo(), freeStudyNo, Study.APPLICANT_STATUS);

				if (participant == null) {
					participateResult = 0;

				} else {
					participateResult = 1;
				}
			}

			Study freeStudy = freeStudyDao.findByNo(freeStudyNo);

			//	jsp에서 무료 스터디 작성자랑 로그인한 사람이 같은지 다른지에 따라 메뉴 다르게 출력하기 위해
			int num = checkWriter(member,freeStudy.getWriter().getNo());

			request.setAttribute("freeStudy", freeStudy);
			request.setAttribute("result", likeResult);
			request.setAttribute("participateResult", participateResult);
			request.setAttribute("checkWriter", num);

			sqlSession.commit();

			request.getRequestDispatcher("FreeStudyDetail.jsp").forward(request, response);

		} catch (Exception e) {
			sqlSession.rollback();
			System.out.println(e.getMessage());
			request.setAttribute("error", e);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}

	private int checkWriter(Member writer, int no) {
		// 로그인을 안했다면
		if (writer == null) {
			return 0;

			// 게시글 작성자라면
		} else if (writer.getNo() == no) {
			return 1;

			// 작성자가 아니라면
		} else {
			return 2;
		}
	}
}