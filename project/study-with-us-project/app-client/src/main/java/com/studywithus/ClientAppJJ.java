package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.studywithus.context.ApplicationContextListener;
import com.studywithus.dao.CommentDao;
import com.studywithus.dao.CommunityDao;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.impl.MariadbCommentDao;
import com.studywithus.dao.impl.MariadbCommunityDao;
import com.studywithus.dao.impl.MariadbMentorApplicationDao;
import com.studywithus.dao.impl.MybatisMemberDaoJJ;
import com.studywithus.dao.impl.NetChargeStudyDao;
import com.studywithus.dao.impl.NetPaymentDao;
import com.studywithus.dao.impl.NetReviewDao;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.chargestudy.ChargeStudyAddHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestCancelHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestListHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDetailMenuPrompt;
import com.studywithus.handler.chargestudy.ChargeStudyInterestAddHandler;
import com.studywithus.handler.chargestudy.ChargeStudyInterestDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyListHandler;
import com.studywithus.handler.chargestudy.ChargeStudyPaymentDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyPaymentHandler;
import com.studywithus.handler.chargestudy.ChargeStudyUpdateHandler;
import com.studywithus.handler.chargestudy.MentorApplicantApproveHandler;
import com.studywithus.handler.chargestudy.MentorApplicationAddHandler;
import com.studywithus.handler.chargestudy.MentorApplicationDetailHandler;
import com.studywithus.handler.chargestudy.ParticipateChargeStudyDetailHandler;
import com.studywithus.handler.chargestudy.ParticipateChargeStudyListHandler;
import com.studywithus.handler.chargestudy.RegisterChargeStudyDetailHandler;
import com.studywithus.handler.chargestudy.RegisterChargeStudyListHandler;
import com.studywithus.handler.chargestudy.ReviewAddHandler;
import com.studywithus.handler.chargestudy.ReviewListHandler;
import com.studywithus.handler.comment.CommentAddHandler;
import com.studywithus.handler.comment.CommentDeleteHandler;
import com.studywithus.handler.community.CommunityAddHandler;
import com.studywithus.handler.community.CommunityDeleteHandler;
import com.studywithus.handler.community.CommunityDetailHandler;
import com.studywithus.handler.community.CommunityListHandler;
import com.studywithus.handler.community.CommunitySearchHandler;
import com.studywithus.handler.community.CommunityUpdateHandler;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.handler.user.AuthLogOutHandler;
import com.studywithus.handler.user.FindEmailHandler;
import com.studywithus.handler.user.MembershipWithdrawalHandler;
import com.studywithus.handler.user.MyInfoHandler;
import com.studywithus.handler.user.ResetPasswordHandler;
import com.studywithus.handler.user.SignUpHandler;
import com.studywithus.handler.user.SnsLogInHandler;
import com.studywithus.handler.user.SnsSignUpHandler;
import com.studywithus.listener.AppInitListener;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;


public class ClientAppJJ {

  Connection con;
  RequestAgent requestAgent;
  SqlSession sqlSession;

  HashMap<String, Command> commandMap = new HashMap<>();

