package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import static com.studywithus.menu.Menu.ACCESS_MENTEE;
import java.util.HashMap;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.study.ChargeStudyAddHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestCancelHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestDetailHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestListHandler;
import com.studywithus.handler.study.ChargeStudyDetailHandler;
import com.studywithus.handler.study.ChargeStudyInterestAddHandler;
import com.studywithus.handler.study.ChargeStudyInterestDeleteHandler;
import com.studywithus.handler.study.ChargeStudyInterestListHandler;
import com.studywithus.handler.study.ChargeStudyListHandler;
import com.studywithus.handler.study.ChargeStudyUpdateHandler;
import com.studywithus.handler.study.MentorApplicantApproveHandler;
import com.studywithus.handler.study.MentorApplicationAddHandler;
import com.studywithus.handler.study.MentorApplicationDetailHandler;
import com.studywithus.handler.study.RegisterChargeStudyDetailHandler;
import com.studywithus.handler.study.RegisterChargeStudyListHandler;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.handler.user.AuthLogOutHandler;
import com.studywithus.handler.user.MembershipWithdrawalHandler;
import com.studywithus.handler.user.ResetPasswordHandler;
import com.studywithus.handler.user.SignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ClientApp_JC {

  RequestAgent requestAgent;

  HashMap<String,Command> commandMap = new HashMap<>();

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
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!\n", menuId);
        e.printStackTrace();
      }
    }
  }

  public ClientApp_JC() throws Exception {
    requestAgent = new RequestAgent("127.0.0.1", 8888);

    commandMap.put("/auth/logIn", new AuthLogInHandler(requestAgent));
    //    commandMap.put("/google/logIn", new SnsLogInHandler(memberList));
    //    commandMap.put("/facebook/logIn", new SnsLogInHandler(memberList));
    //    commandMap.put("/kakao/logIn", new SnsLogInHandler(memberList));
    //    commandMap.put("/naver/logIn", new SnsLogInHandler(memberList));
    //
    commandMap.put("/auth/logOut", new AuthLogOutHandler());
    //
    commandMap.put("/auth/signUp", new SignUpHandler(requestAgent));
    //    commandMap.put("/google/signUp", new SnsSignUpHandler(memberList));
    //    commandMap.put("/facebook/signUp", new SnsSignUpHandler(memberList));
    //    commandMap.put("/kakao/signUp", new SnsSignUpHandler(memberList));
    //    commandMap.put("/naver/signUp", new SnsSignUpHandler(memberList));
    //
    //    commandMap.put("/find/id", new FindIdHandler(memberList));
    commandMap.put("/reset/password", new ResetPasswordHandler(requestAgent));
    //
    commandMap.put("/auth/membershipWithdrawal", new MembershipWithdrawalHandler(requestAgent));
    //
    //    commandMap.put("/myInfo/list", new MyInfoHandler());
    //
    //    commandMap.put("/freeInterest/list", new FreeStudyInterestListHandler(freeStudyList));
    //    commandMap.put("/freeInterest/delete", new FreeStudyInterestDeleteHandler(freeStudyList));
    commandMap.put("/chargeInterest/list", new ChargeStudyInterestListHandler(requestAgent));
    //
    commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(requestAgent));
    commandMap.put("/mentorApplicant/list", new MentorApplicationDetailHandler(requestAgent));
    //    commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
    //    commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList, registerFreeStudyMap));
    //    commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    //    commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList));
    //    commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    //    commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));
    //
    //    commandMap.put("/freeStudy/apply", new FreeStudyApplyHandler(freeStudyList, applyFreeStudyMap));
    //    commandMap.put("/freeStudy/applyCancel",
    //        new FreeStudyApplyCancelHandler(freeStudyList, applyFreeStudyMap));
    //    commandMap.put("/freeStudy/applyList", new FreeStudyApplyListHandler(freeStudyList));
    //    commandMap.put("/freeStudy/addInterest", new FreeStudyInterestAddHandler(freeStudyList));
    //    commandMap.put("/freeStudy/deleteInterest", new FreeStudyInterestDeleteHandler(freeStudyList));
    //    commandMap.put("/freeStudy/registerStudyList",
    //        new RegisterFreeStudyDetailHandler(registerFreeStudyMap, participateFreeStudyMap));
    //    commandMap.put("/freeStudy/participateStudyList",
    //        new ParticipateFreeStudyListHandler(participateFreeStudyMap));
    //
    //    commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));
    commandMap.put("/chargeStudy/add",new ChargeStudyAddHandler(requestAgent));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(requestAgent));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(requestAgent));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(requestAgent));
    commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(requestAgent));
    commandMap.put("/chargeStudy/deleteRequestCancel", new ChargeStudyDeleteRequestCancelHandler(requestAgent));
    commandMap.put("/chargeStudy/deleteRequestList", new ChargeStudyDeleteRequestListHandler(requestAgent));
    commandMap.put("/chargeStudy/deleteRequestDetail", new ChargeStudyDeleteRequestDetailHandler(requestAgent));
    //    commandMap.put("/chargeStudy/payment", new ChargeStudyPaymentHandler(chargeStudyList,
    //        chargePaymentList, chargeApplicantList, participateChargeStudyMap));
    //    commandMap.put("/chargeStudy/paymentCancel", new ChargeStudyPaymentCancelHandler(
    //        chargeStudyList, chargePaymentList, chargeApplicantList, participateChargeStudyMap));
    //    commandMap.put("/chargeStudy/paymentList",
    //        new ChargeStudyPaymentListHandler(chargeStudyList, participateChargeStudyMap));
    commandMap.put("/chargeStudy/interestAdd", new ChargeStudyInterestAddHandler(requestAgent));
    commandMap.put("/chargeStudy/interestDelete", new ChargeStudyInterestDeleteHandler(requestAgent));
    commandMap.put("/chargeStudy/registerChargeStudyList",new RegisterChargeStudyListHandler(requestAgent));
    commandMap.put("/chargeStudy/registerChargeStudyDetail",new RegisterChargeStudyDetailHandler(requestAgent));
    //    commandMap.put("/chargeStudy/participateChargeStudyList",
    //        new ParticipateChargeStudyListHandler(participateChargeStudyMap));
    //    commandMap.put("/chargeStudy/participateChargeStudyDetail", new ParticipateChargeStudyDetailHandler(participateChargeStudyMap)); 
    //    commandMap.put("/review/add", new ReviewAddHandler(chargeStudyList));
    //    commandMap.put("/review/list", new ReviewListHandler(chargeStudyList));
    //
    //    commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
    //    commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
    //    commandMap.put("/communityQa/detail",
    //        new CommunityDetailHandler(communityQaList, "/communityQa/update", "/communityQa/delete"));
    //    commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
    //    commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
    //    commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));
    //
    //    commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
    //    commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
    //    commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList,
    //        "/communityInfo/update", "/communityInfo/delete"));
    //    commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
    //    commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
    //    commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));
    //
    //    commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
    //    commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
    //    commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList,
    //        "/communityTalk/update", "/communityTalk/delete"));
    //    commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
    //    commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
    //    commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));
    //
    //    commandMap.put("/myPost/list",
    //        new MyPostListHandler(communityQaList, communityInfoList, communityTalkList));
    //    commandMap.put("/myPost/detail",
    //        new MyPostDetailHandler(communityQaList, communityInfoList, communityTalkList));
    //
    //    commandMap.put("/jobsSchedule/add", new JobsScheduleAddHandler(jobsScheduleList));
    //    commandMap.put("/jobsSchedule/list", new JobsScheduleListHandler(jobsScheduleList));
    //    commandMap.put("/jobsSchedule/detail", new JobsScheduleDetailHandler(jobsScheduleList));
    //    commandMap.put("/jobsSchedule/update", new JobsScheduleUpdateHandler(jobsScheduleList));
    //    commandMap.put("/jobsSchedule/delete", new JobsScheduleDeleteHandler(jobsScheduleList));
    //
    //    commandMap.put("/examSchedule/add", new ExamScheduleAddHandler(examScheduleList));
    //    commandMap.put("/examSchedule/list", new ExamScheduleListHandler(examScheduleList));
    //    commandMap.put("/examSchedule/detail", new ExamScheduleDetailHandler(examScheduleList));
    //    commandMap.put("/examSchedule/update", new ExamScheduleUpdateHandler(examScheduleList));
    //    commandMap.put("/examSchedule/delete", new ExamScheduleDeleteHandler(examScheduleList));                          
    commandMap.put("/mentorApplication/approve", new MentorApplicantApproveHandler(requestAgent));                          


  }

  // ------------------------------ STUDY WITH US -----------------------------------------

  // 메인 메뉴
  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createSignUpMenu());
    mainMenuGroup.add(createLogInMenu());
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logOut"));
    mainMenuGroup.add(createMyPageMenu());
    mainMenuGroup.add(createAdminPageMenu());
    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createScheduleMenu());

    return mainMenuGroup;
  }

  // 메인 메뉴 / 회원가입
  private Menu createSignUpMenu() {
    MenuGroup signUpMenu = new MenuGroup("회원가입", ACCESS_LOGOUT);

    signUpMenu.add(new MenuItem("이메일로 가입하기", "/auth/signUp"));
    signUpMenu.add(createSnsSignUpMenu());

    return signUpMenu;
  }

  // 메인 메뉴 / 회원가입 / SNS로 시작하기
  private Menu createSnsSignUpMenu() {
    MenuGroup snsSignUpMenu = new MenuGroup("SNS로 시작하기", ACCESS_LOGOUT);

    snsSignUpMenu.add(new MenuItem("구글로 시작하기", "/google/signUp"));
    snsSignUpMenu.add(new MenuItem("페이스북으로 시작하기", "/facebook/signUp"));
    snsSignUpMenu.add(new MenuItem("카카오로 시작하기", "/kakao/signUp"));
    snsSignUpMenu.add(new MenuItem("네이버로 시작하기", "/naver/signUp"));

    return snsSignUpMenu;
  }

  // 메인 메뉴 / 로그인
  private Menu createLogInMenu() {
    MenuGroup logInMenu = new MenuGroup("로그인", ACCESS_LOGOUT);

    logInMenu.add(new MenuItem("이메일 로그인", "/auth/logIn"));
    logInMenu.add(createSnsLogInMenu());
    logInMenu.add(new MenuItem("아이디 찾기", "/find/id"));
    logInMenu.add(new MenuItem("비밀번호 변경", "/reset/password"));

    return logInMenu;
  }

  // 메인 메뉴 / 로그인 / SNS로 시작하기
  private Menu createSnsLogInMenu() {
    MenuGroup createSnsLogInMenu = new MenuGroup("SNS 로그인", ACCESS_LOGOUT);

    createSnsLogInMenu.add(new MenuItem("구글로 로그인", "/google/logIn"));
    createSnsLogInMenu.add(new MenuItem("페이스북으로 로그인", "/facebook/logIn"));
    createSnsLogInMenu.add(new MenuItem("카카오로 로그인", "/kakao/logIn"));
    createSnsLogInMenu.add(new MenuItem("네이버로 로그인", "/naver/logIn"));

    return createSnsLogInMenu;
  }

  // ------------------------------ 무료 스터디 -----------------------------------------

  // 무료 스터디 메인 메뉴
  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
    freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // freeStudyMenu.add(new MenuItem("수정", ACCESS_LEADER, "/freeStudy/update"));
    // freeStudyMenu.add(new MenuItem("삭제", ACCESS_LEADER | ACCESS_ADMIN, "/freeStudy/delete"));

    return freeStudyMenu;
  }

  // ------------------------------ 유료 스터디 -----------------------------------------

  // 유료 스터디 메인 메뉴
  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("생성", "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("멘토 신청", ACCESS_GENERAL, "/mentorApplicant/add"));
    // [삭제] 상세보기 안으로 위치 변경
    // chargeStudyMenu.add(new MenuItem("수정", ACCESS_MENTOR, "/chargeStudy/update"));
    // chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_MENTOR, "/chargeStudy/deleteRequest"));

    return chargeStudyMenu;
  }

  // ------------------------------ 커뮤니티 -----------------------------------------

  // 커뮤니티 메인 메뉴
  private Menu createCommunityMenu() {
    MenuGroup communityMenu = new MenuGroup("커뮤니티");

    communityMenu.add(createCommunityInfoMenu());
    communityMenu.add(createCommunityQaMenu());
    communityMenu.add(createCommunityTalkMenu());

    return communityMenu;
  }

  // 커뮤니티 / 질문
  private Menu createCommunityQaMenu() {
    MenuGroup communityQaMenu = new MenuGroup("질문");

    communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
    communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // communityQaMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityQa/update"));
    // communityQaMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN,
    // "/communityQa/delete"));

    return communityQaMenu;
  }

  // 커뮤니티 / 정보
  private Menu createCommunityInfoMenu() {
    MenuGroup communityInfoMenu = new MenuGroup("정보");

    communityInfoMenu.add(new MenuItem("검색", "/communityInfo/search"));
    communityInfoMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // communityInfoMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityInfo/update"));
    // communityInfoMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN,
    // "/communityInfo/delete"));

    return communityInfoMenu;
  }

  // 커뮤니티 / 스몰톡
  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

    communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
    communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // communityTalkMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityTalk/update"));
    // communityTalkMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN,
    // "/communityTalk/delete"));

    return communityTalkMenu;
  }

  // ------------------------------ 일정 -----------------------------------------

  private Menu createScheduleMenu() {
    MenuGroup ScheduleMenu = new MenuGroup("일정");

    ScheduleMenu.add(createJobsScheduleMenu());
    ScheduleMenu.add(createExamScheduleMenu());

    return ScheduleMenu;
  }

  private Menu createJobsScheduleMenu() {
    MenuGroup jobsScheduleMenu = new MenuGroup("이달의 채용공고");

    jobsScheduleMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsSchedule/add"));
    jobsScheduleMenu.add(new MenuItem("조회", "/jobsSchedule/list"));
    jobsScheduleMenu.add(new MenuItem("상세보기", "/jobsSchedule/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // jobsScheduleMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/jobsSchedule/update"));
    // jobsScheduleMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsSchedule/delete"));

    return jobsScheduleMenu;
  }

  private Menu createExamScheduleMenu() {
    MenuGroup examScheduleMenu = new MenuGroup("이달의 시험일정");

    examScheduleMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleMenu.add(new MenuItem("조회", "/examSchedule/list"));
    examScheduleMenu.add(new MenuItem("상세보기", "/examSchedule/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // examScheduleMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/examSchedule/update"));
    // examScheduleMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examSchedule/delete"));

    return examScheduleMenu;
  }

  // ------------------------------ 마이 페이지 -----------------------------------------

  // 마이 페이지 메인
  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지");

    myPageMenu.add(createActivityDetailMenu());
    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createPaymentListMenu());
    myPageMenu.add(new MenuItem("나의 정보", "/myInfo/list"));
    myPageMenu.add(new MenuItem("회원 탈퇴", "/auth/membershipWithdrawal"));
    // [예정] 결제내역 돌아가는지 확인 후 ACCESS_MENTEE 권한 추가
    // myPageMenu.add(new MenuItem("나의 결제내역", "/chargeStudy/payment"));

    return myPageMenu;
  }

  // 마이 페이지 / 나의 활동
  private Menu createActivityDetailMenu() {

    MenuGroup activityDetailMenu = new MenuGroup("나의 활동", ACCESS_GENERAL);
    activityDetailMenu.add(createMyStudyMenu());
    activityDetailMenu.add(createMyPostMenu());

    return activityDetailMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디
  private Menu createMyStudyMenu() {

    MenuGroup myStudyMenu = new MenuGroup("나의 스터디");
    myStudyMenu.add(createFreeStudyApplyMenu());
    myStudyMenu.add(createRegisterFreeStudyMenu());
    myStudyMenu.add(createParticipateFreeStudyMenu());
    myStudyMenu.add(createRegisterChargeStudyMenu());
    myStudyMenu.add(createParticipateChargeStudyMenu());

    return myStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 게시글
  private Menu createMyPostMenu() {

    MenuGroup myPostMenu = new MenuGroup("나의 게시글");
    myPostMenu.add(new MenuItem("조회", "/myPost/list"));
    myPostMenu.add(new MenuItem("상세보기", "/myPost/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // myPostMenu.add(new MenuItem("수정", "/myPost/update"));
    // myPostMenu.add(new MenuItem("삭제", "/myPost/delete"));

    return myPostMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 무료 스터디 신청 내역
  private Menu createFreeStudyApplyMenu() {

    MenuGroup freeStudyApplyMenu = new MenuGroup("무료 스터디 신청 내역", ACCESS_GENERAL);
    freeStudyApplyMenu.add(new MenuItem("조회", "/freeStudy/applyList"));
    // [삭제] 상세보기 안으로 위치 변경
    // freeStudyApplyMenu.add(new MenuItem("상세보기", "/freeStudyApply/detail"));
    // freeStudyApplyMenu.add(new MenuItem("삭제", "/freeStudyApply/delete"));

    return freeStudyApplyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디(팀장 관점)
  // - "신청자 명단" -> 상세보기(승인/삭제) 추가해야 함
  private Menu createRegisterFreeStudyMenu() {

    MenuGroup registerFreeStudyMenu = new MenuGroup("내가 생성한 무료 스터디", ACCESS_LEADER);
    registerFreeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/registerStudyList"));
    // [삭제] 상세보기와 통합
    // registerFreeStudyMenu.add(new MenuItem("조회", "/freeStudy/registerStudyList"));
    // [삭제] 회의 후 안하기로 결정
    // registerFreeStudyMenu.add(new MenuItem("삭제", "/registerFreeStudy/delete"));

    return registerFreeStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 참여한 무료 스터디(팀원 관점)
  private Menu createParticipateFreeStudyMenu() {

    MenuGroup participateFreeStudyMenu = new MenuGroup("내가 참여한 무료 스터디", Menu.ACCESS_MEMBER);
    participateFreeStudyMenu.add(new MenuItem("조회", "/freeStudy/participateStudyList"));
    // [삭제] 회의 후 안하기로 결정
    // participateFreeStudyMenu.add(new MenuItem("상세보기", "/participateFreeStudy/detail"));

    return participateFreeStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 유료 스터디(멘토 관점) [X]
  // - "신청자 명단" -> 상세보기(승인/삭제) 추가해야 함
  private Menu createRegisterChargeStudyMenu() {

    MenuGroup registerChargeStudyMenu = new MenuGroup("내가 생성한 유료 스터디", ACCESS_GENERAL);
    registerChargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/registerChargeStudyList"));
    registerChargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/registerChargeStudyDetail"));
    registerChargeStudyMenu.add(new MenuItem("삭제", "/registerChargeStudy/delete"));

    return registerChargeStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 참여한 유료 스터디(멘티 관점) [X]
  private Menu createParticipateChargeStudyMenu() {

    MenuGroup participateChargeStudyMenu = new MenuGroup("내가 참여한 유료 스터디", ACCESS_GENERAL);
    participateChargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/participateChargeStudyList"));

    // [추가] 상세보기에 '후기' 메뉴, 결제 취소 추가 및 관련 핸들러 생성
    // 모집중, 진행중 -> 결제 취소 && 진행 완료 -> 후기 작성
    participateChargeStudyMenu
    .add(new MenuItem("상세보기", "/chargeStudy/participateChargeStudyDetail"));
    participateChargeStudyMenu.add(new MenuItem("삭제", "/chargeStudy/participateChargeStudyDetail"));

    return participateChargeStudyMenu;
  }

  // 마이 페이지 / 나의 관심목록
  private Menu createInterestMenu() {

    MenuGroup interestMenu = new MenuGroup("나의 관심목록");
    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  // 마이 페이지 / 나의 관심목록 / 무료 스터디 관심목록
  private Menu createFreeInterestMenu() {

    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");
    freeInterestMenu.add(new MenuItem("조회", "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("삭제", "/freeInterest/delete"));

    return freeInterestMenu;
  }

  // 마이 페이지 / 나의 관심목록 / 유료 스터디 관심목록
  private Menu createChargeInterestMenu() {

    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");
    chargeInterestMenu.add(new MenuItem("조회", "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("삭제", "/chargeStudy/interestDelete"));

    return chargeInterestMenu;
  }

  // 마이 페이지 / 나의 결제 내역
  private Menu createPaymentListMenu() {

    MenuGroup paymentListMenu = new MenuGroup("나의 결제 내역");
    paymentListMenu.add(new MenuItem("조회", ACCESS_MENTEE, "/chargeStudy/paymentList"));
    paymentListMenu.add(new MenuItem("상세보기", ACCESS_MENTEE, "/chargeStudy/paymentCancel"));
    // [추가] 상세보기 / 결제 취소

    return paymentListMenu;
  }

  // ------------------------------ 관리자 페이지 -----------------------------------------

  // 관리자 페이지 메인
  private Menu createAdminPageMenu() {

    MenuGroup adminPageMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);
    adminPageMenu.add(createMemberManagementMenu());
    adminPageMenu.add(createStudyManagementMenu());
    adminPageMenu.add(createScheduleManagementMenu());

    return adminPageMenu;
  }

  // 관리자 페이지 / 회원 관리
  private Menu createMemberManagementMenu() {

    MenuGroup memberManagementMenu = new MenuGroup("회원 관리");
    memberManagementMenu.add(new MenuItem("멘토 승인 관리", "/mentorApplicant/list"));
    memberManagementMenu.add(createBlackListMenu());

    return memberManagementMenu;
  }

  // 관리자 페이지 / 회원 관리 / 블랙리스트 관리
  private Menu createBlackListMenu() {

    MenuGroup mentorManagementMenu = new MenuGroup("블랙리스트 관리");
    mentorManagementMenu.add(new MenuItem("", "/"));

    return mentorManagementMenu;
  }

  // 관리자 페이지 / 스터디 관리
  private Menu createStudyManagementMenu() {

    MenuGroup studyManagementMenu = new MenuGroup("스터디 관리");
    studyManagementMenu.add(createDeleteRequestStudyMenu());

    return studyManagementMenu;
  }

  // 관리자 페이지 / 스터디 관리 / 삭제 요청 스터디 관리
  private Menu createDeleteRequestStudyMenu() {

    MenuGroup deletedRequestMenu = new MenuGroup("삭제 요청 스터디 관리");
    deletedRequestMenu.add(new MenuItem("조회", "/chargeStudy/deleteRequestList"));
    deletedRequestMenu.add(new MenuItem("상세보기", "/chargeStudy/deleteRequestDetail"));
    return deletedRequestMenu;
  }

  // 관리자 페이지 / 일정 관리
  private Menu createScheduleManagementMenu() {

    MenuGroup ScheduleMenu = new MenuGroup("일정 관리", ACCESS_ADMIN);
    ScheduleMenu.add(createJobsScheduleManagementMenu());
    ScheduleMenu.add(createExamScheduleManagementMenu());

    return ScheduleMenu;
  }

  // 관리자 페이지 / 일정 관리 / 이달의 채용공고 관리
  private Menu createJobsScheduleManagementMenu() {
    MenuGroup jobsScheduleManagementMenu = new MenuGroup("이달의 채용공고");

    jobsScheduleManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsSchedule/add"));
    jobsScheduleManagementMenu.add(new MenuItem("상세보기", "/jobsSchedule/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // jobsScheduleManagementMenu.add(new MenuItem("변경", ACCESS_ADMIN, "/jobsSchedule/update"));
    // jobsScheduleManagementMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsSchedule/delete"));

    return jobsScheduleManagementMenu;
  }

  // 관리자 페이지 / 일정 관리 / 이달의 시험일정 관리
  private Menu createExamScheduleManagementMenu() {

    MenuGroup examScheduleManagementMenu = new MenuGroup("이달의 시험일정");
    examScheduleManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleManagementMenu.add(new MenuItem("상세보기", "/examSchedule/detail"));
    // [삭제] 상세보기 안으로 위치 변경
    // examScheduleManagementMenu.add(new MenuItem("변경", ACCESS_ADMIN, "/examSchedule/update"));
    // examScheduleManagementMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examSchedule/delete"));

    return examScheduleManagementMenu;
  }

  void service() throws Exception {

    // [삭제] HashMap 적용
    // loadObjects("chargeInterest.json", chargeInterestList, Study.class);
    //    loadObjects("member.json", memberList, Member.class);
    //    loadObjects("freeStudy.json", freeStudyList, Study.class);
    //    loadObjects("chargeStudy.json", chargeStudyList, Study.class);
    //    loadObjects("communityQa.json", communityQaList, Community.class);
    //    loadObjects("communityInfo.json", communityInfoList, Community.class);
    //    loadObjects("communityTalk.json", communityTalkList, Community.class);
    //    loadObjects("jobsSchedule.json", jobsScheduleList, Schedule.class);
    //    loadObjects("examSchedule.json", examScheduleList, Schedule.class);

    System.out.println();
    System.out.println("|         스터디위더스         |");
    System.out.println("|          STUDYWITHUS         |");
    System.out.println("     ￣￣￣￣∨￣￣￣￣￣￣￣   ");
    System.out.println("　　　      ∧_,,∧");
    System.out.println("　　　     (`･ω･´)");
    System.out.println("　　　     Ｕ θ Ｕ");
    System.out.println("　     ／￣￣｜￣￣＼");
    System.out.println("　     |二二二二二二二|");
    System.out.println("      ｜　　　　　　 ｜");
    System.out.println("    찰칵       찰칵   찰");
    System.out.println("        ∧∧└ 　   ∧∧   칵");
    System.out.println("    　(　　)】 (　　)】");
    System.out.println("    　/　/┘　  /　/┘");
    System.out.println("    ノ￣ヽ　  ノ￣ヽ  Are U ready to STUDY ?");

    createMainMenu().execute();
    requestAgent.request("quit", null);
    Prompt.close();

    // [삭제] HashMap 적용
    // saveObjects("chargeInterest.json", chargeInterestList);
    //    saveObjects("member.json", memberList);
    //    saveObjects("freeStudy.json", freeStudyList);
    //    saveObjects("chargeStudy.json", chargeStudyList);
    //    saveObjects("communityQa.json", communityQaList);
    //    saveObjects("communityInfo.json", communityInfoList);
    //    saveObjects("communityTalk.json", communityTalkList);
    //    saveObjects("jobsSchedule.json", jobsScheduleList);
    //    saveObjects("examSchedule.json", examScheduleList);
  }

  public static void main(String[] args) throws Exception {
    ClientApp_JC app = new ClientApp_JC(); 
    app.service();
    Prompt.close();
  }
}