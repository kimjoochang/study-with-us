package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class FreeStudyInterestListHandler extends AbstractStudyHandler {


  public FreeStudyInterestListHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 관심목록 / 조회]\n");
    // 일치하는 값이 없을경우, 게시글 없다는 출력만 한 번만 출력되게 하기 위한 변수
    int type = 0;
    // 기존 스터디 리스트 가져와서 하나씩 검색
    for(Study freeStudy : studyList) {
      if (freeStudy.getLikeMembers() == null) {
        type = 1; // 일치하는 값 없음
        continue;
      }
      // 스터디 리스트에서 꺼낸 스터디의 좋아요 누른 회원 리스트를 가져와서 하나씩 검색
      for(Member likeMember : freeStudy.getLikeMembers()) {
        // 좋아요 누른 회원 리스트에서 꺼낸 회원의 아이디와 로그인한 회원의 아이디가 같다면
        // 좋아요 누른 회원 리스트를 가지고 있는 스터디의 정보 출력
        if(likeMember.getId().equals(AuthLogInHandler.loginUser.getId())) {
          type = 0; // 일치하는 값 있음
          if (freeStudy.getOnOffLine() == 1) {
            System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
                freeStudy.getNo(), 
                freeStudy.getTitle(), 
                freeStudy.getWriter().getName(),
                freeStudy.getONLINE(),
                freeStudy.getMembers().size(),
                freeStudy.getMaxMembers(),
                freeStudy.getRegisteredDate(),
                freeStudy.getViewCount(), 
                freeStudy.getLikeMembers().size());
          } else {
            System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
                freeStudy.getNo(), 
                freeStudy.getTitle(), 
                freeStudy.getWriter().getName(),
                freeStudy.getOFFLINE() ,
                freeStudy.getArea(),
                freeStudy.getMembers().size(),
                freeStudy.getMaxMembers(),
                freeStudy.getRegisteredDate(),
                freeStudy.getViewCount(), 
                freeStudy.getLikeMembers().size());
          }
        }
      }
    }
    if (type == 1) { // 일치하는 값 없을 경우, 출력
      System.out.println("무료 스터디 관심목록이 존재하지 않습니다.\n");
    }

    System.out.println();
  }
}
