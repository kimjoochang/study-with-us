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
import com.studywithus.domain.MentorApplicant;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteHandler;
import com.studywithus.handler.ChargeStudyDetailHandler;
import com.studywithus.handler.ChargeStudyListHandler;
import com.studywithus.handler.ChargeStudyUpdateHandler;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommunityAddHandler;
import com.studywithus.handler.CommunityDeleteHandler;
import com.studywithus.handler.CommunityDetailHandler;
import com.studywithus.handler.CommunityListHandler;
import com.studywithus.handler.CommunitySearchHandler;
import com.studywithus.handler.CommunityUpdateHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class AppMap {
  List<Member> memberList = new LinkedList<>();
  List<FreeStudy> freeStudy = new ArrayList<>();
  List<JobsCalender> jobsCalenderList = new ArrayList<>();
  List<ExamCalender> examCalenderList = new ArrayList<>();
  List<MentorApplicant> mentorApplicantList = new ArrayList<>();
  List<ChargeStudy> chargeStudyList = new ArrayList<>();
  List<FreeStudy> freeInterestList = new ArrayList<>();
  List<ChargeStudy> chargeInterestList = new ArrayList<>();
  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();

  HashMap<String,Command> commandMap = new HashMap<>();

  AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
  AuthLogoutHandler authLogoutHandler = new AuthLogoutHandler();

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

  public AppMap() {
    commandMap.put("/freestudy/add", new FreeStudyAddHandler(freeStudyList));
    commandMap.put("/freestudy/list", new FreeStudyListHandler(freeStudyList));
    commandMap.put("/freestudy/detail", new FreeStudyDetailHandler(freeStudyList));
    commandMap.put("/freestudy/update", new FreeStudyUpdateHandler(freeStudyList));
    commandMap.put("/freestudy/delete", new FreeStudyDeleteHandler(freeStudyList));
    commandMap.put("/freestudy/search", new FreeStudySearchHandler(freeStudyList));

    commandMap.put("/chargestudy/add", new ChargeStudyAddHandler(chargeStudyList));
    commandMap.put("/chargestudy/list", new ChargeStudyListHandler(chargeStudyList));
    commandMap.put("/chargestudy/detail", new ChargeStudyDetailHandler(chargeStudyList));
    commandMap.put("/chargestudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
    commandMap.put("/chargestudy/delete", new ChargeStudyDeleteHandler(chargeStudyList));
    commandMap.put("/freestudy/search", new ChargeStudySearchHandler(chargeStudyList));

    commandMap.put("/community/add", new CommunityAddHandler(communityInfoList));
    commandMap.put("/community/list", new CommunityListHandler(communityInfoList));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityInfoList));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityInfoList));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityInfoList));
    commandMap.put("/freestudy/search", new CommunitySearchHandler(communityInfoList));

    commandMap.put("/community/add", new CommunityAddHandler(communityQaList));
    commandMap.put("/community/list", new CommunityListHandler(communityQaList));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityQaList));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityQaList));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityQaList));
    commandMap.put("/freestudy/search", new CommunitySearchHandler(communityQaList));

    commandMap.put("/community/add", new CommunityAddHandler(communityTalkList));
    commandMap.put("/community/list", new CommunityListHandler(communityTalkList));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityTalkList));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityTalkList));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityTalkList));
    commandMap.put("/freestudy/search", new CommunitySearchHandler(communityTalkList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    // commandMap.put("/auth/userinfo", new AuthUserInfoHandler());
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ENABLE_LOGIN, "/auth/userinfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_LOGIN, "/auth/logout"));

    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createCommunityMainMenu());
    mainMenuGroup.add(createCommunityInfoMenu());
    mainMenuGroup.add(createCommunityQaMenu());
    mainMenuGroup.add(createCommunityTalkMenu());

    return mainMenuGroup;
  }

  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");
    freeStudyMenu.add(new MenuItem("생성", Menu.ENABLE_LOGIN, "/freestudy/add"));
    freeStudyMenu.add(new MenuItem("조회", "/freestudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freestudy/detail"));
    freeStudyMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/freestudy/update"));
    freeStudyMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/freestudy/delete"));
    freeStudyMenu.add(new MenuItem("검색", "/freestudy/search"));
    return freeStudyMenu;
  }

  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");
    chargeStudyMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/chargestudy/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargestudy/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargestudy/detail"));
    chargeStudyMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/chargestudy/update"));
    chargeStudyMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/chargestudy/delete"));
    return chargeStudyMenu;
  }

  private Menu createCommunityMainMenu() {
    MenuGroup memberMenu = new MenuGroup("커뮤니티");
    return communityMainMenu;
  }

  private Menu createCommunityInfoMenu() {
    MenuGroup memberMenu = new MenuGroup("정보");
    communityInfoMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
    communityInfoMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityInfo/update"));
    communityInfoMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityInfo/delete"));
    return communityInfoMenu;
  }

  private Menu createCommunityQaMenu() {
    MenuGroup memberMenu = new MenuGroup("질문");
    communityQaMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
    communityQaMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityQa/update"));
    communityQaMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityQa/delete"));
    return communityQaMenu;
  }

  private Menu createCommunityTalkMenu() {
    MenuGroup memberMenu = new MenuGroup("정보");
    communityTalkMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
    communityTalkMenu.add(new MenuItem("수정", Menu.ENABLE_LOGIN, "/communityTalk/update"));
    communityTalkMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/communityTalk/delete"));
    return communityTalkMenu;
  }


}

