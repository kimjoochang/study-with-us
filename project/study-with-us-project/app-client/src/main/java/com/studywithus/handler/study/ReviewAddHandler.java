package com.studywithus.handler.study;

import java.sql.Date;
import com.studywithus.domain.Review;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ReviewAddHandler implements Command {

  RequestAgent requestAgent;

  public ReviewAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 후기작성]\n");
    int no = (int) request.getAttribute("chargeNo");

    Review review = new Review();

    review.setStudyNo(no);
    review.setWriterEmail(AuthLogInHandler.getLoginUser().getEmail());
    review.setReview(Prompt.inputString("후기를 작성해주세요. > "));
    while (true) {
      review.setScore(Prompt.inputInt("평점을 입력해주세요. (0 ~ 5점) > "));

      if (review.getScore() < 0 || review.getScore() > 6 ) {
        System.out.println("점수를 다시 입력해주세요.");
        continue;

      } else {
        break;
      }
    }
    review.setRegisteredDate(new Date(System.currentTimeMillis()));

    requestAgent.request("review.insert", review);

    System.out.println();
    System.out.println("후기 등록이 완료되었습니다.\n");
  }
}