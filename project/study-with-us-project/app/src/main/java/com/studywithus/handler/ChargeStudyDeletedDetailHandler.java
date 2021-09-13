package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeStudyDeletedDetailHandler extends AbstractStudyHandler {

  List<Study> chargeDetailRequestList;

  public ChargeStudyDeletedDetailHandler(List<Study> chargeStudyList, List<Study> chargeDetailRequestList) {
    super(chargeStudyList);
    this.chargeDetailRequestList = chargeDetailRequestList;
  }

  @Override
  public void execute() {
    System.out.println("[스터디 삭제 요청 내역 / 상세보기]\n");

    for(Study study : chargeDetailRequestList) {
      System.out.println("제목: " + study.getTitle());
    }

    System.out.println();

    String title = Prompt.inputString("스터디 제목: ");
    Study study = findByName(title);

    if (study == null) {
      System.out.println();
      System.out.println("해당 제목의 삭제 요청 유료 스터디가 없습니다.\n");

      return;
    }

    System.out.printf("스터디 제목: %s\n", study.getTitle());
    System.out.printf("스터디 설명: %s\n", study.getContent());
    System.out.printf("지역: %s\n", study.getArea());
    System.out.printf("멘토: %s\n", study.getWriter().getName());
    System.out.printf("가격: %s\n", study.getPrice());
    System.out.printf("등록일: %s\n", study.getRegisteredDate());

    System.out.println();

    System.out.println("1. 삭제");
    System.out.println("0. 이전");

    while (true) {
      int input = Prompt.inputInt("선택> ");

      // 1. 삭제
      if (input == 1) {
        chargeDetailRequestList.remove(study);
        studyList.remove(study);

        System.out.println("삭제가 완료되었습니다.");

        // 0. 이전
      } else if (input == 0) {
        return;
      }

      else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }

  // 유료 스터디 게시글 제목 조회
  private Study findByName(String title) {
    for (Study chargeStudy : chargeDetailRequestList) {
      if (chargeStudy.getTitle().equals(title)) {
        return chargeStudy;
      }
    }
    return null;
  }
}
