package com.studywithus.table;

public class Test {

  public static void main(String[] args) {
    String commandPrefix = "freeStudy.";
    String action = "freeStudy.insert".substring(commandPrefix.length());
    System.out.println(action);
  }

}
