package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class RegisterFreeStudyDetailHandler implements Command {

  HashMap<String, List<Study>> myRegisteredFreeStudyMap;
  HashMap<String, List<Study>> myParticipatedFreeStudyMap;

  public RegisterFreeStudyDetailHandler(HashMap<String, List<Study>> myRegisteredFreeStudyMap, 
      HashMap<String, List<Study>> myParticipatedFreeStudyMap) {
    this.myRegisteredFreeStudyMap = myRegisteredFreeStudyMap;
    this.myParticipatedFreeStudyMap = myParticipatedFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    /* 해쉬맵의 value값을 myRegisteredFreeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    List<Study> myRegisteredFreeStudy = myRegisteredFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

    System.out.println("[마이 페이지 / 내가 생성한 무료 스터디]\n");

    // 내가 생성한 무료 스터디 리스트 출력
    for (Study freeStudy : myRegisteredFreeStudy) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
          freeStudy.getNo(), 
          freeStudy.getTitle(), 
          freeStudy.getWriter().getName(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), 
          freeStudy.getLike());
    }

    // 내가 생성한 무료 스터디 상세보기
    String title = Prompt.inputString("제목: ");

    Study freeStudy = findByName(title, myRegisteredFreeStudy);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("입력하신 제목과 일치하는 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());


    // 내가 생성한 무료 스터디 상세보기 안에서 신청자 명단 출력
    for (Member freeApplicant : freeStudy.getApplicants()) {
      System.out.printf("신청자: %s\n",freeApplicant.getName());
    }
    System.out.println();
    String name = Prompt.inputString("이름: ");

    //파라미터 값 freeStudy는 해당 스터디의 지원자 명단 확인을 위해 파라미터로 넘김
    Member freeApplicant = findByName(name, freeStudy); 

    if (freeApplicant == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 회원이 없습니다.\n");
      return;
    }

    System.out.printf("이름: %s\n", freeApplicant.getName());
    System.out.printf("이메일: %s\n", freeApplicant.getEmail());
    System.out.printf("아이디: %s\n", freeApplicant.getId());
    System.out.printf("휴대폰 번호: %s\n", freeApplicant.getPhoneNumber());

    System.out.println();
    System.out.println("1. 승인");
    System.out.println("2. 거절");
    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        /* freeStudy는 해당 스터디의 지원자 명단 확인, 
         * freeApplicant는 해당 스터디 정보에 멤버로 추가하기 위해
         *  파라미터로 넘김 */
        studyMemberApproveHandler(freeApplicant, freeStudy);
        System.out.println("팀원 승인이 완료되었습니다.");
        break;

      } else if (input == 2) {
        freeStudy.getApplicants().remove(freeApplicant);
        System.out.println("팀원 신청을 거절하였습니다.");
        break;

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }

  // 내가 생성한 무료 스터디 상세보기 할 때 사용하는 메서드
  private Study findByName(String title, List<Study> myRegisteredFreeStudy) {
    for (Study freeStudy : myRegisteredFreeStudy) {
      if (freeStudy.getTitle().equals(title)) {
        return freeStudy;
      }
    }
    return null;
  }

  // 내가 생성한 무료스터디 신청자 상세보기 할 때 사용하는 메서드
  private Member findByName(String name, Study freeStudy) {
    for (Member freeApplicant : freeStudy.getApplicants()) {
      if (freeApplicant.getName().equals(name)) {
        return freeApplicant;
      }
    }
    return null;
  }
}
