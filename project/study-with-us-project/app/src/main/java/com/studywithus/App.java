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
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Schedule;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.community.CommunityAddHandler;
import com.studywithus.handler.community.CommunityDeleteHandler;
import com.studywithus.handler.community.CommunityDetailHandler;
import com.studywithus.handler.community.CommunityListHandler;
import com.studywithus.handler.community.CommunitySearchHandler;
import com.studywithus.handler.community.CommunityUpdateHandler;
import com.studywithus.handler.community.MyPostDetailHandler;
import com.studywithus.handler.community.MyPostListHandler;
import com.studywithus.handler.schedule.ExamScheduleAddHandler;
import com.studywithus.handler.schedule.ExamScheduleDeleteHandler;
import com.studywithus.handler.schedule.ExamScheduleDetailHandler;
import com.studywithus.handler.schedule.ExamScheduleListHandler;
import com.studywithus.handler.schedule.ExamScheduleUpdateHandler;
import com.studywithus.handler.schedule.JobsScheduleAddHandler;
import com.studywithus.handler.schedule.JobsScheduleDeleteHandler;
import com.studywithus.handler.schedule.JobsScheduleDetailHandler;
import com.studywithus.handler.schedule.JobsScheduleListHandler;
import com.studywithus.handler.schedule.JobsScheduleUpdateHandler;
import com.studywithus.handler.study.ChargeStudyAddHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestDetailHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestListHandler;
import com.studywithus.handler.study.ChargeStudyDetailHandler;
import com.studywithus.handler.study.ChargeStudyInterestAddHandler;
import com.studywithus.handler.study.ChargeStudyInterestDeleteHandler;
import com.studywithus.handler.study.ChargeStudyInterestListHandler;
import com.studywithus.handler.study.ChargeStudyListHandler;
import com.studywithus.handler.study.ChargeStudyPaymentCancelHandler;
import com.studywithus.handler.study.ChargeStudyPaymentHandler;
import com.studywithus.handler.study.ChargeStudyPaymentListHandler;
import com.studywithus.handler.study.ChargeStudySearchHandler;
import com.studywithus.handler.study.ChargeStudyUpdateHandler;
import com.studywithus.handler.study.FreeStudyAddHandler;
import com.studywithus.handler.study.FreeStudyApplyCancelHandler;
import com.studywithus.handler.study.FreeStudyApplyHandler;
import com.studywithus.handler.study.FreeStudyApplyListHandler;
import com.studywithus.handler.study.FreeStudyDeleteHandler;
import com.studywithus.handler.study.FreeStudyDetailHandler;
import com.studywithus.handler.study.FreeStudyInterestAddHandler;
import com.studywithus.handler.study.FreeStudyInterestDeleteHandler;
import com.studywithus.handler.study.FreeStudyInterestListHandler;
import com.studywithus.handler.study.FreeStudyListHandler;
import com.studywithus.handler.study.FreeStudySearchHandler;
import com.studywithus.handler.study.FreeStudyUpdateHandler;
import com.studywithus.handler.study.MentorApplicationAddHandler;
import com.studywithus.handler.study.MentorApplicationDetailHandler;
import com.studywithus.handler.study.ParticipateChargeStudyDetailHandler;
import com.studywithus.handler.study.ParticipateChargeStudyListHandler;
import com.studywithus.handler.study.ParticipateFreeStudyListHandler;
import com.studywithus.handler.study.RegisterChargeStudyDetailHandler;
import com.studywithus.handler.study.RegisterFreeStudyDetailHandler;
import com.studywithus.handler.study.ReviewAddHandler;
import com.studywithus.handler.study.ReviewListHandler;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.handler.user.AuthLogOutHandler;
import com.studywithus.handler.user.FindIdHandler;
import com.studywithus.handler.user.MembershipWithdrawalHandler;
import com.studywithus.handler.user.MyInfoHandler;
import com.studywithus.handler.user.ResetPasswordHandler;
import com.studywithus.handler.user.SignUpHandler;
import com.studywithus.handler.user.SnsLogInHandler;
import com.studywithus.handler.user.SnsSignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class App {
  List<String> mentorList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();
  List<Member> freeApplicantList = new ArrayList<>();
  List<Member> mentorApplicantList = new ArrayList<>();
  List<Member> chargeApplicantList = new ArrayList<>();

  List<Study> freeStudyList = new ArrayList<>();
  List<Study> chargeStudyList = new ArrayList<>();
  List<Study> chargeDeleteRequestList = new ArrayList<>();

  List<MentorApplicationForm> mentorApplicationFormList = new ArrayList<>();

  List<Payment> chargePaymentList = new ArrayList<>();

  List<Community> communityInfoList = new ArrayList<>();
  List<Community> communityQaList = new ArrayList<>();
  List<Community> communityTalkList = new ArrayList<>();

  List<Schedule> jobsScheduleList = new ArrayList<>();
  List<Schedule> examScheduleList = new ArrayList<>();

  // [??????] HashMap ??????
  // List<Study> freeApplicationList = new ArrayList<>();
  // List<Study> chargeInterestList = new ArrayList<>();
  // List<Study> registerFreeStudyList = new ArrayList<>();
  // List<Study> participateFreeStudyList = new ArrayList<>();
  // List<Study> registerChargeStudyList = new ArrayList<>();
  // List<Study> participateChargeStudyList = new ArrayList<>();

  HashMap<String, Command> commandMap = new HashMap<>();
  HashMap<String, List<Study>> participateFreeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> participateChargeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> registerFreeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> registerChargeStudyMap = new HashMap<>();
  HashMap<String, List<Study>> applyFreeStudyMap = new HashMap<>();

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
        System.out.printf("%s ????????? ???????????? ??? ?????? ??????!\n", menuId);
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    App app = new App();
    app.service();
  }

  public App() {
    commandMap.put("/auth/logIn", new AuthLogInHandler(memberList));
    commandMap.put("/google/logIn", new SnsLogInHandler(memberList));
    commandMap.put("/facebook/logIn", new SnsLogInHandler(memberList));
    commandMap.put("/kakao/logIn", new SnsLogInHandler(memberList));
    commandMap.put("/naver/logIn", new SnsLogInHandler(memberList));

    commandMap.put("/auth/logOut", new AuthLogOutHandler(memberList));

    commandMap.put("/auth/signUp", new SignUpHandler(memberList));
    commandMap.put("/google/signUp", new SnsSignUpHandler(memberList));
    commandMap.put("/facebook/signUp", new SnsSignUpHandler(memberList));
    commandMap.put("/kakao/signUp", new SnsSignUpHandler(memberList));
    commandMap.put("/naver/signUp", new SnsSignUpHandler(memberList));

    commandMap.put("/find/id", new FindIdHandler(memberList));
    commandMap.put("/reset/password", new ResetPasswordHandler(memberList));

    commandMap.put("/auth/membershipWithdrawal", new MembershipWithdrawalHandler(memberList));

    commandMap.put("/myInfo/list", new MyInfoHandler());

    commandMap.put("/freeInterest/list", new FreeStudyInterestListHandler(freeStudyList));
    commandMap.put("/freeInterest/delete", new FreeStudyInterestDeleteHandler(freeStudyList));
    commandMap.put("/chargeInterest/list", new ChargeStudyInterestListHandler(chargeStudyList));

    commandMap.put("/mentorApplicant/add",
        new MentorApplicationAddHandler(mentorApplicationFormList, memberList));
    commandMap.put("/mentorApplicant/list",
        new MentorApplicationDetailHandler(mentorApplicationFormList, mentorList));

    commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
    commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList, registerFreeStudyMap));
    commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList));
    commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));

    commandMap.put("/freeStudy/apply", new FreeStudyApplyHandler(freeStudyList, applyFreeStudyMap));
    commandMap.put("/freeStudy/applyCancel",
        new FreeStudyApplyCancelHandler(freeStudyList, applyFreeStudyMap));
    commandMap.put("/freeStudy/applyList", new FreeStudyApplyListHandler(freeStudyList));
    commandMap.put("/freeStudy/addInterest", new FreeStudyInterestAddHandler(freeStudyList));
    commandMap.put("/freeStudy/deleteInterest", new FreeStudyInterestDeleteHandler(freeStudyList));
    commandMap.put("/freeStudy/registerStudyList",
        new RegisterFreeStudyDetailHandler(registerFreeStudyMap, participateFreeStudyMap));
    commandMap.put("/freeStudy/participateStudyList",
        new ParticipateFreeStudyListHandler(participateFreeStudyMap));

    commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));
    commandMap.put("/chargeStudy/add",
        new ChargeStudyAddHandler(chargeStudyList, registerChargeStudyMap));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
    commandMap.put("/chargeStudy/detail",
        new ChargeStudyDetailHandler(chargeStudyList, chargeApplicantList));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
    commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(chargeStudyList));
    commandMap.put("/chargeStudy/deleteRequestList", new ChargeStudyDeleteRequestListHandler(chargeStudyList));
    commandMap.put("/chargeStudy/deleteRequestDetail", new ChargeStudyDeleteRequestDetailHandler(chargeStudyList));
    commandMap.put("/chargeStudy/payment", new ChargeStudyPaymentHandler(chargeStudyList,
        chargePaymentList, chargeApplicantList, participateChargeStudyMap));
    commandMap.put("/chargeStudy/paymentCancel", new ChargeStudyPaymentCancelHandler(
        chargeStudyList, chargePaymentList, chargeApplicantList, participateChargeStudyMap));
    commandMap.put("/chargeStudy/paymentList",
        new ChargeStudyPaymentListHandler(chargeStudyList, participateChargeStudyMap));
    commandMap.put("/chargeStudy/interestAdd", new ChargeStudyInterestAddHandler(chargeStudyList));
    commandMap.put("/chargeStudy/interestDelete",
        new ChargeStudyInterestDeleteHandler(chargeStudyList));

    commandMap.put("/chargeStudy/registerChargeStudy",
        new RegisterChargeStudyDetailHandler(registerChargeStudyMap));
    commandMap.put("/chargeStudy/participateChargeStudyList",
        new ParticipateChargeStudyListHandler(participateChargeStudyMap));
    commandMap.put("/chargeStudy/participateChargeStudyDetail", new ParticipateChargeStudyDetailHandler(participateChargeStudyMap)); 
    commandMap.put("/review/add", new ReviewAddHandler(chargeStudyList));
    commandMap.put("/review/list", new ReviewListHandler(chargeStudyList));

    commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
    commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
    commandMap.put("/communityQa/detail",
        new CommunityDetailHandler(communityQaList, "/communityQa/update", "/communityQa/delete"));
    commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
    commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
    commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));

    commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
    commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
    commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList,
        "/communityInfo/update", "/communityInfo/delete"));
    commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
    commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
    commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));

    commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
    commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
    commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList,
        "/communityTalk/update", "/communityTalk/delete"));
    commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
    commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
    commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));

    commandMap.put("/myPost/list",
        new MyPostListHandler(communityQaList, communityInfoList, communityTalkList));
    commandMap.put("/myPost/detail",
        new MyPostDetailHandler(communityQaList, communityInfoList, communityTalkList));

    commandMap.put("/jobsSchedule/add", new JobsScheduleAddHandler(jobsScheduleList));
    commandMap.put("/jobsSchedule/list", new JobsScheduleListHandler(jobsScheduleList));
    commandMap.put("/jobsSchedule/detail", new JobsScheduleDetailHandler(jobsScheduleList));
    commandMap.put("/jobsSchedule/update", new JobsScheduleUpdateHandler(jobsScheduleList));
    commandMap.put("/jobsSchedule/delete", new JobsScheduleDeleteHandler(jobsScheduleList));

    commandMap.put("/examSchedule/add", new ExamScheduleAddHandler(examScheduleList));
    commandMap.put("/examSchedule/list", new ExamScheduleListHandler(examScheduleList));
    commandMap.put("/examSchedule/detail", new ExamScheduleDetailHandler(examScheduleList));
    commandMap.put("/examSchedule/update", new ExamScheduleUpdateHandler(examScheduleList));
    commandMap.put("/examSchedule/delete", new ExamScheduleDeleteHandler(examScheduleList));
  }

  void service() {

    // [??????] HashMap ??????
    // loadObjects("chargeInterest.json", chargeInterestList, Study.class);
    loadObjects("member.json", memberList, Member.class);
    loadObjects("freeStudy.json", freeStudyList, Study.class);
    loadObjects("chargeStudy.json", chargeStudyList, Study.class);
    loadObjects("communityQa.json", communityQaList, Community.class);
    loadObjects("communityInfo.json", communityInfoList, Community.class);
    loadObjects("communityTalk.json", communityTalkList, Community.class);
    loadObjects("jobsSchedule.json", jobsScheduleList, Schedule.class);
    loadObjects("examSchedule.json", examScheduleList, Schedule.class);

    System.out.println();
    System.out.println("|         ??????????????????         |");
    System.out.println("|          STUDYWITHUS         |");
    System.out.println("     ????????????????????????????????????   ");
    System.out.println("?????????      ???_,,???");
    System.out.println("?????????     (`??????????)");
    System.out.println("?????????     ??? ?? ???");
    System.out.println("???     ?????????????????????");
    System.out.println("???     |?????????????????????|");
    System.out.println("      ????????????????????? ???");
    System.out.println("    ??????       ??????   ???");
    System.out.println("        ????????? ???   ??????   ???");
    System.out.println("    ???(??????)??? (??????)???");
    System.out.println("    ???/???/??????  /???/???");
    System.out.println("    ????????????  ?????????  Are U ready to STUDY ?");

    createMainMenu().execute();
    Prompt.close();

    // [??????] HashMap ??????
    // saveObjects("chargeInterest.json", chargeInterestList);
    saveObjects("member.json", memberList);
    saveObjects("freeStudy.json", freeStudyList);
    saveObjects("chargeStudy.json", chargeStudyList);
    saveObjects("communityQa.json", communityQaList);
    saveObjects("communityInfo.json", communityInfoList);
    saveObjects("communityTalk.json", communityTalkList);
    saveObjects("jobsSchedule.json", jobsScheduleList);
    saveObjects("examSchedule.json", examScheduleList);
  }

  // JSON ???????????? ????????? ???????????? ????????? ????????? ?????????.
  private <E> void loadObjects(String filepath, // ???????????? ?????? ??? ?????? ??????
      List<E> list, // ????????? ???????????? ????????? ?????? ??? ????????? ??????
      Class<E> domainType // ????????? ????????? ?????? ??????
      ) {

    try (BufferedReader in =
        new BufferedReader(new FileReader(filepath, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;

      while ((str = in.readLine()) != null) { // ?????? ????????? ?????????.
        strBuilder.append(str);
      }

      // StringBuilder??? ?????? ??? JSON ???????????? ????????? ?????????.
      Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
      Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

      // JSON ???????????? ????????? ????????? ??????????????? ?????? List ??? ????????????.
      list.addAll(collection);

      System.out.printf("%s ????????? ?????? ??????!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s ????????? ?????? ??????!\n", filepath);
    }
  }

  // ????????? JSON ???????????? ????????????.
  private void saveObjects(String filepath, List<?> list) {
    try (PrintWriter out =
        new PrintWriter(new BufferedWriter(new FileWriter(filepath, Charset.forName("UTF-8"))))) {

      out.print(new Gson().toJson(list));

      System.out.printf("%s ????????? ?????? ??????!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s ????????? ?????? ??????!\n", filepath);
      e.printStackTrace();
    }
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
    logInMenu.add(new MenuItem("????????? ??????", "/find/id"));
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
    // [??????] ???????????? ????????? ?????? ??????
    // freeStudyMenu.add(new MenuItem("??????", ACCESS_LEADER, "/freeStudy/update"));
    // freeStudyMenu.add(new MenuItem("??????", ACCESS_LEADER | ACCESS_ADMIN, "/freeStudy/delete"));

    return freeStudyMenu;
  }

  // ------------------------------ ?????? ????????? -----------------------------------------

  // ?????? ????????? ?????? ??????
  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("?????? ?????????");

    chargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("??????", ACCESS_MENTOR, "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("????????????", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("?????? ??????", ACCESS_GENERAL, "/mentorApplicant/add"));
    // [??????] ???????????? ????????? ?????? ??????
    // chargeStudyMenu.add(new MenuItem("??????", ACCESS_MENTOR, "/chargeStudy/update"));
    // chargeStudyMenu.add(new MenuItem("?????? ??????", ACCESS_MENTOR, "/chargeStudy/deleteRequest"));

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
    // [??????] ???????????? ????????? ?????? ??????
    // communityQaMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityQa/update"));
    // communityQaMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_ADMIN,
    // "/communityQa/delete"));

    return communityQaMenu;
  }

  // ???????????? / ??????
  private Menu createCommunityInfoMenu() {
    MenuGroup communityInfoMenu = new MenuGroup("??????");

    communityInfoMenu.add(new MenuItem("??????", "/communityInfo/search"));
    communityInfoMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("??????", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("????????????", "/communityInfo/detail"));
    // [??????] ???????????? ????????? ?????? ??????
    // communityInfoMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityInfo/update"));
    // communityInfoMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_ADMIN,
    // "/communityInfo/delete"));

    return communityInfoMenu;
  }

  // ???????????? / ?????????
  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("?????????");

    communityTalkMenu.add(new MenuItem("??????", "/communityTalk/search"));
    communityTalkMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("??????", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("????????????", "/communityTalk/detail"));
    // [??????] ???????????? ????????? ?????? ??????
    // communityTalkMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityTalk/update"));
    // communityTalkMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_ADMIN,
    // "/communityTalk/delete"));

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
    // [??????] ???????????? ????????? ?????? ??????
    // jobsScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsSchedule/update"));
    // jobsScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsSchedule/delete"));

    return jobsScheduleMenu;
  }

  private Menu createExamScheduleMenu() {
    MenuGroup examScheduleMenu = new MenuGroup("????????? ????????????");

    examScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleMenu.add(new MenuItem("??????", "/examSchedule/list"));
    examScheduleMenu.add(new MenuItem("????????????", "/examSchedule/detail"));
    // [??????] ???????????? ????????? ?????? ??????
    // examScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/update"));
    // examScheduleMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/delete"));

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
    // [??????] ???????????? ??????????????? ?????? ??? ACCESS_MENTEE ?????? ??????
    // myPageMenu.add(new MenuItem("?????? ????????????", "/chargeStudy/payment"));

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
    // [??????] ???????????? ????????? ?????? ??????
    // myPostMenu.add(new MenuItem("??????", "/myPost/update"));
    // myPostMenu.add(new MenuItem("??????", "/myPost/delete"));

    return myPostMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ??????
  private Menu createFreeStudyApplyMenu() {

    MenuGroup freeStudyApplyMenu = new MenuGroup("?????? ????????? ?????? ??????", ACCESS_GENERAL);
    freeStudyApplyMenu.add(new MenuItem("??????", "/freeStudy/applyList"));
    // [??????] ???????????? ????????? ?????? ??????
    // freeStudyApplyMenu.add(new MenuItem("????????????", "/freeStudyApply/detail"));
    // freeStudyApplyMenu.add(new MenuItem("??????", "/freeStudyApply/delete"));

    return freeStudyApplyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????(?????? ??????)
  // - "????????? ??????" -> ????????????(??????/??????) ???????????? ???
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
    // [??????] ?????? ??? ???????????? ??????
    // participateFreeStudyMenu.add(new MenuItem("????????????", "/participateFreeStudy/detail"));

    return participateFreeStudyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????(?????? ??????) [X]
  // - "????????? ??????" -> ????????????(??????/??????) ???????????? ???
  private Menu createRegisterChargeStudyMenu() {

    MenuGroup registerChargeStudyMenu = new MenuGroup("?????? ????????? ?????? ?????????", ACCESS_GENERAL);
    registerChargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/registerChargeStudyList"));
    registerChargeStudyMenu.add(new MenuItem("????????????", "/chargeStudy/registerChargeStudyDetail"));
    registerChargeStudyMenu.add(new MenuItem("??????", "/registerChargeStudy/delete"));

    return registerChargeStudyMenu;
  }

  // ?????? ????????? / ?????? ?????? / ?????? ????????? / ?????? ????????? ?????? ?????????(?????? ??????) [X]
  private Menu createParticipateChargeStudyMenu() {

    MenuGroup participateChargeStudyMenu = new MenuGroup("?????? ????????? ?????? ?????????", ACCESS_GENERAL);
    participateChargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/participateChargeStudyList"));

    // [??????] ??????????????? '??????' ??????, ?????? ?????? ?????? ??? ?????? ????????? ??????
    // ?????????, ????????? -> ?????? ?????? && ?????? ?????? -> ?????? ??????
    participateChargeStudyMenu
    .add(new MenuItem("????????????", "/chargeStudy/participateChargeStudyDetail"));
    participateChargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/participateChargeStudyDetail"));

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
    chargeInterestMenu.add(new MenuItem("??????", "/chargeStudy/interestDelete"));

    return chargeInterestMenu;
  }

  // ?????? ????????? / ?????? ?????? ??????
  private Menu createPaymentListMenu() {

    MenuGroup paymentListMenu = new MenuGroup("?????? ?????? ??????");
    paymentListMenu.add(new MenuItem("??????", ACCESS_MENTEE, "/chargeStudy/paymentList"));
    paymentListMenu.add(new MenuItem("????????????", ACCESS_MENTEE, "/chargeStudy/paymentCancel"));
    // [??????] ???????????? / ?????? ??????

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
    memberManagementMenu.add(createBlackListMenu());

    return memberManagementMenu;
  }

  // ????????? ????????? / ?????? ?????? / ??????????????? ??????
  private Menu createBlackListMenu() {

    MenuGroup mentorManagementMenu = new MenuGroup("??????????????? ??????");
    mentorManagementMenu.add(new MenuItem("", "/"));

    return mentorManagementMenu;
  }

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
    // [??????] ???????????? ????????? ?????? ??????
    // jobsScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsSchedule/update"));
    // jobsScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsSchedule/delete"));

    return jobsScheduleManagementMenu;
  }

  // ????????? ????????? / ?????? ?????? / ????????? ???????????? ??????
  private Menu createExamScheduleManagementMenu() {

    MenuGroup examScheduleManagementMenu = new MenuGroup("????????? ????????????");
    examScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleManagementMenu.add(new MenuItem("????????????", "/examSchedule/detail"));
    // [??????] ???????????? ????????? ?????? ??????
    // examScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/update"));
    // examScheduleManagementMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examSchedule/delete"));

    return examScheduleManagementMenu;
  }

  /*
   * ?????? App (2021-09-18 ver) ??????
   * 
   * package com.studywithus;
   * 
   * import static com.studywithus.menu.Menu.ACCESS_ADMIN; import static
   * com.studywithus.menu.Menu.ACCESS_GENERAL; import static
   * com.studywithus.menu.Menu.ACCESS_LEADER; import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
   * import static com.studywithus.menu.Menu.ACCESS_MENTOR; import java.io.BufferedReader; import
   * java.io.BufferedWriter; import java.io.FileReader; import java.io.FileWriter; import
   * java.io.PrintWriter; import java.lang.reflect.Type; import java.nio.charset.Charset; import
   * java.util.ArrayList; import java.util.Collection; import java.util.HashMap; import
   * java.util.LinkedList; import java.util.List; import com.google.gson.Gson; import
   * com.google.gson.reflect.TypeToken; import com.studywithus.domain.Schedule; import
   * com.studywithus.domain.Community; import com.studywithus.domain.Member; import
   * com.studywithus.domain.MentorApplicationForm; import com.studywithus.domain.Payment; import
   * com.studywithus.domain.Study; import com.studywithus.handler.Command; import
   * com.studywithus.handler.CommandRequest; import
   * com.studywithus.handler.FreeInterestDeleteHandler; import
   * com.studywithus.handler.FreeInterestListHandler; import
   * com.studywithus.handler.community.CommunityAddHandler; import
   * com.studywithus.handler.community.CommunityDeleteHandler; import
   * com.studywithus.handler.community.CommunityDetailHandler; import
   * com.studywithus.handler.community.CommunityListHandler; import
   * com.studywithus.handler.community.CommunitySearchHandler; import
   * com.studywithus.handler.community.CommunityUpdateHandler; import
   * com.studywithus.handler.schedule.ExamScheduleAddHandler; import
   * com.studywithus.handler.schedule.ExamScheduleDeleteHandler; import
   * com.studywithus.handler.schedule.ExamScheduleDetailHandler; import
   * com.studywithus.handler.schedule.ExamScheduleListHandler; import
   * com.studywithus.handler.schedule.ExamScheduleUpdateHandler; import
   * com.studywithus.handler.schedule.JobsScheduleAddHandler; import
   * com.studywithus.handler.schedule.JobsScheduleDeleteHandler; import
   * com.studywithus.handler.schedule.JobsScheduleDetailHandler; import
   * com.studywithus.handler.schedule.JobsScheduleListHandler; import
   * com.studywithus.handler.schedule.JobsScheduleUpdateHandler; import
   * com.studywithus.handler.study.ChargeStudyAddHandler; import
   * com.studywithus.handler.study.ChargeStudyDeleteRequestHandler; import
   * com.studywithus.handler.study.ChargeStudyDeletedDetailHandler; import
   * com.studywithus.handler.study.ChargeStudyDeletedListHandler; import
   * com.studywithus.handler.study.ChargeStudyDetailHandler; import
   * com.studywithus.handler.study.ChargeStudyInterestAddHandler; import
   * com.studywithus.handler.study.ChargeStudyInterestDeleteHandler; import
   * com.studywithus.handler.study.ChargeStudyInterestListHandler; import
   * com.studywithus.handler.study.ChargeStudyListHandler; import
   * com.studywithus.handler.study.ChargeStudyPaymentHandler; import
   * com.studywithus.handler.study.ChargeStudySearchHandler; import
   * com.studywithus.handler.study.ChargeStudyUpdateHandler; import
   * com.studywithus.handler.study.FreeStudyAddHandler; import
   * com.studywithus.handler.study.FreeStudyDeleteHandler; import
   * com.studywithus.handler.study.FreeStudyDetailHandler; import
   * com.studywithus.handler.study.FreeStudyListHandler; import
   * com.studywithus.handler.study.FreeStudySearchHandler; import
   * com.studywithus.handler.study.FreeStudyUpdateHandler; import
   * com.studywithus.handler.study.MentorApplicationAddHandler; import
   * com.studywithus.handler.study.MentorApplicationDetailHandler; import
   * com.studywithus.handler.user.AuthLogInHandler; import
   * com.studywithus.handler.user.AuthLogOutHandler; import
   * com.studywithus.handler.user.MembershipWithdrawalHandler; import
   * com.studywithus.handler.user.MyInfoHandler; import com.studywithus.handler.user.SignUpHandler;
   * import com.studywithus.menu.Menu; import com.studywithus.menu.MenuGroup; import
   * com.studywithus.util.Prompt;
   * 
   * public class AppOriginal { List<Member> memberList = new LinkedList<>(); List<Member>
   * freeApplicantList = new ArrayList<>(); List<Member> mentorApplicantList = new ArrayList<>();
   * List<Member> chargeApplicantList = new ArrayList<>();
   * 
   * List<String> mentorList = new ArrayList<>();
   * 
   * List<Study> registerFreeStudyList = new ArrayList<>(); List<Study> participateFreeStudyList =
   * new ArrayList<>(); List<Study> registerChargeStudyList = new ArrayList<>(); List<Study>
   * participateChargeStudyList = new ArrayList<>(); List<Study> freeInterestList = new
   * ArrayList<>(); List<Study> chargeInterestList = new ArrayList<>(); List<Study> freeStudyList =
   * new ArrayList<>(); List<Study> freeApplicationList = new ArrayList<>(); List<Study>
   * chargeStudyList = new ArrayList<>(); List<Study> chargeDeleteRequestList = new ArrayList<>();
   * 
   * List<MentorApplicationForm> mentorApplicationFormList = new ArrayList<>();
   * 
   * List<Payment> chargePaymentList = new ArrayList<>();
   * 
   * List<Community> communityInfoList = new ArrayList<>(); List<Community> communityQaList = new
   * ArrayList<>(); List<Community> communityTalkList = new ArrayList<>();
   * 
   * List<Schedule> jobsCalendarList = new ArrayList<>(); List<Schedule> examCalendarList = new
   * ArrayList<>();
   * 
   * HashMap<String, Command> commandMap = new HashMap<>(); HashMap<String, List<Study>>
   * participateFreeStudyMap = new HashMap<>(); HashMap<String, List<Study>>
   * participateChargeStudyMap = new HashMap<>(); HashMap<String, List<Study>> registerFreeStudyMap
   * = new HashMap<>(); HashMap<String, List<Study>> registerChargeStudyMap = new HashMap<>();
   * 
   * class MenuItem extends Menu { String menuId;
   * 
   * public MenuItem(String title, String menuId) { super(title); this.menuId = menuId; }
   * 
   * public MenuItem(String title, int accessScope, String menuId) { super(title, accessScope);
   * this.menuId = menuId; }
   * 
   * @Override public void execute() { Command command = commandMap.get(menuId); try {
   * command.execute(new CommandRequest(commandMap)); } catch (Exception e) {
   * System.out.printf("%s ????????? ???????????? ??? ?????? ??????!\n", menuId); e.printStackTrace(); } } }
   * 
   * public static void main(String[] args) { App app = new App(); app.service(); }
   * 
   * public AppOriginal() { commandMap.put("/auth/login", new AuthLogInHandler(memberList));
   * commandMap.put("/auth/logout", new AuthLogOutHandler(memberList));
   * commandMap.put("/auth/signUp", new SignUpHandler(memberList));
   * commandMap.put("/auth/membershipwithdrawal", new MembershipWithdrawalHandler(memberList));
   * commandMap.put("/auth/userinfo", new MyInfoHandler(memberList));
   * 
   * commandMap.put("/freeInterest/list", new FreeInterestListHandler(freeInterestList));
   * commandMap.put("/freeInterest/delete", new FreeInterestDeleteHandler(freeStudyList,
   * freeInterestList)); commandMap.put("/chargeInterest/list", new
   * ChargeStudyInterestListHandler(chargeInterestList));
   * 
   * commandMap.put("/mentorApplicant/add", new
   * MentorApplicationAddHandler(mentorApplicationFormList));
   * commandMap.put("/mentorApplicant/list", new
   * MentorApplicationDetailHandler(mentorApplicationFormList, mentorList));
   * 
   * commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
   * commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList, registerFreeStudyMap));
   * commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
   * commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList,
   * freeApplicationList, freeInterestList)); commandMap.put("/freeStudy/update", new
   * FreeStudyUpdateHandler(freeStudyList)); commandMap.put("/freeStudy/delete", new
   * FreeStudyDeleteHandler(freeStudyList));
   * 
   * commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));
   * commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList,
   * registerChargeStudyMap)); commandMap.put("/chargeStudy/list", new
   * ChargeStudyListHandler(chargeStudyList)); commandMap.put("/chargeStudy/detail", new
   * ChargeStudyDetailHandler(chargeStudyList, chargeInterestList, chargePaymentList,
   * chargeApplicantList, participateChargeStudyMap)); commandMap.put("/chargeStudy/update", new
   * ChargeStudyUpdateHandler(chargeStudyList)); commandMap.put("/chargeStudy/deleteRequest", new
   * ChargeStudyDeleteRequestHandler(chargeStudyList, chargeDeleteRequestList));
   * commandMap.put("/chargeStudy/deleteList", new
   * ChargeStudyDeletedListHandler(chargeDeleteRequestList));
   * commandMap.put("/chargeStudy/deleteDetail", new
   * ChargeStudyDeletedDetailHandler(chargeStudyList, chargeDeleteRequestList));
   * commandMap.put("/chargeStudy/payment", new ChargeStudyPaymentHandler(chargeStudyList,
   * chargePaymentList, chargeApplicantList, participateChargeStudyMap));
   * commandMap.put("/chargeStudy/interestAdd", new ChargeStudyInterestAddHandler(chargeStudyList,
   * chargeInterestList)); commandMap.put("/chargeStudy/InterestDelete", new
   * ChargeStudyInterestDeleteHandler(chargeStudyList, chargeInterestList));
   * 
   * commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
   * commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
   * commandMap.put("/communityQa/detail", new CommunityDetailHandler(communityQaList));
   * commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
   * commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
   * commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));
   * 
   * commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
   * commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
   * commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList));
   * commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
   * commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
   * commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));
   * 
   * commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
   * commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
   * commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList));
   * commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
   * commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
   * commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));
   * 
   * commandMap.put("/jobsCalendar/add", new JobsScheduleAddHandler(jobsCalendarList));
   * commandMap.put("/jobsCalendar/list", new JobsScheduleListHandler(jobsCalendarList));
   * commandMap.put("/jobsCalendar/detail", new JobsScheduleDetailHandler(jobsCalendarList));
   * commandMap.put("/jobsCalendar/update", new JobsScheduleUpdateHandler(jobsCalendarList));
   * commandMap.put("/jobsCalendar/delete", new JobsScheduleDeleteHandler(jobsCalendarList));
   * 
   * commandMap.put("/examCalendar/add", new ExamScheduleAddHandler(examCalendarList));
   * commandMap.put("/examCalendar/list", new ExamScheduleListHandler(examCalendarList));
   * commandMap.put("/examCalendar/detail", new ExamScheduleDetailHandler(examCalendarList));
   * commandMap.put("/examCalendar/update", new ExamScheduleUpdateHandler(examCalendarList));
   * commandMap.put("/examCalendar/delete", new ExamScheduleDeleteHandler(examCalendarList)); }
   * 
   * void service() { // loadObjects("member.json", memberList, Member.class); //
   * loadObjects("freeInterest.json", freeInterestList, Study.class); //
   * loadObjects("chargeInterest.json", chargeInterestList, Study.class); //
   * loadObjects("freeStudy.json", freeStudyList, Study.class); // loadObjects("chargeStudy.json",
   * chargeStudyList, Study.class); // loadObjects("communityQa.json", communityQaList,
   * Community.class); // loadObjects("communityInfo.json", communityInfoList, Community.class); //
   * loadObjects("communityTalk.json", communityTalkList, Community.class); //
   * loadObjects("jobsCalendar.json", jobsCalendarList, Calendar.class); //
   * loadObjects("examCalendar.json", examCalendarList, Calendar.class);
   * 
   * createMainMenu().execute(); Prompt.close();
   * 
   * saveObjects("member.json", memberList); saveObjects("freeInterest.json", freeInterestList);
   * saveObjects("chargeInterest.json", chargeInterestList); saveObjects("freeStudy.json",
   * freeStudyList); saveObjects("chargeStudy.json", chargeStudyList);
   * saveObjects("communityQa.json", communityQaList); saveObjects("communityInfo.json",
   * communityInfoList); saveObjects("communityTalk.json", communityTalkList);
   * saveObjects("jobsCalendar.json", jobsCalendarList); saveObjects("examCalendar.json",
   * examCalendarList); }
   * 
   * // JSON ???????????? ????????? ???????????? ????????? ????????? ?????????. private <E> void loadObjects( String filepath, // ???????????? ?????? ???
   * ?????? ?????? List<E> list, // ????????? ???????????? ????????? ?????? ??? ????????? ?????? Class<E> domainType // ????????? ????????? ?????? ?????? ) {
   * 
   * try (BufferedReader in = new BufferedReader( new FileReader(filepath,
   * Charset.forName("UTF-8")))) {
   * 
   * StringBuilder strBuilder = new StringBuilder(); String str;
   * 
   * while ((str = in.readLine()) != null) { // ?????? ????????? ?????????. strBuilder.append(str); }
   * 
   * // StringBuilder??? ?????? ??? JSON ???????????? ????????? ?????????. Type type =
   * TypeToken.getParameterized(Collection.class, domainType).getType(); Collection<E> collection =
   * new Gson().fromJson(strBuilder.toString(), type);
   * 
   * // JSON ???????????? ????????? ????????? ??????????????? ?????? List ??? ????????????. list.addAll(collection);
   * 
   * System.out.printf("%s ????????? ?????? ??????!\n", filepath);
   * 
   * } catch (Exception e) { System.out.printf("%s ????????? ?????? ??????!\n", filepath); } }
   * 
   * // ????????? JSON ???????????? ????????????. private void saveObjects(String filepath, List<?> list) { try
   * (PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter(filepath,
   * Charset.forName("UTF-8"))))) {
   * 
   * out.print(new Gson().toJson(list));
   * 
   * System.out.printf("%s ????????? ?????? ??????!\n", filepath);
   * 
   * } catch (Exception e) { System.out.printf("%s ????????? ?????? ??????!\n", filepath); e.printStackTrace(); }
   * }
   * 
   * Menu createMainMenu() { MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
   * mainMenuGroup.setPrevMenuTitle("??????");
   * 
   * mainMenuGroup.add(new MenuItem("?????????", ACCESS_LOGOUT, "/auth/login")); mainMenuGroup.add(new
   * MenuItem("????????????", ACCESS_LOGOUT, "/auth/signUp")); mainMenuGroup.add(new MenuItem("????????????",
   * ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logout")); mainMenuGroup.add(new MenuItem("?????? ??????",
   * ACCESS_GENERAL, "/auth/membershipwithdrawal"));
   * 
   * mainMenuGroup.add(createMyPageMenu()); mainMenuGroup.add(createAdminMenu());
   * mainMenuGroup.add(createFreeStudyMenu()); mainMenuGroup.add(createChargeStudyMenu());
   * mainMenuGroup.add(createCommunityMenu()); mainMenuGroup.add(createCalendarMenu());
   * 
   * return mainMenuGroup; }
   * 
   * private Menu createMyPageMenu() { MenuGroup myPageMenu = new MenuGroup("?????? ?????????",
   * ACCESS_GENERAL);
   * 
   * myPageMenu.add(new MenuItem("?????????", ACCESS_GENERAL, "/auth/userinfo"));
   * 
   * myPageMenu.add(createInterestMenu()); myPageMenu.add(createFreeStudyApplyMenu());
   * 
   * return myPageMenu; }
   * 
   * private Menu createAdminMenu() { MenuGroup adminMenu = new MenuGroup("????????? ?????????", ACCESS_ADMIN);
   * 
   * adminMenu.add(createMemberMenu()); adminMenu.add(createMentorApplicantMenu());
   * adminMenu.add(createDeleteRequestStudyMenu());
   * 
   * return adminMenu; }
   * 
   * private Menu createMemberMenu() { MenuGroup memberMenu = new MenuGroup("?????? ??????");
   * 
   * memberMenu.add(createMentorApplicantMenu());
   * 
   * return memberMenu; }
   * 
   * private Menu createMentorApplicantMenu() { MenuGroup mentorApplicantMenu = new
   * MenuGroup("?????? ?????? ??????");
   * 
   * mentorApplicantMenu.add(new MenuItem("??????", "/mentorApplicant/list"));
   * mentorApplicantMenu.add(new MenuItem("????????????", "/mentorApplicant/detail"));
   * 
   * return mentorApplicantMenu; }
   * 
   * private Menu createDeleteRequestStudyMenu() { MenuGroup deletedRequestMenu = new
   * MenuGroup("?????? ?????? ????????? ??????");
   * 
   * deletedRequestMenu.add(new MenuItem("??????", "/chargeStudy/deleteList"));
   * 
   * return deletedRequestMenu; }
   * 
   * private Menu createInterestMenu() { MenuGroup interestMenu = new MenuGroup("????????????",
   * ACCESS_GENERAL);
   * 
   * interestMenu.add(createFreeInterestMenu()); interestMenu.add(createChargeInterestMenu());
   * 
   * return interestMenu; }
   * 
   * private Menu createFreeInterestMenu() { MenuGroup freeInterestMenu = new
   * MenuGroup("?????? ????????? ????????????");
   * 
   * freeInterestMenu.add(new MenuItem("??????", "/freeInterest/list")); freeInterestMenu.add(new
   * MenuItem("??????", "/freeInterest/delete"));
   * 
   * return freeInterestMenu; }
   * 
   * private Menu createChargeInterestMenu() { MenuGroup chargeInterestMenu = new
   * MenuGroup("?????? ????????? ????????????");
   * 
   * chargeInterestMenu.add(new MenuItem("??????", "/chargeInterest/list")); chargeInterestMenu.add(new
   * MenuItem("??????", "/chargeInterest/delete"));
   * 
   * return chargeInterestMenu; }
   * 
   * private Menu createFreeStudyApplyMenu() { MenuGroup freeStudyApplyMenu = new
   * MenuGroup("?????? ????????? ?????? ??????", ACCESS_GENERAL);
   * 
   * freeStudyApplyMenu.add(new MenuItem("??????", "/freeStudyApply/list")); freeStudyApplyMenu.add(new
   * MenuItem("????????????", "/freeStudyApply/detail")); freeStudyApplyMenu.add(new MenuItem("??????",
   * "/freeStudyApply/update")); freeStudyApplyMenu.add(new MenuItem("??????",
   * "/freeStudyApply/delete"));
   * 
   * return freeStudyApplyMenu; }
   * 
   * private Menu createFreeStudyMenu() { MenuGroup freeStudyMenu = new MenuGroup("?????? ?????????");
   * 
   * freeStudyMenu.add(new MenuItem("??????", "/freeStudy/search")); freeStudyMenu.add(new
   * MenuItem("??????", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add")); freeStudyMenu.add(new
   * MenuItem("??????", "/freeStudy/list")); freeStudyMenu.add(new MenuItem("????????????",
   * "/freeStudy/detail")); freeStudyMenu.add(new MenuItem("??????", ACCESS_LEADER,
   * "/freeStudy/update")); freeStudyMenu.add(new MenuItem("??????", ACCESS_LEADER | ACCESS_ADMIN,
   * "/freeStudy/delete"));
   * 
   * return freeStudyMenu; }
   * 
   * private Menu createChargeStudyMenu() { MenuGroup chargeStudyMenu = new MenuGroup("?????? ?????????");
   * 
   * chargeStudyMenu.add(new MenuItem("??????", "/chargeStudy/search")); chargeStudyMenu.add(new
   * MenuItem("?????? ??????", ACCESS_GENERAL, "/mentorApplicant/add")); chargeStudyMenu.add(new
   * MenuItem("??????", ACCESS_MENTOR, "/chargeStudy/add")); chargeStudyMenu.add(new MenuItem("??????",
   * "/chargeStudy/list")); chargeStudyMenu.add(new MenuItem("????????????", "/chargeStudy/detail"));
   * chargeStudyMenu.add(new MenuItem("??????", ACCESS_MENTOR, "/chargeStudy/update"));
   * chargeStudyMenu.add(new MenuItem("?????? ??????", ACCESS_MENTOR, "/chargeStudy/deleteRequest"));
   * 
   * return chargeStudyMenu; }
   * 
   * private Menu createCommunityMenu() { MenuGroup communityMenu = new MenuGroup("????????????");
   * 
   * communityMenu.add(createCommunityInfoMenu()); communityMenu.add(createCommunityQaMenu());
   * communityMenu.add(createCommunityTalkMenu());
   * 
   * return communityMenu; }
   * 
   * private Menu createCommunityInfoMenu() { MenuGroup communityInfoMenu = new MenuGroup("??????");
   * 
   * communityInfoMenu.add(new MenuItem("??????", "/communityInfo/search")); communityInfoMenu.add(new
   * MenuItem("??????", ACCESS_GENERAL, "/communityInfo/add")); communityInfoMenu.add(new MenuItem("??????",
   * "/communityInfo/list")); communityInfoMenu.add(new MenuItem("????????????", "/communityInfo/detail"));
   * communityInfoMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityInfo/update"));
   * communityInfoMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_ADMIN,
   * "/communityInfo/delete"));
   * 
   * return communityInfoMenu; }
   * 
   * private Menu createCommunityQaMenu() { MenuGroup communityQaMenu = new MenuGroup("??????");
   * 
   * communityQaMenu.add(new MenuItem("??????", "/communityQa/search")); communityQaMenu.add(new
   * MenuItem("??????", ACCESS_GENERAL, "/communityQa/add")); communityQaMenu.add(new MenuItem("??????",
   * "/communityQa/list")); communityQaMenu.add(new MenuItem("????????????", "/communityQa/detail"));
   * communityQaMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityQa/update"));
   * communityQaMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_ADMIN, "/communityQa/delete"));
   * 
   * return communityQaMenu; }
   * 
   * private Menu createCommunityTalkMenu() { MenuGroup communityTalkMenu = new MenuGroup("?????????");
   * 
   * communityTalkMenu.add(new MenuItem("??????", "/communityTalk/search")); communityTalkMenu.add(new
   * MenuItem("??????", ACCESS_GENERAL, "/communityTalk/add")); communityTalkMenu.add(new MenuItem("??????",
   * "/communityTalk/list")); communityTalkMenu.add(new MenuItem("????????????", "/communityTalk/detail"));
   * communityTalkMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/communityTalk/update"));
   * communityTalkMenu.add(new MenuItem("??????", ACCESS_GENERAL | ACCESS_ADMIN,
   * "/communityTalk/delete"));
   * 
   * return communityTalkMenu; }
   * 
   * private Menu createCalendarMenu() { MenuGroup calendarMenu = new MenuGroup("?????????");
   * 
   * calendarMenu.add(createJobsCalendarMenu()); calendarMenu.add(createExamCalendarMenu());
   * 
   * return calendarMenu; }
   * 
   * private Menu createJobsCalendarMenu() { MenuGroup jobsCalendarMenu = new MenuGroup("????????? ????????????");
   * 
   * jobsCalendarMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/jobsCalendar/add"));
   * jobsCalendarMenu.add(new MenuItem("??????", "/jobsCalendar/list")); jobsCalendarMenu.add(new
   * MenuItem("????????????", "/jobsCalendar/detail")); jobsCalendarMenu.add(new MenuItem("??????",
   * ACCESS_ADMIN, "/jobsCalendar/update")); jobsCalendarMenu.add(new MenuItem("??????", ACCESS_ADMIN,
   * "/jobsCalendar/delete"));
   * 
   * return jobsCalendarMenu; }
   * 
   * private Menu createExamCalendarMenu() { MenuGroup examCalendarMenu = new MenuGroup("????????? ????????????");
   * 
   * examCalendarMenu.add(new MenuItem("??????", ACCESS_ADMIN, "/examCalendar/add"));
   * examCalendarMenu.add(new MenuItem("??????", "/examCalendar/list")); examCalendarMenu.add(new
   * MenuItem("????????????", "/examCalendar/detail")); examCalendarMenu.add(new MenuItem("??????",
   * ACCESS_ADMIN, "/examCalendar/update")); examCalendarMenu.add(new MenuItem("??????", ACCESS_ADMIN,
   * "/examCalendar/delete"));
   * 
   * return examCalendarMenu; } }
   */
}
