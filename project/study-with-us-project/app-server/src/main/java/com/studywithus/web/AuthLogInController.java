package com.studywithus.web;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

@Controller
public class AuthLogInController {

  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("/user/loginForm")
  public ModelAndView loginForm() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "스터디위더스 : 로그인");
    mv.setViewName("LoginForm");
    return mv;
  }

  @PostMapping("/auth/login")
  public ModelAndView login(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {

    Cookie cookie = null;

    if (saveEmail != null) {

      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      //cookie.setPath(sc.getContextPath() + "/app/user"); // 예) http://localhost:8080/pms/app/auth

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 유효기간을 0으로 설정하면 웹브라우저가 받는 즉시 무효한 쿠기가 된다.
    }

    response.addCookie(cookie);

    ModelAndView mv = new ModelAndView();

    Member loginUser = memberDao.findMemberByEmailPassword(email, password);

    if (loginUser != null) {
      session.setAttribute("loginUser", loginUser);
      mv.setViewName("redirect:../");

    } else {
      //? mv.addObject("refresh", "2;url=loginForm");
      mv.addObject("pageTitle", "스터디위더스 : 로그인오류");
      mv.addObject("contentUrl", "user/LoginFail.jsp");
      // mv.setViewName("template1");
    }
    return mv;
  }

  @GetMapping("/auth/logout")
  public ModelAndView logout(HttpSession session) throws Exception {

    session.invalidate();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:loginForm");
    return mv;
  }
}

