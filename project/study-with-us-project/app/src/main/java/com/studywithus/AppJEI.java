package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
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
import com.studywithus.handler.AuthLoginHandler;
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
import com.studywithus.handler.ExamCalendarUpdateHandler;
import com.studywithus.handler.JobsCalendarAddHandler;
import com.studywithus.handler.JobsCalendarDeleteHandler;
import com.studywithus.handler.JobsCalendarDetailHandler;
import com.studywithus.handler.JobsCalendarUpdateHandler;
import com.studywithus.handler.MemberPrompt;
import com.studywithus.handler.SignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class AppJEI {
  List<Member> memberList = new LinkedList<>();
  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();
  List<Calendar> jobsCalendarList = new ArrayList<>();
  List<Calendar> examCalendarList = new ArrayList<>();

  HashMap<String, Command> commandMap = new HashMap<>();

  MemberPrompt memberPrompt = new MemberPrompt(memberList);

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
    AppJEI appJEI = new AppJEI(); 
    appJEI.service();
  }

  public AppJEI() {

    commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
    commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
    commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList));
    commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
    commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
    commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));

    commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
    commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
    commandMap.put("/communityQa/detail", new CommunityDetailHandler(communityQaList));
    commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
    commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
    commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));

    commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
    commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
    commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList));
    commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
    commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
    commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));

    commandMap.put("/jobsCalendar/add", new JobsCalendarAddHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/detail", new JobsCalendarDetailHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/update", new JobsCalendarUpdateHandler(jobsCalendarList));
    commandMap.put("/jobsCalendar/delete", new JobsCalendarDeleteHandler(jobsCalendarList));

    commandMap.put("/examCalendar/add", new ExamCalendarAddHandler(examCalendarList));
    commandMap.put("/examCalendar/detail", new ExamCalendarDetailHandler(examCalendarList));
    commandMap.put("/examCalendar/update", new ExamCalendarUpdateHandler(examCalendarList));
    commandMap.put("/examCalendar/delete", new ExamCalendarDeleteHandler(examCalendarList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    // commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
  }

  void service() {
    loadMembers();
    loadCommunityQas();
    loadCommunityInfos();
    loadCommunityTalks();
    loadJobsCalendars();
    loadExamCalendars();

    createMainMenu().execute();
    Prompt.close();

    saveMembers();
    saveCommunityQas();
    saveCommunityInfos();
    saveCommunityTalks();
    saveJobsalendars();
    saveExamCalendars();
  }

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", ACCESS_LOGOUT, "/auth/signUp"));
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logout"));
    mainMenuGroup.add(new MenuItem("회원탈퇴", ACCESS_GENERAL, "/auth/membershipwithdrawal"));

    mainMenuGroup.add(createCommunityMenu());

    return mainMenuGroup;
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
    communityInfoMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/communityInfo/delete"));

    return communityInfoMenu;
  }

  private Menu createCommunityQaMenu() {
    MenuGroup communityQaMenu = new MenuGroup("질문");

    communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
    communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
    communityQaMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityQa/update"));
    communityQaMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/communityQa/delete"));

    return communityQaMenu;
  }

  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

    communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
    communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
    communityTalkMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityTalk/update"));
    communityTalkMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/communityTalk/delete"));

    return communityTalkMenu;
  }

  private Menu createJobsCalendarMenu() {
    MenuGroup jobsCalendarMenu = new MenuGroup("이달의 채용공고 관리");

    jobsCalendarMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsCalendar/add"));
    jobsCalendarMenu.add(new MenuItem("상세보기", "/jobsCalendar/detail"));
    jobsCalendarMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/jobsCalendar/update"));
    jobsCalendarMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsCalendar/delete"));

    return jobsCalendarMenu;
  }

  private Menu createExamCalendarMenu() {
    MenuGroup examCalendarMenu = new MenuGroup("이달의 시험일정 관리");

    examCalendarMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examCalendar/add"));
    examCalendarMenu.add(new MenuItem("상세보기", "/examCalendar/detail"));
    examCalendarMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/examCalendar/update"));
    examCalendarMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examCalendar/delete"));

    return examCalendarMenu;
  }

  @SuppressWarnings("unchecked")
  private void loadMembers() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("member.data"))) {

      memberList.addAll((List<Member>) in.readObject());

    } catch (Exception e) {
      System.out.println("파일에서 회원 데이터를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveMembers() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("member.data"))) {

      out.writeObject(memberList);

    } catch (Exception e) {
      System.out.println("회원 데이터를 파일에 저장 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadCommunityQas() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("communityQa.data"))) {

      communityQaList.addAll((List<Community>) in.readObject());

    } catch (Exception e) {
      System.out.println("파일에서 커뮤니티 질문 데이터를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveCommunityQas() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("communityQa.data"))) {

      out.writeObject(communityQaList);

    } catch (Exception e) {
      System.out.println("커뮤니티 질문 데이터를 파일에 저장 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadCommunityInfos() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("communityInfo.data"))) {

      communityInfoList.addAll((List<Community>) in.readObject());

    } catch (Exception e) {
      System.out.println("파일에서 커뮤니티 정보 데이터를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveCommunityInfos() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("communityInfo.data"))) {

      out.writeObject(communityInfoList);

    } catch (Exception e) {
      System.out.println("커뮤니티 정보 데이터를 파일에 저장 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadCommunityTalks() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("communityTalk.data"))) {

      communityTalkList.addAll((List<Community>) in.readObject());

    } catch (Exception e) {
      System.out.println("파일에서 커뮤니티 스몰톡 데이터를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveCommunityTalks() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("communityTalk.data"))) {

      out.writeObject(communityTalkList);

    } catch (Exception e) {
      System.out.println("커뮤니티 스몰톡 데이터를 파일에 저장 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadJobsCalendars() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("jobsCalendar.data"))) {

      jobsCalendarList.addAll((List<Calendar>) in.readObject());

      System.out.println("이달의 채용공고 데이터 로딩이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("파일에서 이달의 채용공고 데이터를 읽어 오는 중 오류가 발생하였습니다.");
    }
  }

  private void saveJobsalendars() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("jobsCalendar.data"))) {

      out.writeObject(jobsCalendarList);

      System.out.println("이달의 채용공고 데이터 저장이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("이달의 채용공고 데이터를 파일에 저장 중 오류가 발생하였습니다.");
    }
  }

  @SuppressWarnings("unchecked")
  private void loadExamCalendars() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("examCalendar.data"))) {

      examCalendarList.addAll((List<Calendar>) in.readObject());

      System.out.println("이달의 시험일정 데이터 로딩이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("파일에서 이달의 시험일정 데이터를 읽어 오는 중 오류가 발생하였습니다.");
    }
  }

  private void saveExamCalendars() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("examCalendar.data"))) {

      out.writeObject(examCalendarList);

      System.out.println("이달의 시험일정 데이터 저장이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("이달의 시험일정 데이터를 파일에 저장 중 오류가 발생하였습니다.");
    }
  }


}