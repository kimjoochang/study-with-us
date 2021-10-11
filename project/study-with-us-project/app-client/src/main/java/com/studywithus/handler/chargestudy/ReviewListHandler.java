package com.studywithus.handler.chargestudy;

import java.text.DecimalFormat;
import java.util.Collection;
import com.studywithus.dao.ReviewDao;
import com.studywithus.domain.Review;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class ReviewListHandler implements Command {

  ReviewDao reviewDao;

  public ReviewListHandler(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  String star;

  @Override
  public void execute(CommandRequest request) throws Exception {

    double sum = 0;
    int count = 0;

    System.out.println("[유료 스터디 / 후기 조회]\n");
    int no = (int) request.getAttribute("chargeNo");

    Collection<Review> reviewList = reviewDao.findAll();

    if (reviewList.isEmpty()) {
      System.out.println("아직 등록된 후기가 없습니다.");
      return;
    }

    for (Review review : reviewList) {
      if (review.getStudyNo() == no) {
        sum += review.getScore();
        count ++;
        System.out.printf("[아이디 = %s, 후기 = %s, 평점 = %d, 등록일 = %s]\n",
            review.getWriterEmail(),
            review.getReview(),
            (int)review.getScore(),
            review.getRegisteredDate());
      }
    }

    DecimalFormat form = new DecimalFormat("#.#");

    double average = sum / count;
    System.out.println(average);
    // float average = Math.round((sum / reviewList.size()) * 100 / 100.0);

    System.out.println();
    System.out.println("평점: " + form.format(sum) + "/" + 5);
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

/*
  ChargeStudyDao chargeStudyDao;

  public ReviewListHandler(ChargeStudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  String star;

  @Override
  public void execute(CommandRequest request) throws Exception {
    double sum= 0;
    int count = 0;

    System.out.println("[유료 스터디 / 후기 조회]\n");
    int no = (int) request.getAttribute("chargeNo");

    Collection<Study> reviewList = chargeStudyDao.findAll();

    if (reviewList.isEmpty()) {
      System.out.println("아직 등록된 후기가 없습니다.");
      return;
    }

    for (Review review : reviewList) {
      if (review.getStudyNo() == no) {
        sum += review.getScore();
        count ++;
        System.out.printf("[아이디 = %s, 후기 = %s, 평점 = %d, 등록일 = %s]\n",
            review.getWriterEmail(),
            review.getReview(),
            (int)review.getScore(),
            review.getRegisteredDate());
      }
    }
    DecimalFormat form = new DecimalFormat("#.#");

    double average = sum / count;
    System.out.println(average);
    // float average = Math.round((sum / reviewList.size()) * 100 / 100.0);

    System.out.println();
    System.out.println("평점: " + form.format(sum) + "/" + 5);
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
 */
