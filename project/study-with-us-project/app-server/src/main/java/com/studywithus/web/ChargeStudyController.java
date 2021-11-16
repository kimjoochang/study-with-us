package com.studywithus.web;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@Controller
public class ChargeStudyController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao chargeStudyDao;

  @PostMapping("/chargestudy/add")
  public ModelAndView add(Study chargeStudy, HttpSession session) throws Exception {

    chargeStudy.setWriter((Member) session.getAttribute("loginUser"));

    chargeStudyDao.insert(chargeStudy);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/chargestudy/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "스터디위더스 : 멘토링 등록");
    mv.setViewName("chargestudy/ChargeStudyAddForm");
    return mv;
  }


  @GetMapping("/chargestudy/list")
  public ModelAndView list() throws Exception {
    Collection<Study> chargeStudyList = chargeStudyDao.findAll(1,10000000);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudyList", chargeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 멘토링 목록");
    mv.setViewName("chargestudy/ChargeStudyList");
    return mv;
  }

  @GetMapping("/chargestudy/detail")
  public ModelAndView detail(int no) throws Exception {
    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    chargeStudyDao.updateCount(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudy", chargeStudy);
    mv.addObject("pageTitle", "스터디위더스 : 상세보기");
    mv.setViewName("chargestudy/ChargeStudyDetail");
    return mv;
  }

  @GetMapping("/chargestudy/updateform")
  public ModelAndView form(int no) throws Exception {
    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudy", chargeStudy);
    mv.addObject("pageTitle", "스터디위더스 : 수정");
    mv.setViewName("chargestudy/ChargeStudyUpdateForm");
    return mv;
  }

  @PostMapping("/chargestudy/update")
  public ModelAndView update(Study chargeStudy) throws Exception {
    Study oldStudy= chargeStudyDao.findByNo(chargeStudy.getNo());
    if (oldStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    } 

    chargeStudyDao.update(chargeStudy);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + chargeStudy.getNo());
    return mv;
  }

}
