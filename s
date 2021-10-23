[1mdiff --git a/project/study-with-us-project/app-client/src/main/java/com/studywithus/ClientApp_JC.java b/project/study-with-us-project/app-client/src/main/java/com/studywithus/ClientApp_JC.java[m
[1mindex dc862182..967ffcfe 100644[m
[1m--- a/project/study-with-us-project/app-client/src/main/java/com/studywithus/ClientApp_JC.java[m
[1m+++ b/project/study-with-us-project/app-client/src/main/java/com/studywithus/ClientApp_JC.java[m
[36m@@ -7,14 +7,19 @@[m [mimport static com.studywithus.menu.Menu.ACCESS_LOGOUT;[m
 import java.sql.Connection;[m
 import java.sql.DriverManager;[m
 import java.util.HashMap;[m
[32m+[m[32mimport org.apache.ibatis.io.Resources;[m
[32m+[m[32mimport org.apache.ibatis.session.SqlSession;[m
[32m+[m[32mimport org.apache.ibatis.session.SqlSessionFactoryBuilder;[m
 import com.studywithus.dao.CommentDao;[m
 import com.studywithus.dao.CommunityDao;[m
 import com.studywithus.dao.MemberDao;[m
 import com.studywithus.dao.MentorApplicationDao;[m
[32m+[m[32mimport com.studywithus.dao.StudyDao;[m
 import com.studywithus.dao.impl.MariadbCommentDao;[m
 import com.studywithus.dao.impl.MariadbCommunityDao;[m
 import com.studywithus.dao.impl.MariadbMemberDaoJC;[m
 import com.studywithus.dao.impl.MariadbMentorApplicationDao;[m
[32m+[m[32mimport com.studywithus.dao.impl.MybatisStudyDaoJC;[m
 import com.studywithus.dao.impl.NetChargeStudyDao;[m
 import com.studywithus.dao.impl.NetPaymentDao;[m
 import com.studywithus.dao.impl.NetReviewDao;[m
[36m@@ -104,14 +109,16 @@[m [mpublic class ClientApp_JC {[m
   }[m
 [m
   public ClientApp_JC() throws Exception {[m
[31m-    requestAgent = null;[m
 [m
[31m-    con = DriverManager.getConnection([m
[31m-        "jdbc:mysql://localhost:3306/team3db?user=team3&password=1111");[m
[32m+[m[32m    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team3db?user=team3&password=1111");[m
[32m+[m
[32m+[m[32m    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream([m
[32m+[m[32m        "com/studywithus/conf/mybatis-config.xml")).openSession();[m
 [m
     MemberDao memberDao = new MariadbMemberDaoJC(con);[m
     CommunityDao communityDao = new MariadbCommunityDao(con);[m
     CommentDao commentDao = new MariadbCommentDao(con);[m
[32m+[m[32m    StudyDao studyDao = new MybatisStudyDaoJC(sqlSession);[m
     MentorApplicationDao mentorApplicationDao = new MariadbMentorApplicationDao(con);[m
 [m
 [m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyAddHandler.java b/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyAddHandler.java[m
[1mindex a71cd846..cb868b1a 100644[m
[1m--- a/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyAddHandler.java[m
[1m+++ b/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyAddHandler.java[m
[36m@@ -1,6 +1,5 @@[m
 package com.studywithus.handler.chargestudy;[m
 [m
[31m-import java.sql.Date;[m
 import com.studywithus.dao.ChargeStudyDao;[m
 import com.studywithus.domain.Study;[m
 import com.studywithus.handler.Command;[m
[36m@@ -23,6 +22,7 @@[m [mpublic class ChargeStudyAddHandler implements Command {[m
     Study chargeStudy = new Study();[m
 [m
     chargeStudy.setWriter(AuthLogInHandler.getLoginUser());[m
[32m+[m
     chargeStudy.setArea(Prompt.inputString("지역: "));[m
     chargeStudy.setTitle(Prompt.inputString("스터디 제목: "));[m
     chargeStudy.setContent(Prompt.inputString("스터디 설명: "));[m
[36m@@ -38,14 +38,6 @@[m [mpublic class ChargeStudyAddHandler implements Command {[m
         continue;[m
       }[m
 [m
[31m-      // 현재 날짜 > 시작일인 경우[m
[31m-      /* if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == 1) {[m
[31m-        System.out.println("다시 입력하세요.\n");[m
[31m-        continue;[m
[31m-[m
[31m-      } else {[m
[31m-        break;[m
[31m-      }*/[m
       break;[m
     }[m
 [m
[36m@@ -58,23 +50,9 @@[m [mpublic class ChargeStudyAddHandler implements Command {[m
         continue;[m
       }[m
 [m
[31m-      /*if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == 1) {[m
[31m-        System.out.println("다시 입력하세요.\n");[m
[31m-        continue;[m
[31m-[m
[31m-        // 시작일 < 종료일이 아닌 경우[m
[31m-      }else if (chargeStudy.getEndDate().compareTo(chargeStudy.getStartDate()) != 1) {[m
[31m-        System.out.println("종료일은 시작일 이후로 설정하세요.\n");[m
[31m-        continue;[m
[31m-[m
[31m-      } else {[m
[31m-        break;[m
[31m-      }*/[m
       break;[m
     }[m
 [m
[31m-    chargeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));[m
[31m-[m
     chargeStudyDao.insert(chargeStudy);[m
 [m
     System.out.println();[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyDetailHandler_JC.java b/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyDetailHandler_JC.java[m
[1mindex 7fa7fcbe..7cf06d25 100644[m
[1m--- a/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyDetailHandler_JC.java[m
[1m+++ b/project/study-with-us-project/app-client/src/main/java/com/studywithus/handler/chargestudy/ChargeStudyDetailHandler_JC.java[m
[36m@@ -39,7 +39,7 @@[m [mpublic class ChargeStudyDetailHandler_JC implements Command {[m
 [m
     System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());[m
     System.out.printf("조회수: %d\n", chargeStudy.getViewCount());[m
[31m-    System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembersEmail().size());[m
[32m+[m[32m    System.out.printf("좋아요수: %d\n", chargeStudy.getViewCount());[m
     System.out.println();[m
 [m
     request.setAttribute("chargeNo", no);[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/CommunityMapper.xml b/project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/CommunityMapper.xml[m
[1msimilarity index 100%[m
[1mrename from project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/CommunityMapper.xml[m
[1mrename to project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/CommunityMapper.xml[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/MemberMapper.xml b/project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/MemberMapper.xml[m
[1msimilarity index 100%[m
[1mrename from project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/MemberMapper.xml[m
[1mrename to project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/MemberMapper.xml[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/MemberMapperJJ.xml b/project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/MemberMapperJJ.xml[m
[1msimilarity index 100%[m
[1mrename from project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/MemberMapperJJ.xml[m
[1mrename to project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/MemberMapperJJ.xml[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/StudyMapper.xml b/project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/StudyMapper.xml[m
[1msimilarity index 100%[m
[1mrename from project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/mapper/StudyMapper.xml[m
[1mrename to project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/StudyMapper.xml[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/conf/jdbc.properties b/project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/jdbc.properties[m
[1msimilarity index 100%[m
[1mrename from project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/conf/jdbc.properties[m
[1mrename to project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/jdbc.properties[m
[1mdiff --git a/project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/conf/mybatis-config.xml b/project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/mybatis-config.xml[m
[1msimilarity index 100%[m
[1mrename from project/study-with-us-project/app-client/src/main/resources/com/studywithus/pms/conf/mybatis-config.xml[m
[1mrename to project/study-with-us-project/app-client/src/main/resources/com/studywithus/conf/pms/mapper/mybatis-config.xml[m
