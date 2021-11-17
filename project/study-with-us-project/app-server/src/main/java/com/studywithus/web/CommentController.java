package com.studywithus.web;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Member;

@Controller
public class CommentController {

	@Autowired SqlSessionFactory sqlSessionFactory;
	@Autowired CommentDao commentDao;

	@PostMapping("/community/comment/add")
	public ModelAndView add(Comment comment, HttpSession session) throws Exception {

		comment.setWriter((Member) session.getAttribute("loginUser"));

		commentDao.insert(comment);
		sqlSessionFactory.openSession().commit();

		ModelAndView mv = new ModelAndView();
		//mv.setViewName("community/CommnityDetail");
		return mv;
	} 


	@GetMapping("/comment/delete")
	public ModelAndView delete(int no) throws Exception {

		Comment comment = commentDao.findByNo(no);
		if (comment == null) {
			throw new Exception("해당 번호의 댓글이 없습니다.");
		}

		commentDao.delete(no);
		sqlSessionFactory.openSession().commit();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:list");
		return mv;
	}
	/*
	 */
}







