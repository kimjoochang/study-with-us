package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.Command;
import com.studywithus.handler.MembershipWithdrawalHandler;
import com.studywithus.handler.SignUpHandler;
import com.studywithus.handler.StudyAddHandler;
import com.studywithus.handler.StudyDeleteHandler;
import com.studywithus.handler.StudyDetailHandler;
import com.studywithus.handler.StudyListHandler;
import com.studywithus.handler.StudySearchHandler;
import com.studywithus.handler.StudyUpdateHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class AppBgr {
  List<Member> memberList = new LinkedList<>();
  List<Study> freeStudyList = new ArrayList<>();
  List<Study> freeStudyApplyList = new ArrayList<>();
  List<Study> freeInterestList = new ArrayList<>();
  List<Study> applicationList = new ArrayList<>();
  List<Payment> paymentList = new ArrayList<>();
  List<Member> mentorApplicantList = new ArrayList<>();
  List<Study> chargeStudyList = new ArrayList<>();
  List<Study> chargeDeleteRequestList = new ArrayList<>();
  List<Study> chargeInterestList = new ArrayList<>();
  List<MentorApplicationForm> mentorList = new ArrayList<>();

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
    AppBgr app = new AppBgr(); 
    app.service();
  }

  public AppBgr() {
    commandMap.put("/freeStudy/add", new StudyAddHandler(freeStudyList, chargeStudyList));
    commandMap.put("/freeStudy/list", new StudyListHandler(freeStudyList, chargeStudyList));
    commandMap.put("/freeStudy/detail", new StudyDetailHandler(freeStudyList, chargeStudyList, freeInterestList, chargeInterestList, applicationList, paymentList));
    commandMap.put("/freeStudy/update", new StudyUpdateHandler(freeStudyList, chargeStudyList));
    commandMap.put("/freeStudy/delete", new StudyDeleteHandler(freeStudyList, chargeStudyList));
    commandMap.put("/freeStudy/search", new StudySearchHandler(freeStudyList, chargeStudyList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler(memberList));
    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
    commandMap.put("/auth/membershipwithdrawal", new MembershipWithdrawalHandler(memberList));
  }

  void service() {
    //    loadMembers();
    //    loadFreeStudies();
    //    loadChargeStudies();
    //    loadFreeInterests();
    //    loadChargeInterests();

    createMainMenu().execute();
    Prompt.close();

    //    saveMembers();
    //    saveFreeStudies();
    //    saveChargeStudies();
    //    saveFreeInterests();
    //    saveChargeInterests();
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

    return mainMenuGroup;
  }

  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createFreeStudyApplyMenu());

    return myPageMenu;
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
}
