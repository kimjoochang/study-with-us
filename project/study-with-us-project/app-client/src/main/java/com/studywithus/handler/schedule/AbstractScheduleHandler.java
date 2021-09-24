package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;

public abstract class AbstractScheduleHandler implements Command {

  protected List<Schedule> scheduleList;

  public AbstractScheduleHandler(List<Schedule> scheduleList) {
    this.scheduleList = scheduleList;
  }

  // 일정 게시글 번호 검색
  protected Schedule findByNo(int no) {
    for (Schedule schedule: scheduleList) {
      if (schedule.getNo() == no) {
        return schedule;
      }
    }
    return null;
  }

  // 일정 게시글 제목 검색
  //  protected Schedule findByTitle(String title) {
  //    for (Schedule schedule: scheduleList) {
  //      if (schedule.getTitle() == title) {
  //        return schedule;
  //      }
  //    }
  //    return null;
  //  }
}
