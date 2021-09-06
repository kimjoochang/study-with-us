package com.studywithus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.Community;
import com.studywithus.domain.ExamCalender;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.JobsCalender;
import com.studywithus.domain.Member;
import com.studywithus.domain.Mentor;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.ChargeInterestAddHandler;
import com.studywithus.handler.ChargeInterestDeleteHandler;
import com.studywithus.handler.ChargeInterestListHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteHandler;
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
import com.studywithus.handler.FreeInterestAddHandler;
import com.studywithus.handler.FreeInterestDeleteHandler;
import com.studywithus.handler.FreeInterestListHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudySearchHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.handler.MentorApplicantAddHandler;
import com.studywithus.handler.MentorApplicantDetailHandler;
import com.studywithus.handler.MentorApplicantListHandler;
import com.studywithus.handler.MentorApproveHandler;
import com.studywithus.handler.MentorRejectHandler;
import com.studywithus.handler.SignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class App {
  List<Member> memberList = new LinkedList<>();
  List<FreeStudy> freeStudyList = new ArrayList<>();
  List<FreeStudy> freeInterestList = new ArrayList<>();
  List<JobsCalender> jobsCalenderList = new ArrayList<>();
  List<ExamCalender> examCalenderList = new ArrayList<>();
  List<Member> mentorApplicantList = new ArrayList<>();
  List<ChargeStudy> chargeStudyList = new ArrayList<>();
  List<ChargeStudy> chargeInterestList = new ArrayList<>();
  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();
  List<Mentor> mentorList = new ArrayList<>();

  HashMap<String, Command> commandMap = new HashMap<>();

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      this(title, ENABLE_ALL, menuId);
    }

    public MenuItem(String title, int enableState, String menuId) {
      super(title, enableState);
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
    commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList));
    commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList));
    commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));
    commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));

    commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(chargeStudyList));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
    commandMap.put("/chargeStudy/delete", new ChargeStudyDeleteHandler(chargeStudyList));
    commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));

    commandMap.put("/community/add", new CommunityAddHandler(communityInfoList));
    commandMap.put("/community/list", new CommunityListHandler(communityInfoList));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityInfoList));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityInfoList));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityInfoList));
    commandMap.put("/community/search", new CommunitySearchHandler(communityInfoList));

    commandMap.put("/community/add", new CommunityAddHandler(communityQaList));
    commandMap.put("/community/list", new CommunityListHandler(communityQaList));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityQaList));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityQaList));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityQaList));
    commandMap.put("/community/search", new CommunitySearchHandler(communityQaList));

    commandMap.put("/community/add", new CommunityAddHandler(communityTalkList));
    commandMap.put("/community/list", new CommunityListHandler(communityTalkList));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityTalkList));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityTalkList));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityTalkList));
    commandMap.put("/community/search", new CommunitySearchHandler(communityTalkList));

    commandMap.put("/mentorApplicant/add", new MentorApplicantAddHandler(mentorApplicantList));
    commandMap.put("/mentorApplicant/list", new MentorApplicantListHandler(mentorApplicantList));
    commandMap.put("/mentorApplicant/detail", new MentorApplicantDetailHandler(mentorApplicantList));
    commandMap.put("/mentorApplicant/Approve", new MentorApproveHandler(mentorList));
    commandMap.put("/mentorApplicant/Reject", new MentorRejectHandler(mentorApplicantList));

    commandMap.put("/freeInterest/add", new FreeInterestAddHandler(freeInterestList));
    commandMap.put("/freeInterest/list", new FreeInterestListHandler(freeInterestList));
    commandMap.put("/freeInterest/delete", new FreeInterestDeleteHandler(freeInterestList));

    commandMap.put("/chargeInterest/add", new ChargeInterestAddHandler(chargeInterestList));
    commandMap.put("/chargeInterest/list", new ChargeInterestListHandler(chargeInterestList));
    commandMap.put("/chargeInterest/detail", new ChargeInterestDeleteHandler(chargeInterestList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
  }

  void service() {
    createMainMenu().execute();
    Prompt.close();
  }

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", Menu.ENABLE_LOGIN, "/auth/signUp"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_LOGIN, "/auth/logout"));

    mainMenuGroup.add(createAdminMenu());
    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createInterestMenu());

    return mainMenuGroup;
  }

  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
    freeStudyMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
    freeStudyMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/freeStudy/update"));
    freeStudyMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/freeStudy/delete"));

    return freeStudyMenu;
  }

  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료스터디");

    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudyMenu/search"));
    chargeStudyMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/chargeStudyMenu/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudyMenu/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudyMenu/detail"));
    chargeStudyMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/chargeStudyMenu/update"));
    chargeStudyMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/chargeStudyMenu/delete"));

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

    communityInfoMenu.add(new MenuItem("검색", "/communityInfoMenu/search"));
    communityInfoMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
    communityInfoMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityInfo/update"));
    communityInfoMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityInfo/delete"));

    return communityInfoMenu;
  }

  private Menu createCommunityQaMenu() {
    MenuGroup communityQaMenu = new MenuGroup("질문");

    communityQaMenu.add(new MenuItem("검색", "/communityQaMenu/search"));
    communityQaMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
    communityQaMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityQa/update"));
    communityQaMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityQa/delete"));

    return communityQaMenu;
  }

  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

    communityTalkMenu.add(new MenuItem("검색", "/communityTalkMenu/search"));
    communityTalkMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
    communityTalkMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityTalk/update"));
    communityTalkMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityTalk/delete"));

    return communityTalkMenu;
  }

  private Menu createAdminMenu() {
    MenuGroup adminMenu = new MenuGroup("관리자");

    adminMenu.add(createMemberMenu());
    adminMenu.add(createCalenderMenu());

    return adminMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원 관리");

    memberMenu.add(createMentorApplicantMenu());

    return memberMenu;
  }

  private Menu createCalenderMenu() {
    MenuGroup calenderMenu = new MenuGroup("캘린더 관리");

    calenderMenu.add(createJobsCalenderMenu());
    calenderMenu.add(createExamCalenderMenu());

    return calenderMenu;
  }

  private Menu createMentorApplicantMenu() {
    MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");

    mentorApplicantMenu.add(new MenuItem("조회", "/mentorApplicant/list"));
    mentorApplicantMenu.add(new MenuItem("상세보기", "/mentorApplicant/detail"));

    return mentorApplicantMenu;
  }

  private Menu createJobsCalenderMenu() {
    MenuGroup jobsCalenderMenu = new MenuGroup("이달의 채용공고 관리");

    jobsCalenderMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/jobsCalender/add"));
    jobsCalenderMenu.add(new MenuItem("상세보기", "/jobsCalender/detail"));
    jobsCalenderMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/jobsCalender/update"));
    jobsCalenderMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/jobsCalender/delete"));

    return jobsCalenderMenu;
  }

  private Menu createExamCalenderMenu() {
    MenuGroup examCalenderMenu = new MenuGroup("이달의 시험일정 관리");

    examCalenderMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/examCalender/add"));
    examCalenderMenu.add(new MenuItem("상세보기", "/examCalender/detail"));
    examCalenderMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/examCalender/update"));
    examCalenderMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/examCalender/delete"));

    return examCalenderMenu;
  }

  private Menu createInterestMenu() {
    MenuGroup interestMenu = new MenuGroup("관심목록");

    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  private Menu createFreeInterestMenu() {
    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");

    freeInterestMenu.add(new MenuItem("조회", Menu.ENABLE_LOGIN, "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN,"/freeInterest/delete"));

    return freeInterestMenu;
  }

  private Menu createChargeInterestMenu() {
    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");

    chargeInterestMenu.add(new MenuItem("조회", Menu.ENABLE_LOGIN, "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/chargeInterest/delete"));

    return chargeInterestMenu;
  }
}
