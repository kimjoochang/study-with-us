package com.studywithus.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MyRegisteredFreeStudyDetailHandler implements Command {

  HashMap<String, List<Study>> myRegisteredFreeStudyMap;
  HashMap<String, List<Study>> myParticipatedFreeStudyMap;

  public MyRegisteredFreeStudyDetailHandler(HashMap<String, List<Study>> myRegisteredFreeStudyMap, 
      HashMap<String, List<Study>> myParticipatedFreeStudyMap) {
    this.myRegisteredFreeStudyMap = myRegisteredFreeStudyMap;
    this.myParticipatedFreeStudyMap = myParticipatedFreeStudyMap;
  }

  @Override
  public void execute() {
    /* 해쉬맵의 value값을 myRegisteredFreeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    List<Study> myRegisteredFreeStudy = myRegisteredFreeStudyMap.get(AuthLoginHandler.getLoginUser().getId());

    System.out.println("[마이 페이지 / 내가 생성한 무료 스터디]\n");

    // 내가 생성한 무료 스터디 리스트 출력
    for (Study freeStudy : myRegisteredFreeStudy) {
      System.out.println("스터디 제목:" + freeStudy.getTitle());
      System.out.println("등록일:" + freeStudy.getRegisteredDate());
      System.out.println();
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

  private void studyMemberApproveHandler(Member freeApplicant, Study freeStudy) {
    List<Member> studyMember = new ArrayList<>();
    studyMember.add(freeApplicant);
    freeStudy.getApplicants().remove(freeApplicant);

    // 스터디 도메인 members에 넣을 회원리스트 생성해서 추가
    List<Member> studyMembers = new ArrayList<>();
    studyMembers.add(freeApplicant);
    freeStudy.setMembers(studyMembers);

    List<Study> myParticipatedFreeStudy; // 해쉬맵에 객체 담기 위한 임시 변수

    // 개개인이 참여한 무료 스터디
    /* 해쉬맵에 key값으로 신청한 회원 id , value값으로 회원이 참여한 스터디 리스트 
     * 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디 추가 */
    if (myParticipatedFreeStudyMap.containsKey(freeApplicant.getId())) {
      myParticipatedFreeStudy = myParticipatedFreeStudyMap.get(freeApplicant.getId());
      myParticipatedFreeStudy.add(freeStudy);
      myParticipatedFreeStudyMap.put(freeApplicant.getId(), myParticipatedFreeStudy);
      // 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
    } else {
      myParticipatedFreeStudy = new ArrayList<>();
      myParticipatedFreeStudy.add(freeStudy);
      myParticipatedFreeStudyMap.put(freeApplicant.getId(), myParticipatedFreeStudy);
    }

    freeApplicant.setUserAccessLevel(freeApplicant.getUserAccessLevel() | Menu.ACCESS_MEMBER);
  }
}

