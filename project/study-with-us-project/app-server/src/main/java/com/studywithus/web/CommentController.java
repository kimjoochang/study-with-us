package com.studywithus.web;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

@Controller
public class CommentController {

	@Autowired SqlSessionFactory sqlSessionFactory;
	@Autowired CommunityDao communityDao;

	@GetMapping("/community/list")
	public ModelAndView list(int no) throws Exception {
		Collection<Community> communityList = communityDao.findAll(no);

		communityDao.updateCount(no);

		ModelAndView mv = new ModelAndView();
		mv.addObject("communityList", communityList);
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
		mv.setViewName("redirect:list");
		return mv;
	}

	@GetMapping("/community/detail")
	public ModelAndView detail(int no) throws Exception {
		Community community = communityDao.findByNo(no);

		if (community == null) {
			throw new Exception("해당 번호의 게시글이 없습니다.");
		}

		communityDao.updateCount(no);

		ModelAndView mv = new ModelAndView();
		mv.addObject("community", community);
		mv.addObject("pageTitle", "스터디위더스 : 커뮤니티상세보기");
		mv.setViewName("community/CommunityDetail");
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
		mv.addObject("community", community);
		mv.addObject("pageTitle", "스터디위더스 : 커뮤니티수정");
		mv.setViewName("community/CommunityUpdateForm");
		return mv;
	}

	/*

  @GetMapping("/board/delete")
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
	 */
}







