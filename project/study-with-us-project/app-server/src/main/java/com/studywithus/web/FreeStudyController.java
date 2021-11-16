package com.studywithus.web;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@Controller
public class FreeStudyController {

  @Autowired StudyDao freeStudyDao;

  @GetMapping("/freestudy/list")
  public ModelAndView list() throws Exception {
    Collection<Study> freeStudyList = freeStudyDao.findAll(0,0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudyList", freeStudyList);
    mv.addObject("pageTitle", "스터디위더스 : 스터디목록");
    mv.setViewName("freestudy/FreeStudyList");
    return mv;
  }
}
