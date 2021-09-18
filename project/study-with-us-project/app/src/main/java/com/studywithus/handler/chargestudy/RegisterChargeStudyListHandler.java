package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;

public class RegisterChargeStudyListHandler implements Command {

  // 각 멘토의 생성 유료 스터디 리스트
  HashMap<String, List<Study>> registerChargeStudyMap;

  public RegisterChargeStudyListHandler(HashMap<String, List<Study>> registerChargeStudyMap) {
    this.registerChargeStudyMap = registerChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이 페이지 / 나의 활동 / 내 스터디 / 생성 유료 스터디]\n");

    if (registerChargeStudyMap.isEmpty() == true) {
      System.out.println("생성 유료 스터디가 존재하지 않습니다.\n");
      return;
    }

    // 멘토 아이디로 값 호출
    for (Study chargeStudy : registerChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId())) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]",
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getViewCount(),
          chargeStudy.getLike());
    }
    System.out.println();
  }
}
