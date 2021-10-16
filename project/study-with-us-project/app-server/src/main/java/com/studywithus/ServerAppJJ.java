package com.studywithus;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.RequestProcessor;
import com.studywithus.table.ChargeStudyTableJJ;
import com.studywithus.table.CommentTable;
import com.studywithus.table.ExamScheduleTable;
import com.studywithus.table.JobsScheduleTable;
import com.studywithus.table.MemberTable;
import com.studywithus.table.MentorApplicationTable;
import com.studywithus.table.PaymentTable;

public class ServerAppJJ {

  public static void main(String[] args) throws Exception {

    System.out.println("서버 실행중");
    ServerSocket serverSocket = new ServerSocket(8888);

    // RequestProcessor 가 사용할 DataProcessor 맵 준비
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<String,DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.

    dataProcessorMap.put("member.", new MemberTable());
    dataProcessorMap.put("chargeStudy.", new ChargeStudyTableJJ());
    dataProcessorMap.put("payment.", new PaymentTable());
    dataProcessorMap.put("examScheduleTable.", new ExamScheduleTable());
    dataProcessorMap.put("jobsScheduleTable.", new JobsScheduleTable());
    dataProcessorMap.put("mentorApplication.", new MentorApplicationTable());
    dataProcessorMap.put("comment.", new CommentTable());
    //    dataProcessorMap.put("mentorApplication.", new MentorApplicationTable());
    //    dataProcessorMap.put("project.", new ProjectTable());

    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트 접속");

      // 1) 새 실행 흐름 생성
      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);

      // 2) 새로 생성한 실행 흐름을 시작시킨다.
      // => run()이 호출될 것이다.
      // => 시작시킨 후 즉시 리턴한다. 
      //    즉 새로 생성한 실행 흐름이 종료될 때까지 기다리지 않는다.
      requestProcessor.start();
    }
  }
}

