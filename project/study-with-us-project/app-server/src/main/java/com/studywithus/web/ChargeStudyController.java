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
import com.studywithus.dao.DeleteRequestFormDao;
import com.studywithus.dao.PaymentDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.DeleteRequestForm;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@Controller
public class ChargeStudyController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao chargeStudyDao;
  @Autowired DeleteRequestFormDao deleteRequestFormDao;
  @Autowired StudyMemberDao studyMemberDao;
  @Autowired PaymentDao paymentDao;

  @GetMapping("/chargestudy/search")
  public ModelAndView search(String keyword) throws Exception {

    Collection <Study> chargeStudyList = chargeStudyDao.findByKeyword(keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudyList", chargeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 멘토링검색결과");
    mv.setViewName("chargestudy/ChargeStudyList");
    return mv;
  }

  @PostMapping("/chargestudy/add")
  public ModelAndView add(Study chargeStudy, HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    chargeStudy.setWriter(member);

    chargeStudyDao.insert(chargeStudy);
    studyMemberDao.insert(member.getNo(), chargeStudy.getNo(), Study.OWNER_STATUS);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/chargestudy/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "스터디위더스 : 멘토링등록");
    mv.setViewName("chargestudy/ChargeStudyAddForm");
    return mv;
  }


  @GetMapping("/chargestudy/list")
  public ModelAndView list() throws Exception {
    Collection<Study> chargeStudyList = chargeStudyDao.findAll(1,10000000);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudyList", chargeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 멘토링목록");
    mv.setViewName("chargestudy/ChargeStudyList");
    return mv;
  }

  @GetMapping("/chargestudy/detail")
  public ModelAndView detail(int no, HttpSession session) throws Exception {

    int result;
    int payResult;

    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    }

    if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == -1) {
      chargeStudy.setStudyStatus(0); // 모집중

    } else if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == -1) {
      chargeStudy.setStudyStatus(1); // 진행중

    } else {
      chargeStudy.setStudyStatus(2); // 진행완료
    }

    chargeStudyDao.updateCount(no);

    Member member = (Member) session.getAttribute("loginUser");

    if (member != null) {
      result =  chargeStudyDao.checkLikesByMember(member.getNo(), no);
      payResult = paymentDao.check(member.getNo(), no);

    } else {
      result = 0;
      payResult = -1;
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudy", chargeStudy);
    mv.addObject("result", result);
    mv.addObject("payResult", payResult);
    mv.addObject("pageTitle", "스터디위더스 : 멘토링상세");
    mv.setViewName("chargestudy/ChargeStudyDetail");
    return mv;
  }

  @GetMapping("/chargestudy/updateform")
  public ModelAndView updateForm(int no) throws Exception {
    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudy", chargeStudy);
    mv.addObject("pageTitle", "스터디위더스 : 멘토링수정");
    mv.setViewName("chargestudy/ChargeStudyUpdateForm");
    return mv;
  }

  @PostMapping("/chargestudy/update")
  public ModelAndView update(Study chargeStudy) throws Exception {
    Study oldStudy= chargeStudyDao.findByNo(chargeStudy.getNo());
    if (oldStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    } 

    chargeStudyDao.update(chargeStudy);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + chargeStudy.getNo());
    return mv;
  }

  @PostMapping("/chargestudy/deleterequest")
  public ModelAndView deleteRequest(int no, String reason) throws Exception {
    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    }

    chargeStudy.setDeleteStatus(1);

    DeleteRequestForm deleteRequestForm = new DeleteRequestForm();
    deleteRequestForm.setStudy(chargeStudy);
    deleteRequestForm.setReason(reason);

    chargeStudyDao.update(chargeStudy);
    deleteRequestFormDao.insert(deleteRequestForm);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + chargeStudy.getNo());
    return mv;
  }

  @GetMapping("/chargestudy/deletecancel")
  public ModelAndView deleteCancel(int no) throws Exception {

    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    }

    chargeStudy.setDeleteStatus(0);

    chargeStudyDao.update(chargeStudy);
    deleteRequestFormDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + chargeStudy.getNo());
    return mv;
  }

  @GetMapping("/chargestudy/interest/add")
  public ModelAndView interestAdd(int no, HttpSession session) throws Exception {

    Study oldStudy= chargeStudyDao.findByNo(no);

    if (oldStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    } 

    int memberNo = ((Member) session.getAttribute("loginUser")).getNo();

    chargeStudyDao.insertInterest(memberNo, no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?no=" + no);
    return mv;
  }

  @GetMapping("/chargestudy/interest/delete")
  public ModelAndView interestDelete(int no, HttpSession session) throws Exception {

    Study oldStudy= chargeStudyDao.findByNo(no);

    if (oldStudy == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    } 

    int memberNo = ((Member) session.getAttribute("loginUser")).getNo();

    chargeStudyDao.deleteInterest(memberNo, no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?no=" + no);
    return mv;
  }

  @GetMapping("/adminpage/deleterequestlist")
  public ModelAndView deleteRequestList() throws Exception {
    Collection<DeleteRequestForm> deleteRequestFormList = deleteRequestFormDao.findAllForAdmin();

    ModelAndView mv = new ModelAndView();
    mv.addObject("deleteRequestFormList", deleteRequestFormList);
    mv.addObject("pageTitle", "스터디위더스 : 삭제요청목록");
    mv.setViewName("../jsp/AdminPage_study");
    return mv;
  }

  @PostMapping("/adminpage/requestapprove")
  public ModelAndView requestApprove(int no, int studyNo) throws Exception {

    Study study = chargeStudyDao.findByNo(studyNo);

    if (study == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    } 

    study.setDeleteStatus(2);

    deleteRequestFormDao.delete(no);
    chargeStudyDao.update(study);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:deleterequestlist");
    return mv;
  }

  @PostMapping("/adminpage/requestreject")
  public ModelAndView requestReject(int no, int studyNo, String remarks) throws Exception {

    Study study = chargeStudyDao.findByNo(studyNo);

    if (study == null) {
      throw new Exception("해당 번호의 멘토링이 없습니다.");
    } 

    study.setDeleteStatus(0);

    DeleteRequestForm deleteRequestForm = deleteRequestFormDao.findByNo(no);

    deleteRequestForm.setRemarks(remarks);

    deleteRequestFormDao.updateRemarks(deleteRequestForm);
    chargeStudyDao.update(study);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:deleterequestlist");
    return mv;
  }

  @GetMapping("/mypage/chargeregisterlist")
  public ModelAndView chargeRegisterList(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    Collection<Study> chargeStudyList = 
        studyMemberDao.findAllStudy(member.getNo(),Study.OWNER_STATUS,1,10000000);

    ModelAndView mv = new ModelAndView();
    mv.addObject("studyList", chargeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 내가 생성한 스터디");
    mv.setViewName("../jsp/MyPage_chargeStudy");
    return mv;
  }

  @GetMapping("/mypage/chargeparticipatelist")
  public ModelAndView chargeParticipateList(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    Collection<Study> chargeStudyList = 
        studyMemberDao.findAllStudy(member.getNo(),Study.PARTICIPANT_STATUS,1,10000000);

    ModelAndView mv = new ModelAndView();
    mv.addObject("studyList", chargeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 내가 참여한 스터디");
    mv.setViewName("../jsp/MyPage_chargeStudy");
    return mv;
  }

  @GetMapping("/mypage/chargeinterestlist")
  public ModelAndView chargeInterestList(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    Collection<Study> chargeStudyList = chargeStudyDao.findAllInterest(member.getNo(),1,10000000);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudyList", chargeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 나의 관심목록");
    mv.setViewName("../jsp/chargestudy/ChargeStudyInterestList");
    return mv;
  }

}
