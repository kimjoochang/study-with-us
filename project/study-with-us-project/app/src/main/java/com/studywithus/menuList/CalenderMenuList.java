package com.studywithus.menuList;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.domain.ExamCalender;
import com.studywithus.domain.JobsCalender;
import com.studywithus.handler.ExamCalendarAddHandler;
import com.studywithus.handler.ExamCalendarDeleteHandler;
import com.studywithus.handler.ExamCalendarDetailHandler;
import com.studywithus.handler.ExamCalendarUpdateHandler;
import com.studywithus.handler.JobsCalenderAddHandler;
import com.studywithus.handler.JobsCalenderDeleteHandler;
import com.studywithus.handler.JobsCalenderDetailHandler;
import com.studywithus.handler.JobsCalenderUpdateHandler;
import com.studywithus.util.Prompt;

public class CalenderMenuList {

  private List<ExamCalender> examCalenderList = new ArrayList<>();
  private List<JobsCalender> jobsCalenderList = new ArrayList<>();

  ExamCalendarAddHandler examCalenderAddHandler = new ExamCalendarAddHandler(examCalenderList);
  ExamCalendarDetailHandler examCalenderDetailHandler = new ExamCalendarDetailHandler(examCalenderList);
  ExamCalendarUpdateHandler examCalenderUpdateHandler = new ExamCalendarUpdateHandler(examCalenderList);
  ExamCalendarDeleteHandler examCalenderDeleteHandler = new ExamCalendarDeleteHandler(examCalenderList);

  JobsCalenderAddHandler jobsCalenderAddHandler = new JobsCalenderAddHandler(jobsCalenderList);
  JobsCalenderDetailHandler jobsCalenderDetailHandler = new JobsCalenderDetailHandler(jobsCalenderList);
  JobsCalenderUpdateHandler jobsCalenderUpdateHandler = new JobsCalenderUpdateHandler(jobsCalenderList);
  JobsCalenderDeleteHandler jobsCalenderDeleteHandler = new JobsCalenderDeleteHandler(jobsCalenderList);

  int input;

  public void calenderMenuList() {

    while (true) {
      System.out.println("[관리자 / 캘린더 관리]\n");
      System.out.println("1. 이달의 시험일정 관리");
      System.out.println("2. 이달의 채용공고 관리");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      // 1. 이달의 시험일정 관리
      if (input == 1) {
        examCalenderMenuList();

        // 2. 이달의 채용공고 관리
      } else if (input == 2) {
        jobsCalenderMenuList();

        // 0. 이전
      } else if (input == 0) {
        return;
      }
    }
  }

  // 이달의 시험일정 메뉴
  public void examCalenderMenuList() {

    while (true) {
      System.out.println("[관리자 / 캘린더 관리 / 이달의 시험일정 관리]");
      System.out.println("1. 생성");
      System.out.println("2. 상세보기");
      System.out.println("3. 변경");
      System.out.println("4. 삭제");
      System.out.println("0. 이전");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      // 1. 생성
      if (input == 1) {
        examCalenderAddHandler.execute();

        // 2. 상세보기
      } else if (input == 2) {
        examCalenderDetailHandler.execute();

        // 3. 변경
      } else if (input == 3) {
        examCalenderUpdateHandler.execute();

        // 4. 삭제
      } else if (input == 4) {
        examCalenderDeleteHandler.execute();

        // 0. 이전
      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 이달의 채용공고 메뉴
  public void jobsCalenderMenuList() {

    while (true) {
      System.out.println("[관리자 / 캘린더 관리 / 이달의 채용공고 관리]");
      System.out.println("1. 생성");
      System.out.println("2. 상세보기");
      System.out.println("3. 수정");
      System.out.println("4. 삭제");
      System.out.println("0. 이전");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      // 1. 생성
      if (input == 1) {
        jobsCalenderAddHandler.execute();

        // 2. 상세보기
      } else if (input == 2) {
        jobsCalenderDetailHandler.execute();

        // 3. 변경
      } else if (input == 3) {
        jobsCalenderUpdateHandler.execute();

        // 4. 삭제
      } else if (input == 4) {
        jobsCalenderDeleteHandler.execute();

        // 0. 이전
      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }
}