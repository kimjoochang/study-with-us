package com.studywithus.handler.study;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Review;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ReviewAddHandler extends AbstractStudyHandler {

  public ReviewAddHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 후기작성]\n");
    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = findByNo(no);

    Review review = new Review();

    review.setId(AuthLogInHandler.getLoginUser().getId());
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

    chargeStudy.getReviewList().add(review);
  }
}