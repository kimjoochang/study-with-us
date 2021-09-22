package com.studywithus.handler.study;

import java.util.List;

import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyInterestAddHandler extends AbstractStudyHandler {


	// 유료 스터디 관심목록 리스트 (회원 관점)
	//	List<Study> chargeInterestList;

	public ChargeStudyInterestAddHandler(List<Study> chargeStudyList) {
		super(chargeStudyList);
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[마이페이지 / 유료 스터디 / 관심 목록 / 추가]\n");
		int no = (int) request.getAttribute("ChargeNo");

		Study chargeStudy = findByNo(no);

		if (chargeStudy == null) {
			System.out.println("해당 번호의 게시글이 없습니다.");
			return;
		}

		String input = Prompt.inputString("관심 목록에 추가하시겠습니까? (y/N) ");

		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
			return;
		}

		// 유료 스터디 관심목록 리스트 (회원 관점)
		List<Member> likeMember = chargeStudy.getLikeMembers();
		likeMember.add(AuthLogInHandler.getLoginUser());
		chargeStudy.setLikeMembers(likeMember);

		System.out.println();
		System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
	}
}
