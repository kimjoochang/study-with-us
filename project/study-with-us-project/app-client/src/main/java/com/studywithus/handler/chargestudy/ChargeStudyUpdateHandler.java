package com.studywithus.handler.chargestudy;

import java.util.HashMap;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudyUpdateHandler implements Command {

  ChargeStudyDao chargeStudyDao;

  public ChargeStudyUpdateHandler(ChargeStudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;	
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 수정]");
    int no = (int) request.getAttribute("chargeNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    Study chargeStudy = chargeStudyDao.findByNo(no);

    String title = Prompt.inputString(String.format("[%s] 수정된 스터디 제목: ", chargeStudy.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", chargeStudy.getContent()));

    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) "); 

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("유료 스터디 수정을 취소하였습니다.\n");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        chargeStudy.setTitle(title);
        chargeStudy.setContent(content);

        chargeStudyDao.update(chargeStudy);

        System.out.println("유료 스터디를 수정하였습니다.\n");
        return;

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
