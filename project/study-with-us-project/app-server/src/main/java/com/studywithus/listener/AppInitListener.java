package com.studywithus.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.studywithus.dao.CommentDao;
import com.studywithus.dao.CommunityDao;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.StudyDao;

@WebListener
public class AppInitListener implements ServletContextListener {

  SqlSession sqlSession = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("애플리케이션 시작됨!");

    try {
      // Mybatis의 SqlSession 객체 준비
      sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
          "com/studywithus/conf/mybatis-config.xml")).openSession();

      // SqlSession 객체를 통해 MemberDao 구현체를 자동 생성한다.
      MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
      CommunityDao communityDao = sqlSession.getMapper(CommunityDao.class);
      CommentDao commentDao = sqlSession.getMapper(CommentDao.class);
      StudyDao studyDao = sqlSession.getMapper(StudyDao.class);

      /* 
      // 상동
      MentorApplicationDao mentorApplicationDao = sqlSession.getMapper(MentorApplicationDao.class);
      ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
      ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
      StudyMemberDao studyMemberDao = sqlSession.getMapper(StudyMemberDao.class);
      PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
       */

      // 모든 웹 애플리케이션의 컴포넌트(서블릿, 리스너, 필터)가 공유할 객체를 두는 저장소
      ServletContext servletContext = sce.getServletContext();

      // 웹 애플리케이션 공용 저장소에 DAO 객체를 보관한다.
      // => 이 저장소에 보관된 객체는 서블릿에서 사용할 것이다.
      servletContext.setAttribute("memberDao", memberDao);
      servletContext.setAttribute("communityDao", communityDao);
      servletContext.setAttribute("commentDao", commentDao);
      servletContext.setAttribute("studyDao", studyDao);
      servletContext.setAttribute("sqlSession", sqlSession); // dao마다 하나씩 해줘야되나
      /*
      // 상동
      servletContext.setAttribute("memberDao", commentDao);
      servletContext.setAttribute("mentorApplicationDao", mentorApplicationDao);
      servletContext.setAttribute("reviewDao", reviewDao);
      servletContext.setAttribute("scheduleDao", scheduleDao);
      servletContext.setAttribute("studyMemberDao", studyMemberDao);
      servletContext.setAttribute("paymentDao", paymentDao);
       */

    } catch (Exception e) {
      System.out.println("DAO 객체 준비 중 오류 발생!");
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("애플리케이션 종료됨!");

    sqlSession.close();
  }
}
