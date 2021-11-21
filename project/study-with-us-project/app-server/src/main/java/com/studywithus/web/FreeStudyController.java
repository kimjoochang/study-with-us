package com.studywithus.web;

import java.sql.Date;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@Controller
public class FreeStudyController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao freeStudyDao;
  @Autowired StudyMemberDao studyMemberDao;

  @GetMapping("/freestudy/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "스터디위더스 : 스터디등록");
    mv.setViewName("freestudy/FreeStudyAddForm");
    return mv;
  }

  @GetMapping("/freestudy/search")
  public ModelAndView search(String keyword) throws Exception {

    Collection <Study> freeStudyList = freeStudyDao.findByKeyword(keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 스터디검색결과");
    mv.setViewName("freestudy/FreeStudyList");
    return mv;
  }

  @GetMapping("/freestudy/findByCategory")
  public ModelAndView findByCategory(String keyword) throws Exception {

    Collection <Study> freeStudyList = freeStudyDao.findAllByKeyword(0, 0, keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 스터디검색결과");
    mv.setViewName("freestudy/FreeStudyList");
    return mv;
  }

  @PostMapping("/freestudy/add")
  public ModelAndView add(Study freeStudy, HttpSession session) throws Exception {

    Member freeMember = (Member) session.getAttribute("loginUser");
    freeStudy.setWriter(freeMember);

    freeStudyDao.insert(freeStudy);
    studyMemberDao.insert(freeMember.getNo(), freeStudy.getNo(), Study.OWNER_STATUS);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/freestudy/list")
  public ModelAndView list() throws Exception {
    Collection<Study> freeStudyList = freeStudyDao.findAll(0,0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 스터디목록");
    mv.setViewName("freestudy/FreeStudyList");
    return mv;
  }

  @GetMapping("/freestudy/detail")
  public ModelAndView detail(int no, HttpSession session) throws Exception {

    int result; // 관심목록 추가 여부 확인용 임시 변수
    int participateResult;
    Member participant = null;

    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    if (new Date(System.currentTimeMillis()).compareTo(freeStudy.getStartDate()) == -1) {
      freeStudy.setStudyStatus(0); // 모집중

    } else if (new Date(System.currentTimeMillis()).compareTo(freeStudy.getEndDate()) == -1) {
      freeStudy.setStudyStatus(1); // 진행중

    } else {
      freeStudy.setStudyStatus(2); // 진행완료
    }

    freeStudyDao.updateCount(no);

    Member freeMember = (Member)session.getAttribute("loginUser");

    if (freeMember != null) {
      result =  freeStudyDao.checkLikesByMember(freeMember.getNo(), no);
      participant = studyMemberDao.findByNoMember(freeMember.getNo(), no, Study.APPLICANT_STATUS);
    } else {
      result = 0;
    }

    if (participant == null) {
      participateResult = 0;

    } else {
      participateResult = 1;
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudy", freeStudy);
    mv.addObject("result", result);
    mv.addObject("participateResult", participateResult);
    mv.addObject("pageTitle", "스터디위더스 : 스터디상세");
    mv.setViewName("freestudy/FreeStudyDetail");
    return mv;
  }

  @PostMapping("/freestudy/update")
  public ModelAndView update(Study freeStudy) throws Exception {

    Study oldFreeStudy = freeStudyDao.findByNo(freeStudy.getNo());

    if (oldFreeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    } 

    freeStudyDao.update(freeStudy);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + freeStudy.getNo());
    return mv;
  }

  @GetMapping("/freestudy/updateform")
  public ModelAndView updateForm(int no) throws Exception {
    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudy", freeStudy);
    mv.addObject("pageTitle", "스터디위더스 : 스터디수정");
    mv.setViewName("freestudy/FreeStudyUpdateForm");
    return mv;
  }

  @GetMapping("/freestudy/delete")
  public ModelAndView delete(int no) throws Exception {

    Study freeStudy = freeStudyDao.findByNo(no);
    if (freeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    freeStudyDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/freestudy/interest/add")
  public ModelAndView interestAdd(int no, HttpSession session) throws Exception {

    Study oldFreeStudy= freeStudyDao.findByNo(no);

    if (oldFreeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    } 

    int memberNo = ((Member) session.getAttribute("loginUser")).getNo();

    freeStudyDao.insertInterest(memberNo, no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?no=" + no);
    return mv;
  }

  @GetMapping("/freestudy/apply")
  public ModelAndView apply(int no, HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    studyMemberDao.insert(member.getNo(), no, Study.APPLICANT_STATUS);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + no);
    return mv;
  }

  @GetMapping("/freestudy/applycancel")
  public ModelAndView applycancel(int no, HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    studyMemberDao.delete(member.getNo(), no);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + no);
    return mv;
  }

  @GetMapping("/freestudy/interest/delete")
  public ModelAndView interestDelete(int no, HttpSession session) throws Exception {

    Study oldFreeStudy= freeStudyDao.findByNo(no);

    if (oldFreeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    } 

    int memberNo = ((Member) session.getAttribute("loginUser")).getNo();

    freeStudyDao.deleteInterest(memberNo, no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?no=" + no);
    return mv;
  }

  @GetMapping("/mypage/freeregisterlist")
  public ModelAndView freeRegisterList(HttpSession session) throws Exception {

    Member freeMember = (Member) session.getAttribute("loginUser");

    Collection<Study> freeStudyList = 
        studyMemberDao.findAllStudy(freeMember.getNo(),Study.OWNER_STATUS,0,0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 내가 생성한 스터디");
    mv.setViewName("../jsp/MyPage_freeStudy");
    return mv;
  }

  @GetMapping("/mypage/freeparticipatelist")
  public ModelAndView freeParticipateList(HttpSession session) throws Exception {

    Member freeMember = (Member) session.getAttribute("loginUser");

    Collection<Study> freeStudyList = 
        studyMemberDao.findAllStudy(freeMember.getNo(),Study.PARTICIPANT_STATUS,0,0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 내가 참여한 스터디");
    mv.setViewName("../jsp/MyPage_freeStudy");
    return mv;
  }

  @GetMapping("/mypage/freeinterestlist")
  public ModelAndView freeInterestList(HttpSession session) throws Exception {

    Member freeMember = (Member) session.getAttribute("loginUser");

    Collection<Study> freeStudyList = freeStudyDao.findAllInterest(freeMember.getNo(),0,0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 나의 관심목록");
    mv.setViewName("../jsp/chargestudy/ChargeStudyInterestList");
    return mv;
  }

}
