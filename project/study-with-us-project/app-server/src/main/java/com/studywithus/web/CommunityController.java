package com.studywithus.web;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.CommentDao;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

@Controller
public class CommunityController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CommunityDao communityDao;
  @Autowired CommentDao commentDao;

  @GetMapping("/community/search")
  public ModelAndView search(String keyword, int categoryNo) throws Exception {

    Collection <Community> communityList = communityDao.findByKeyword(keyword, categoryNo);

    ModelAndView mv = new ModelAndView();
    mv.addObject("communityList", communityList);
    mv.addObject("categoryNo", categoryNo);
    mv.addObject("pageTitle", "스터디위더스 : 커뮤니티검색결과");
    mv.setViewName("community/CommunityList");
    return mv;
  }

  @GetMapping("/community/list")
  public ModelAndView list(int no, int pageNo) throws Exception {
    int communityStartNo = (pageNo-1) * 8; 

    Collection<Community> communityList = communityDao.findAll(no, communityStartNo);

    int totalNum = communityDao.communityCount(no);

    double calNum;

    if ((pageNo % 3.0) == 0) {
      calNum = pageNo/3.0;
    } else {
      calNum = Math.ceil(pageNo/3.0);
    }


    int startNo = (int) ((3*calNum)-2);
    int endNo = (int) ((3*calNum));
    int totalPageNo = ((int) Math.ceil(totalNum / 8) + 1);

    System.out.println("페이지 시작 번호 =>" + startNo);
    System.out.println("페이지 끝 번호 =>" + endNo);
    System.out.println("전체 페이지 번호 =>" + totalPageNo);
    int previousBtn = 0;
    int nextBtn = 0;

    if (totalPageNo == 0) {
      totalPageNo = 1;
    }

    if (totalPageNo < endNo) {
      endNo = totalPageNo;
    }

    if (startNo < 4) {
      previousBtn = 1;
    }

    if (endNo != totalPageNo) {
      System.out.println(endNo % 3);
      if ((endNo % 3) == 0) {
        nextBtn =1;
      }
    }

    System.out.println("페이지 시작 번호 =>" + startNo);
    System.out.println("페이지 끝 번호 =>" + endNo);
    System.out.println("전체 페이지 번호 =>" + totalPageNo);

    ModelAndView mv = new ModelAndView();
    mv.addObject("communityList", communityList);
    mv.addObject("categoryNo", no);
    mv.addObject("startNo", startNo);
    mv.addObject("endNo", endNo);
    mv.addObject("previousBtn", previousBtn);
    mv.addObject("nextBtn", nextBtn);
    mv.addObject("pageTitle", "스터디위더스 : 커뮤니티목록");
    mv.setViewName("community/CommunityList");
    return mv;
  }

  @GetMapping("/community/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "스터디위더스 : 커뮤니티등록");
    mv.setViewName("community/CommunityForm");
    return mv;
  }

  @PostMapping("/community/add")
  public ModelAndView add(Community community, HttpSession session) throws Exception {

    community.setWriter((Member) session.getAttribute("loginUser"));

    communityDao.insert(community);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?no=" + community.getCategory() + "&pageNo=1");
    return mv;
  }

  @GetMapping("/community/detail")
  public ModelAndView detail(int no) throws Exception {
    Community community = communityDao.findByNo(no);

    Collection<Comment> commentList = commentDao.findAll(no);

    if (community == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    communityDao.updateCount(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("comments", commentList);
    mv.addObject("community", community);
    mv.addObject("pageTitle", "스터디위더스 : 커뮤니티상세보기");
    mv.setViewName("community/CommunityDetail");
    return mv;
  }

  @GetMapping("/community/updateform")
  public ModelAndView updateForm(int no) throws Exception {
    Community community = communityDao.findByNo(no);

    if (community == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("community", community);
    mv.addObject("pageTitle", "스터디위더스 : 커뮤니티수정");
    mv.setViewName("community/CommunityUpdateForm");
    return mv;
  }

  @PostMapping("/community/update")
  public ModelAndView update(Community community) throws Exception {

    Community oldCommunity = communityDao.findByNo(community.getNo());
    if (oldCommunity == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 

    communityDao.update(community);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + community.getNo());
    return mv;
  }

  @GetMapping("/community/delete")
  public ModelAndView delete(int no) throws Exception {

    Community community = communityDao.findByNo(no);

    if (community == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    communityDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?no=" + community.getCategory() + "&pageNo=1");
    return mv;
  }

  @GetMapping("/community/interest/add")
  public ModelAndView interestAdd(int memberNo, int communityNo, HttpSession session) throws Exception {

    Community oldCommunity= communityDao.findByNo(communityNo);

    if (oldCommunity == null) {
      throw new Exception("해당 번호의 커뮤니티가 없습니다.");
    } 

    memberNo = ((Member) session.getAttribute("loginUser")).getNo();

    communityDao.insertLikes(memberNo, communityNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?no=" + communityNo);
    return mv;
  }

  @GetMapping("/community/interest/delete")
  public ModelAndView interestDelete(int memberNo, int communityNo, HttpSession session) throws Exception {

    Community oldCommunity= communityDao.findByNo(communityNo);

    if (oldCommunity == null) {
      throw new Exception("해당 번호의 커뮤니티가 없습니다.");
    } 

    memberNo = ((Member) session.getAttribute("loginUser")).getNo();

    communityDao.deleteLikes(memberNo, communityNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?no=" + communityNo);
    return mv;
  }

}







