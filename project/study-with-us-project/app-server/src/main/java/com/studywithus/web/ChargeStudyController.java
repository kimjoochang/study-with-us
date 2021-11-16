package com.studywithus.web;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@Controller
public class ChargeStudyController {

  @Autowired StudyDao chargeStudyDao;

  @GetMapping("/chargestudy/list")
  public ModelAndView list() throws Exception {
    Collection<Study> chargeStudyList = chargeStudyDao.findAll(1,10000000);

    ModelAndView mv = new ModelAndView();
    mv.addObject("chargeStudyList", chargeStudyList);
    mv.addObject("pageTitle", "멘토링 목록");
    mv.setViewName("chargestudy/ChargeStudyList");
    return mv;
  }
}
