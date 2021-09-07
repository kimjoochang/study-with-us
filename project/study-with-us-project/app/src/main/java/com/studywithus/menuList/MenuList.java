package com.studywithus.menuList;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.ExamCalender;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.Interest;
import com.studywithus.domain.JobsCalender;
import com.studywithus.domain.LoginMemberInfo;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.ChargeStudyDetailHandler;
import com.studywithus.handler.ChargeStudyListHandler;
import com.studywithus.handler.ChargeStudyUpdateHandler;
import com.studywithus.handler.ExamCalendarAddHandler;
import com.studywithus.handler.ExamCalendarDeleteHandler;
import com.studywithus.handler.ExamCalendarDetailHandler;
import com.studywithus.handler.ExamCalendarUpdateHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyApplyHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.handler.InterestHandler;
import com.studywithus.handler.JobsCalendarAddHandler;
import com.studywithus.handler.JobsCalendarDeleteHandler;
import com.studywithus.handler.JobsCalendarDetailHandler;
import com.studywithus.handler.JobsCalendarUpdateHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.util.Prompt;

public class MenuList {

  Interest[] interests = new Interest[100];

  private List<FreeStudy> freeStudyList = new ArrayList<>();
  private List<ChargeStudy> chargeStudyList = new ArrayList<>();
  private List<ExamCalender> examCalenderList = new ArrayList<>();
  private List<JobsCalender> jobsCalenderList = new ArrayList<>();

  FreeStudyApplyHandler freeStudyApplyHandler = new FreeStudyApplyHandler(freeStudyList);
  FreeStudyAddHandler freeStudyAddHandler = new FreeStudyAddHandler(freeStudyList);
  FreeStudyListHandler freeStudyListHandler = new FreeStudyListHandler(freeStudyList);
  FreeStudyDetailHandler freeStudyDetailHandler = new FreeStudyDetailHandler(freeStudyList);
  FreeStudyUpdateHandler freeStudyUpdateHandler = new FreeStudyUpdateHandler(freeStudyList);
  FreeStudyDeleteHandler freeStudyDeleteHandler = new FreeStudyDeleteHandler(freeStudyList);

  ChargeStudyAddHandler chargeStudyAddHandler = new ChargeStudyAddHandler(chargeStudyList);
  ChargeStudyListHandler chargeStudyListHandler = new ChargeStudyListHandler(chargeStudyList);
  ChargeStudyDetailHandler chargeStudyDetailHandler = new ChargeStudyDetailHandler(chargeStudyList);
  ChargeStudyUpdateHandler chargeStudyUpdateHandler = new ChargeStudyUpdateHandler(chargeStudyList);
  ChargeStudyDeleteRequestHandler chargeStudyDeleteHandler = new ChargeStudyDeleteRequestHandler(chargeStudyList);

  CommunityMenuList communityMenuList = new CommunityMenuList();

  ExamCalendarAddHandler examCalenderAddHandler = new ExamCalendarAddHandler(examCalenderList);
  ExamCalendarDetailHandler examCalenderDetailHandler = new ExamCalendarDetailHandler(examCalenderList);
  ExamCalendarUpdateHandler examCalenderUpdateHandler = new ExamCalendarUpdateHandler(examCalenderList);
  ExamCalendarDeleteHandler examCalenderDeleteHandler = new ExamCalendarDeleteHandler(examCalenderList);

  JobsCalendarAddHandler jobsCalenderAddHandler = new JobsCalendarAddHandler(jobsCalenderList);
  JobsCalendarDetailHandler jobsCalenderDetailHandler = new JobsCalendarDetailHandler(jobsCalenderList);
  JobsCalendarUpdateHandler jobsCalenderUpdateHandler = new JobsCalendarUpdateHandler(jobsCalenderList);
  JobsCalendarDeleteHandler jobsCalendarDeleteHandler = new JobsCalendarDeleteHandler(jobsCalenderList);

  InterestHandler interestHandler = new InterestHandler();
  NewMemberHandler newMemberHandler = new NewMemberHandler();


  int input;

  // 메인 메뉴 조회
  public int mainMenuList() {
    System.out.println();
    System.out.println("[STUDY WITH US]\n");
    System.out.println("1. 비회원으로 시작하기");
    System.out.println("2. 로그인");
    System.out.println("3. 회원가입");
    System.out.println("4. 아이디 / 비밀번호 찾기"); // 추가한 부분
    System.out.println("0. 종료하기\n");

    input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();

    return input;
  }

