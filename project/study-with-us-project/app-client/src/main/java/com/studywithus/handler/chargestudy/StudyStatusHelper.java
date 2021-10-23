package com.studywithus.handler.chargestudy;

import java.sql.Date;
import com.studywithus.domain.Study;

public class StudyStatusHelper {

  public static String studyStatus(Study chargeStudy) {
    // 현재 날짜 < 시작일인 경우
    if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == -1) {
      chargeStudy.setStudyStatus(0); // 모집중
      return "모집중";

      // 현재 날짜 < 종료일
    } else if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == -1) {
      chargeStudy.setStudyStatus(1); // 진행중
      return "진행중";

    } else {
      chargeStudy.setStudyStatus(2); // 진행완료
      return "진행완료";
    }
  }
}
