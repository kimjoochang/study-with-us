package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.domain.Community;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Study;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.ChargeStudyDeletedDetailHandler;
import com.studywithus.handler.ChargeStudyDeletedListHandler;
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
import com.studywithus.handler.ExamCalendarUpdateHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudySearchHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.handler.JobsCalendarAddHandler;
import com.studywithus.handler.JobsCalendarDeleteHandler;
import com.studywithus.handler.JobsCalendarDetailHandler;
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
  List<Study> freeStudyList = new ArrayList<>();
  List<Member> freeApplicantList = new ArrayList<>();
  List<Study> freeApplicationList = new ArrayList<>();
  List<Study> freeInterestList = new ArrayList<>();
  List<Study> chargeStudyList = new ArrayList<>();
  List<Member> mentorApplicantList = new ArrayList<>();
  List<Study> chargeDeleteRequestList = new ArrayList<>();
  List<Study> chargeInterestList = new ArrayList<>();
  List<MentorApplicationForm> mentorList = new ArrayList<>();
  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();
  List<Calendar> jobsCalendarList = new ArrayList<>();
  List<Calendar> examCalendarList = new ArrayList<>();
  List<MentorApplicationForm> MentorApplicationAddHandler = new ArrayList<>();
  List<MentorApplicationForm> MentorApplicationDetailHandler = new ArrayList<>();
  List<MentorApplicationForm> MentorApplicationFormListHandler = new ArrayList<>();

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
    AppJ app = new AppJ(); 
    app.service();
  }

  public AppJ() {
    commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList));
    commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList, freeApplicantList, freeApplicationList, freeInterestList));
    commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));
    commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));

    commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDeleteHandler(chargeStudyList, chargeInterestList));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
    commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(chargeStudyList, chargeDeleteRequestList, 1));
    commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));

    commandMap.put("/chargeStudy/deleteList", new ChargeStudyDeletedListHandler(chargeDeleteRequestList ,1));
    commandMap.put("/chargeStudy/deleteDetail", new ChargeStudyDeletedDetailHandler(chargeStudyList, chargeDeleteRequestList, 1));

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

    commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(mentorApplicantList));
    commandMap.put("/mentorApplicant/list", new MentorApplicationDetailHandler(mentorApplicantList));
    commandMap.put("/mentorApplicant/detail", new MentorApplicationFormListHandler(mentorApplicantList, mentorList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler(memberList));
    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
    commandMap.put("/auth/membershipwithdrawal", new MembershipWithdrawalHandler(memberList));
  }

  void service() {
    loadMembers();
    loadFreeStudies();
    loadChargeStudies();
    loadFreeInterests();
    loadChargeInterests();
    loadCommunityQas();
    loadCommunityInfos();
    loadCommunityTalks();
    loadJobsCalendars();
    loadExamCalendars();

    createMainMenu().execute();
    Prompt.close();

    saveMembers();
    saveFreeStudies();
    saveChargeStudies();
    saveFreeInterests();
    saveChargeInterests();
    saveCommunityQas();
    saveCommunityInfos();
    saveCommunityTalks();
    saveJobsalendars();
    saveExamCalendars();
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
  private void loadFreeStudies() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("freeStudy.data"))) {

      freeStudyList.addAll((List<Study>) in.readObject());

      System.out.println("무료 스터디 데이터 로딩이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("파일에서 무료 스터디 데이터를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveFreeStudies() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("freeStudy.data"))) {

      out.writeObject(freeStudyList);

      System.out.println("무료 스터디 데이터 저장이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("무료 스터디 데이터를 파일에 저장 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadChargeStudies() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("ChargeStudy.data"))) {

      chargeStudyList.addAll((List<Study>) in.readObject());

      System.out.println("유료 스터디 정보 로딩이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("파일에서 유료 스터디 정보를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveChargeStudies() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("ChargeStudy.data"))) {

      out.writeObject(chargeStudyList);

      System.out.println("유료 스터디 정보 저장이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("유료 스터디 정보를 파일에 저장하던 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadFreeInterests() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("freeInterest.data"))) {

      freeInterestList.addAll((List<Study>) in.readObject());

      System.out.println("무료 스터디 관심목록 데이터 로딩이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("파일에서 무료 스터디 관심목록 데이터를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveFreeInterests() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("freeInterest.data"))) {

      out.writeObject(freeInterestList);

      System.out.println("무료 스터디 관심목록 데이터 저장이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("무료 스터디 관심목록 데이터를 파일에 저장 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadChargeInterests() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("chargeInterest.data"))) {

      chargeInterestList.addAll((List<Study>) in.readObject());

      System.out.println("유료 스터디 관심목록 정보 로딩이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("파일에서 유료스터디 관심목록 정보를 읽어 오는 중 오류가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  private void saveChargeInterests() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("chargeInterest.data"))) {

      out.writeObject(chargeInterestList);

      System.out.println("유료 스터디 관심목록 정보 저장이 완료되었습니다.");

    } catch (Exception e) {
      System.out.println("유료 스터디 관심 목록 정보 저장하던 중 오류가 발생하였습니다.");
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

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", ACCESS_LOGOUT, "/auth/signUp"));
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logout"));
    mainMenuGroup.add(new MenuItem("회원탈퇴", ACCESS_GENERAL, "/auth/membershipwithdrawal"));

    mainMenuGroup.add(createMyPageMenu());
    mainMenuGroup.add(createAdminMenu());
    mainMenuGroup.add(createInterestMenu());
    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createMentorApplyMenu());
    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createCalendarMenu());

    return mainMenuGroup;
  }
  // 기존
  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

    myPageMenu.add(createMyInfoMenu());
    myPageMenu.add(createActivityDetailMenu());
    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createFreeStudyApplyMenu());
    myPageMenu.add(createPaymentListMenu());


    return myPageMenu;
  }

  /* 추가내용 

  // ------------------------------ 마이 페이지 -----------------------------------------

  // 마이 페이지 메인
  private Menu createMyPageMenu() {

    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);
    myPageMenu.add(createMyInfoMenu());
    myPageMenu.add(createActivityDetailMenu());
    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createFreeStudyApplyMenu());
    myPageMenu.add(createPaymentListMenu());

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

// 마이 페이지 / 나의 활동 / 내 스터디 / 내가 생성한 무료 스터디(팀장)
  // - 팀원 수락 / 거절

// 마이 페이지 / 나의 활동 / 내 스터디 / 내가 참여한 무료 스터디(팀원~)

// 마이 페이지 / 나의 활동 / 내 스터디 / 내가 생성한 유료 스터디(멘토)

// 마이 페이지 / 나의 활동 / 내 스터디 / 내가 참여한 유료 스터디(팀원~)

// 마이 페이지 / 멘토 신청하기

// 마이 페이지 / 회원 탈퇴

  // 스터디 후기 작성 마이페이지에 넣을건지 말건지?


  // 마이 페이지 / 나의 활동 / 내 게시글
  private Menu createMyPostMenu() {

    MenuGroup myPostMenu = new MenuGroup("내 게시글");
    myPostMenu.add(new MenuItem("조회", "//list"));
    myPostMenu.add(new MenuItem("상세보기", "//detail"));
    myPostMenu.add(new MenuItem("수정", "//update"));
    myPostMenu.add(new MenuItem("삭제", "//delete"));

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
    paymentListMenu.add(new MenuItem("", "/"));
    return paymentListMenu;
  }


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
    memberManagementMenu.add(createMentorApplicantMenu());
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

   */

  private Menu createMyInfoMenu() {
    MenuGroup myInfoMenu = new MenuGroup("나의 정보");

    myInfoMenu.add(new MenuItem("", "/"));

    return myInfoMenu;
  }

  private Menu createActivityDetailMenu() {
    MenuGroup activityDetailMenu = new MenuGroup("나의 활동");

    activityDetailMenu.add(createMyStudyMenu());
    activityDetailMenu.add(createMyPostMenu());

    return activityDetailMenu;
  }

  private Menu createMyStudyMenu() {
    MenuGroup myStudyMenu = new MenuGroup("나의 스터디");

    myStudyMenu.add(new MenuItem("조회", "//list"));
    myStudyMenu.add(new MenuItem("상세보기", "//detail"));
    myStudyMenu.add(new MenuItem("탈퇴 신청", "/"));

    return myStudyMenu;
  }

  private Menu createMyPostMenu() {
    MenuGroup myPostMenu = new MenuGroup("나의 게시글");

    myPostMenu.add(new MenuItem("조회", "//list"));
    myPostMenu.add(new MenuItem("상세보기", "//detail"));
    myPostMenu.add(new MenuItem("수정", "//update"));
    myPostMenu.add(new MenuItem("삭제", "//delete"));

    return myPostMenu;
  }

  private Menu createInterestMenu() {
    MenuGroup interestMenu = new MenuGroup("관심목록");

    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  private Menu createFreeInterestMenu() {
    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");

    freeInterestMenu.add(new MenuItem("조회", ACCESS_GENERAL, "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("삭제", ACCESS_GENERAL,"/freeInterest/delete"));

    return freeInterestMenu;
  }

  private Menu createChargeInterestMenu() {
    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");

    chargeInterestMenu.add(new MenuItem("조회", ACCESS_GENERAL, "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/chargeInterest/delete"));

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

  private Menu createPaymentListMenu() {
    MenuGroup paymentListMenu = new MenuGroup("결제 내역");

    paymentListMenu.add(new MenuItem("", "/"));

    return paymentListMenu;
  }

  private Menu createAdminMenu() {
    MenuGroup adminMenu = new MenuGroup("관리자", ACCESS_ADMIN);

    adminMenu.add(createMemberMenu());
    adminMenu.add(createDeleteRequestStudyMenu());

    return adminMenu;
  }

  private Menu createDeleteRequestStudyMenu() {
    MenuGroup deletedRequestMenu = new MenuGroup("삭제 요청 스터디 내역");
    deletedRequestMenu.add(new MenuItem("조회", "/chargeStudy/deleteList"));
    deletedRequestMenu.add(new MenuItem("상세보기", "/chargeStudy/deleteDetail"));

    return deletedRequestMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원 관리", ACCESS_ADMIN);

    memberMenu.add(createMentorApplicantMenu());

    return memberMenu;
  }

  private Menu createMentorApplyMenu() {
    MenuGroup mentorApplyMenu = new MenuGroup("멘토 신청하기");
    mentorApplyMenu.add(new MenuItem("신청", ACCESS_GENERAL, "/mentorApplicant/add"));

    return mentorApplyMenu;
  }

  private Menu createMentorApplicantMenu() {
    MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");

    mentorApplicantMenu.add(new MenuItem("조회", /*ACCESS_ADMIN,*/ "/mentorApplicant/list"));
    mentorApplicantMenu.add(new MenuItem("상세보기", /*ACCESS_ADMIN,*/ "/mentorApplicant/detail"));

    return mentorApplicantMenu;
  }

  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
    freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
    freeStudyMenu.add(new MenuItem("수정", ACCESS_LEADER, "/freeStudy/update"));
    freeStudyMenu.add(new MenuItem("삭제", ACCESS_LEADER, "/freeStudy/delete"));

    return freeStudyMenu;
  }

  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/chargeStudy/update"));
    chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_GENERAL, "/chargeStudy/deleteRequest"));

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

  private Menu createCalendarMenu() {
    MenuGroup calendarMenu = new MenuGroup("캘린더");

    calendarMenu.add(createJobsCalendarMenu());
    calendarMenu.add(createExamCalendarMenu());

    return calendarMenu;
  }


}
