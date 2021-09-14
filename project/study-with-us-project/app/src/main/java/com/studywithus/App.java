package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import static com.studywithus.menu.Menu.ACCESS_MEMBER;
import static com.studywithus.menu.Menu.ACCESS_MENTOR;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
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

public class App {
  List<Member> memberList = new LinkedList<>();
  List<Member> freeApplicantList = new ArrayList<>();
  List<Member> mentorApplicantList = new ArrayList<>();
  List<Member> chargeApplicantList = new ArrayList<>();
  List<Member> mentorList = new ArrayList<>();

  List<Study> freeInterestList = new ArrayList<>();
  List<Study> chargeInterestList = new ArrayList<>();
  List<Study> freeStudyList = new ArrayList<>();
  List<Study> freeApplicationList = new ArrayList<>();
  List<Study> chargeStudyList = new ArrayList<>();
  List<Study> chargeDeleteRequestList = new ArrayList<>();

  List<MentorApplicationForm> mentorApplicationForm = new ArrayList<>();

  List<Payment> paymentList = new ArrayList<>();

  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();

  List<Calendar> jobsCalendarList = new ArrayList<>();
  List<Calendar> examCalendarList = new ArrayList<>();

  HashMap<String, Command> commandMap = new HashMap<>();

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
    App app = new App(); 
    app.service();
  }

  public App() {
    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler(memberList));
    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
    commandMap.put("/auth/membershipwithdrawal", new MembershipWithdrawalHandler(memberList));

    commandMap.put("/freeInterest/list", new FreeInterestListHandler(freeInterestList));
    commandMap.put("/freeInterest/delete", new FreeInterestDeleteHandler(freeInterestList));
    commandMap.put("/chargeInterest/list", new ChargeInterestListHandler(chargeInterestList));
    commandMap.put("/chargeInterest/delete", new ChargeInterestDeleteHandler(chargeInterestList));

    commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(mentorApplicationForm));
    commandMap.put("/mentorApplicant/list", new MentorApplicationDetailHandler(mentorApplicationForm, mentorList));
    commandMap.put("/mentorApplicant/detail", new MentorApplicationFormListHandler());

    commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
    commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList));
    commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList, freeApplicantList, freeApplicationList, freeInterestList));
    commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));

    commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));
    commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(chargeStudyList, chargeInterestList, paymentList, chargeApplicantList));
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
  }

  void service() {
    loadObjects("member.data", memberList);
    loadObjects("freeInterest.data", freeInterestList);
    loadObjects("chargeInterest.data", chargeInterestList);
    loadObjects("freeStudy.data", freeStudyList);
    loadObjects("chargeStudy.data", chargeStudyList);
    loadObjects("communityQa.data", communityQaList);
    loadObjects("communityInfo.data", communityInfoList);
    loadObjects("communityTalk.data", communityTalkList);
    loadObjects("jobsCalendar.data", jobsCalendarList);
    loadObjects("examCalendar.data", examCalendarList);

    System.out.println("비회원 => " + ACCESS_LOGOUT);
    System.out.println("회원 => " + ACCESS_GENERAL);
    System.out.println("팀원 => " + ACCESS_MEMBER);
    System.out.println("팀장 => " + ACCESS_LEADER);
    System.out.println("멘토 => " + ACCESS_MENTOR);
    System.out.println("관리자 => " + ACCESS_ADMIN);

    createMainMenu().execute();
    Prompt.close();

    saveObjects("member.data", memberList);
    saveObjects("freeInterest.data", freeInterestList);
    saveObjects("chargeInterest.data", chargeInterestList);
    saveObjects("freeStudy.data", freeStudyList);
    saveObjects("chargeStudy.data", chargeStudyList);
    saveObjects("communityQa.data", communityQaList);
    saveObjects("communityInfo.data", communityInfoList);
    saveObjects("communityTalk.data", communityTalkList);
    saveObjects("jobsCalendar.data", jobsCalendarList);
    saveObjects("examCalendar.data", examCalendarList);
  }

  @SuppressWarnings("unchecked")
  private <E> void loadObjects(String filepath, List<E> list) {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream(filepath)))) {

      list.addAll((List<E>) in.readObject());

      System.out.printf("%s 파일 로딩 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 파일에서 데이터를 읽어 오는 중 오류 발생!\n", filepath);
      e.printStackTrace();
    }
  }

  private <E> void saveObjects(String filepath, List<E> list) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(filepath)))) {

      out.writeObject(list);

      System.out.printf("%s 파일 저장 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 파일에 데이터를 저장 중 오류 발생!\n", filepath);
      e.printStackTrace();
    }
  }

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", ACCESS_LOGOUT, "/auth/signUp"));
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logout"));
    mainMenuGroup.add(new MenuItem("회원 탈퇴", ACCESS_GENERAL, "/auth/membershipwithdrawal"));

    mainMenuGroup.add(createMyPageMenu());
    mainMenuGroup.add(createAdminMenu());
    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createMentorApplyMenu());
    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createCalendarMenu());

    return mainMenuGroup;
  }

  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createFreeStudyApplyMenu());
    myPageMenu.add(createMentorApplyMenu());

    return myPageMenu;
  }

  private Menu createAdminMenu() {
    MenuGroup adminMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);

    adminMenu.add(createMemberMenu());
    adminMenu.add(createMentorApplicantMenu());
    adminMenu.add(createDeleteRequestStudyMenu());

    return adminMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원 관리");

    memberMenu.add(createMentorApplicantMenu());

    return memberMenu;
  }

  private Menu createMentorApplicantMenu() {
    MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");

    mentorApplicantMenu.add(new MenuItem("조회", "/mentorApplicant/list"));
    mentorApplicantMenu.add(new MenuItem("상세보기", "/mentorApplicant/detail"));

    return mentorApplicantMenu;
  }

  // 관리자 관점
  private Menu createDeleteRequestStudyMenu() {
    MenuGroup deletedRequestMenu = new MenuGroup("삭제 요청 스터디 관리");

    deletedRequestMenu.add(new MenuItem("조회", "/chargeStudy/deleteList"));

    return deletedRequestMenu;
  }

  private Menu createInterestMenu() {
    MenuGroup interestMenu = new MenuGroup("관심목록", ACCESS_GENERAL);

    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  private Menu createFreeInterestMenu() {
    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");

    freeInterestMenu.add(new MenuItem("조회", "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("삭제", "/freeInterest/delete"));

    return freeInterestMenu;
  }

  private Menu createChargeInterestMenu() {
    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");

    chargeInterestMenu.add(new MenuItem("조회", "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("삭제", "/chargeInterest/delete"));

    return chargeInterestMenu;
  }

  private Menu createFreeStudyApplyMenu() {
    MenuGroup freeStudyApplyMenu = new MenuGroup("무료 스터디 신청 내역", ACCESS_GENERAL);

    freeStudyApplyMenu.add(new MenuItem("조회", "/freeStudyApply/list"));
    freeStudyApplyMenu.add(new MenuItem("상세보기", "/freeStudyApply/detail"));
    freeStudyApplyMenu.add(new MenuItem("수정", "/freeStudyApply/update"));
    freeStudyApplyMenu.add(new MenuItem("삭제", "/freeStudyApply/delete"));

    return freeStudyApplyMenu;
  }

  private Menu createMentorApplyMenu() {
    MenuGroup mentorApplyMenu = new MenuGroup("멘토 신청", ACCESS_GENERAL);
    mentorApplyMenu.add(new MenuItem("신청", "/mentorApplicant/add"));

    return mentorApplyMenu;
  }

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

  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("생성", ACCESS_MENTOR, "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("수정", ACCESS_MENTOR, "/chargeStudy/update"));
    chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_MENTOR, "/chargeStudy/deleteRequest"));

    return chargeStudyMenu;
  }

  private Menu createCommunityMenu() {
    MenuGroup communityMenu = new MenuGroup("커뮤니티");

    communityMenu.add(createCommunityInfoMenu());
    communityMenu.add(createCommunityQaMenu());
    communityMenu.add(createCommunityTalkMenu());

    return communityMenu;
  }

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

  private Menu createCalendarMenu() {
    MenuGroup calendarMenu = new MenuGroup("캘린더");

    calendarMenu.add(createJobsCalendarMenu());
    calendarMenu.add(createExamCalendarMenu());

    return calendarMenu;
  }

  private Menu createJobsCalendarMenu() {
    MenuGroup jobsCalendarMenu = new MenuGroup("이달의 채용공고");

    jobsCalendarMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsCalendar/add"));
    jobsCalendarMenu.add(new MenuItem("조회", "/jobsCalendar/list"));
    jobsCalendarMenu.add(new MenuItem("상세보기", "/jobsCalendar/detail"));
    jobsCalendarMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/jobsCalendar/update"));
    jobsCalendarMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsCalendar/delete"));

    return jobsCalendarMenu;
  }

  private Menu createExamCalendarMenu() {
    MenuGroup examCalendarMenu = new MenuGroup("이달의 시험일정");

    examCalendarMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examCalendar/add"));
    examCalendarMenu.add(new MenuItem("조회", "/examCalendar/list"));
    examCalendarMenu.add(new MenuItem("상세보기", "/examCalendar/detail"));
    examCalendarMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/examCalendar/update"));
    examCalendarMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examCalendar/delete"));

    return examCalendarMenu;
  }
}
