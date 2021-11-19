package com.studywithus.web.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

@Controller
public class MyInfoController {

  @Autowired MemberDao memberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

@PostMapping("/mypage/info/add")
  public ModelAndView add(Member member, Part photoFile) throws Exception {
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });
    }

    memberDao.insert(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=list");
    mv.addObject("pageTitle", "회원등록");
    mv.addObject("contentUrl", "member/MemberAdd.jsp");
    mv.setViewName("template1");
    return mv;
  }

   @PostMapping("/mypage/info/update")
  public ModelAndView update(Member member, Part photoFile) throws Exception {

    Member oldMember = memberDao.findByNo(member.getNo());
    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    member.setPhoto(oldMember.getPhoto());
    member.setRegisteredDate(oldMember.getRegisteredDate());

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      member.setPhoto(filename);
    }

    memberDao.update(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

/* 기존 MyInfoHandler 코드

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[나의 정보]\n");

    Member member = AuthLogInHandler.getLoginUser();

    if (member == null) {
      System.out.println("로그인 후 이용 바랍니다.");
      return;
    }

    switch (member.getUserAccessLevel()) {

      case 0x02:
        System.out.println("등급: 회원");
        break;

      case 0x04:
        System.out.println("등급: 회원");
        break;

      case 0x08:
        System.out.println("등급: 회원");
        break;

      case 0x40:
        System.out.println("등급: 회원");
        break;

      case 0x10:
        System.out.println("등급: 멘토");
        break;

      case 0x20:
        System.out.println("등급: 관리자");
        break;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("휴대폰 번호: %s\n", member.getPhoneNumber());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }
  */
}
