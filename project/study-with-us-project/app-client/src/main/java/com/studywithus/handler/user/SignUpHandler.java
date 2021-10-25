package com.studywithus.handler.user;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class SignUpHandler implements Command {

  MemberDao memberDao;
  SqlSession sqlSession;

  public SignUpHandler(MemberDao memberDao, SqlSession sqlSession) {
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    Member member = new Member();

    while (true) {

      String name = Prompt.inputString("이름을 입력하세요. > ");
      String phoneNumber = Prompt.inputString("휴대폰 번호를 입력하세요.('-'를 제외한 숫자 11자) > ");
      String email = Prompt.inputString("사용할 이메일을 입력하세요. > ");
      String password = Prompt.inputString("사용할 비밀번호를 입력하세요.(특수문자 !,@,$,^ 포함 8자 이상 16자 이하) > ");
      // ?
      memberDao.findByEmail(email);

      if (email == null) {
        System.out.println("중복된 아이디가 있습니다.\n");

      } else if (!email.contains("@") || !email.contains(".com")){
        System.out.println("이메일 형식의 아이디를 입력하세요.\n");

      } else if (password.length() < 8 || password.length() > 16) {
        System.out.println("비밀번호는 8자 이상 16자 이하로 설정 가능합니다.\n");

      } else if (!password.contains("!") && !password.contains("@") && !password.contains("$") && !password.contains("^")) {
        System.out.println("비밀번호는 다음의 특수문자를 하나 이상 포함해야 합니다.(!,@,$,^)\n");

      }  else if (password.contains(email) == true) {
        System.out.println("아이디를 포함한 비밀번호는 사용하실 수 없습니다.\n");

      }  else if (password.contains(phoneNumber) == true) {
        System.out.println("휴대폰 번호를 포함한 비밀번호는 사용하실 수 없습니다.\n");

      } else if (phoneNumber.length() != 11) {
        System.out.println("올바른 형식의 휴대폰 번호를 입력하세요.\n");

      } else {
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);
        member.setPhoneNumber(phoneNumber);

        memberDao.insert(member);
        sqlSession.commit();

        System.out.println("회원가입이 완료되었습니다.\n");
      }
      return;
    }
  }

  //  private String findById(String id) {
  //    for (Member member : memberList) {
  //      if (id.equalsIgnoreCase(member.getId())) {
  //        return null;
  //      }
  //    }
  //    return id;
  //  }
}
