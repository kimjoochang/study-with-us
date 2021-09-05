package com.studywithus.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScan = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static Date inputDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static void close() {
    keyboardScan.close();
  }
}
