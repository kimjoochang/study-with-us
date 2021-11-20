package com.studywithus.web;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.PaymentDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Payment;

@Controller
public class PaymentController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired PaymentDao paymentDao;
  @Autowired StudyDao studyDao;

  @GetMapping("/payment/add")
  public ModelAndView add(int no, int payMethod, HttpSession session) throws Exception {
    Payment payment = new Payment();

    Member mentee = (Member) session.getAttribute("loginUser");

    studyDao.findByNo(no);

    payment.setMemberNo(mentee.getNo());
    payment.setStudy(studyDao.findByNo(no));
    payment.setPaymentMethod(0);


    paymentDao.insert(payment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../chargestudy/detail?no=" + no);
    //		mv.setViewName("community/CommunityDetail");

    return mv;
  } 
  /*
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
   */
}







