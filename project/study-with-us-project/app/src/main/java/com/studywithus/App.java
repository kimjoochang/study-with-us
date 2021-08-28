package main.java.com.studywithus;

import main.java.com.studywithus.handler.ChargeStudyHandler;
import main.java.com.studywithus.handler.CommunityHandler;
import main.java.com.studywithus.handler.FreeStudyHandler;
import main.java.com.studywithus.handler.NewMemberHandler;
import main.java.com.studywithus.util.Prompt;

public class App {
  public static void main(String[] args) {
    FreeStudyHandler freeStudyHandler = new FreeStudyHandler();
    CommunityHandler communityHandler = new CommunityHandler();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    ChargeStudyHandler chargeStudyHandler = new ChargeStudyHandler();

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
        System.out.println("종료되었습니다.");
        return;
      }

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
                    }
                  }
                }
              }
            }
            break Main;

          case 3 :
            newMemberHandler.add();
            continue Main;

          case 4 : System.out.println("종료되었습니다.");
          break Main;

          default : System.out.println("잘못된 번호입니다");
          continue;
        }
      }
    }
  }
}