  // 비회원 메뉴 조회
  public void noneMemberMenuList() {
    while(true) {
      System.out.println();
      System.out.println("[비회원으로 시작]\n");
      System.out.println("1. 무료스터디 조회");
      System.out.println("2. 유료스터디 조회");
      System.out.println("3. 커뮤니티 조회");
      System.out.println("4. 캘린더 조회");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        freeStudyListHandler.execute();

      } else if (input == 2) {
        chargeStudyListHandler.execute();

      } else if (input == 3) {
        communityMenuList.communityMainMenuList();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue; // 다시 반복
      }
      continue; // 완료 후 돌아가기
    }
  }

  // 회원 메뉴
  public int memberMenuList() {
    System.out.println("[회원]\n");
    System.out.println("1. 무료 스터디");
    System.out.println("2. 유료 스터디");
    System.out.println("3. 관심목록");
    System.out.println("4. 커뮤니티");
    System.out.println("5. 캘린더");
    System.out.println("6. 멘토 신청하기");
    System.out.println("0. 로그아웃\n");

    input = Prompt.inputInt("메뉴를 선택해주세요. > ");

    System.out.println();
    return input;
  }

  // 관리자 메뉴 조회
  public void adminMenuList() {
    System.out.println("관리자로 로그인 되었습니다.\n");

    while (true) {
      System.out.println("[관리자]\n");
      System.out.println("1. 회원 관리");
      System.out.println("2. 캘린더 관리");
      System.out.println("0. 로그아웃\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");

      System.out.println();

      // 1. 회원 관리
      if (input == 1) {
        System.out.println("[관리자 / 회원 관리]");
        System.out.println("1. 멘토 승인 관리");
        System.out.println("2. 블랙 리스트 관리");
        System.out.println("0. 이전");

        input = Prompt.inputInt("메뉴를 선택해주세요. > ");
        System.out.println();

        // 1. 멘토 승인 관리
        if (input == 1) {
          System.out.println("[관리자 / 회원 관리 / 멘토 승인 관리]");

          // [추가] 메서드 호출
          System.out.println("1. 멘토 승인");
          System.out.println("2. 멘토 거절");
          System.out.println("0. 이전");

          // 2. 블랙 리스트 관리
        } else if (input == 2) {
          System.out.println("[관리자 / 회원 관리 / 블랙 리스트 관리]");

          // [추가] 메서드 호출
          input = Prompt.inputInt("회원 번호를 선택하세요. > ");
          System.out.println();

          // 0. 이전
        } else if (input == 0) {
          continue;
        }

        // 2. 캘린더 관리
      } else if (input == 2) {
        System.out.println("[관리자 / 캘린더 관리]\n");
        System.out.println("1. 이달의 채용 공고 관리");
        System.out.println("2. 이달의 시험 공고 관리");
        System.out.println("0. 이전");

        input = Prompt.inputInt("메뉴를 선택해주세요. > ");
        System.out.println();

        // 1. 이달의 채용 공고 관리
        if (input == 1) {
          examCalenderMenuList();

          // 2. 이달의 시험 공고 관리
        } else if (input == 2) {
          jobsCalenderMenuList();

          // 0. 이전
        } else if (input == 0) {
          continue;
        }

        // 0. 로그아웃
      } else if (input == 0) {
        System.out.println("로그아웃이 완료되었습니다.\n");
        return;

        // 그 외 번호 입력
      } else {
        System.out.println("잘못된 번호입니다.\n");
        continue;
      }
      continue;
    }
  }

  // 로그인
  public int loginExecute(NewMember[] loginInfo) {

    String id = Prompt.inputString("아이디 입력: ");
    String pwd = Prompt.inputString("비밀번호 입력: ");

    for (int i = 0; i < loginInfo.length; i++) {
      if (id.equals(NewMember.getAdminId()) && pwd.equals(NewMember.getAdminPwd())) {// 아이디 매칭
        return 2;

      } else if (! id.equals (loginInfo[i].getId()) || ! pwd.equals (loginInfo[i].getPwd())) {

      } else {
        LoginMemberInfo.name = loginInfo[i].getName(); 
        LoginMemberInfo.id = loginInfo[i].getId();

        System.out.println();
        System.out.println("로그인이 완료되었습니다.\n");

        return 1;
      }
      continue;
    }
    System.out.println("아이디 또는 비밀번호가 다릅니다.\n");
    return 0;
  }

  // 김제이 ing

  public void findMemberInfoList() {
    NewMemberHandler newMemberHandler = new NewMemberHandler();

    while(true) {
      System.out.println("[회원 정보 찾기]\n");
      System.out.println("1. 아이디 찾기");
      System.out.println("2. 비밀번호 찾기"); // 비밀번호 변경?
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        newMemberHandler.findMemberId();

      } else if (input == 2) {
        newMemberHandler.updateMemberPwd();

      } else if (input == 0) {
        this.mainMenuList();

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue; 
    }
  }
}

// [수정] 어디에 채워야 할지 모름
// if (input == 1) {
//   noneMemberMenuList();
//
// } else if (input == 2) {
//   // [추가] 로그인
//
// } else if (input == 3) {
//   adminMenuList();
//
// } else if (input == 4) {
//   // [추가] 회원가입
//
// } else if (input == 0) {
//   return;
// }
