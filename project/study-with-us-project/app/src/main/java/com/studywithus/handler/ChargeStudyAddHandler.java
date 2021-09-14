package com.studywithus.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeStudyAddHandler extends AbstractStudyHandler {

  // 각 멘토의 생성 유료 스터디 리스트
  HashMap<String, List<Study>> registerChargeStudyMap;
  List<Study> registerChargeStudyList;

  public ChargeStudyAddHandler(List<Study> chargeStudyList, HashMap<String, List<Study>> registerChargeStudyMap) {
    super(chargeStudyList);
    this.registerChargeStudyMap = registerChargeStudyMap;
  }

  @Override
  public void execute() {
    System.out.println("[유료 스터디 / 생성]\n");

    Study chargeStudy = new Study();

    chargeStudy.setWriter(AuthLoginHandler.getLoginUser());
    chargeStudy.setNo(Prompt.inputInt("번호: "));
    chargeStudy.setArea(Prompt.inputString("지역: "));
    chargeStudy.setTitle(Prompt.inputString("스터디 제목: "));
    chargeStudy.setContent(Prompt.inputString("스터디 설명: "));
    chargeStudy.setPrice(Prompt.inputInt("가격: " ));
    chargeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    studyList.add(chargeStudy);

    // 멘토의 생성 유료 스터디 리스트 / 아이디 저장
    registerChargeStudyMap.put(AuthLoginHandler.getLoginUser().getId(), registerChargeStudyList);

    System.out.println();
    System.out.println("유료스터디 등록이 완료되었습니다.\n");
  }
}
