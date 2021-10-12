package com.studywithus.handler.chargestudy;

import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class RegisterChargeStudyDetailHandler implements Command {

  ChargeStudyDao chargeStudyDao;

  public RegisterChargeStudyDetailHandler(ChargeStudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception  {

    System.out.println("[마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 유료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (!chargeStudy.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
      System.out.println("번호에 해당하는 내가 생성한 유료 스터디가 없습니다.");
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

    int changableMenu = 0; // 메뉴 출력 다르게 하기 위한 변수
    if(chargeStudy.isDeleteRequest() == false) {
      System.out.println("2. 삭제 요청");
      changableMenu = 1;
    } else {
      System.out.println("2. 삭제 요청 취소");
      changableMenu = 2;
    }

    System.out.println("0. 이전\n");

    int input = Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
    System.out.println();

    if (input == 1) {
      request.getRequestDispatcher("/chargeStudy/update").forward(request);
    } else if (input == 2 && changableMenu == 1) {
      request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
    }  else if (input == 2 && changableMenu == 2) {
      request.getRequestDispatcher("/chargeStudy/deleteRequestCancel").forward(request);
    } else if (input == 0) {
      return;
    }
  }

}