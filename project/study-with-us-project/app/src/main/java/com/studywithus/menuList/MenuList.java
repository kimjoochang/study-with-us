package com.studywithus.menuList;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.InterestList;
import com.studywithus.domain.LoginMemberInfo;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.ChargeStudyHandler;
import com.studywithus.handler.CommunityInfoHandler;
import com.studywithus.handler.CommunityMenuList;
import com.studywithus.handler.CommunityQaHandler;
import com.studywithus.handler.CommunityTalkHandler;
import com.studywithus.handler.ExamCalenderHandler;
import com.studywithus.handler.FreeStudyHandler;
import com.studywithus.handler.InterestHandler;
import com.studywithus.handler.JobsCalenderHandler;
import com.studywithus.handler.MentorApplicantHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.util.Prompt;

public class MenuList {

  InterestList[] interests = new InterestList[100];

  ChargeStudyHandler chargeStudyHandler = new ChargeStudyHandler();
  CommunityMenuList communityMenuList = new CommunityMenuList();
  CommunityInfoHandler communityInfoHandler = new CommunityInfoHandler();
  CommunityQaHandler communityQaHandler = new CommunityQaHandler();
  CommunityTalkHandler communityTalkHandler = new CommunityTalkHandler();
  ExamCalenderHandler examCalenderHandler = new ExamCalenderHandler();
  FreeStudyHandler freeStudyHandler = new FreeStudyHandler();
  InterestHandler interestHandler = new InterestHandler();
  JobsCalenderHandler jobsCalenderHandler = new JobsCalenderHandler();
  NewMemberHandler newMemberHandler = new NewMemberHandler();
  MentorApplicantHandler mentorApplicantHandler = new MentorApplicantHandler();

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
        freeStudyHandler.list();

      } else if (input == 2) {
        chargeStudyHandler.list();

      } else if (input == 3) {
        communityHandler.list();

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

        // 1-1. 멘토 승인 관리
        if (input == 1) {
          System.out.println("[관리자 / 회원 관리 / 멘토 승인 관리]");

          // [추가] 메서드 호출
          System.out.println("1. 멘토 승인");
          System.out.println("2. 멘토 거절");
          System.out.println("0. 이전");

          // 1-2. 블랙 리스트 관리
        } else if (input == 2) {
          System.out.println("[관리자 / 회원 관리 / 블랙 리스트 관리]");

          // [추가] 메서드 호출
          input = Prompt.inputInt("회원 번호를 선택하세요. > ");
          System.out.println();

          // 1-0. 이전
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

        // 2-1. 이달의 채용 공고 관리
        if (input == 1) {
          JobsCalenderMenuList();
        }

        // 2-2. 이달의 시험 공고 관리
        else if (input == 2) {
          ExamCalenderMenuList();
        }

        // 2-0. 이전
        else if (input == 0) {
          continue;
        }
        // 0. 로그아웃
      }else if (input == 0) {
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

  // 무료 스터디 메뉴 조회
  public void freeStudyMenuList() {
    while(true) {
      System.out.println("[무료 스터디]\n");
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");

      if (input == 1) {
        freeStudyHandler.add();

      } else if (input == 2) {
        freeStudyHandler.list();

      } else if (input == 3) {
        freeStudyHandler.detail();

      } else if (input == 4) {
        freeStudyHandler.update();

      } else if (input == 5) {
        freeStudyHandler.delete();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 유료 스터디 메뉴 조회
  public int chargeStudyMenuList() {

    System.out.println("[유료 스터디]\n");
    System.out.println("1. 생성");
    System.out.println("2. 조회");
    System.out.println("3. 상세보기");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전\n");

    input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();

    return input;
  }

  // 유료 스터디 상세보기
  public static int detailMenuList(int input, ChargeStudy study) {

    while(true) {
      if (input == 1) {
        String input1 = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N)");
        if (input1.equalsIgnoreCase("n") || input1.length() == 0) {
          System.out.println(" 유료 스터디 결제를 취소하셨습니다.");
          continue;
        }
        else {
          StringBuffer dot = new StringBuffer("");

          System.out.print("------------------------------------");
          System.out.println("\n"
              + "(＼(＼     \n"
              + "(  -.- )~♥\n"
              + " O_(\")(\")");
          System.out.println("------------------------------------");
          System.out.print("결제중");
          for(int i = 0; i < 3; i++) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.print(dot.append("."));
          }
          System.out.println();
          System.out.println("유료 스터디 결제가 완료 되었습니다.\n");
        }
        return 1;
      } else if (input == 2) {
        InterestHandler.chargeAdd(study);
        return 1;
      }
    }
  }

  // 관심목록 기본 메뉴 조회
  public void interestMenuList() {
    while (true) {
      System.out.println("[관심목록]\n");
      System.out.println("1. 무료 스터디 관심목록");
      System.out.println("2. 유료 스터디 관심목록");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        freeInterestMenuList();

      } else if (input == 2) {
        chargeInterestMenuList();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    } 
  }

  private boolean chargeInterestMenuList() {
    while(true) {
      System.out.println("[유료 스터디 / 관심목록]\n");
      System.out.println("1. 유료 스터디 관심목록 조회");
      System.out.println("2. 유료 스터디 관심목록 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if(input == 1) {
        interestHandler.chargeList();

      } else if(input == 2) {
        interestHandler.chargeDelete();
        // 삭제
      } else if (input == 0) {
        return false;
      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }

  private void freeInterestMenuList() {
    while (true) {
      System.out.println("[무료 스터디 / 관심목록]\n");
      System.out.println("1. 무료 스터디 관심목록 조회");
      System.out.println("2. 무료 스터디 관심목록 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        interestHandler.freeList();

      } else if (input == 2) {

        // 삭제
      } else if (input == 0) {
        interestMenuList();

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }

  // 커뮤니티 기본 메뉴 조회
  public int communityMenuList() {
    System.out.println("[커뮤니티]\n");
    System.out.println("1. 질문");
    System.out.println("2. 정보");
    System.out.println("3. 스몰톡");
    System.out.println("0. 이전\n");

    input = Prompt.inputInt("게시판을 선택해주세요. >");

    System.out.println();
    return input;
  }

  // 질문 커뮤니티 메뉴 조회
  public void communityQaMenuList() {
    while(true) {
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        communityQaHandler.add();

      } else if (input == 2) {
        communityQaHandler.list();

      } else if (input == 3) {
        communityQaHandler.detail();

      } else if (input == 4) {
        communityQaHandler.update();

      } else if (input == 5) {
        communityQaHandler.delete();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 정보 커뮤니티 메뉴 조회
  public void communityInfoMenuList() {
    while(true) {
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        communityInfoHandler.add();

      } else if (input == 2) {
        communityInfoHandler.list();

      } else if (input == 3) {
        communityInfoHandler.detail();

      } else if (input == 4) {
        communityInfoHandler.update();

      } else if (input == 5) {
        communityInfoHandler.delete();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 스몰톡 커뮤니티 메뉴 조회
  public void communityTalkMenuList() {
    while(true) {
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        communityTalkHandler.add();

      } else if (input == 2) {
        communityTalkHandler.list();

      } else if (input == 3) {
        communityTalkHandler.detail();

      } else if (input == 4) {
        communityTalkHandler.update();

      } else if (input == 5) {
        communityTalkHandler.delete();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 이달의 시험일정 메뉴
  public int ExamCalenderMenuList() {
    System.out.println("[관리자 / 캘린더 관리 / 이달의 시험일정 관리]");
    System.out.println("1. 생성");
    System.out.println("2. 상세 목록");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
    input = Prompt.inputInt("메뉴를 선택해주세요. > ");

    System.out.println();
    return input;
  }

  // 이달의 채용공고 메뉴
  public int JobsCalenderMenuList() {
    System.out.println("[관리자 / 캘린더 관리 / 이달의 채용공고 관리]");
    System.out.println("1. 생성");
    System.out.println("2. 상세 목록");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
    input = Prompt.inputInt("메뉴를 선택해주세요. > ");

    System.out.println();
    // 0. 로그아웃
    if (input == 0) {
      System.out.println("로그아웃이 완료되었습니다.");
      return 0;

      // 1. 회원 관리
    } else if (input == 1) {

    }
    // [수정] 리턴값 필요해서 억지로 채움 
    return input;
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


//[수정] 어디에 채워야 할지 모름
//if (input == 1) {
//  noneMemberMenuList();
//
//} else if (input == 2) {
//  // [추가] 로그인
//
//} else if (input == 3) {
//  adminMenuList();
//
//} else if (input == 4) {
//  // [추가] 회원가입
//
//} else if (input == 0) {
//  return;
//}
