package com.studywithus.handler.chargestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.ReviewDao;
import com.studywithus.domain.Review;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ReviewAddHandler implements Command {

  ReviewDao reviewDao;
  SqlSession sqlSession;

  public ReviewAddHandler(ReviewDao reviewDao, SqlSession sqlSession) {
    this.reviewDao = reviewDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 후기작성]\n");

    int no = (int) request.getAttribute("chargeNo");

    Review review = new Review();

    review.setStudyNo(no);
    review.setWriter(AuthLogInHandler.getLoginUser());
    review.setTitle(Prompt.inputString("제목을 작성해주세요. > "));
    review.setContent(Prompt.inputString("후기를 작성해주세요. > "));
    while (true) {
      review.setScore(Prompt.inputInt("평점을 입력해주세요. (0 ~ 5점) > "));

      if (review.getScore() < 0 || review.getScore() > 6 ) {
        System.out.println("점수를 다시 입력해주세요.");
        continue;

      } else {
        break;
      }
    }

    reviewDao.insert(review);
    sqlSession.commit();

    System.out.println();
    System.out.println("후기 등록이 완료되었습니다.\n");
  }
}