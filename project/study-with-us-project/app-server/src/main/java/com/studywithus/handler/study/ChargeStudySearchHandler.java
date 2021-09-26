//* [ 09.24 수정 소스]
package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudySearchHandler extends AbstractStudyHandler {

  public ChargeStudySearchHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);	
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 검색]\n");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    if (studyList.isEmpty() == true) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.\n");
      return;
    }

    int type = 0; // 일치하는 값이 없을 경우, 게시글 없음을 한 번만 출력하기 위한 변수

    for (Study chargeStudy : studyList) {
      if (!chargeStudy.getTitle().contains(input) &&
          !chargeStudy.getContent().contains(input) &&
          !chargeStudy.getWriter().getName().contains(input)) {
        type = 1;
        continue;
      }

      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
          chargeStudy.getNo(), 
          chargeStudy.getTitle(), 
          chargeStudy.getWriter().getName(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getViewCount(), 
          chargeStudy.getLikeMembers().size());
      return;
    } 

    if (type == 1) {
      System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.");
    }
  }
}
