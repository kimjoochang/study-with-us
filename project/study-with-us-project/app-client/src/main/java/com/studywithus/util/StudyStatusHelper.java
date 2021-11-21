package com.studywithus.util;

import java.sql.Date;
import com.studywithus.domain.Study;

public class StudyStatusHelper {


  public static String studyStatus(Study chargeStudy) {
    if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == -1) {
      chargeStudy.setStudyStatus(0); // 모집중

    } else if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == -1) {
      chargeStudy.setStudyStatus(1); // 진행중

    } else {
      chargeStudy.setStudyStatus(2); // 진행완료
    }
  }
}
