package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

// [09.24 merge by 제이] App에서 재확인 필요

public class ChargeStudyInterestDeleteHandler extends AbstractStudyHandler {

  public ChargeStudyInterestDeleteHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이페이지 / 유료 스터디 / 관심 목록 / 삭제]\n");

    int type = 0;

    if (request.getAttribute("chargeNo") == null) {

      int no = Prompt.inputInt("삭제할 관심목록 번호를 입력하세요. > ");

      Study chargeInterest = findByNo(no);

      for (Member member : chargeInterest.getLikeMembers()) {

        if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
          type = 1;
          break;
        }
      }

      if (type == 0) {
        System.out.println("해당 번호의 관심목록이 없습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("유료 스터디 관심 목록 삭제를 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하시오.\n");
          continue;

        } else {
          break;
        }
      }

      List<Member> likeMember = chargeInterest.getLikeMembers();
      likeMember.remove(AuthLogInHandler.getLoginUser());
      chargeInterest.setLikeMembers(likeMember);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");

    } else {
      int no = (int) request.getAttribute("chargeNo");

      Study chargeInterest = findByNo(no);

      if (chargeInterest == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      if (chargeInterest.getLikeMembers() == null) {
        System.out.println("유료 스터디 관심목록이 존재하지 않습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하시오.\n");
          continue;

        } else {
          break;
        }
      }

      List<Member> likeMember = chargeInterest.getLikeMembers();
      likeMember.remove(AuthLogInHandler.getLoginUser());
      chargeInterest.setLikeMembers(likeMember);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
    }
  }
}

/*
 * 기존 소스 
 * 
 * public class ChargeStudyInterestDeleteHandler extends AbstractStudyHandler {
 * 
 * // 유료 스터디 관심목록 리스트 (회원 관점) // List<Study> chargeInterestList;
 * 
 * public ChargeStudyInterestDeleteHandler(List<Study> chargeStudyList) { super(chargeStudyList); }
 * 
 * @Override public void execute(CommandRequest request) {
 * System.out.println("[마이페이지 / 유료 스터디 / 관심 목록 / 삭제]\n");
 * 
 * // 일치하는 값이 없을 경우, 게시글 없다는 출력만 한 번만 출력되게 하기 위한 변수 int type = 0;
 * 
 * // [마이 페이지] if (request.getAttribute("chargeNo") == null) { int no =
 * Prompt.inputInt("메뉴 번호를 입력하세요. > ");
 * 
 * Study chargeInterest = findByNo(no);
 * 
 * for (Member member : chargeInterest.getLikeMembers()) { // [마이페이지 / 2. 내관심목록 / 2.유료스터디관심목록 /
 * 2.삭제] X 오류 if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) { type = 1; break;
 * } }
 * 
 * if (type == 0) { System.out.println("해당 번호의 관심목록이 없습니다."); return; }
 * 
 * while (true) { String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
 * 
 * if (input.equalsIgnoreCase("n") || input.length() == 0) {
 * System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n"); return; } else if (!input.equalsIgnoreCase("y"))
 * { System.out.println("다시 입력하시오.\n"); continue;
 * 
 * } else { break; } }
 * 
 * List<Member> likeMember = chargeInterest.getLikeMembers();
 * likeMember.remove(AuthLogInHandler.getLoginUser()); chargeInterest.setLikeMembers(likeMember);
 * 
 * System.out.println(); System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
 * 
 * // [유료 스터디 / 상세보기] } else { int no = (int) request.getAttribute("chargeNo");
 * 
 * Study chargeInterest = findByNo(no);
 * 
 * if (chargeInterest == null) { System.out.println("해당 번호의 게시글이 없습니다."); return; }
 * 
 * if (chargeInterest.getLikeMembers() == null) { System.out.println("유료 스터디 관심목록이 존재하지 않습니다.\n");
 * return; }
 * 
 * while(true) { String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
 * 
 * if (input.equalsIgnoreCase("n") || input.length() == 0) {
 * System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n"); return;
 * 
 * } else if (!input.equalsIgnoreCase("y")) { System.out.println("다시 입력하시오.\n"); continue;
 * 
 * } else { break; } } // 유료 스터디 관심목록 리스트 (회원 관점) List<Member> likeMember =
 * chargeInterest.getLikeMembers(); likeMember.remove(AuthLogInHandler.getLoginUser());
 * chargeInterest.setLikeMembers(likeMember);
 * 
 * System.out.println(); System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n"); } } }
 */
