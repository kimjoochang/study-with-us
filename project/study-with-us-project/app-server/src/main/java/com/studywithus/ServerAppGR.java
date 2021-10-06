package com.studywithus;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.RequestProcessor;
import com.studywithus.table.CommunityTable;
import com.studywithus.table.JsonDataTable;
import com.studywithus.table.MemberTableGR;
import com.studywithus.table.StudyTable;

public class ServerAppGR {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행중");
    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트가 접속했음");

    // RequestProcessor 가 사용할 DataProcessor 맵 준비
    HashMap<String, DataProcessor> dataProcessorMap = new HashMap<String, DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.
    dataProcessorMap.put("member.", new MemberTableGR());
    // [삭제]
    // dataProcessorMap.put("freeStudy.", new FreeStudyTable());
    dataProcessorMap.put("freeStudy.", new StudyTable("freestudy.json", "freeStudy."));
    dataProcessorMap.put("chargeStudy.", new StudyTable("charge.json", "charge."));
    dataProcessorMap.put("communityInfo.",
        new CommunityTable("communityInfo.json", "communityInfo"));
    dataProcessorMap.put("communityQa.", new CommunityTable("communityQa.json", "communityQa"));
    dataProcessorMap.put("communityTalk.",
        new CommunityTable("communityTalk.json", "communityTalk"));

    RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
    requestProcessor.service();
    requestProcessor.close();

    // => 데이터를 파일에 저장한다.
    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    for (DataProcessor dataProcessor : dataProcessors) {
      if (dataProcessor instanceof JsonDataTable) {
        // 만약 데이터 처리 담당자가 JsonDataTable의 자손이라면,
        ((JsonDataTable<?>) dataProcessor).save();
      }
    }

    System.out.println("서버 종료");
    serverSocket.close();
  }
}
