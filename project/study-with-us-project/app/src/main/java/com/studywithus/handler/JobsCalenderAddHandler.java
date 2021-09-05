package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalender;
import com.studywithus.util.Prompt;

public class JobsCalenderAddHandler extends AbstractJobsCalenderHandler {

  public JobsCalenderAddHandler(List<JobsCalender> jobsCalenderList) {
    super(jobsCalenderList);

    JobsCalender testUser = new JobsCalender();
    testUser.setNo(1);
    testUser.setTitle("[브런치] web backend 경력 개발자 모집");
    testUser.setContent("업무내용\n"
        + "- 브런치 서비스 개발 및 운영\n"
        + "- 프로젝트 단위 신규 업무 수행\n"
        + "- 지속적인 개선을 통한 서비스 안정성 및 효율성 강화");
    testUser.setStartDate("2021-06-28");
    testUser.setEndDate("2021-11-23");

    jobsCalenderList.add(testUser);
  }

  // 이달의 채용공고 생성
  public void execute() {
    System.out.println("[이달의 채용공고 / 등록]\n");

    JobsCalender jobsCalender = new JobsCalender();

    jobsCalender.setNo(Prompt.inputInt("번호? "));
    jobsCalender.setTitle(Prompt.inputString("제목? "));
    jobsCalender.setContent(Prompt.inputString("내용? "));
    jobsCalender.setStartDate(Prompt.inputString("시작일? "));
    jobsCalender.setEndDate(Prompt.inputString("종료일? "));

    jobsCalenderList.add(jobsCalender);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.\n");
  }
}
