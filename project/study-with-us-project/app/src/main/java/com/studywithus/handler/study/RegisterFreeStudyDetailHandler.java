package com.studywithus.handler.study;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class RegisterFreeStudyDetailHandler implements Command {

  Study freeStudy;
  List<Study> myRegisteredFreeStudy;
  HashMap<String, List<Study>> registerFreeStudyMap;
  HashMap<String, List<Study>> participateFreeStudyMap;

  public RegisterFreeStudyDetailHandler(HashMap<String, List<Study>> registerFreeStudyMap, 
      HashMap<String, List<Study>> participateFreeStudyMap, List<Study> myRegisteredFreeStudy) {
    this.registerFreeStudyMap = registerFreeStudyMap;
    this.participateFreeStudyMap = participateFreeStudyMap;
    this.myRegisteredFreeStudy = myRegisteredFreeStudy;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이 페이지 / 내가 생성한 무료 스터디]\n");
    /* 해쉬맵의 value값을 myRegisteredFreeStudy에 담음
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    myRegisteredFreeStudy = registerFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());
    Date nowDate = new Date(System.currentTimeMillis());

    //    if (myRegisteredFreeStudy.isEmpty() == true) {
    //      System.out.println("내가 생성한 무료 스터디가 존재하지 않습니다.");
    //      return;
    //    }

    for (int i = 0; i < myRegisteredFreeStudy.size(); i++) {
      // 모집 인원 = 참여 인원 && 현재 시간 < 시작일
      if (myRegisteredFreeStudy.get(i).getMembers().size() == myRegisteredFreeStudy.get(i).getMaxMembers() && myRegisteredFreeStudy.get(i).getStartDate().compareTo(nowDate) == 1) {
        System.out.println("<<모집 완료>>");
        list(myRegisteredFreeStudy);
        System.out.println();

        // 현재 시간 < 시작일
      } else if (myRegisteredFreeStudy.get(i).getStartDate().compareTo(nowDate) == 1) {
        System.out.println("<<모집 중>>");
        list(myRegisteredFreeStudy);
        System.out.println();

        // 현재 시간 >= 시작일
      } else if (myRegisteredFreeStudy.get(i).getStartDate().compareTo(nowDate) != 1) {
        System.out.println("<<진행 중>>");
        list(myRegisteredFreeStudy);
        System.out.println();
      }
    }

    // 내가 생성한 무료 스터디 상세보기
    System.out.println();
    String title = Prompt.inputString("제목을 입력하세요. > ");
    System.out.println();

    Study freeStudy = findByName(title, myRegisteredFreeStudy);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("입력하신 제목과 일치하는 스터디가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("온/오프라인: %s\n", freeStudy.getOFFLINE());
      System.out.printf("지역: %s\n", freeStudy.getArea());
    } else {
      System.out.printf("온/오프라인: %s\n", freeStudy.getONLINE());
    }
    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("모집인원: %d / %d\n", freeStudy.getMembers().size(), freeStudy.getMaxMembers());
    System.out.printf("시작일 : %s\n", freeStudy.getStartDate());
    System.out.printf("종료일 : %s\n", freeStudy.getEndDate());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", freeStudy.getLikeMembers().size());
    System.out.println();

    if (freeStudy.getApplicants().isEmpty()) {
      System.out.println("스터디를 신청한 회원이 없습니다.");
      return;
    }

    // 내가 생성한 무료 스터디 상세보기 안에서 신청자 명단 출력1
    for (Member freeApplicant : freeStudy.getApplicants()) {
      System.out.printf("신청자: %s\n",freeApplicant.getName());
    }

    System.out.println();
    String name = Prompt.inputString("이름을 입력하세요. > ");

    //파라미터 값 freeStudy는 해당 스터디의 지원자 명단 확인을 위해 파라미터로 넘김
    Member freeApplicant = findByName(name, freeStudy); 

    if (freeApplicant == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 회원이 없습니다.");
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
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
      System.out.println();

      if (input == 1) {
        /* freeStudy는 해당 스터디의 지원자 명단 확인, 
         * freeApplicant는 해당 스터디 정보에 멤버로 추가하기 위해
         *  파라미터로 넘김 */
        studyMemberApproveHandler(freeApplicant, freeStudy);
        freeApplicant.setUserAccessLevel(Menu.ACCESS_MEMBER | Menu.ACCESS_GENERAL);
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

  // 내가 참여한 무료 스터디 리스트 출력
  private void list(List<Study> myRegisteredFreeStudy) {
    for (Study freeStudy : myRegisteredFreeStudy) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, ", 
          freeStudy.getNo(),
          freeStudy.getTitle(),
          freeStudy.getWriter().getName(),
          freeStudy.getOFFLINE());

      if (freeStudy.getOFFLINE() == "오프라인") {
        System.out.printf("지역 = %s, ", freeStudy.getArea());
      }

      System.out.printf("모집 인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]",
          freeStudy.getMembers().size(),
          freeStudy.getMaxMembers(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(),
          freeStudy.getLikeMembers().size());
    }
  }

  // 내가 생성한 무료 스터디 상세보기 할 때 사용하는 메서드
  private Study findByName(String title, List<Study> registerFreeStudyMap) {
    for (Study freeStudy : registerFreeStudyMap) {
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
    // 승인한 신청자의 정보를 신청자 리스트에서 삭제
    List<Member> studyApplicants = freeStudy.getApplicants();
    studyApplicants.remove(freeApplicant);
    freeStudy.setApplicants(studyApplicants);

    // 스터디 도메인 members에 회원 정보 추가
    List<Member> studyMembers = freeStudy.getMembers();
    studyMembers.add(freeApplicant);
    freeStudy.setMembers(studyMembers);

    List<Study> myParticipatedFreeStudy; // 해쉬맵에 객체 담기 위한 임시 변수

    // 개개인이 참여한 무료 스터디
    /* 해쉬맵에 key값으로 신청한 회원 id , value값으로 회원이 참여한 스터디 리스트 
     * 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디 추가 */
    if (participateFreeStudyMap.containsKey(freeApplicant.getId())) {
      myParticipatedFreeStudy = participateFreeStudyMap.get(freeApplicant.getId());
      myParticipatedFreeStudy.add(freeStudy);
      participateFreeStudyMap.put(freeApplicant.getId(), myParticipatedFreeStudy);

      // 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
    } else {
      myParticipatedFreeStudy = new ArrayList<>();
      myParticipatedFreeStudy.add(freeStudy);
      participateFreeStudyMap.put(freeApplicant.getId(), myParticipatedFreeStudy);
    }
    freeApplicant.setUserAccessLevel(freeApplicant.getUserAccessLevel() | Menu.ACCESS_MEMBER);
  }
}
