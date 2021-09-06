package com.studywithus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.studywithus.AppMap.MenuItem;
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
import com.studywithus.handler.ExamCalenderAddHandler;
import com.studywithus.handler.ExamCalenderDeleteHandler;
import com.studywithus.handler.ExamCalenderDetailHandler;
import com.studywithus.handler.ExamCalenderUpdateHandler;
import com.studywithus.handler.FreeInterestAddHandler;
import com.studywithus.handler.FreeInterestDeleteHandler;
import com.studywithus.handler.FreeInterestListHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyApplyHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudySearchHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.handler.JobsCalenderAddHandler;
import com.studywithus.handler.JobsCalenderDeleteHandler;
import com.studywithus.handler.JobsCalenderDetailHandler;
import com.studywithus.handler.JobsCalenderUpdateHandler;
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

  MemberPrompt memberPrompt = new MemberPrompt(memberList);
  ProjectPrompt projectPrompt = new ProjectPrompt(projectList);

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

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
  }

  void service() {
    createMainMenu().execute();
    Prompt.close();
  }

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", Menu.ENABLE_LOGIN, "/auth/userinfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_LOGIN, "/auth/logout"));

    mainMenuGroup.add(createFreeStudyMenu());

    return mainMenuGroup;
  }

  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

    freeStudyMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("목록", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
    freeStudyMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/freeStudy/update"));
    freeStudyMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/freeStudy/delete"));
    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));

    return freeStudyMenu;
  }

  private Menu chargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료스터디");

    chargeStudyMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/chargeStudyMenu/add"));
    chargeStudyMenu.add(new MenuItem("목록", "/chargeStudyMenu/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudyMenu/detail"));
    chargeStudyMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/chargeStudyMenu/update"));
    chargeStudyMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/chargeStudyMenu/delete"));
    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudyMenu/search"));

    return chargeStudyMenu;
  }

  private Menu createCommunityMainMenu() {
    MenuGroup memberMenu = new MenuGroup("커뮤니티");

    return communityMainMenu;
  }

  private Menu createCommunityInfoMenu() {
    MenuGroup memberMenu = new MenuGroup("정보");

    communityInfoMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
    communityInfoMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityInfo/update"));
    communityInfoMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityInfo/delete"));

    return communityInfoMenu;
  }

  private Menu createCommunityQaMenu() {
    MenuGroup memberMenu = new MenuGroup("질문");

    communityQaMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
    communityQaMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityQa/update"));
    communityQaMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityQa/delete"));

    return communityQaMenu;
  }

  private Menu createCommunityTalkMenu() {
    MenuGroup memberMenu = new MenuGroup("스몰톡");

    communityTalkMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
    communityTalkMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityTalk/update"));
    communityTalkMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityTalk/delete"));

    return communityTalkMenu;
  }

  MenuGroup adminMenu = new MenuGroup("관리자");
  mainMenuGroup.add(adminMenu);

  MenuGroup memberMenu = new MenuGroup("회원 관리");
  adminMenu.add(memberMenu);

  MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");
  memberMenu.add(mentorApplicantMenu);

  mentorApplicantMenu.add(new Menu("멘토 신청내역 조회") {
    @Override 
    public void execute() {
      mentorApplicantListHandler.execute();
    }});

  mentorApplicantMenu.add(new Menu("멘토 신청내역 상세보기") {
    @Override 
    public void execute() {
      mentorApplicantListHandler.execute();
      System.out.println();
      Member applicant = mentorApplicantDetailHandler.execute1();

      System.out.println("1. 승인");
      System.out.println("2. 거절");
      System.out.println("0. 이전");

      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        mentorApproveHandler.execute1(applicant);

      } else if (input == 2) {
        mentorRejectHandler.execute1(applicant);
      }
    }});

  MenuGroup calenderMenu = new MenuGroup("캘린더 관리");
  adminMenu.add(calenderMenu);

  MenuGroup jobsCalenderMenu = new MenuGroup("이달의 채용공고 관리");
  calenderMenu.add(jobsCalenderMenu);

  jobsCalenderMenu.add(new Menu("생성") {
    @Override 
    public void execute() {
      jobsCalenderAddHandler.execute();
    }});

  jobsCalenderMenu.add(new Menu("상세보기") {
    @Override 
    public void execute() {
      jobsCalenderDetailHandler.execute();
    }});

  jobsCalenderMenu.add(new Menu("수정") {
    @Override 
    public void execute() {
      jobsCalenderUpdateHandler.execute();
    }});

  jobsCalenderMenu.add(new Menu("삭제") {
    @Override 
    public void execute() {
      jobsCalenderDeleteHandler.execute();
    }});

  MenuGroup examCalenderMenu = new MenuGroup("이달의 시험일정 관리");
  calenderMenu.add(examCalenderMenu);

  examCalenderMenu.add(new Menu("생성") {
    @Override 
    public void execute() {
      examCalenderAddHandler.execute();
    }});

  examCalenderMenu.add(new Menu("상세보기") {
    @Override 
    public void execute() {
      examCalenderDetailHandler.execute();
    }});

  examCalenderMenu.add(new Menu("변경") {
    @Override 
    public void execute() {
      examCalenderUpdateHandler.execute();
    }});

  examCalenderMenu.add(new Menu("삭제") {
    @Override 
    public void execute() {
      examCalenderDeleteHandler.execute();
    }});

  MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");
  mainMenuGroup.add(freeStudyMenu);

  freeStudyMenu.add(new Menu("검색") {
    @Override 
    public void execute() {
      freeStudySearchHandler.execute();
    }});

  freeStudyMenu.add(new Menu("생성") {
    @Override 
    public void execute() {
      freeStudyAddHandler.execute();
    }});

  freeStudyMenu.add(new Menu("조회") {
    @Override 
    public void execute() {
      freeStudyListHandler.execute();
    }});

  freeStudyMenu.add(new Menu("상세보기") {
    @Override
    public void execute() {
      FreeStudy freeInterest = freeStudyDetailHandler.executeDetail();

      System.out.println("1. 신청");
      System.out.println("2. 관심목록 추가");
      System.out.println("0. 이전");

      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        freeStudyApplyHandler.execute();

      } else if (input == 2) {
        freeInterestAddHandler.execute(freeInterest);
      }
    }});

  freeStudyMenu.add(new Menu("수정") {
    @Override 
    public void execute() {
      freeStudyUpdateHandler.execute();
    }});

  freeStudyMenu.add(new Menu("삭제") {
    @Override 
    public void execute() {
      freeStudyDeleteHandler.execute();
    }});

  MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");
  mainMenuGroup.add(chargeStudyMenu);

  chargeStudyMenu.add(new Menu("생성") {
    @Override
    public void execute() {
      chargeStudyAddHandler.execute(); 
    }});

  chargeStudyMenu.add(new Menu("조회") {
    @Override
    public void execute() {
      chargeStudyListHandler.execute(); 
    }});

  chargeStudyMenu.add(new Menu("상세보기") {
    @Override
    public void execute() {
      ChargeStudy chargeInterest = chargeStudyDetailHandler.execute1(); 

      System.out.println("1. 결제하기");
      System.out.println("2. 관심목록 추가하기");
      System.out.println("0. 이전");

      int input = Prompt.inputInt("선택>");

      if (input == 1) {
        return;

      } else if(input == 2) {
        chargeInterestAddHandler.execute(chargeInterest);
      }
    }});

  chargeStudyMenu.add(new Menu("수정") {
    @Override
    public void execute() {
      chargeStudyUpdateHandler.execute(); 
    }});

  chargeStudyMenu.add(new Menu("삭제") {
    @Override
    public void execute() {
      chargeStudyDeleteHandler.execute(); 
    }});

  MenuGroup interestMenu = new MenuGroup("관심목록");
  mainMenuGroup.add(interestMenu);

  MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");
  interestMenu.add(freeInterestMenu);

  freeInterestMenu.add(new Menu("조회") {
    @Override
    public void execute() {
      freeInterestListHandler.execute(); 
    }});

  freeInterestMenu.add(new Menu("삭제") {
    @Override
    public void execute() {
      freeInterestDeleteHandler.execute(); 
    }});

  MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");
  interestMenu.add(chargeInterestMenu);

  chargeInterestMenu.add(new Menu("조회") {
    @Override
    public void execute() {
      chargeInterestListHandler.execute(); 
    }});

  chargeInterestMenu.add(new Menu("삭제") {
    @Override
    public void execute() {
      chargeInterestDeleteHandler.execute(); 
    }});

  MenuGroup applyMentorMenu = new MenuGroup("멘토 신청하기");
  mainMenuGroup.add(applyMentorMenu);

  applyMentorMenu.add(new Menu("신청") {
    @Override
    public void execute() {
      mentorApplicantAddHandler.execute(); 
    }});

  // 커뮤니티 메인 메뉴
  MenuGroup communityMainMenu = new MenuGroup("커뮤니티");
  mainMenuGroup.add(communityMainMenu);

  // 커뮤니티 정보 메뉴
  MenuGroup communityInfoMenu = new MenuGroup("정보");
  communityMainMenu.add(communityInfoMenu);

  communityInfoMenu.add(new Menu("생성") {
    @Override
    public void execute() {
      communityInfoAddHandler.execute();
    }});

  communityInfoMenu.add(new Menu("조회") {
    @Override
    public void execute() {
      communityInfoListHandler.execute();
    }});

  communityInfoMenu.add(new Menu("상세보기") {
    @Override
    public void execute() {
      communityInfoDetailHandler.execute();
    }});

  communityInfoMenu.add(new Menu("수정") {
    @Override
    public void execute() {
      communityInfoUpdateHandler.execute();
    }});

  communityInfoMenu.add(new Menu("삭제") {
    @Override
    public void execute() {
      communityInfoDeleteHandler.execute();
    }});

  communityInfoMenu.add(new Menu("검색") {
    @Override
    public void execute() {
      communityInfoSearchHandler.execute();
    }});

  // 커뮤니티 질문 메뉴
  MenuGroup communityQaMenu = new MenuGroup("질문");
  communityMainMenu.add(communityQaMenu);

  communityQaMenu.add(new Menu("생성") {
    @Override
    public void execute() {
      communityQaAddHandler.execute();
    }});

  communityQaMenu.add(new Menu("조회") {
    @Override
    public void execute() {
      communityQaListHandler.execute();
    }});

  communityQaMenu.add(new Menu("상세보기") {
    @Override
    public void execute() {
      communityQaDetailHandler.execute();
    }});

  communityQaMenu.add(new Menu("수정") {
    @Override
    public void execute() {
      communityQaUpdateHandler.execute();
    }});

  communityQaMenu.add(new Menu("삭제") {
    @Override
    public void execute() {
      communityQaDeleteHandler.execute();
    }});

  communityQaMenu.add(new Menu("검색") {
    @Override
    public void execute() {
      communityQaSearchHandler.execute();
    }});

  // 커뮤니티 스몰톡 메뉴
  MenuGroup communityTalkMenu = new MenuGroup("스몰톡");
  communityMainMenu.add(communityTalkMenu);

  communityTalkMenu.add(new Menu("생성") {
    @Override
    public void execute() {
      communityTalkAddHandler.execute();
    }});

  communityTalkMenu.add(new Menu("조회") {
    @Override
    public void execute() {
      communityTalkListHandler.execute();
    }});

  communityTalkMenu.add(new Menu("상세보기") {
    @Override
    public void execute() {
      communityTalkDetailHandler.execute();
    }});

  communityTalkMenu.add(new Menu("수정") {
    @Override
    public void execute() {
      communityTalkUpdateHandler.execute();
    }});

  communityTalkMenu.add(new Menu("삭제") {
    @Override
    public void execute() {
      communityTalkDeleteHandler.execute();
    }});

  communityTalkMenu.add(new Menu("검색") {
    @Override
    public void execute() {
      communityTalkSearchHandler.execute();
    }});
}
