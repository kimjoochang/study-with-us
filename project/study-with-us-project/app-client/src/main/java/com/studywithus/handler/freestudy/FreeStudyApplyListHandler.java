package com.studywithus.handler.freestudy;

import java.util.Collection;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class FreeStudyApplyListHandler implements Command {

    FreeStudyDao freeStudyDao;
    // List<Study> freeStudyApplyList;

    public FreeStudyApplyListHandler(FreeStudyDao freeStudyDao) {
        this.freeStudyDao = freeStudyDao;
        // super(freeStudyList);
    }

    @Override
    public void execute(CommandRequest request) throws Exception {
        System.out.println("[마이페이지 / 나의 활동 / 무료 스터디 신청 내역 / 조회]\n");

        //      Collection<Study> freeStudyList = requestAgent.getObjects(Study.class);
        int type = 0; // 일치하는 값 X -> 게시글 없다는 출력문 한 번만 출력

        Collection<Study> studyList = freeStudyDao.findAll();

        //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        //      System.out.println("무료 스터디 신청 내역 조회 실패!");
        //      return;
        //    }


        for (Study freeStudy : studyList) {
            // 스터디 신청자 X
            if (freeStudy.getApplicants().isEmpty()) {
                // [삭제] 신청내역 조회 + "무료 스터디 신청 내역이 존재하지 않습니다." 출력
                // type = 0;
                // continue;

                if (type == 1) {
                    continue;

                } else {
                    type = 0;
                    continue;
                }
            }

            // 스터디 신청자 O
            for (Member applicant : freeStudy.getApplicants()) {
                // [테스트]
                // System.out.println(freeStudy.getApplicants());

                // 스터디 신청한 회원 == 로그인한 회원
                if (applicant.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
                    type = 1;

                    System.out.printf("제목: %s\n", freeStudy.getTitle());
                    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

                    if (freeStudy.getArea() != null) {
                        System.out.printf("온/오프라인: %s\n", freeStudy.getOFFLINE());
                        System.out.printf("지역: %s\n", freeStudy.getArea());

                    } else {
                        System.out.printf("온/오프라인: %s\n", freeStudy.getONLINE());
                    }

                    System.out.printf("설명: %s\n", freeStudy.getContent());
                    System.out.printf("규칙: %s\n", freeStudy.getRule());
                    System.out.printf("모집인원 = %d / %d\n", freeStudy.getMembers().size(),
                            freeStudy.getMaxMembers());
                    System.out.printf("시작일: %s\n", freeStudy.getStartDate());
                    System.out.printf("종료일: %s\n", freeStudy.getEndDate());
                    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
                    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
                    System.out.printf("좋아요: %d\n", freeStudy.getLikeMembers().size());
                    System.out.println();

                    // 스터디 신청한 회원 != 로그인한 회원
                } else {
                    if (type == 1) {
                        continue;

                    } else {
                        type = 0;
                        continue;
                    }
                }
            }
        }

        // 나의 신청내역 X
        if (type == 0) {
            System.out.println("무료 스터디 신청 내역이 존재하지 않습니다.");
        }
    }
}
