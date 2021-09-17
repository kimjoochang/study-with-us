package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;

public class ParticipateChargeStudyListHandler implements Command {

  // 각 회원의 참여 유료 스터디 리스트
  HashMap<String, List<Study>> participateChargeStudyMap;

  public ParticipateChargeStudyListHandler(HashMap<String, List<Study>> participateChargeStudyMap) {
    this.participateChargeStudyMap = participateChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이 페이지 / 나의 활동 / 내 스터디 / 참여 유료 스터디]");

    // 회원 아이디로 값 호출
    for (Study chargeStudy : participateChargeStudyMap.get(AuthLoginHandler.getLoginUser().getId())) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeStudy.getNo(), chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(), chargeStudy.getRegisteredDate(), chargeStudy.getViewCount(),
          chargeStudy.getLike());
    }
  }
}
