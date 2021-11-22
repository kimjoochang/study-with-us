package com.studywithus.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

@Controller
public class UserController {

  @Autowired MemberDao memberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @PostMapping("/user/login")
  public ModelAndView login(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {

    Cookie cookie = null;

    if (saveEmail != null) {

      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      // cookie.setPath(sc.getContextPath() + "/app/user"); // 예) http://localhost:8080/pms/app/auth

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 유효기간을 0으로 설정하면 웹브라우저가 받는 즉시 무효한 쿠기가 된다.
    }

    response.addCookie(cookie);

    ModelAndView mv = new ModelAndView();

    Member loginUser = memberDao.findMemberByEmailPassword(email, password);

    if (loginUser != null) {
      session.setAttribute("loginUser", loginUser);

    } else {
      //? mv.addObject("refresh", "2;url=loginForm");
      mv.addObject("pageTitle", "스터디위더스 : 로그인오류");
    }
    mv.setViewName("redirect:../index");
    return mv;
  }

  @GetMapping("/user/logout")
  public ModelAndView logout(HttpSession session) throws Exception {

    session.invalidate();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../index");
    return mv;
  }

  @PostMapping("/user/join")
  public ModelAndView join(Member member) throws Exception {

    memberDao.insert(member);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../index");
    return mv;
  }

  @GetMapping("/user/memberdelete") 
  public ModelAndView memberDelete(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    //  if(loginUser.getNo() == no) {
    memberDao.delete(member.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../index");
    return mv;
  }

  @GetMapping("/user/findemail") 
  public ModelAndView findEmail(String name, String phoneNumber) throws Exception {

    Member member =  memberDao.findMemberByNamePhoneNumber(name, phoneNumber);

    ModelAndView mv = new ModelAndView();

    if (member != null) {
      String email = member.getEmail();

      int index = email.length()-4; // 
      String tempEmail = email.substring(0,4); //0~3번 문자까지 보여주고

      for(int i = 0; i < index; i++) {
        tempEmail += "*";} // 나머지 *처리

      email = tempEmail; // 별표처리된 이메일 대입

      mv.addObject("email", email);

    } else {
      mv.addObject("email", null);

    }

    mv.setViewName("user/FindEmailForm");
    return mv;
  }

  @GetMapping("/user/myinfo") 
  public ModelAndView myInfo(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName("../jsp/MyPage_info"); 
    return mv;
  }

  @RequestMapping(value = "/user/idCheck", method = RequestMethod.GET)
  @ResponseBody
  public int idCheck(String userId) throws Exception {

    int data = memberDao.emailCheck(userId);

    return data;
  }

  @PostMapping("/mypage/updateNickname")
  public ModelAndView updateNickname(String nickname, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    System.out.println(nickname);

    Member oldMember= (Member) session.getAttribute("loginUser");

    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    oldMember.setNickname(nickname);

    memberDao.update(oldMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../user/myinfo"); 
    return mv;
  }

  @PostMapping("/mypage/updatePassword")
  public ModelAndView updatePwd(String password, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    Member oldMember= memberDao.findByNo(member.getNo());
    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    oldMember.setPassword(password);

    memberDao.update(oldMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:user/info"); 
    return mv;
  }

  @PostMapping("/mypage/updateNumber")
  public ModelAndView update(String phoneNumber, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    Member oldMember= memberDao.findByNo(member.getNo());
    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    oldMember.setPhoneNumber(phoneNumber);

    memberDao.update(oldMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:user/info"); 
    return mv;
  }
}
