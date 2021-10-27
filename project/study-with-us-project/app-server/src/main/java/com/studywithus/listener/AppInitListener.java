package com.studywithus.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.studywithus.dao.MemberDao;

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

      /* 
      // 상동
      CommentDao commentDao = sqlSession.getMapper(CommentDao.class);
      CommunityDao communityDao = sqlSession.getMapper(CommunityDao.class);
      MentorApplicationDao mentorApplicationDao = sqlSession.getMapper(MentorApplicationDao.class);
      ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
      ScheduleDao scheduleDao = sqlSession.getMapper(ScheduleDao.class);
      StudyDao studyDao = sqlSession.getMapper(StudyDao.class);
      StudyMemberDao studyMemberDao = sqlSession.getMapper(StudyMemberDao.class);
      PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
       */

      // 모든 웹 애플리케이션의 컴포넌트(서블릿, 리스너, 필터)가 공유할 객체를 두는 저장소
      ServletContext 웹애플리케이션공용저장소 = sce.getServletContext();

      // 웹 애플리케이션 공용 저장소에 DAO 객체를 보관한다.
      // => 이 저장소에 보관된 객체는 서블릿에서 사용할 것이다.
      웹애플리케이션공용저장소.setAttribute("memberDao", memberDao);
      /*
      // 상동
      웹애플리케이션공용저장소.setAttribute("memberDao", commentDao);
      웹애플리케이션공용저장소.setAttribute("communityDao", communityDao);
      웹애플리케이션공용저장소.setAttribute("mentorApplicationDao", mentorApplicationDao);
      웹애플리케이션공용저장소.setAttribute("reviewDao", reviewDao);
      웹애플리케이션공용저장소.setAttribute("scheduleDao", scheduleDao);
      웹애플리케이션공용저장소.setAttribute("studyDao", studyDao);
      웹애플리케이션공용저장소.setAttribute("studyMemberDao", studyMemberDao);
      웹애플리케이션공용저장소.setAttribute("paymentDao", paymentDao);
       */
      웹애플리케이션공용저장소.setAttribute("sqlSession", sqlSession); // dao마다 하나씩 해줘야되나

    } catch (Exception e) {
      System.out.println("DAO 객체 준비 중 오류 발생!");
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("애플리케이션 종료됨!");

    sqlSession.close();
  }
}
