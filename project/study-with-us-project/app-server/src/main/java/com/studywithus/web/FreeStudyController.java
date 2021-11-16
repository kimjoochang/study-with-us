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
public class FreeStudyController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao freeStudyDao;

  @GetMapping("/freestudy/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "스터디위더스 : 스터디등록");
    mv.addObject("contentUrl", "freestudy/FreeStudyForm.jsp");
    // mv.setViewName("freestudy/FreeStudyForm");
    return mv;
  }

  @PostMapping("/freestudy/add")
  public ModelAndView add(Study freeStudy, HttpSession session) throws Exception {

    freeStudy.setWriter((Member) session.getAttribute("loginUser"));

    freeStudyDao.insert(freeStudy);
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
  public ModelAndView detail(int no) throws Exception {
    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    freeStudyDao.updateCount(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("freeStudy", freeStudy);
    mv.addObject("pageTitle", "스터디위더스 : 스터디상세");
    mv.addObject("contentUrl", "freestudy/FreeStudyDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/freestudy/update")
  public ModelAndView update(Board board) throws Exception {

    Board oldBoard = boardDao.findByNo(board.getNo());
    if (oldBoard == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 

    boardDao.update(board);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/freestudy/delete")
  public ModelAndView delete(int no) throws Exception {

    Board board = boardDao.findByNo(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    boardDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

}
