package com.studywithus.handler.study;

import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyApplyCancelHandler implements Command {

  RequestAgent requestAgent;
  // List<Member> freeApplicantList;
  // HashMap<String, List<Study>> applyFreeStudyMap;

  public FreeStudyApplyCancelHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
    // super(freeStudyList);
    // this.applyFreeStudyMap = applyFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 신청 취소]\n");
    int no = (int) request.getAttribute("freeNo");

    // Study freeStudy = findByNo(no);

    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.selectOne", params);

    // if (freeStudy == null) {
    // System.out.println("해당 번호의 게시글이 없습니다.");
    // return;
    // }

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("무료 스터디 상세보기 실패!");
      System.out.println(requestAgent.getObject(Study.class));
      return;
    }

    Study freeStudy = requestAgent.getObject(Study.class);

    while (true) {
      String input = Prompt.inputString("무료 스터디를 신청을 취소하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("무료 스터디 신청 취소가 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        freeStudy.getApplicants().remove(AuthLogInHandler.getLoginUser());
        requestAgent.request("freeStudy.update", freeStudy);

        if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
          System.out.println("무료 스터디 신청 취소 성공!");
          return;

        } else {
          System.out.println("무료 스터디 신청 취소 실패!");
          return;
        }

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }

    // 회원 개개인의 신청한 스터디
    // List<Member> freeApplicantList;
    // List<Study> freeApplicationList;

    // 무료 스터디 신청자 리스트에 회원 정보 추가 (멘토 관점)
    // freeApplicantList = freeStudy.getApplicants();
    // freeApplicantList.remove(AuthLogInHandler.getLoginUser());
    // freeStudy.setApplicants(freeApplicantList);

    // if (applyFreeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getEmail())) {
    // freeApplicationList = applyFreeStudyMap.get(AuthLogInHandler.getLoginUser().getEmail());
    //
    // freeApplicationList.remove(freeStudy);
    // applyFreeStudyMap.put(AuthLogInHandler.getLoginUser().getEmail(), freeApplicationList);
    //
    // 생성 리스트가 없는 회원이라면 새로운 생성 리스트에 스터디 추가
    // } else {
    // freeApplicationList = new ArrayList<>();
    //
    // freeApplicationList.remove(freeStudy);
    // applyFreeStudyMap.put(AuthLogInHandler.getLoginUser().getEmail(), freeApplicationList);
    // }

    // [질문]
    // requestAgent.request("freeStudy.apply.delete", freeStudy);
    // requestAgent.request("freeStudy.delete", freeStudy);

    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // System.out.println("해당 번호의 게시글이 없습니다.");
    // return;
    //
    // } else if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
    // System.out.println("무료 스터디 신청 취소가 완료되었습니다.");
    // return;
    // }

    // System.out.println();
    // System.out.println("무료 스터디 신청 취소가 완료되었습니다.");
  }
}
