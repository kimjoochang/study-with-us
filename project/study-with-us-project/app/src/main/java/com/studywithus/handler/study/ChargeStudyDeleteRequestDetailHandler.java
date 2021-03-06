package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudyDeleteRequestDetailHandler extends AbstractStudyHandler {

  public ChargeStudyDeleteRequestDetailHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);
  }

  // 관리자 관점
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[스터디 삭제 요청 내역 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Study chargeStudy = findByNo(no);

    if (chargeStudy == null ) {
      System.out.println();
      System.out.println("해당 번호의 삭제 요청 유료 스터디가 없습니다.\n");
      return;
    } 

    if ( chargeStudy.isDeleteRequest() == false) {
      System.out.println();
      System.out.println("해당 번호의 삭제 요청 유료 스터디가 없습니다.\n");
      return;
    }

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

    System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembers().size());
    System.out.println();

    System.out.println("1. 삭제");
    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("번호를 입력하세요. > ");
      System.out.println();

      // 1. 삭제
      if (input == 1) {
        studyList.remove(chargeStudy);
        System.out.println("삭제가 완료되었습니다.\n");
        return;

        // 0. 이전
      } else if (input == 0) {
        return;
      }

      else {
        System.out.println("무효한 메뉴 번호입니다.\n");
        continue;
      }
    }
  }

}
