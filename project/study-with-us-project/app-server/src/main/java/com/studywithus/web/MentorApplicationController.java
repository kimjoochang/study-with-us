package com.studywithus.web;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplication;

@Controller
public class MentorApplicationController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MentorApplicationDao mentorApplicationDao;

  @GetMapping("/adminpage/mentorapplicationlist")
  public ModelAndView list() throws Exception {
    List<MentorApplication> mentorApplicantList = mentorApplicationDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("mentorApplicantList", mentorApplicantList);
    mv.addObject("pageTitle", "스터디위더스 : 멘토신청서 조회");
    mv.setViewName("../AdminPage_member");
    return mv;
  }
}
