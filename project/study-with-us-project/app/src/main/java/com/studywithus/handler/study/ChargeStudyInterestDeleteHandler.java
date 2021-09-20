package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyInterestDeleteHandler extends AbstractStudyHandler {

  Study chargeStudy;

  // 유료 스터디 관심목록 리스트 (회원 관점)
  List<Study> chargeInterestList;

  public ChargeStudyInterestDeleteHandler(List<Study> chargeStudyList, List<Study> chargeInterestList) {
    super(chargeStudyList);
    this.chargeInterestList = chargeInterestList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 상세보기 / 관심 목록]\n");

    // 일치하는 값이 없을 경우, 게시글 없다는 출력만 한 번만 출력되게 하기 위한 변수
    int type = 0;

    if (request.getAttribute("no") == null) {
      int no = Prompt.inputInt("메뉴 번호를 입력하세요. > ");

      chargeStudy = findByNo(no);

      for (Member member : chargeStudy.getLikeMembers()) {
        if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
          type = 1;
          break;
        }
      }

      if (type == 0) {
        System.out.println("해당 번호의 관심목록이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
        return;
      }

      // 유료 스터디 관심목록 리스트 (회원 관점)
      List<Member> likeMember = chargeStudy.getLikeMembers();
      likeMember.remove(AuthLogInHandler.getLoginUser());
      chargeStudy.setLikeMembers(likeMember);

      // 유료 스터디 관심 목록에 좋아요한 유료 스터디 추가 (회원 관점)
      chargeStudy.setLike(chargeStudy.getLike() - 1);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
    }
  }
}
