package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import static com.studywithus.menu.Menu.ACCESS_MENTEE;
import static com.studywithus.menu.Menu.ACCESS_MENTOR;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.studywithus.domain.Calendar;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.AuthUserInfoHandler;
import com.studywithus.handler.ChargeInterestDeleteHandler;
import com.studywithus.handler.ChargeInterestListHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.ChargeStudyDeletedDetailHandler;
import com.studywithus.handler.ChargeStudyDeletedListHandler;
import com.studywithus.handler.ChargeStudyDetailHandler;
import com.studywithus.handler.ChargeStudyListHandler;
import com.studywithus.handler.ChargeStudySearchHandler;
import com.studywithus.handler.ChargeStudyUpdateHandler;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommunityAddHandler;
import com.studywithus.handler.CommunityDeleteHandler;
import com.studywithus.handler.CommunityDetailHandler;
import com.studywithus.handler.CommunityListHandler;
import com.studywithus.handler.CommunitySearchHandler;
import com.studywithus.handler.CommunityUpdateHandler;
import com.studywithus.handler.ExamCalendarAddHandler;
import com.studywithus.handler.ExamCalendarDeleteHandler;
import com.studywithus.handler.ExamCalendarDetailHandler;
import com.studywithus.handler.ExamCalendarListHandler;
import com.studywithus.handler.ExamCalendarUpdateHandler;
import com.studywithus.handler.FreeInterestDeleteHandler;
import com.studywithus.handler.FreeInterestListHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudySearchHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.handler.JobsCalendarAddHandler;
import com.studywithus.handler.JobsCalendarDeleteHandler;
import com.studywithus.handler.JobsCalendarDetailHandler;
import com.studywithus.handler.JobsCalendarListHandler;
import com.studywithus.handler.JobsCalendarUpdateHandler;
import com.studywithus.handler.MembershipWithdrawalHandler;
import com.studywithus.handler.MentorApplicationAddHandler;
import com.studywithus.handler.MentorApplicationDetailHandler;
import com.studywithus.handler.MentorApplicationFormListHandler;
import com.studywithus.handler.SignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class AppJ {
  List<Member> memberList = new LinkedList<>();
  List<Member> freeApplicantList = new ArrayList<>();
  List<Member> mentorApplicantList = new ArrayList<>();
  List<Member> chargeApplicantList = new ArrayList<>();
  List<Member> mentorList = new ArrayList<>();

  List<Study> registerFreeStudyList = new ArrayList<>();
  List<Study> participateFreeStudyList = new ArrayList<>();
  List<Study> registerChargeStudyList = new ArrayList<>();
  List<Study> participateChargeStudyList = new ArrayList<>();
  List<Study> freeInterestList = new ArrayList<>();
  List<Study> chargeInterestList = new ArrayList<>();
  List<Study> freeStudyList = new ArrayList<>();
  List<Study> freeApplicationList = new ArrayList<>();
  List<Study> chargeStudyList = new ArrayList<>();
  List<Study> chargeDeleteRequestList = new ArrayList<>();

  List<MentorApplicationForm> mentorApplicationForm = new ArrayList<>();

  List<Payment> chargePaymentList = new ArrayList<>();

  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();

  // 추가중 for 내 게시글
  List<Community> myPostist = new ArrayList<>();


  List<Calendar> jobsCalendarList = new ArrayList<>();
  List<Calendar> examCalendarList = new ArrayList<>();

  HashMap<String, Command> commandMap = new HashMap<>();
  HashMap<String, List<Study>> participateFreeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> participateChargeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> registerFreeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> registerChargeStudyMap = new HashMap<>();


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
      command.execute();
    }
  }

  public static void main(String[] args) {
    AppJ appJ = new AppJ(); 
    appJ.service();
  }

  public AppJ() {
    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler(memberList));
    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
    commandMap.put("/auth/membershipwithdrawal", new MembershipWithdrawalHandler(memberList));
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler(memberList));

    commandMap.put("/freeInterest/list", new FreeInterestListHandler(freeInterestList));
    commandMap.put("/freeInterest/delete", new FreeInterestDeleteHandler(freeInterestList));
    commandMap.put("/chargeInterest/list", new ChargeInterestListHandler(chargeInterestList));
    commandMap.put("/chargeInterest/delete", new ChargeInterestDeleteHandler(chargeInterestList));

    commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(mentorApplicationForm));
    commandMap.put("/mentorApplicant/list", new MentorApplicationDetailHandler(mentorApplicationForm, mentorList));
    commandMap.put("/mentorApplicant/detail", new MentorApplicationFormListHandler());

    commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
    commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList, registerFreeStudyMap));
    commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList, freeApplicationList, freeInterestList));
    commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));

    commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));
    commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList, registerChargeStudyMap));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(chargeStudyList, chargeInterestList, chargePaymentList, chargeApplicantList, participateChargeStudyMap));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
    commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(chargeStudyList, chargeDeleteRequestList));
    commandMap.put("/chargeStudy/deleteList", new ChargeStudyDeletedListHandler(chargeDeleteRequestList));
    commandMap.put("/chargeStudy/deleteDetail", new ChargeStudyDeletedDetailHandler(chargeStudyList, chargeDeleteRequestList));

    commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
    commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
    commandMap.put("/communityQa/detail", new CommunityDetailHandler(communityQaList));
    commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
    commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
    commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));

    commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
    commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
    commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList));
    commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
    commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
    commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));

    commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
    commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
    commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList));
    commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
    commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
    commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));

    commandMap.put("/jobsCalendar/add", new JobsCalendarAddHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/list", new JobsCalendarListHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/detail", new JobsCalendarDetailHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/update", new JobsCalendarUpdateHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/delete", new JobsCalendarDeleteHandler(jobsCalendarList));

    commandMap.put("/examCalendar/add", new ExamCalendarAddHandler(examCalendarList));
    commandMap.put("/examCalendar/list", new ExamCalendarListHandler(examCalendarList));
    commandMap.put("/examCalendar/detail", new ExamCalendarDetailHandler(examCalendarList));
    commandMap.put("/examCalendar/update", new ExamCalendarUpdateHandler(examCalendarList));
    commandMap.put("/examCalendar/delete", new ExamCalendarDeleteHandler(examCalendarList));

    // 추가중 for 내 게시글
    commandMap.put("/myPost/list", new CommunityListHandler(myPostList));
    commandMap.put("/myPost/detail", new CommunityDetailHandler(myPostList));
    commandMap.put("/myPost/update", new CommunityUpdateHandler(myPostList));
    commandMap.put("/myPost/delete", new CommunityDeleteHandler(myPostList));
  }

  void service() {
    loadObjects("member.json", memberList);
    loadObjects("freeInterest.json", freeInterestList);
    loadObjects("chargeInterest.json", chargeInterestList);
    loadObjects("freeStudy.json", freeStudyList);
    loadObjects("chargeStudy.json", chargeStudyList);
    loadObjects("communityQa.json", communityQaList);
    loadObjects("communityInfo.json", communityInfoList);
    loadObjects("communityTalk.json", communityTalkList);
    loadObjects("jobsCalendar.json", jobsCalendarList);
    loadObjects("examCalendar.json", examCalendarList);

    //loadObjects("myPost.json", myPostList);

    createMainMenu().execute();
    Prompt.close();

    saveObjects("member.json", memberList);
    saveObjects("freeInterest.json", freeInterestList);
    saveObjects("chargeInterest.json", chargeInterestList);
    saveObjects("freeStudy.json", freeStudyList);
    saveObjects("chargeStudy.json", chargeStudyList);
    saveObjects("communityQa.json", communityQaList);
    saveObjects("communityInfo.json", communityInfoList);
    saveObjects("communityTalk.json", communityTalkList);
    saveObjects("jobsCalendar.json", jobsCalendarList);
    saveObjects("examCalendar.json", examCalendarList);

    //saveObjects("myPost.json", myPostList);
  }

  //JSON 형식으로 저장된 데이터를 읽어서 객체로 만든다.
  private <E> void loadObjects(
      String filepath, // 데이터를 읽어 올 파일 경로 
      List<E> list, // 로딩한 데이터를 객체로 만든 후 저장할 목록 
      Class<E> domainType // 생성할 객체의 타입정보
      ) {

    try (BufferedReader in = new BufferedReader(
        new FileReader(filepath, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) { // 파일 전체를 읽는다.
        strBuilder.append(str);
      }

      // StringBuilder로 읽어 온 JSON 문자열을 객체로 바꾼다.
      Type type = TypeToken.getParameterized(Collection.class, domainType).getType(); 
      Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

      // JSON 데이터로 읽어온 목록을 파라미터로 받은 List 에 저장한다.
      list.addAll(collection);

      System.out.printf("%s 데이터 로딩 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", filepath);
    }
  }

  // 객체를 JSON 형식으로 저장한다.
  private void saveObjects(String filepath, List<?> list) {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(
            new FileWriter(filepath, Charset.forName("UTF-8"))))) {

      out.print(new Gson().toJson(list));

      System.out.printf("%s 데이터 출력 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n", filepath);
      e.printStackTrace();
    }
  }

  // 메인 메뉴
  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createSignUpMenu());
    mainMenuGroup.add(createLogInMenu());
    mainMenuGroup.add(new MenuItem("회원가입", ACCESS_LOGOUT, "/auth/signUp"));
    mainMenuGroup.add(new MenuItem("로그인", ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logout"));

    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());

    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createCalendarManagementMenu());
    mainMenuGroup.add(createMyPageMenu());
    mainMenuGroup.add(createAdminPageMenu());

    return mainMenuGroup;
  }

  // 메인 메뉴 / 회원가입
  private Menu createSignUpMenu() {
    MenuGroup signUpMenu = new MenuGroup("회원가입");

    signUpMenu.add(new MenuItem("이메일로 가입하기", ACCESS_LOGOUT,"/auth/signUp"));

    // SNS 계정별 로그인(ex. 카카오, 구글) 추가할지 논의하기
    signUpMenu.add(new MenuItem("SNS로 시작하기", ACCESS_LOGOUT, "/")); 

    return signUpMenu;
  }

  // 메인 메뉴 / 로그인
  private Menu createLogInMenu() {
    MenuGroup logInMenu = new MenuGroup("로그인");

    logInMenu.add(new MenuItem("이메일 로그인", ACCESS_LOGOUT,"/auth/login"));

    // 상동
    logInMenu.add(new MenuItem("SNS 로그인", ACCESS_LOGOUT, "/"));

    return logInMenu;
  }
  // ------------------------------ 무료 스터디 -----------------------------------------

  //무료 스터디 메인 메뉴
  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
    freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
    freeStudyMenu.add(new MenuItem("수정", ACCESS_LEADER, "/freeStudy/update"));
    freeStudyMenu.add(new MenuItem("삭제", ACCESS_LEADER | ACCESS_ADMIN, "/freeStudy/delete"));

    return freeStudyMenu;
  }

  // ------------------------------ 유료 스터디 -----------------------------------------

  // 유료 스터디 메인 메뉴
  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("생성", ACCESS_MENTOR, "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("수정", ACCESS_MENTOR, "/chargeStudy/update"));
    chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_MENTOR, "/chargeStudy/deleteRequest"));
    //chargeStudyMenu.add(new MenuGroup("멘토 신청", ACCESS_GENERAL, "/mentorApplicant/add"));

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

  // 커뮤니티 / 정보
  private Menu createCommunityInfoMenu() {
    MenuGroup communityInfoMenu = new MenuGroup("정보");

    communityInfoMenu.add(new MenuItem("검색", "/communityInfo/search"));
    communityInfoMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
    communityInfoMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityInfo/update"));
    communityInfoMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityInfo/delete"));

    return communityInfoMenu;
  }

  // 커뮤니티 / 질문
  private Menu createCommunityQaMenu() {
    MenuGroup communityQaMenu = new MenuGroup("질문");

    communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
    communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
    communityQaMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityQa/update"));
    communityQaMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityQa/delete"));

    return communityQaMenu;
  }

  // 커뮤니티 / 스몰톡
  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

    communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
    communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
    communityTalkMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityTalk/update"));
    communityTalkMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityTalk/delete"));

    return communityTalkMenu;
  }

  // ------------------------------ 마이 페이지 -----------------------------------------

  // 마이 페이지 메인
  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

    myPageMenu.add(createMyInfoMenu());
    myPageMenu.add(createActivityDetailMenu());
    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createPaymentListMenu());
    myPageMenu.add(new MenuItem("회원 탈퇴", ACCESS_GENERAL, "/auth/membershipwithdrawal"));

    return myPageMenu;
  }

  // 마이 페이지 / 나의 정보
  private Menu createMyInfoMenu() {
    MenuGroup myInfoMenu = new MenuGroup("나의 정보");

    myInfoMenu.add(new MenuItem("", "/"));

    return myInfoMenu;
  }

  // 마이 페이지 / 나의 활동
  private Menu createActivityDetailMenu() {

    MenuGroup activityDetailMenu = new MenuGroup("나의 활동");
    activityDetailMenu.add(createMyStudyMenu());
    activityDetailMenu.add(createMyPostMenu());

    return activityDetailMenu;
  }

  // 마이 페이지 / 나의 활동 / 내 스터디
  private Menu createMyStudyMenu() {

    MenuGroup myStudyMenu = new MenuGroup("내 스터디");
    myStudyMenu.add(createFreeStudyApplyMenu());

    return myStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 내 스터디 / 무료 스터디 신청 내역
  private Menu createFreeStudyApplyMenu() {

    MenuGroup freeStudyApplyMenu = new MenuGroup("무료 스터디 신청 내역", ACCESS_GENERAL);
    freeStudyApplyMenu.add(new MenuItem("조회", "/freeStudyApply/list"));
    freeStudyApplyMenu.add(new MenuItem("상세보기", "/freeStudyApply/detail"));
    freeStudyApplyMenu.add(new MenuItem("수정", "/freeStudyApply/update"));
    freeStudyApplyMenu.add(new MenuItem("삭제", "/freeStudyApply/delete"));

    return freeStudyApplyMenu;
  }

  // 마이 페이지 / 나의 활동 / 내 게시글
  private Menu createMyPostMenu() {

    MenuGroup myPostMenu = new MenuGroup("내 게시글");
    myPostMenu.add(new MenuItem("조회", "/myPost/list"));
    myPostMenu.add(new MenuItem("상세보기", "/myPost/detail"));
    myPostMenu.add(new MenuItem("수정", "/myPost/update"));
    myPostMenu.add(new MenuItem("삭제", "/myPost/delete"));

    return myPostMenu;
  }

  // 마이 페이지 / 내 관심목록
  private Menu createInterestMenu() {

    MenuGroup interestMenu = new MenuGroup("내 관심목록");
    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  // 마이 페이지 / 내 관심목록 / 무료 스터디 관심목록
  private Menu createFreeInterestMenu() {

    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");
    freeInterestMenu.add(new MenuItem("조회", ACCESS_GENERAL, "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("삭제", ACCESS_GENERAL,"/freeInterest/delete"));

    return freeInterestMenu;
  }

  // 마이 페이지 / 내 관심목록 / 유료 스터디 관심목록
  private Menu createChargeInterestMenu() {

    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");
    chargeInterestMenu.add(new MenuItem("조회", ACCESS_GENERAL, "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/chargeInterest/delete"));

    return chargeInterestMenu;
  }

  // 마이 페이지 / 나의 결제 내역
  private Menu createPaymentListMenu() {
    MenuGroup paymentListMenu = new MenuGroup("나의 결제 내역");
    paymentListMenu.add(new MenuItem("조회", ACCESS_MENTEE, "/"));
    paymentListMenu.add(new MenuItem("조회", ACCESS_MENTEE, "/"));
    return paymentListMenu;
  }

  /* 09/14 수업 이후 적용 예정 메뉴

   *마이 페이지 / 나의 활동 / 내 스터디 / 내가 생성한 무료 스터디(팀장)
   - 팀원 수락 / 거절

   *마이 페이지 / 나의 활동 / 내 스터디 / 내가 참여한 무료 스터디(팀원~)

   *마이 페이지 / 나의 활동 / 내 스터디 / 내가 생성한 유료 스터디(멘토)

   *마이 페이지 / 나의 활동 / 내 스터디 / 내가 참여한 유료 스터디(팀원~)

   *마이 페이지 / 스터디 후기
   */


  // ------------------------------ 관리자 페이지 -----------------------------------------

  // 관리자 페이지 메인
  private Menu createAdminPageMenu() {

    MenuGroup adminPageMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);
    adminPageMenu.add(createMemberManagementMenu());
    adminPageMenu.add(createStudyManagementMenu());
    adminPageMenu.add(createCalendarManagementMenu());

    return adminPageMenu;
  }

  // 관리자 페이지 / 회원 관리
  private Menu createMemberManagementMenu() {

    MenuGroup memberManagementMenu = new MenuGroup("회원 관리");
    memberManagementMenu.add(createMentorManagementMenu());
    memberManagementMenu.add(createBlackListMenu());

    return memberManagementMenu;
  }

  // 관리자 페이지 / 회원 관리 / 멘토 신청 내역 관리
  private Menu createMentorManagementMenu() {

    MenuGroup mentorManagementMenu = new MenuGroup("회원 관리");
    mentorManagementMenu.add(new MenuItem("조회", "/mentorApplicant/list"));
    mentorManagementMenu.add(new MenuItem("상세보기", "/mentorApplicant/detail"));

    return mentorManagementMenu;
  }

  //관리자 페이지 / 회원 관리 / 블랙리스트 관리
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
    deletedRequestMenu.add(new MenuItem("", "/"));

    return deletedRequestMenu;
  }

  // 관리자 페이지 / 캘린더 관리
  private Menu createCalendarManagementMenu() {

    MenuGroup adminMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);
    adminMenu.add(createJobsCalendarManagementMenu());
    adminMenu.add(createExamCalendarManagementMenu());

    return adminMenu;
  }

  // 관리자 페이지 / 캘린더 관리 / 이달의 채용공고 관리
  private Menu createJobsCalendarManagementMenu() {
    MenuGroup jobsCalendarManagementMenu = new MenuGroup("이달의 채용공고");

    jobsCalendarManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsCalendar/add"));
    jobsCalendarManagementMenu.add(new MenuItem("상세보기", "/jobsCalendar/detail"));
    jobsCalendarManagementMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/jobsCalendar/update"));
    jobsCalendarManagementMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsCalendar/delete"));

    return jobsCalendarManagementMenu;
  }

  //관리자 페이지 / 캘린더 관리 / 이달의 시험일정 관리
  private Menu createExamCalendarManagementMenu() {

    MenuGroup examcalendarManagementMenu = new MenuGroup("이달의 시험일정");
    examcalendarManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examCalendar/add"));
    examcalendarManagementMenu.add(new MenuItem("상세보기", "/examCalendar/detail"));
    examcalendarManagementMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/examCalendar/update"));
    examcalendarManagementMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examCalendar/delete"));

    return examcalendarManagementMenu;
  }

}