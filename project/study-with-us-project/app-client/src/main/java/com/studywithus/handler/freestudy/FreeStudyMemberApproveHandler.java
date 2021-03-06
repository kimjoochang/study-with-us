package com.studywithus.handler.freestudy;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyMemberApproveHandler implements Command {

  StudyMemberDao studyMemberDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  public FreeStudyMemberApproveHandler(StudyMemberDao studyMemberDao, MemberDao memberDao, SqlSession sqlSession) {
    this.studyMemberDao = studyMemberDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디/ 상세보기 / 승인]\n");
    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = studyMemberDao.findByNoStudy(AuthLogInHandler.getLoginUser().getNo(), no, Study.OWNER_STATUS);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    List<Member> applicants = studyMemberDao.findAllMember(no, Study.PARTICIPANT_STATUS);

    while (true) {
      for (Member freeApplicant : applicants) {
        System.out.printf("[회원번호 = %d, 이름 = %s]", freeApplicant.getNo(), freeApplicant.getName());
      }

      int applicantNo = Prompt.inputInt("신청자 번호를 입력하세요. > ");

      String input = Prompt.inputString("해당 회원을 멤버로 승인하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("멤버 승인이 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        Member member = memberDao.findByNo(applicantNo);

        int temp = member.getUserAccessLevel(); 
        temp |= Menu.ACCESS_MEMBER;
        member.setUserAccessLevel(temp);

        memberDao.update(member);
        studyMemberDao.update(applicantNo, no, 1);
        sqlSession.commit();

        System.out.println();
        System.out.println("멤버 승인이 완료되었습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
