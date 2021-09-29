package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class RegisterChargeStudyDetailHandler implements Command {

  HashMap<String, List<Study>> registerChargeStudyMap;
  List<Study> myRegisterChargeStudyList;

  public RegisterChargeStudyDetailHandler(HashMap<String, List<Study>> registerChargeStudyMap) {
    this.registerChargeStudyMap = registerChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) throws Exception  {

    System.out.println("[마이 페이지 / 내가 생성한 유료 스터디 / 상세보기]\n");

    myRegisterChargeStudyList = registerChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study chargeStudy = findByNo(no);

    if (chargeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 내가 생성한 유료 스터디가 없습니다.\n");
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

    request.setAttribute("chargeNo", no);

    System.out.println("1. 수정"); 
    System.out.println("2. 삭제");
    System.out.println("0. 이전\n");

    int input = Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
    System.out.println();

    if (input == 1) {
      request.getRequestDispatcher("/chargeStudy/update").forward(request);
    } else if (input == 2) {
      request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
    } else if (input == 0) {
      return;
    }
  }

  private Study findByNo(int no) {
    for (Study myRegisterChargeStudy : myRegisterChargeStudyList) {
      if (myRegisterChargeStudy.getNo() == no) {
        return myRegisterChargeStudy;
      }
    }
    return null;
  }
}
