package com.studywithus.web;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplication;

@Controller
public class MentorApplicationController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MentorApplicationDao mentorApplicationDao;
  @Autowired MemberDao memberDao;

  @PostMapping("/mentorapplication/add")
  public ModelAndView add(String selfIntroduction, String subject, HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");


    if (member == null) {
      throw new Exception("로그인이 필요합니다.");
    }

    MentorApplication mentorApplicantEmail = mentorApplicationDao.findByNo(member.getNo());

    // 이미 멘토
    if (member.getUserAccessLevel() == Member.MENTOR) {
      throw new Exception("이미 멘토입니다.");
    }

    if (mentorApplicantEmail != null && mentorApplicantEmail.getStatus() == 0) {
      throw new Exception("이미 신청하셨습니다.");
    }

    MentorApplication mentorApplication = new MentorApplication();

    mentorApplication.setApplicant(member);
    mentorApplication.setSelfIntroduction(selfIntroduction);
    mentorApplication.setSubject(subject);

    mentorApplicationDao.insert(mentorApplication);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../index");
    return mv;
  }

  @GetMapping("/adminpage/mentorapplicationlist")
  public ModelAndView list() throws Exception {
    List<MentorApplication> mentorApplicantList = mentorApplicationDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("mentorApplicantList", mentorApplicantList);
    mv.addObject("pageTitle", "스터디위더스 : 멘토신청서 조회");
    mv.setViewName("AdminPage_member");
    return mv;
  }


  @PostMapping("/mentorapplication/approve")
  public ModelAndView approve(int no, int applicantNo) throws Exception {

    Member applicant = memberDao.findByNo(applicantNo);

    applicant.setUserAccessLevel(Member.MENTOR);

    mentorApplicationDao.updateApproveStatus(no);

    memberDao.update(applicant);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../adminpage/mentorapplicationlist");
    return mv;
  }

  @PostMapping("/mentorapplication/reject")
  public ModelAndView reject(int no, String remarks) throws Exception {

    MentorApplication mentorApplication = mentorApplicationDao.findByNo(no);
    mentorApplication.setRemarks(remarks);

    mentorApplicationDao.updateRejectStatus(mentorApplication);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../adminpage/mentorapplicationlist");
    return mv;
  }
}
