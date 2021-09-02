package com.studywithus.handler;

import java.util.List;

public abstract class AbstractCommunityTalkHandler implements Command{

	protected List<CommunityTalk> communityTalk;

	public AbstractCommunityTalkHandler(List<CommunityTalk> communityTalk) {
		this.communityTalkList = communityTalkList;
	}

	// 스몰톡 게시글 번호 조회
	private CommunityTalk findByNo(int no) {
		for (CommunityTalk communityTalk : communityTalkList) {
			if (communityTalk.getNo() == no) {
				return communityTalk;
			}
		}
		return null;
	}

}
