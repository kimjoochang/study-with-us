// * [ 09.24 수정 소스]
package com.studywithus.handler.study;


import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FreeStudySearchHandler extends AbstractStudyHandler {

  public FreeStudySearchHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 검색]\n");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    if (studyList.isEmpty() == true) {
      System.out.println("무료 스터디 게시글이 존재하지 않습니다.\n");
      return;
    }

    int type = 0; // 일치하는 값이 없을경우, 게시글 없다는 출력만 한 번만 출력되게 하기 위한 변수

    for (Study freeStudy : studyList) {
      if (!freeStudy.getTitle().contains(input) && !freeStudy.getContent().contains(input)
          && !freeStudy.getWriter().getName().contains(input)) {
        type = 1;
        continue;
      }

      /*
       * 기존 if (type == 1) { continue; } else { type = 0; } }
       */

      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
          freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
          freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
          freeStudy.getLikeMembers().size());
      return;
    }

    if (type == 1) {
      // if (type == 0) {
      System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.");
    }
  }
}

