package com.studywithus.handler.study;

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

public class RegisterChargeStudyDetailHandler implements Command {

  // 각 멘토의 생성 유료 스터디 리스트
  HashMap<String, List<Study>> registerChargeStudyMap;
  HashMap<String, List<Study>> participateChargeStudyMap;

  public RegisterChargeStudyDetailHandler(HashMap<String, List<Study>> registerChargeStudyMap,
      HashMap<String, List<Study>> participateChargeStudyMap) {
    this.registerChargeStudyMap = registerChargeStudyMap;
    this.participateChargeStudyMap = participateChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    /* 해쉬맵의 value값을 myRegisteredChargeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */

    List<Study> myRegisteredChargeStudyList = registerChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

    System.out.println("[마이 페이지 / 내가 생성한 유료 스터디]\n");

    if (myRegisteredChargeStudyList.isEmpty() == true) {
      System.out.println("내가 생성한 유료 스터디가 존재하지 않습니다.");
      return;
    }

    for (Study chargeStudy : myRegisteredChargeStudyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getMembers().size(),
          chargeStudy.getMaxMembers(),
          chargeStudy.getViewCount(),
          chargeStudy.getLikeMembers().size());
    }


    // 내가 생성한 유료 스터디 상세보기
    System.out.println();
    String title = Prompt.inputString("제목을 입력하세요. > ");
    System.out.println();

    Study chargeStudy = findByName(title, myRegisteredChargeStudyList);

    if (chargeStudy == null) {
      System.out.println();
      System.out.println("입력하신 제목과 일치하는 스터디가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

    System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembers().size());
    System.out.println();

    if (chargeStudy.getApplicants().isEmpty() == true) {
      System.out.println("스터디를 신청한 회원이 없습니다.");
      return;
    }

    // 내가 생성한 유료 스터디 상세보기 안에서 신청자 명단 출력1
    for (Member chargeApplicant : chargeStudy.getApplicants()) {
      System.out.printf("신청자: %s\n",chargeApplicant.getName());
    }

    System.out.println();
    String name = Prompt.inputString("이름을 입력하세요. > ");

    //파라미터 값 chargeStudy는 해당 스터디의 지원자 명단 확인을 위해 파라미터로 넘김
    Member chargeApplicant = findByName(name, chargeStudy); 

    if (chargeApplicant == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 회원이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", chargeApplicant.getName());
    System.out.printf("이메일: %s\n", chargeApplicant.getEmail());
    System.out.printf("아이디: %s\n", chargeApplicant.getId());
    System.out.printf("휴대폰 번호: %s\n", chargeApplicant.getPhoneNumber());

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
        studyMemberApproveHandler(chargeApplicant, chargeStudy);
        chargeApplicant.setUserAccessLevel(Menu.ACCESS_MENTEE | Menu.ACCESS_GENERAL);
        System.out.println("팀원 승인이 완료되었습니다.");
        break;

      } else if (input == 2) {
        chargeStudy.getApplicants().remove(chargeApplicant);
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

  // [09.27 추가]
  // 내가 참여한 유료 스터디 리스트 출력
  private void list(List<Study> myRegisteredChargeStudyList) {
    for (Study chargeStudy : myRegisteredChargeStudyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getMembers().size(),
          chargeStudy.getMaxMembers(),
          chargeStudy.getViewCount(),
          chargeStudy.getLikeMembers().size());
    }
    System.out.println();
  }


  // 내가 생성한 유료 스터디 상세보기 할 때 사용하는 메서드
  private Study findByName(String title, List<Study> registerChargeStudyMap) {
    for (Study chargeStudy : registerChargeStudyMap) {
      if (chargeStudy.getTitle().equals(title)) {
        return chargeStudy;
      }
    }
    return null;
  }

  // 내가 생성한 유료스터디 신청자 상세보기 할 때 사용하는 메서드
  private Member findByName(String name, Study chargeStudy) {
    for (Member chargeApplicant : chargeStudy.getApplicants()) {
      if (chargeApplicant.getName().equals(name)) {
        return chargeApplicant;
      }
    }
    return null;
  }

  private void studyMemberApproveHandler(Member chargeApplicant, Study chargeStudy) {
    // 승인한 신청자의 정보를 신청자 리스트에서 삭제
    List<Member> studyApplicants = chargeStudy.getApplicants();
    studyApplicants.remove(chargeApplicant);
    chargeStudy.setApplicants(studyApplicants);

    // 스터디 도메인 members에 회원 정보 추가
    List<Member> studyMembers = chargeStudy.getMembers();
    studyMembers.add(chargeApplicant);
    chargeStudy.setMembers(studyMembers);

    List<Study> myParticipatedChargeStudyList; // 해쉬맵에 객체 담기 위한 임시 변수

    // 개개인이 참여한 무료 스터디
    /* 해쉬맵에 key값으로 신청한 회원 id , value값으로 회원이 참여한 스터디 리스트 
     * 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디 추가 */
    if (participateChargeStudyMap.containsKey(chargeApplicant.getId())) {
      myParticipatedChargeStudyList = participateChargeStudyMap.get(chargeApplicant.getId());
      myParticipatedChargeStudyList.add(chargeStudy);
      participateChargeStudyMap.put(chargeApplicant.getId(), myParticipatedChargeStudyList);

      // 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
    } else {
      myParticipatedChargeStudyList = new ArrayList<>();
      myParticipatedChargeStudyList.add(chargeStudy);
      participateChargeStudyMap.put(chargeApplicant.getId(), myParticipatedChargeStudyList);
    }

    chargeApplicant.setUserAccessLevel(chargeApplicant.getUserAccessLevel() | Menu.ACCESS_MENTEE);
  }

}
