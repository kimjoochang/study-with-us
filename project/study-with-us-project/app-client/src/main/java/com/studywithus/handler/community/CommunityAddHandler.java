package com.studywithus.handler.community;

import java.sql.Date;

import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class CommunityAddHandler implements Command {

<<<<<<< HEAD
	RequestAgent requestAgent;
	int no = 1;
=======
  RequestAgent requestAgent;
  int no = 1;
>>>>>>> b240d08a9a773171851be36bc18e87bec1b4adb5

	public CommunityAddHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[커뮤니티 / 생성] \n");

		Community community = new Community();

<<<<<<< HEAD
		community.setNo(no++);
		community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
		community.setContent(Prompt.inputString("내용을 입력하세요. > "));
		community.setWriter(AuthLogInHandler.getLoginUser());
		community.setRegisteredDate(new Date(System.currentTimeMillis()));
=======
    community.setNo(no++);
    community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    community.setContent(Prompt.inputString("내용을 입력하세요. > "));
    community.setWriter(AuthLogInHandler.getLoginUser());
    community.setRegisteredDate(new Date(System.currentTimeMillis()));
>>>>>>> b240d08a9a773171851be36bc18e87bec1b4adb5

		//    communityList.add(community);

		requestAgent.request("community.insert", community);
		if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			System.out.println("커뮤니티 저장 실패!");
			return;
		}

		System.out.println();
		System.out.println("커뮤니티 등록이 완료되었습니다.\n");
	}
}