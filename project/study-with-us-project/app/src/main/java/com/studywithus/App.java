package main.java.com.studywithus;

<<<<<<< HEAD
import main.java.com.studywithus.handler.ChargeStudyHandler;
import main.java.com.studywithus.handler.CommunityHandler;
import main.java.com.studywithus.handler.FreeStudyHandler;
import main.java.com.studywithus.handler.NewMemberHandler;
import main.java.com.studywithus.util.Prompt;
=======
import com.studywithus.domain.InterestList;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.ChargeStudyHandler;
import com.studywithus.handler.CommunityHandler;
import com.studywithus.handler.FreeStudyHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.util.Prompt;
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3

public class App {

  public static void main(String[] args) {

    InterestList[] interests = new InterestList[100];
    FreeStudyHandler freeStudyHandler = new FreeStudyHandler();
    CommunityHandler communityHandler = new CommunityHandler();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    ChargeStudyHandler chargeStudyHandler = new ChargeStudyHandler();
<<<<<<< HEAD

    Main : while(true) {
      System.out.println();
      System.out.println("[STUDY WITH US]\n");
      System.out.println("1. 비회원으로 시작하기");
      System.out.println("2. 로그인");
      System.out.println("3. 회원가입");
      System.out.println("0. 종료하기\n");
      int input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if(input==0) {
=======
    Main : while(true) {
      int input = mainMenuList();
      if (input==0) {
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
        System.out.println("종료되었습니다.");
        return;
      }

<<<<<<< HEAD
      NoneMember : while(true) {
        switch(input) {
          case 1 :
            System.out.println();
            System.out.println("[비회원으로 시작]\n");
            System.out.println("1. 무료스터디 조회");
            System.out.println("2. 유료스터디 조회");
            System.out.println("3. 커뮤니티 조회");
            System.out.println("4. 캘린더 조회");
            System.out.println("0. 이전\n");
            int input2 = Prompt.inputInt("메뉴를 선택해주세요. > ");
            System.out.println();

            switch(input2) {
              case 1 : freeStudyHandler.list(); continue NoneMember;
              case 2 : chargeStudyHandler.list(); continue ;
              case 3 : communityHandler.list(); continue ;
              case 0 : continue Main;
              default : System.out.println("잘못된 번호입니다.");
              continue NoneMember;
            }

          case 2 :
            Login : for(int i=0; i < newMemberHandler.newMembers.length;) {
              String id = Prompt.inputString("아이디 입력 :");
              String pwd = Prompt.inputString("비밀번호 입력 :");

              if(!id.equals(newMemberHandler.newMembers[i].id) || !pwd.equals(newMemberHandler.newMembers[i].pwd)) {
                System.out.println("아이디 또는 비밀번호가 다릅니다.\n");
                continue Login;
              }

              else{
                System.out.println();
                System.out.println("로그인 성공!!\n");

                Member : while(true) {
                  System.out.println("[회원 메뉴]\n");
                  System.out.println("1. 무료스터디");
                  System.out.println("2. 유료스터디");
                  System.out.println("3. 관심목록");
                  System.out.println("4. 커뮤니티");
                  System.out.println("0. 로그아웃\n");
                  input = Prompt.inputInt("메뉴를 선택해주세요. > ");
                  System.out.println();

                  if(input==5) {
                    System.out.println("로그아웃 완료!");
                    continue Main;
                  }

                  Free : while(true) {
                    switch(input) {
                      case 1 :
                        System.out.println("[무료 스터디]\n");
                        System.out.println("1. 생성");
                        System.out.println("2. 조회");
                        System.out.println("3. 상세보기");
                        System.out.println("4. 변경");
                        System.out.println("5. 삭제");
                        System.out.println("0. 이전\n");
                        int input3 = Prompt.inputInt("메뉴를 선택해주세요. > ");

                        switch(input3) {
                          case 1 : freeStudyHandler.add(); continue Free;
                          case 2 : freeStudyHandler.list(); continue Free;
                          case 3 : freeStudyHandler.detail(); continue Free;
                          case 4 : freeStudyHandler.update(); continue Free;
                          case 5 : freeStudyHandler.delete(); continue Free;
                          case 0 : continue Member;
                          default : System.out.println("잘못된 번호입니다");
                          continue;
                        }

                      case 2 :
                        Charge : while(true) {
                          System.out.println("[유료 스터디]\n");
                          System.out.println("1. 생성");
                          System.out.println("2. 조회");
                          System.out.println("3. 상세보기");
                          System.out.println("4. 변경");
                          System.out.println("5. 삭제");
                          System.out.println("0. 이전\n");
                          int input4 = Prompt.inputInt("메뉴를 선택해주세요. > ");
                          System.out.println();

                          switch(input4) {
                            case 1 : chargeStudyHandler.add(); continue Charge;
                            case 2 : chargeStudyHandler.list(); continue Charge;
                            case 3 : chargeStudyHandler.detail(); continue Charge;
                            case 4 : chargeStudyHandler.update(); continue Charge;
                            case 5 : chargeStudyHandler.delete(); continue Charge;
                            case 0 : continue Member;
                            default : System.out.println("잘못된 번호입니다");
                            continue;
                          }
                        }

                      case 3 :
                        System.out.println("[관심목록]\n");
                        System.out.println("1. 조회");
                        System.out.println("2. 삭제");
                        System.out.println("아직 구현 못함ㅎㅎ");

                      case 4 :
                        Community : while(true) {
                          System.out.println("[커뮤니티]\n");
                          System.out.println("1. 생성");
                          System.out.println("2. 조회");
                          System.out.println("3. 상세보기");
                          System.out.println("4. 변경");
                          System.out.println("5. 삭제");
                          System.out.println("0. 이전");
                          int input5 = Prompt.inputInt("메뉴를 선택해주세요. > ");
                          System.out.println();

                          switch(input5) {
                            case 1 : communityHandler.add(); continue Community;
                            case 2 : communityHandler.list(); continue Community;
                            case 3 : communityHandler.detail(); continue Community;
                            case 4 : communityHandler.update(); continue Community;
                            case 5 : communityHandler.delete(); continue Community;
                            case 0 : continue Member;
                            default : System.out.println("잘못된 번호입니다");
                            continue;
                          }
                        }

                      case 0 : continue Main;
                      default : System.out.println("잘못된 번호입니다");
                      break;
=======
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
        while(true) {
          for (int i=0; i < newMemberHandler.newMembers.length;) {
            String id = Prompt.inputString("아이디 입력: ");
            String pwd = Prompt.inputString("비밀번호 입력: ");
            Login : while(true) {
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

                  input = Prompt.inputInt("게시판을 선택해주세요. >");
                  if(input == 1) {
                    while(true) {
                      System.out.println();
                      System.out.println("[커뮤니티 / 질문]\n");
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
                        continue Community;
                      }else {
                        System.out.println("잘못된 번호입니다.");
                        continue;
                      }
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
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
<<<<<<< HEAD
            break Main;

          case 3 :
            newMemberHandler.add();
            continue Main;

          case 4 : System.out.println("종료되었습니다.");
          break Main;

          default : System.out.println("잘못된 번호입니다");
          continue;
=======
          }
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
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