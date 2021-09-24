package com.studywithus.handler.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class RegisterChargeStudyListHandler implements Command {

	// 각 멘토의 생성 유료 스터디 리스트
	HashMap<String, List<Study>> registerChargeStudyMap;
	HashMap<String, List<Study>> participateChargeStudyMap;

	public RegisterChargeStudyListHandler(HashMap<String, List<Study>> registerChargeStudyMap,
			HashMap<String, List<Study>> participateChargeStudyMap) {
		this.registerChargeStudyMap = registerChargeStudyMap;
		this.participateChargeStudyMap = participateChargeStudyMap;
	}

	@Override
	public void execute(CommandRequest request) {
		/* 해쉬맵의 value값을 myRegisteredFreeStudy에 담음 
		 * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
		List<Study> myRegisteredChargeStudy = registerChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

		System.out.println("[마이 페이지 / 내가 생성한 유료 스터디]\n");

		for (Study chargeStudy : myRegisteredChargeStudy) {
			System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
					chargeStudy.getNo(),
					chargeStudy.getTitle(),
					chargeStudy.getWriter().getName(),
					chargeStudy.getRegisteredDate(),
					chargeStudy.getMembers().size(),
					chargeStudy.getMaxMembers(),
					chargeStudy.getViewCount(),
					chargeStudy.getLikeMembers().size());
		}
	}

	// 내가 생성한 무료 스터디 상세보기
	System.out.println();
	String title = Prompt.inputString("제목을 입력하세요. > ");
	System.out.println();

	Study chargeStudy = findByName(title, myRegisteredChargeStudy);

	if (chargeStudy == null) {
		System.out.println();
		System.out.println("입력하신 제목과 일치하는 스터디가 없습니다.");
		return;
	}
	chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

	System.out.printf("제목: %s\n", chargeStudy.getTitle());
	System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

	System.out.printf("설명: %s\n", chargeStudy.getContent());
	System.out.printf("지역: %s\n", chargeStudy.getArea());
	System.out.printf("가격: %s\n", chargeStudy.getPrice());
	System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

	System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());
	System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
	System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembers().size());
	System.out.println();

	if (chargeStudy.getApplicants().isEmpty()) {
		System.out.println("스터디를 신청한 회원이 없습니다.");
		return;
	}
	// 내가 생성한 무료 스터디 상세보기 안에서 신청자 명단 출력1
	for (Member chargeApplicant : chargeStudy.getApplicants()) {
		System.out.printf("신청자: %s\n",chargeApplicant.getName());
	}
	System.out.println();
	String name = Prompt.inputString("이름을 입력하세요. > ");

	//파라미터 값 freeStudy는 해당 스터디의 지원자 명단 확인을 위해 파라미터로 넘김
	Member chargeApplicant = findByName(name, chargeStudy); 

	if (chargeApplicant == null) {
		System.out.println();
		System.out.println("입력하신 이름과 일치하는 회원이 없습니다.");
		return;
	}

	System.out.printf("이름: %s\n", freeApplicant.getName());
	System.out.printf("이메일: %s\n", freeApplicant.getEmail());
	System.out.printf("아이디: %s\n", freeApplicant.getId());
	System.out.printf("휴대폰 번호: %s\n", freeApplicant.getPhoneNumber());

	System.out.println();
	System.out.println("1. 승인");
	System.out.println("2. 거절");
	System.out.println("0. 이전\n");

}
