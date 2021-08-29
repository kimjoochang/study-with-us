package com.studywithus;

import com.studywithus.domain.InterestList;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.ChargeStudyHandler;
import com.studywithus.handler.CommunityHandler;
import com.studywithus.handler.CommunityInfoHandler;
import com.studywithus.handler.CommunityQaHandler;
import com.studywithus.handler.CommunityTalkHandler;
import com.studywithus.handler.FreeStudyHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.util.Prompt;

public class AppJ6 {

  public static void main(String[] args) {

    InterestList[] interests = new InterestList[100];
    FreeStudyHandler freeStudyHandler = new FreeStudyHandler();
    CommunityHandler communityHandler = new CommunityHandler();
    CommunityQaHandler communityQaHandler = new CommunityQaHandler();
    CommunityInfoHandler communityInfoHandler = new CommunityInfoHandler();
    CommunityTalkHandler communityTalkHandler = new CommunityTalkHandler();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    ChargeStudyHandler chargeStudyHandler = new ChargeStudyHandler();



    Main : while(true) {

      try{

        int input = mainMenuList();
        if (input==0) {
          System.out.println("종료되었습니다.");
          return;
        }

        else if (input == 1) { // 비회원
          input = noneMemberMenuList(); //비회원 시작 시 메뉴
          if (input == 1) {
            freeStudyHandler.list();
          } else if (input == 2) {
            chargeStudyHandler.list();
          } else if (input == 3) {
            communityHandler.list();
          } else if (input == 0) {
            continue Main;
          } else {
            System.out.println("잘못된 번호입니다.");
            return;
          }
        }

        else if (input == 2) { // 로그인
          while(true) {
            for (int i=0; i < newMemberHandler.newMembers.length;) {
              Login : while(true) { // sysout 무한재생이라 loop 여기로 옮김...맞나여...?

                String id = Prompt.inputString("아이디 입력: ");
                String pwd = Prompt.inputString("비밀번호 입력: ");
                // Login : while(true) { -< 원래 여기였쌈
                if (id.equals(NewMember.adminId)&&pwd.equals(NewMember.adminPwd)) { // 아이디 매칭
                  input = adminMenuList();

                  if (input == 0) { // 메서드 추출할 예정
                    System.out.println("로그아웃이 완료되었습니다.");
                    continue Main;
                  } else if (input > 4) {
                    System.out.println("잘못된 번호입니다.");
                  }

                } else if (!id.equals(newMemberHandler.newMembers[i].id) || !pwd.equals(newMemberHandler.newMembers[i].pwd)) {
                  System.out.println("아이디 또는 비밀번호가 다릅니다.\n");
                  continue;
                } else {
                  System.out.println();
                  System.out.println("로그인이 완료되었습니다.\n");
                }
                input = memberMenuList(); // 로그인 성공 후 메뉴

                if (input==0) {
                  System.out.println("로그아웃이 완료되었습니다.");
                  continue Main;
                } else if (input == 1) {
                  Free : while (true) {
                    input = freeStudyMenuList(); // 무료 스터디 메뉴
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
                      continue Login;
                    } else {
                      System.out.println("잘못된 번호입니다.");
                      continue Free;
                    }
                  }

                }else if (input == 2) { // 유료스터디 메뉴
                  Charge : while (true) {
                    input = chargeStudyMenu();
                    if (input == 1) {
                      chargeStudyHandler.add();
                    } else if (input == 2) {
                      chargeStudyHandler.list();
                    } else if (input == 3) {
                      chargeStudyHandler.detail();
                    } else if (input == 4) {
                      chargeStudyHandler.update();
                    } else if (input == 5) {
                      chargeStudyHandler.delete();
                    } else if (input == 0) {
                      continue Login;
                    }else {
                      System.out.println("잘못된 번호입니다.");
                      continue Charge;
                    }
                  }

                } else if (input == 3) { // 관심목록 메뉴
                  InterestList : while (true) {
                    System.out.println("[관심목록]\n");
                    System.out.println("1. 조회");
                    System.out.println("2. 삭제");
                    System.out.println("3. 이전");
                  }
                } else if (input == 4) { // 커뮤니티 메뉴
                  Community : while (true) {
                    System.out.println("[커뮤니티]");
                    System.out.println("1. 질문");
                    System.out.println("2. 정보");
                    System.out.println("3. 스몰톡");
                    System.out.println("0. 이전\n");

                    input = Prompt.inputInt("게시판을 선택해주세요. > ");
                    if(input == 1) {
                      while(true) {
                        System.out.println();
                        System.out.println("[커뮤니티 / 질문]\n");
                        input = communityMenuList();
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
                          continue Community;
                        }else {
                          System.out.println("잘못된 번호입니다.");
                          continue;
                        }
                      }
                    } else if(input == 2) {
                      while(true) {
                        System.out.println();
                        System.out.println("[커뮤니티 / 정보]\n");
                        input = communityMenuList();
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
                          continue Community;
                        }else {
                          System.out.println("잘못된 번호입니다.");
                          continue;
                        }
                      }
                    }else if(input == 3) {
                      while(true) {
                        System.out.println();
                        System.out.println("[커뮤니티 / 스몰톡]\n");
                        input = communityMenuList();
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
                          continue Community;
                        }else {
                          System.out.println("잘못된 번호입니다.");
                          continue;
                        }
                      }
                    }else if(input == 0) {
                      continue Login;
                    }else {
                      System.out.println("잘못된 번호입니다.");
                      continue Community;
                    }
                  }
                }
              }
            }
          }

        } else if (input == 3) { // 회원가입
          newMemberHandler.add();
        } else if (input == 4) {
          System.out.println("종료되었습니다.");
          return;
        } else {
          System.out.println("잘못된 번호입니다.");
        }
      }catch(Throwable e) {
        System.out.println("---------------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        System.out.println("---------------------------------------------------");
      } finally {
        System.out.println("처음 메뉴로 돌아갑니다.");
      }
    }
  }


  private static int chargeStudyMenu() {
    System.out.println("[유료 스터디]\n");
    System.out.println("1. 생성");
    System.out.println("2. 조회");
    System.out.println("3. 상세보기");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전\n");
    int input4 = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input4;
  }


  private static int freeStudyMenuList() {
    System.out.println("[무료 스터디]\n");
    System.out.println("1. 생성");
    System.out.println("2. 조회");
    System.out.println("3. 상세보기");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전\n");
    int input3 = Prompt.inputInt("메뉴를 선택해주세요. > ");
    return input3;
  }


  private static int memberMenuList() {
    int input;
    System.out.println("[회원 메뉴]\n");
    System.out.println("1. 무료스터디");
    System.out.println("2. 유료스터디");
    System.out.println("3. 관심목록");
    System.out.println("4. 커뮤니티");
    System.out.println("0. 로그아웃\n");
    input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input;
  }

  private static int adminMenuList() {
    int input;
    System.out.println("관리자로 로그인 되었습니다.\n");
    System.out.println("[관리자 메뉴]\n");
    System.out.println("1. 회원 관리");
    System.out.println("2. 캘린더 관리");
    System.out.println("0. 로그아웃\n");
    input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input;
  }

  private static int noneMemberMenuList() {
    System.out.println();
    System.out.println("[비회원으로 시작]\n");
    System.out.println("1. 무료스터디 조회");
    System.out.println("2. 유료스터디 조회");
    System.out.println("3. 커뮤니티 조회");
    System.out.println("4. 캘린더 조회");
    System.out.println("0. 이전\n");
    int input2 = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input2;
  }

  private static int mainMenuList() {
    System.out.println();
    System.out.println("[STUDY WITH US]\n");
    System.out.println("1. 비회원으로 시작하기");
    System.out.println("2. 로그인");
    System.out.println("3. 회원가입");
    System.out.println("0. 종료하기\n");
    int input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input;
  }

  private static int communityMenuList() {

    System.out.println("1. 생성");
    System.out.println("2. 조회");
    System.out.println("3. 상세보기");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전\n");
    int input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input;
  }

}