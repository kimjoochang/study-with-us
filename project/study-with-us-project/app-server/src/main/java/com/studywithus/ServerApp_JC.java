package com.studywithus;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.RequestProcessor;
import com.studywithus.table.ChargeStudyTable;
import com.studywithus.table.ExamScheduleTable;
import com.studywithus.table.JobsScheduleTable;
import com.studywithus.table.MemberTable2;
import com.studywithus.table.MentorApplicationTable;
import com.studywithus.table.PaymentTable;
import com.studywithus.table.ReviewTable;

public class ServerApp_JC {

  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행중");
    ServerSocket serverSocket = new ServerSocket(8888);

    // RequestProcessor 가 사용할 DataProcessor 맵 준비
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<String,DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.
    //    dataProcessorMap.put("board.", new BoardTable());
    dataProcessorMap.put("member.", new MemberTable2());
    dataProcessorMap.put("chargeStudy.", new ChargeStudyTable());
    dataProcessorMap.put("mentorApplication.", new MentorApplicationTable());
    dataProcessorMap.put("payment.", new PaymentTable());
    dataProcessorMap.put("review.", new ReviewTable());
    dataProcessorMap.put("examSchedule.", new ExamScheduleTable());
    dataProcessorMap.put("jobsSchedule.", new JobsScheduleTable());
    //    dataProcessorMap.put("project.", new ProjectTable());

    while(true) {
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트가 접속했음");

      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);

      requestProcessor.start();
    }

  }
}

