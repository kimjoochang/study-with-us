package com.studywithus.web;

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
import com.studywithus.domain.Member;

@Controller
public class CommentController {

	@Autowired SqlSessionFactory sqlSessionFactory;
	@Autowired CommunityDao communityDao;
	@Autowired CommentDao commentDao;

	@PostMapping("/community/comment/add")
	public ModelAndView add(int communityNo, String content, HttpSession session) throws Exception {
		Comment comment = new Comment();
		comment.setWriter((Member) session.getAttribute("loginUser"));
		comment.setCommunityNo(communityNo);
		comment.setContent(content);

		commentDao.insert(comment);
		sqlSessionFactory.openSession().commit();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:../detail?no=" + communityNo);
		return mv;
	} 

	@GetMapping("/community/comment/delete")
	public ModelAndView delete(int commentNo, HttpSession session) throws Exception {

		Comment comment = commentDao.findByNo(commentNo);

		if (comment == null) {
			throw new Exception("해당 번호의 댓글이 없습니다.");
		}

		commentDao.delete(commentNo);
		sqlSessionFactory.openSession().commit();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:../detail?no=" + comment.getCommunityNo());
		return mv;
	}
}







