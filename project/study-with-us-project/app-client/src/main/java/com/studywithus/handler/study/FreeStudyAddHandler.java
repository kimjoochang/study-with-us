package com.studywithus.handler.study;

import java.sql.Date;

import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler implements Command {

	RequestAgent requestAgent;

	public FreeStudyAddHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[무료 스터디 / 생성]\n");

		Study freeStudy = new Study();

		freeStudy.setNo(Prompt.inputInt("번호를 입력하세요. > "));
		System.out.println("온/오프라인을 선택하세요.");
		System.out.println("1. 온라인");
		System.out.println("2. 오프라인");
		freeStudy.setOnOffLine(Prompt.inputInt("> "));

		if (freeStudy.getOnOffLine() == 2) {
			freeStudy.setArea(Prompt.inputString("지역을 입력하세요. > "));

		} else {
			freeStudy.setArea(null);
		}

		freeStudy.setTitle(Prompt.inputString("제목을 입력하세요. > "));
		freeStudy.setWriter(AuthLogInHandler.getLoginUser());
		freeStudy.setContent(Prompt.inputString("설명을 입력하세요. > "));
		freeStudy.setRule(Prompt.inputString("규칙을 입력하세요. > "));
		freeStudy.setMaxMembers(Prompt.inputInt("모집 인원을 입력하세요. > "));
		freeStudy.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

		while (true) {
			freeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

			// 시작일 < 종료일이 아닌 경우
			if (freeStudy.getEndDate().compareTo(freeStudy.getStartDate()) != 1) {
				System.out.println("종료일은 시작일 이후로 설정하시오.\n");
				continue;

			} else {
				break;
			}
		}

		freeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

		requestAgent.request("freeStudy.insert", freeStudy);

		while (true) {
			for (Member member : freeStudy.getMembers()) {

				if (member.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {

					requestAgent.request("registerFreeStudy.insert", freeStudy);

					// 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
				} else {
					//					registerFreeStudyList = new ArrayList<>();

					//					requestAgent.request("registerFreeStudy.insert", freeStudy);
				}
			}

			//		if (registerFreeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getEmail())) { // 스터디에 참여한 팀원들 
			//			registerFreeStudyList = registerFreeStudyMap.get(AuthLogInHandler.getLoginUser().getEmail());
			//
			//			registerFreeStudyList.add(freeStudy);
			//			registerFreeStudyMap.put(AuthLogInHandler.getLoginUser().getEmail(), registerFreeStudyList);
			//
			//			// 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
			//		} else {
			//			registerFreeStudyList = new ArrayList<>();
			//
			//			registerFreeStudyList.add(freeStudy);
			//			registerFreeStudyMap.put(AuthLogInHandler.getLoginUser().getEmail(), registerFreeStudyList);
			//		}

			AuthLogInHandler.userAccessLevel |= Menu.ACCESS_LEADER;

			System.out.println();
			System.out.println("무료 스터디 등록이 완료되었습니다.");
		}
	}
}
