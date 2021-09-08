package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyDeletedDetailHandler extends AbstractChargeStudyHandler{

  public ChargeStudyDeletedDetailHandler(List<ChargeStudy> chargeStudyList, List<ChargeStudy> chargeDetailRequestList, int nothing) {
    super(chargeStudyList, chargeDetailRequestList, 1);	
  }

  // 삭제 요청된 유료 스터디 상세보기
  @Override
  public void execute() {
    System.out.println("[스터디 삭제 요청 내역 / 상세보기]\n");

    for(ChargeStudy study : chargeDetailRequestList) {
      System.out.println("제목 : " + study.getTitle());
    }
    System.out.println();
    String title = Prompt.inputString("스터디 제목? ");

    ChargeStudy study = findByName(title);

    if (study == null) {
      System.out.println();
      System.out.println("해당 제목의 삭제 요청 유료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("스터디 제목: %s\n", study.getTitle());
    System.out.printf("스터디 설명: %s\n", study.getExplanation());
    System.out.printf("지역: %s\n", study.getArea());
    System.out.printf("멘토: %s\n", study.getWriter());
    System.out.printf("가격: %s\n", study.getPrice());
    System.out.printf("등록일: %s\n", study.getRegisteredDate());

    System.out.println();
    System.out.println("1. 삭제");
    System.out.println("0. 이전");
    while(true) {
      int input = Prompt.inputInt("선택>");
      if(input == 1) {
        chargeDetailRequestList.remove(study);
        chargeStudyList.remove(study);
        System.out.println("삭제가 완료되었습니다.");
      } else if (input == 0) {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      return;
    }
  }



  private ChargeStudy findByName(String title) {
    for (ChargeStudy chargeStudy : chargeDetailRequestList) {
      if (chargeStudy.getTitle().equals(title)) {
        return chargeStudy;
      }
    }
    return null;
  }

}
