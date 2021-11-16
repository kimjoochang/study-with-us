package com.studywithus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{
  @GetMapping("/index")
  public ModelAndView home() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "메인화면");
    mv.addObject("contentUrl", "index.jsp");
    return mv;
  }
}