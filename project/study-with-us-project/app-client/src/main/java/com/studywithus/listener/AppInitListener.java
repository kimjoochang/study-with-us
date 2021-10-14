package com.studywithus.listener;

import java.util.Map;
import com.studywithus.context.ApplicationContextListener;

// 애플리케이션이 시작하거나 종료할 때 보고를 받는 객체
// => ApplicationContextListener 규칙에 따라 클래스를 정의한다.
// 
public class AppInitListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String,Object> params) {
    System.out.println();
    System.out.println("|         스터디위더스         |");
    System.out.println("|          STUDYWITHUS         |");
    System.out.println("     ￣￣￣￣∨￣￣￣￣￣￣￣   ");
    System.out.println("　　　      ∧_,,∧");
    System.out.println("　　　     (`･ω･´)");
    System.out.println("　　　     Ｕ θ Ｕ");
    System.out.println("　     ／￣￣｜￣￣＼");
    System.out.println("　     |二二二二二二二|");
    System.out.println("      ｜　　　　　　 ｜");
    System.out.println("    찰칵       찰칵   찰");
    System.out.println("        ∧∧└ 　   ∧∧   칵");
    System.out.println("    　(　　)】 (　　)】");
    System.out.println("    　/　/┘　  /　/┘");
    System.out.println("    ノ￣ヽ　  ノ￣ヽ  Are U ready to STUDY ?");

  }
}






