package com.studywithus.handler.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;


public class FreeStudyApplyHandler extends AbstractStudyHandler {
  List<Member> freeApplicantList;
  HashMap<String, List<Study>> applyFreeStudyMap;

  public FreeStudyApplyHandler(List<Study> freeStudyList,
      HashMap<String, List<Study>> applyFreeStudyMap) {
    super(freeStudyList);
    this.applyFreeStudyMap = applyFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 상세보기 / 신청]\n");

    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = findByNo(no);


    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    // 중복신청 확인
    for (Member member : freeStudy.getApplicants()) {
      if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
        System.out.println("이미 신청하셨습니다.");
        return;
      }
    }

    // 모집인원 다 찼을 경우
    if (freeStudy.getMembers().size() == freeStudy.getMaxMembers()) {
      System.out.println("모집 인원이 다 찼습니다.");
      return;
    }
    while (true) {
      String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("무료 스터디 신청이 취소되었습니다.");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    // 회원 개개인의 신청한 스터디
    List<Member> freeApplicantList;
    List<Study> freeApplicationList;

    // 무료 스터디 신청자 리스트에 회원 정보 추가 (멘토 관점)
    freeApplicantList = freeStudy.getApplicants();
    freeApplicantList.add(AuthLogInHandler.getLoginUser());
    freeStudy.setApplicants(freeApplicantList);

    if (applyFreeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getId())) {
      freeApplicationList = applyFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

      freeApplicationList.add(freeStudy);
      applyFreeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), freeApplicationList);

      // 생성 리스트가 없는 회원이라면 새로운 생성 리스트에 스터디 추가
    } else {
      freeApplicationList = new ArrayList<>();

      freeApplicationList.add(freeStudy);
      applyFreeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), freeApplicationList);
    }

    System.out.println();
    System.out.println("무료 스터디 신청이 완료되었습니다.");
  }
}
