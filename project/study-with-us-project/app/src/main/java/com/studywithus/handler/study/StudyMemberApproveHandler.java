package com.studywithus.handler.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.menu.Menu;

public class StudyMemberApproveHandler extends RegisterFreeStudyDetailHandler {

  public StudyMemberApproveHandler(HashMap<String, List<Study>> myRegisteredFreeStudyMap,
      HashMap<String, List<Study>> myParticipatedFreeStudyMap) {
    super(myRegisteredFreeStudyMap, myParticipatedFreeStudyMap);
  }

  public void studyMemberApproveHandler(Member freeApplicant, Study freeStudy) {
    List<Member> studyMember = new ArrayList<>();
    studyMember.add(freeApplicant);
    freeStudy.getApplicants().remove(freeApplicant);

    // 스터디 도메인 members에 넣을 회원리스트 생성해서 추가
    List<Member> studyMembers = new ArrayList<>();
    studyMembers.add(freeApplicant);
    freeStudy.setMembers(studyMembers);

    List<Study> myParticipatedFreeStudy; // 해쉬맵에 객체 담기 위한 임시 변수

    // 개개인이 참여한 무료 스터디
    /* 해쉬맵에 key값으로 신청한 회원 id , value값으로 회원이 참여한 스터디 리스트 
     * 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디 추가 */
    if (myParticipatedFreeStudyMap.containsKey(freeApplicant.getId())) {
      myParticipatedFreeStudy = myParticipatedFreeStudyMap.get(freeApplicant.getId());
      myParticipatedFreeStudy.add(freeStudy);
      myParticipatedFreeStudyMap.put(freeApplicant.getId(), myParticipatedFreeStudy);

      // 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
    } else {
      myParticipatedFreeStudy = new ArrayList<>();
      myParticipatedFreeStudy.add(freeStudy);
      myParticipatedFreeStudyMap.put(freeApplicant.getId(), myParticipatedFreeStudy);
    }

    freeApplicant.setUserAccessLevel(freeApplicant.getUserAccessLevel() | Menu.ACCESS_MEMBER);
  }
}
