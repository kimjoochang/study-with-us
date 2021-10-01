package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Review;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;

public class ReviewListHandler extends AbstractStudyHandler {

  public ReviewListHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);
  }
  int sum;
  //  float average;
  String star;

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 후기 조회]\n");
    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = findByNo(no);

    List<Review> reviewList = chargeStudy.getReviewList();

    if (reviewList == null) {
      System.out.println("아직 등록된 후기가 없습니다.");
      return;
    }

    for (Review review : reviewList) {
      sum += review.getScore();
      System.out.printf("[아이디 = %s, 후기 = %s, 평점 = %d, 등록일 = %s]\n",
          review.getId(),
          review.getReview(),
          review.getScore(),
          review.getRegisteredDate());
    }

    double average = Math.floor((double)(sum / reviewList.size()) * 100 / 100.0);

    System.out.println();
    System.out.println("합: " + sum);
    System.out.println("갯수: " + reviewList.size());
    System.out.println("평점: " + average + "/" + 5);
    System.out.println(getStar(average));
  }

  private String getStar(double average) {
    if (average < 1) {
      star = "☆☆☆☆☆";
    } else if (average < 2) {
      star = "★☆☆☆☆";

    } else if (average < 3) {
      star = "★★☆☆☆";

    } else if (average < 4) {
      star = "★★★☆☆";

    } else if (average < 5) {
      star = "★★★★☆";

    } else {
      star = "★★★★★";
    }
    return star;
  }
}