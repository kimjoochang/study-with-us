package com.studywithus.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import com.google.gson.Gson;
import com.studywithus.table.JsonDataTable;

public class RequestProcessor extends Thread {
  Socket socket;
  Map<String,DataProcessor> dataProcessorMap;

  public RequestProcessor(Socket socket, Map<String,DataProcessor> dataProcessorMap) throws Exception {
    this.socket = socket;
    this.dataProcessorMap = dataProcessorMap; 
  }

  @Override
  public void run() {
    try (Socket socket = this.socket;
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

      // 데이터 처리 담당자의 이름 목록 가져오기
      Set<String> dataProcessorNames = dataProcessorMap.keySet();

      String command = in.readLine();
      Request request = new Request(command, in.readLine());
      Response response = new Response();

      if (command.equalsIgnoreCase("quit")) {
        response.setStatus(Response.SUCCESS);
        response.setValue("goodbye");
        sendResult(response, out);
        return;
      } 

      // 명령어에 해당하는 데이터 처리 담당자를 찾는다.
      DataProcessor dataProcessor = null;
      for (String dataProcessorName : dataProcessorNames) {
        if (command.startsWith(dataProcessorName)) {
          dataProcessor = dataProcessorMap.get(dataProcessorName);
          break;
        }
      }

      if (dataProcessor != null) { // 명령어에 해당하는 데이터 처리 담당자가 있으면
        dataProcessor.execute(request, response);

      } else {
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령어를 처리할 수 없습니다.");
      }

      sendResult(response, out); // 클라이언트에게 실행 결과를 보낸다.

      saveData();

      System.out.println("클라이언트 접속 종료!");

    } catch (Exception e) {
      System.out.println("클라이언트 요청 처리 중 오류 발생!");
    }
  }

  private void saveData() throws Exception {
    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    for (DataProcessor dataProcessor : dataProcessors) {
      if (dataProcessor instanceof JsonDataTable) {
        ((JsonDataTable<?>)dataProcessor).save();
      }
    }
  }

  private void sendResult(Response response, PrintWriter out) throws Exception {
    // Response 객체에 보관된 실행 결과를 클라이언트에게 보낸다.
    out.println(response.status);
    if (response.getValue() != null) {
      out.println(new Gson().toJson(response.getValue()));
    } else {
      out.println();
    }
    out.flush();
  }

}