  CommandRequest request = new CommandRequest(commandMap);

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(request);
      } catch (Exception e) {
        System.out.printf("%s ????????? ???????????? ??? ?????? ??????!\n", menuId);
        e.printStackTrace();
      }
    }
  }

  public ClientAppJJ() throws Exception {

    requestAgent = null;

    con =
        DriverManager.getConnection("jdbc:mysql://localhost:3306/team3db?user=team3&password=1111");

    // Mybatis??? SqlSession ?????? ??????
    SqlSession sqlSession = new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("com/studywithus/conf/mybatis-config.xml"))
        .openSession();

    // ????????? ????????? ????????? DAO ????????? ????????????.
    MemberDao memberDao = new MybatisMemberDaoJJ(sqlSession);
    StudyDao studyDao = sqlSession.getMapper(StudyDao.class);
    CommunityDao communityDao = new MariadbCommunityDao(con);
    CommentDao commentDao = new MariadbCommentDao(con);
    MentorApplicationDao mentorApplicationDao = new MariadbMentorApplicationDao(con);


    NetChargeStudyDao chargeStudyDao = new NetChargeStudyDao(requestAgent);
    ChargeStudyDetailMenuPrompt chargeStudyDetailMenuPrompt =
        new ChargeStudyDetailMenuPrompt(studyDao, request);

    NetPaymentDao paymentDao = new NetPaymentDao(requestAgent);
    NetReviewDao reviewDao = new NetReviewDao(requestAgent);


    // Command ?????? ??????
    commandMap.put("/auth/logIn", new AuthLogInHandler(memberDao));
    commandMap.put("/google/logIn", new SnsLogInHandler(memberDao));
    commandMap.put("/facebook/logIn", new SnsLogInHandler(memberDao));
    commandMap.put("/kakao/logIn", new SnsLogInHandler(memberDao));
    commandMap.put("/naver/logIn", new SnsLogInHandler(memberDao));

    commandMap.put("/auth/logOut", new AuthLogOutHandler());

    commandMap.put("/auth/signUp", new SignUpHandler(memberDao));
    commandMap.put("/google/signUp", new SnsSignUpHandler(memberDao));
    commandMap.put("/facebook/signUp", new SnsSignUpHandler(memberDao));
    commandMap.put("/kakao/signUp", new SnsSignUpHandler(memberDao));
    commandMap.put("/naver/signUp", new SnsSignUpHandler(memberDao));

    commandMap.put("/find/email", new FindEmailHandler(memberDao));
    commandMap.put("/reset/password", new ResetPasswordHandler(memberDao));

    commandMap.put("/auth/membershipWithdrawal", new MembershipWithdrawalHandler(memberDao));

    commandMap.put("/myInfo/list", new MyInfoHandler());

    // commandMap.put("/freeInterest/list", new FreeStudyInterestListHandler(freeStudyList));
    // commandMap.put("/freeInterest/delete", new FreeStudyInterestDeleteHandler(freeStudyList));

    //
    // commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
    // commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList,
    // registerFreeStudyMap));
    // commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    // commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList));
    // commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    // commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));
    //
    // commandMap.put("/freeStudy/apply", new FreeStudyApplyHandler(freeStudyList,
    // applyFreeStudyMap));
    // commandMap.put("/freeStudy/applyCancel",
    // new FreeStudyApplyCancelHandler(freeStudyList, applyFreeStudyMap));
    // commandMap.put("/freeStudy/applyList", new FreeStudyApplyListHandler(freeStudyList));
    // commandMap.put("/freeStudy/addInterest", new FreeStudyInterestAddHandler(freeStudyList));
    // commandMap.put("/freeStudy/deleteInterest", new
    // FreeStudyInterestDeleteHandler(freeStudyList));
    // commandMap.put("/freeStudy/registerStudyList",
    // new RegisterFreeStudyDetailHandler(registerFreeStudyMap, participateFreeStudyMap));
    // commandMap.put("/freeStudy/participateStudyList",
    // new ParticipateFreeStudyListHandler(participateFreeStudyMap));
    //

    commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(mentorApplicationDao));
    commandMap.put("/mentorApplicant/list",
        new MentorApplicationDetailHandler(mentorApplicationDao));
    //    commandMap.put("/mentorApplication/approve",
    //        new MentorApplicantApproveHandler(mentorApplicationDao));

    commandMap.put("/chargeStudy/add",new ChargeStudyAddHandler(studyDao));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(studyDao));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(studyDao, chargeStudyDetailMenuPrompt));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequestCancel", new ChargeStudyDeleteRequestCancelHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequestList", new ChargeStudyDeleteRequestListHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequestDetail", new ChargeStudyDeleteRequestDetailHandler(studyDao));
    commandMap.put("/chargeStudy/payment", new ChargeStudyPaymentHandler(paymentDao, studyDao));
    commandMap.put("/chargeStudy/paymentDetail", new ChargeStudyPaymentDetailHandler(paymentDao, studyDao));
    //    commandMap.put("/chargeStudy/paymentCancel", new ChargeStudyPaymentCancelHandler(paymentDao, studyDao));
    //    commandMap.put("/chargeStudy/paymentList", new ChargeStudyPaymentListHandler(paymentDao));
    commandMap.put("/chargeStudy/interestAdd", new ChargeStudyInterestAddHandler(studyDao));
    //    commandMap.put("/chargeStudy/interestDelete", new ChargeStudyInterestDeleteHandler(studyDao));
    commandMap.put("/chargeStudy/registerChargeStudyList",new RegisterChargeStudyListHandler(studyDao));
    commandMap.put("/chargeStudy/registerChargeStudyDetail",new RegisterChargeStudyDetailHandler(studyDao));
    commandMap.put("/chargeStudy/participateChargeStudyList", new ParticipateChargeStudyListHandler(studyDao));
    commandMap.put("/chargeStudy/participateChargeStudyDetail", new ParticipateChargeStudyDetailHandler(studyDao));
    commandMap.put("/review/add", new ReviewAddHandler(reviewDao));
    commandMap.put("/review/list", new ReviewListHandler(reviewDao));
    //

    commandMap.put("/community/add", new CommunityAddHandler(communityDao));
    commandMap.put("/community/list", new CommunityListHandler(communityDao));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityDao));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityDao));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityDao));
    commandMap.put("/community/search", new CommunitySearchHandler(communityDao));

    // commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
    // commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
    // commandMap.put("/communityQa/detail",
    // new CommunityDetailHandler(communityQaList, "/communityQa/update", "/communityQa/delete"));
    // commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
    // commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
    // commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));
    //
    // commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
    // commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
    // commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList,
    // "/communityInfo/update", "/communityInfo/delete"));
    // commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
    // commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
    // commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));
    //
    // commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
    // commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
    // commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList,
    // "/communityTalk/update", "/communityTalk/delete"));
    // commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
    // commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
    // commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));
    //
    // commandMap.put("/myPost/list",
    // new MyPostListHandler(communityQaList, communityInfoList, communityTalkList));
    // commandMap.put("/myPost/detail",
    // new MyPostDetailHandler(communityQaList, communityInfoList, communityTalkList));

    //    commandMap.put("/jobsSchedule/add", new JobsScheduleAddHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/list", new JobsScheduleListHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/detail", new JobsScheduleDetailHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/update", new JobsScheduleUpdateHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/delete", new JobsScheduleDeleteHandler(jobsScheduleDao));
    //
    //    commandMap.put("/examSchedule/add", new ExamScheduleAddHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/list", new ExamScheduleListHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/detail", new ExamScheduleDetailHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/update", new ExamScheduleUpdateHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/delete", new ExamScheduleDeleteHandler(examScheduleDao));

    commandMap.put("/mentorApplication/approve", new MentorApplicantApproveHandler(memberDao));                          
    commandMap.put("/chargeInterest/detail", new ChargeStudyInterestDetailHandler(studyDao, chargeStudyDetailMenuPrompt));                          
    commandMap.put("/comment/add", new CommentAddHandler(commentDao));                          
    commandMap.put("/comment/delete", new CommentDeleteHandler(commentDao));                          



  }

  // ------------------------------ STUDY WITH US -----------------------------------------

  // ?????? ??????
  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
    mainMenuGroup.setPrevMenuTitle("??????");

    mainMenuGroup.add(createSignUpMenu());
    mainMenuGroup.add(createLogInMenu());
    mainMenuGroup.add(new MenuItem("????????????", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logOut"));
    mainMenuGroup.add(createMyPageMenu());
    mainMenuGroup.add(createAdminPageMenu());
    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createScheduleMenu());

    return mainMenuGroup;
  }

  // ?????? ?????? / ????????????
  private Menu createSignUpMenu() {
    MenuGroup signUpMenu = new MenuGroup("????????????", ACCESS_LOGOUT);

    signUpMenu.add(new MenuItem("???????????? ????????????", "/auth/signUp"));
    signUpMenu.add(createSnsSignUpMenu());

    return signUpMenu;
  }

  // ?????? ?????? / ???????????? / SNS??? ????????????
  private Menu createSnsSignUpMenu() {
    MenuGroup snsSignUpMenu = new MenuGroup("SNS??? ????????????", ACCESS_LOGOUT);

    snsSignUpMenu.add(new MenuItem("????????? ????????????", "/google/signUp"));
    snsSignUpMenu.add(new MenuItem("?????????????????? ????????????", "/facebook/signUp"));
    snsSignUpMenu.add(new MenuItem("???????????? ????????????", "/kakao/signUp"));
    snsSignUpMenu.add(new MenuItem("???????????? ????????????", "/naver/signUp"));

    return snsSignUpMenu;
  }

  // ?????? ?????? / ?????????
  private Menu createLogInMenu() {
    MenuGroup logInMenu = new MenuGroup("?????????", ACCESS_LOGOUT);

    logInMenu.add(new MenuItem("????????? ?????????", "/auth/logIn"));
    logInMenu.add(createSnsLogInMenu());
    logInMenu.add(new MenuItem("????????? ??????", "/find/email"));
    logInMenu.add(new MenuItem("???????????? ??????", "/reset/password"));

    return logInMenu;
  }

  // ?????? ?????? / ????????? / SNS??? ????????????
  private Menu createSnsLogInMenu() {
    MenuGroup createSnsLogInMenu = new MenuGroup("SNS ?????????", ACCESS_LOGOUT);

    createSnsLogInMenu.add(new MenuItem("????????? ?????????", "/google/logIn"));
    createSnsLogInMenu.add(new MenuItem("?????????????????? ?????????", "/facebook/logIn"));
    createSnsLogInMenu.add(new MenuItem("???????????? ?????????", "/kakao/logIn"));
    createSnsLogInMenu.add(new MenuItem("???????????? ?????????", "/naver/logIn"));

    return createSnsLogInMenu;
  }

  // ------------------------------ ?????? ????????? -----------------------------------------

  // ?????? ????????? ?????? ??????
  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("?????? ?????????");

    freeStudyMenu.add(new MenuItem("??????", "/freeStudy/search"));
    freeStudyMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("??????", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("????????????", "/freeStudy/detail"));

    return freeStudyMenu;
  }

  // ------------------------------ ?????? ????????? -----------------------------------------

  // ?????? ????????? ?????? ??????
  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("?????? ?????????");

    chargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("????????????", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("?????? ??????", ACCESS_GENERAL, "/mentorApplicant/add"));

    return chargeStudyMenu;
  }

  // ------------------------------ ???????????? -----------------------------------------

  // ???????????? ?????? ??????
  private Menu createCommunityMenu() {
    MenuGroup communityMenu = new MenuGroup("????????????");

    communityMenu.add(createCommunityInfoMenu());
    communityMenu.add(createCommunityQaMenu());
    communityMenu.add(createCommunityTalkMenu());

    return communityMenu;
  }

  // ???????????? / ??????
  private Menu createCommunityQaMenu() {
    MenuGroup communityQaMenu = new MenuGroup("??????");

    communityQaMenu.add(new MenuItem("??????", "/communityQa/search"));
    communityQaMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("??????", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("????????????", "/communityQa/detail"));

    return communityQaMenu;
  }

  // ???????????? / ??????
  private Menu createCommunityInfoMenu() {
    MenuGroup communityInfoMenu = new MenuGroup("??????");

    communityInfoMenu.add(new MenuItem("??????", "/communityInfo/search"));
    communityInfoMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("??????", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("????????????", "/communityInfo/detail"));

    return communityInfoMenu;
  }

  // ???????????? / ?????????
  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("?????????");

    communityTalkMenu.add(new MenuItem("??????", "/communityTalk/search"));
    communityTalkMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("??????", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("????????????", "/communityTalk/detail"));

    return communityTalkMenu;
  }

  // ------------------------------ ?????? -----------------------------------------

  private Menu createScheduleMenu() {
    MenuGroup ScheduleMenu = new MenuGroup("??????");

    ScheduleMenu.add(createJobsScheduleMenu());
    ScheduleMenu.add(createExamScheduleMenu());

    return ScheduleMenu;
  }

  private Menu createJobsScheduleMenu() {
    MenuGroup jobsScheduleMenu = new MenuGroup("????????? ????????????");

    jobsScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsSchedule/add"));
    jobsScheduleMenu.add(new MenuItem("??????", "/jobsSchedule/list"));
    jobsScheduleMenu.add(new MenuItem("????????????", "/jobsSchedule/detail"));

    return jobsScheduleMenu;
  }

  private Menu createExamScheduleMenu() {
    MenuGroup examScheduleMenu = new MenuGroup("????????? ????????????");

    examScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleMenu.add(new MenuItem("??????", "/examSchedule/list"));
    examScheduleMenu.add(new MenuItem("????????????", "/examSchedule/detail"));

    return examScheduleMenu;
  }

  // ------------------------------ ?????? ????????? -----------------------------------------

  // ?????? ????????? ??????
  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("?????? ?????????", ACCESS_GENERAL);

    myPageMenu.add(createActivityDetailMenu());
    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createPaymentListMenu());
    myPageMenu.add(new MenuItem("?????? ??????", "/myInfo/list"));
    myPageMenu.add(new MenuItem("?????? ??????", ACCESS_GENERAL, "/auth/membershipWithdrawal"));

    return myPageMenu;
  }

  // ?????? ????????? / ?????? ??????
  private Menu createActivityDetailMenu() {

    MenuGroup activityDetailMenu = new MenuGroup("?????? ??????", ACCESS_GENERAL);
    activityDetailMenu.add(createMyStudyMenu());
    activityDetailMenu.add(createMyPostMenu());

    return activityDetailMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ?????????
  private Menu createMyStudyMenu() {

    MenuGroup myStudyMenu = new MenuGroup("?????? ?????????");
    myStudyMenu.add(createFreeStudyApplyMenu());
    myStudyMenu.add(createRegisterFreeStudyMenu());
    myStudyMenu.add(createParticipateFreeStudyMenu());
    myStudyMenu.add(createRegisterChargeStudyMenu());
    myStudyMenu.add(createParticipateChargeStudyMenu());

    return myStudyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ?????????
  private Menu createMyPostMenu() {

    MenuGroup myPostMenu = new MenuGroup("?????? ?????????");
    myPostMenu.add(new MenuItem("??????", "/myPost/list"));
    myPostMenu.add(new MenuItem("????????????", "/myPost/detail"));

    return myPostMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ??????
  private Menu createFreeStudyApplyMenu() {

    MenuGroup freeStudyApplyMenu = new MenuGroup("?????? ????????? ?????? ??????", ACCESS_GENERAL);
    freeStudyApplyMenu.add(new MenuItem("??????", "/freeStudy/applyList"));

    return freeStudyApplyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????(?????? ??????)

  private Menu createRegisterFreeStudyMenu() {

    MenuGroup registerFreeStudyMenu = new MenuGroup("?????? ????????? ?????? ?????????", ACCESS_LEADER);
    registerFreeStudyMenu.add(new MenuItem("????????????", "/freeStudy/registerStudyList"));
    // [??????] ??????????????? ??????
    // registerFreeStudyMenu.add(new MenuItem("??????", "/freeStudy/registerStudyList"));
    // [??????] ?????? ??? ???????????? ??????
    // registerFreeStudyMenu.add(new MenuItem("??????", "/registerFreeStudy/delete"));

    return registerFreeStudyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????(?????? ??????)
  private Menu createParticipateFreeStudyMenu() {

    MenuGroup participateFreeStudyMenu = new MenuGroup("?????? ????????? ?????? ?????????", Menu.ACCESS_MEMBER);
    participateFreeStudyMenu.add(new MenuItem("??????", "/freeStudy/participateStudyList"));
    // participateFreeStudyMenu.add(new MenuItem("????????????", "/participateFreeStudy/detail"));

    return participateFreeStudyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????
  private Menu createRegisterChargeStudyMenu() {

    MenuGroup registerChargeStudyMenu = new MenuGroup("?????? ????????? ?????? ?????????", ACCESS_GENERAL);
    registerChargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/registerChargeStudyList"));
    registerChargeStudyMenu.add(new MenuItem("????????????", "/chargeStudy/registerChargeStudyDetail"));

    return registerChargeStudyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????
  private Menu createParticipateChargeStudyMenu() {

    MenuGroup participateChargeStudyMenu = new MenuGroup("?????? ????????? ?????? ?????????", ACCESS_GENERAL);
    participateChargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/participateChargeStudyList"));
    participateChargeStudyMenu
    .add(new MenuItem("????????????", "/chargeStudy/participateChargeStudyDetail"));

    return participateChargeStudyMenu;
  }

  // ?????? ????????? / ?????? ????????????
  private Menu createInterestMenu() {

    MenuGroup interestMenu = new MenuGroup("?????? ????????????");
    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  // ?????? ????????? / ?????? ???????????? / ?????? ????????? ????????????
  private Menu createFreeInterestMenu() {

    MenuGroup freeInterestMenu = new MenuGroup("?????? ????????? ????????????");
    freeInterestMenu.add(new MenuItem("??????", "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("??????", "/freeInterest/delete"));

    return freeInterestMenu;
  }

  // ?????? ????????? / ?????? ???????????? / ?????? ????????? ????????????
  private Menu createChargeInterestMenu() {

    MenuGroup chargeInterestMenu = new MenuGroup("?????? ????????? ????????????");
    chargeInterestMenu.add(new MenuItem("??????", "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("????????????", "/chargeInterest/detail"));

    return chargeInterestMenu;
  }

  // ?????? ????????? / ?????? ?????? ??????
  private Menu createPaymentListMenu() {

    MenuGroup paymentListMenu = new MenuGroup("?????? ?????? ??????");
    paymentListMenu.add(new MenuItem("??????", "/chargeStudy/paymentList"));
    paymentListMenu.add(new MenuItem("????????????", "/chargeStudy/paymentDetail"));

    return paymentListMenu;
  }

  // ------------------------------ ????????? ????????? -----------------------------------------

  // ????????? ????????? ??????
  private Menu createAdminPageMenu() {

    MenuGroup adminPageMenu = new MenuGroup("????????? ?????????", ACCESS_ADMIN);
    adminPageMenu.add(createMemberManagementMenu());
    adminPageMenu.add(createStudyManagementMenu());
    adminPageMenu.add(createScheduleManagementMenu());

    return adminPageMenu;
  }

  // ????????? ????????? / ?????? ??????
  private Menu createMemberManagementMenu() {

    MenuGroup memberManagementMenu = new MenuGroup("?????? ??????");
    memberManagementMenu.add(new MenuItem("?????? ?????? ??????", "/mentorApplicant/list"));
    // memberManagementMenu.add(createBlackListMenu());

    return memberManagementMenu;
  }

  /*
   * // ????????? ????????? / ?????? ?????? / ??????????????? ?????? private Menu createBlackListMenu() {
   * 
   * MenuGroup mentorManagementMenu = new MenuGroup("??????????????? ??????"); mentorManagementMenu.add(new
   * MenuItem("", "/"));
   * 
   * return mentorManagementMenu; }
   */

  // ????????? ????????? / ????????? ??????
  private Menu createStudyManagementMenu() {

    MenuGroup studyManagementMenu = new MenuGroup("????????? ??????");
    studyManagementMenu.add(createDeleteRequestStudyMenu());

    return studyManagementMenu;
  }

  // ????????? ????????? / ????????? ?????? / ?????? ?????? ????????? ??????
  private Menu createDeleteRequestStudyMenu() {

    MenuGroup deletedRequestMenu = new MenuGroup("?????? ?????? ????????? ??????");
    deletedRequestMenu.add(new MenuItem("??????", "/chargeStudy/deleteRequestList"));
    deletedRequestMenu.add(new MenuItem("????????????", "/chargeStudy/deleteRequestDetail"));

    return deletedRequestMenu;
  }

  // ????????? ????????? / ?????? ??????
  private Menu createScheduleManagementMenu() {

    MenuGroup ScheduleMenu = new MenuGroup("?????? ??????", ACCESS_ADMIN);
    ScheduleMenu.add(createJobsScheduleManagementMenu());
    ScheduleMenu.add(createExamScheduleManagementMenu());

    return ScheduleMenu;
  }

  // ????????? ????????? / ?????? ?????? / ????????? ???????????? ??????
  private Menu createJobsScheduleManagementMenu() {
    MenuGroup jobsScheduleManagementMenu = new MenuGroup("????????? ????????????");

    jobsScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsSchedule/add"));
    jobsScheduleManagementMenu.add(new MenuItem("????????????", "/jobsSchedule/detail"));

    return jobsScheduleManagementMenu;
  }

  // ????????? ????????? / ?????? ?????? / ????????? ???????????? ??????
  private Menu createExamScheduleManagementMenu() {

    MenuGroup examScheduleManagementMenu = new MenuGroup("????????? ????????????");
    examScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleManagementMenu.add(new MenuItem("????????????", "/examSchedule/detail"));

    return examScheduleManagementMenu;
  }

  List<ApplicationContextListener> listeners = new ArrayList<>();

  // => ?????????(?????????)??? ???????????? ?????????
  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  // => ?????????(?????????)??? ???????????? ?????????
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }

  private void notifyOnApplicationStarted() {
    HashMap<String, Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  void service() {
    notifyOnApplicationStarted();
    createMainMenu().execute();
    Prompt.close();
    sqlSession.close();
  }

  public static void main(String[] args) throws Exception {
    ClientAppJJ app = new ClientAppJJ();
    app.addApplicationContextListener(new AppInitListener());
    app.service();
    Prompt.close();
  }
}
