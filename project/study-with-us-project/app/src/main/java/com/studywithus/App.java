package com.studywithus;

import java.util.ArrayList;
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
import com.studywithus.handler.ChargeStudyUpdateHandler;
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

  AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
  AuthLogoutHandler authLogoutHandler = new AuthLogoutHandler();
  SignUpHandler signUpHandler = new SignUpHandler(memberList);

  MentorApplicantAddHandler mentorApplicantAddHandler = new MentorApplicantAddHandler(mentorApplicantList);
  MentorApplicantListHandler mentorApplicantListHandler = new MentorApplicantListHandler(mentorApplicantList);
  MentorApplicantDetailHandler mentorApplicantDetailHandler = new MentorApplicantDetailHandler(mentorApplicantList);
  MentorApproveHandler mentorApproveHandler = new MentorApproveHandler(mentorList);
  MentorRejectHandler mentorRejectHandler = new MentorRejectHandler(mentorApplicantList);


  FreeInterestAddHandler freeInterestAddHandler = new FreeInterestAddHandler(freeInterestList);
  FreeInterestListHandler freeInterestListHandler = new FreeInterestListHandler(freeInterestList);
  FreeInterestDeleteHandler freeInterestDeleteHandler = new FreeInterestDeleteHandler(freeInterestList);

  ChargeInterestAddHandler chargeInterestAddHandler = new ChargeInterestAddHandler(chargeInterestList);
  ChargeInterestListHandler chargeInterestListHandler = new ChargeInterestListHandler(chargeInterestList);
  ChargeInterestDeleteHandler chargeInterestDeleteHandler = new ChargeInterestDeleteHandler(chargeInterestList);

  FreeStudyAddHandler freeStudyAddHandler = new FreeStudyAddHandler(freeStudyList);
  FreeStudyListHandler freeStudyListHandler = new FreeStudyListHandler(freeStudyList);
  FreeStudyDetailHandler freeStudyDetailHandler = new FreeStudyDetailHandler(freeStudyList);
  FreeStudyUpdateHandler freeStudyUpdateHandler = new FreeStudyUpdateHandler(freeStudyList);
  FreeStudyDeleteHandler freeStudyDeleteHandler = new FreeStudyDeleteHandler(freeStudyList);
  FreeStudyApplyHandler freeStudyApplyHandler = new FreeStudyApplyHandler(freeStudyList);

  ChargeStudyAddHandler chargeStudyAddHandler = new ChargeStudyAddHandler(chargeStudyList);
  ChargeStudyListHandler chargeStudyListHandler = new ChargeStudyListHandler(chargeStudyList);
  ChargeStudyUpdateHandler chargeStudyUpdateHandler = new ChargeStudyUpdateHandler(chargeStudyList);
  ChargeStudyDetailHandler chargeStudyDetailHandler = new ChargeStudyDetailHandler(chargeStudyList);
  ChargeStudyDeleteHandler chargeStudyDeleteHandler = new ChargeStudyDeleteHandler(chargeStudyList);

  CommunityAddHandler communityInfoAddHandler = new CommunityAddHandler(communityInfoList);
  CommunityAddHandler communityQaAddHandler = new CommunityAddHandler(communityQaList);
  CommunityAddHandler communityTalkAddHandler = new CommunityAddHandler(communityTalkList);

  CommunityListHandler communityInfoListHandler = new CommunityListHandler(communityInfoList);
  CommunityListHandler communityQaListHandler = new CommunityListHandler(communityQaList);
  CommunityListHandler communityTalkListHandler = new CommunityListHandler(communityTalkList);

  CommunityDetailHandler communityInfoDetailHandler = new CommunityDetailHandler(communityInfoList);
  CommunityDetailHandler communityQaDetailHandler = new CommunityDetailHandler(communityQaList);
  CommunityDetailHandler communityTalkDetailHandler = new CommunityDetailHandler(communityTalkList);

  CommunityUpdateHandler communityInfoUpdateHandler = new CommunityUpdateHandler(communityInfoList);
  CommunityUpdateHandler communityQaUpdateHandler = new CommunityUpdateHandler(communityQaList);
  CommunityUpdateHandler communityTalkUpdateHandler = new CommunityUpdateHandler(communityTalkList);

  CommunityDeleteHandler communityInfoDeleteHandler = new CommunityDeleteHandler(communityInfoList);
  CommunityDeleteHandler communityQaDeleteHandler = new CommunityDeleteHandler(communityQaList);
  CommunityDeleteHandler communityTalkDeleteHandler = new CommunityDeleteHandler(communityTalkList);

  CommunitySearchHandler communityInfoSearchHandler = new CommunitySearchHandler(communityInfoList);
  CommunitySearchHandler communityQaSearchHandler = new CommunitySearchHandler(communityQaList);
  CommunitySearchHandler communityTalkSearchHandler = new CommunitySearchHandler(communityTalkList);

  JobsCalenderAddHandler jobsCalenderAddHandler = new JobsCalenderAddHandler(jobsCalenderList);
  JobsCalenderDetailHandler jobsCalenderDetailHandler = new JobsCalenderDetailHandler(jobsCalenderList);
  JobsCalenderUpdateHandler jobsCalenderUpdateHandler = new JobsCalenderUpdateHandler(jobsCalenderList);
  JobsCalenderDeleteHandler jobsCalenderDeleteHandler = new JobsCalenderDeleteHandler(jobsCalenderList);

  ExamCalenderAddHandler examCalenderAddHandler = new ExamCalenderAddHandler(examCalenderList);
  ExamCalenderDetailHandler examCalenderDetailHandler = new ExamCalenderDetailHandler(examCalenderList);
  ExamCalenderUpdateHandler examCalenderUpdateHandler = new ExamCalenderUpdateHandler(examCalenderList);
  ExamCalenderDeleteHandler examCalenderDeleteHandler = new ExamCalenderDeleteHandler(examCalenderList);

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new Menu("로그인", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authLoginHandler.execute(); 
      }
    });

    mainMenuGroup.add(new Menu("회원가입") {
      @Override
      public void execute() {
        signUpHandler.execute(); 
      }
    });

    mainMenuGroup.add(new Menu("로그아웃", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        authLogoutHandler.execute(); 
      }
    });

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
        int input = Prompt.inputInt("선택>");
        if(input == 1) {
          mentorApproveHandler.execute1(applicant);
        }else if(input == 2) {
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

        if(input == 1) {
          return;
        }else if(input == 2) {
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
    mainMenuGroup.add(communityQaMenu);

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
    MenuGroup communityTalkMenu = new MenuGroup("질문");
    mainMenuGroup.add(communityTalkMenu);

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

    return mainMenuGroup;
  }
}
