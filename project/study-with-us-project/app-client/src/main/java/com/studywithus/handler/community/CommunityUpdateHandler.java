package com.studywithus.handler.community;

import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityUpdateHandler implements Command {

	CommunityDao communityDao;

	public CommunityUpdateHandler(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[커뮤니티 / 수정] \n");

		int no = (int) request.getAttribute("communityNo");

		//    HashMap<String, String> params = new HashMap<>();
		//    params.put("no", String.valueOf(no));

		//    requestAgent.request("community.selectOne", params);

		Community community = communityDao.findByNo(no);

		//    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
		//      System.out.println();
		//      System.out.println("해당 번호의 게시글이 없습니다.\n");
		//      return;
		//    }

		//    Community community = requestAgent.getObject(Community.class);

		if (community.getWriter().getNo() != AuthLogInHandler.getLoginUser().getNo()) {
			System.out.println("변경 권한이 없습니다.\n");
			return;
		}

		String title = Prompt.inputString(String.format("[%s] 수정할 제목을 입력하세요. > ", community.getTitle()));
		String content = Prompt.inputString(String.format("[%s] 수정할 내용을 입력하세요. > ", community.getContent()));
		System.out.println();

		while (true) {
			String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("게시글 수정을 취소하였습니다.\n");
				return;

			} else if (!input.equalsIgnoreCase("y")) {
				System.out.println("다시 입력하세요.\n");
				continue;
			} else {
				break;
			}

			// } else if (input.equalsIgnoreCase("y")) {
			//
			// community.setTitle(title);
			// community.setContent(content);
			//
			// requestAgent.request("community.update", community);
			//
			// System.out.println("");
			// System.out.println("게시글을 수정하였습니다.\n");
			// return;
			//
			// } else {
			// System.out.println("다시 입력하세요.\n");
			// continue;
			// }
		}

		community.setTitle(title);
		community.setContent(content);

		//		requestAgent.request("community.update", community);
		communityDao.update(community);
		System.out.println("게시글을 수정하였습니다.\n");
	}
}
