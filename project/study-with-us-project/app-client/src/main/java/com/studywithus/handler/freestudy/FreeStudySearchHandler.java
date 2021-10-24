// [ 09.24 수정 소스]
package com.studywithus.handler.freestudy;

import java.util.Collection;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FreeStudySearchHandler implements Command {

  StudyDao freeStudyDao;

  public FreeStudySearchHandler(StudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 검색]\n");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    Collection<Study> studyList = freeStudyDao.findByKeyword(input);

    if (studyList.isEmpty()) {
      System.out.println("키워드와 일치하는 무료 스터디 게시글이 존재하지 않습니다.\n");
      return;
    }

    for (Study freeStudy : studyList) {
      if (!freeStudy.getTitle().contains(input) && !freeStudy.getContent().contains(input)
          && !freeStudy.getWriter().getName().contains(input)) {

        System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
            freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
            freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
            freeStudy.getLikeMembers().size());
      }
    }
  }
}
