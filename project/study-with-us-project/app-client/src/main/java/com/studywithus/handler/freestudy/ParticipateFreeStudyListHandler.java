package com.studywithus.handler.freestudy;

import java.util.Collection;
import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class ParticipateFreeStudyListHandler implements Command {

  FreeStudyDao freeStudyDao;

  public ParticipateFreeStudyListHandler(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디 / 조회]");
    // List<Study> LoginIdList =
    // participateFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());
    Collection<Study> freeStudyList = freeStudyDao.findAll();

    int count = 0;

    for (Study freeStudy : freeStudyList) {
      if (freeStudy.getOnOffLine() == 1) {
        System.out.printf(
            "[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
            freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
            freeStudy.getONLINE(), freeStudy.getMembers().size(), freeStudy.getMaxMembers(),
            freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
            freeStudy.getLikeMembers().size());
      } else {
        System.out.printf(
            "[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
            freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
            freeStudy.getOFFLINE(), freeStudy.getArea(), freeStudy.getMembers().size(),
            freeStudy.getMaxMembers(), freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
            freeStudy.getLikeMembers().size());
      }
    }

    if (count == 0) {
      System.out.println("참여 무료 스터디가 존재하지 않습니다.");
      return;
    }

    // if (freeStudyList.isEmpty()) {
    // System.out.println("참여 무료 스터디가 존재하지 않습니다.");
    // return;
    // }

    // Date nowDate = new Date(System.currentTimeMillis());

    // 모집 인원 = 참여 인원 && 현재 시간 < 시작일
    // for (Study study : LoginIdList) {
    // for (int i = 0; i < LoginIdList.size(); i++) {
    // if (LoginIdList.get(i).getMembers().size() == LoginIdList.get(i).getMaxMembers()
    // && LoginIdList.get(i).getStartDate().compareTo(nowDate) == 1) {
    // System.out.println("<<모집 완료>>");
    // list(LoginIdList);
    // System.out.println();
    // }
    // }
    //
    // 현재 시간 < 시작일
    // for (int i = 0; i < LoginIdList.size(); i++) {
    // if (LoginIdList.get(i).getStartDate().compareTo(nowDate) == 1) {
    // System.out.println("<<모집 중>>");
    // list(LoginIdList);
    // System.out.println();
    // }
    // }
    //
    // 현재 시간 >= 시작일
    // for (int i = 0; i < LoginIdList.size(); i++) {
    // if (LoginIdList.get(i).getStartDate().compareTo(nowDate) != 1) {
    // System.out.println("<<진행 중>>");
    // list(LoginIdList);
    // System.out.println();
    // }
    // }
    // }

    // 내가 참여한 무료 스터디 리스트 출력
    // private void list(List<Study> LoginIdList) {
    // for (Study freeStudy : LoginIdList) {
    // if (freeStudy.getOnOffLine() == 1) {
    // System.out.printf(
    // "[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
    // freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
    // freeStudy.getONLINE(), freeStudy.getMembers().size(), freeStudy.getMaxMembers(),
    // freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
    // freeStudy.getLikeMembers().size());
    // } else {
    // System.out.printf(
    // "[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 =
    // %d]\n",
    // freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
    // freeStudy.getOFFLINE(), freeStudy.getArea(), freeStudy.getMembers().size(),
    // freeStudy.getMaxMembers(), freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
    // freeStudy.getLikeMembers().size());
    // }
    // }
  }
}

// ParticipateFreeStudyListHandler Ver.1
// for (int i = 0; i < myParticipatedFreeStudyList.size(); i++) {
// // 현재 시간 < 시작일
// if (myParticipatedFreeStudyList.get(i).getStartDate().compareTo(nowDate) == 1) {
//
// // 모집 인원 = 참여 인원
// if (myParticipatedFreeStudyList.get(i).getMembers().size() == myParticipatedFreeStudyList
// .get(i).getMaxMembers()) {
// System.out.println("<<모집 완료>>");
// list(myParticipatedFreeStudyList);
// System.out.println();
// }
//
// System.out.println("<<모집 중>>");
// list(myParticipatedFreeStudyList);
// System.out.println();
// }
//
// // 현재 시간 >= 시작일
// if (myParticipatedFreeStudyList.get(i).getStartDate().compareTo(nowDate) != 1) {
// System.out.println("<<진행 중>>");
// list(myParticipatedFreeStudyList);
// System.out.println();
// }
// }
