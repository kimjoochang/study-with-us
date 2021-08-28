package com.studywithus;

import com.studywithus.domain.NewMember;
import com.studywithus.handler.ChargeStudyHandler;
import com.studywithus.handler.CommunityHandler;
import com.studywithus.handler.FreeStudyHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.util.Prompt;

public class App1 {

  public static void main(String[] args) {

    FreeStudyHandler freeStudyHandler = new FreeStudyHandler();
    CommunityHandler communityHandler = new CommunityHandler();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    ChargeStudyHandler chargeStudyHandler = new ChargeStudyHandler();
    Main : while(true) {
      int input = mainMenuList();
      if (input==0) {
        System.out.println("종료되었습니다.");
        return;
      }

      else if (input == 1) { // 회원가입
        input = noneMemberMenuList();
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
        Login : while(true) {
          for (int i=0; i < newMemberHandler.newMembers.length;) {
            String id = Prompt.inputString("아이디 입력: ");
            String pwd = Prompt.inputString("비밀번호 입력: ");

            if (id.equals(NewMember.adminId)&&pwd.equals(NewMember.adminPwd)) { // 아이디 매칭
              input = adminMenuList();

              if (input ==0) { // 메서드 추출할 예정
                System.out.println("로그아웃이 완료되었습니다.");
                continue Main;
              } else if (input > 4) {
                System.out.println("잘못된 번호입니다.");
              }

            } else if (!id.equals(newMemberHandler.newMembers[i].id) || !pwd.equals(newMemberHandler.newMembers[i].pwd)) {
              System.out.println("아이디 또는 비밀번호가 다릅니다.\n");
              continue Login;
            } else {
              System.out.println();
              System.out.println("로그인이 완료되었습니다.\n");

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

                    // 무료 스터디 신청
                    System.out.println("1. 신청하기");
                    System.out.println("0. 이전");

                    if (input == 1) {
                      String input1 = Prompt.inputString("정말 신청하시겠습니까? (y/N) ");
                      if (input1.equalsIgnoreCase("n") || input1.equals("")) {
                        System.out.println("무료 스터디 신청을 취소하였습니다.");
                        return;
                      }
                      else if (input1.equalsIgnoreCase("y")) {
                        System.out.println("무료 스터디 신청이 완료되었습니다.");
                      }
                    } else if (input == 0) {
                      continue Free;
                    }


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
                Interest : while (true) {
                  System.out.println("[관심목록]\n");
                  System.out.println("1. 조회");
                  System.out.println("2. 삭제");
                  System.out.println("3. 이전");
                }
              } else if (input == 4) { // 커뮤니티 메뉴
                Community : while (true) {

                  input = communityMenuList();
                  if (input == 1) {
                    communityHandler.add();
                  } else if (input == 2) {
                    communityHandler.list();
                  } else if (input == 3) {
                    communityHandler.detail();
                  } else if (input == 4) {
                    communityHandler.update();
                  } else if (input == 5) {
                    communityHandler.delete();
                  } else if (input == 0) {
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
    System.out.println("[커뮤니티]\n");
    System.out.println("1. 생성");
    System.out.println("2. 조회");
    System.out.println("3. 상세보기");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전");
    int input = Prompt.inputInt("메뉴를 선택해주세요. > ");
    System.out.println();
    return input;
  }
}