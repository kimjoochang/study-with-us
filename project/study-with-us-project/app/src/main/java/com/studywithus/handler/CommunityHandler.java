package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.Board;
import com.studywithus.util.Prompt;

public class CommunityHandler {

  static final int MAX_LENGTH = 5;

  Board[] communities = new Board[MAX_LENGTH];
  int size = 0;

  // 커뮤니티 생성
  public void add() {
    System.out.println("[새 커뮤니티]");

    Board community = new Board();

    community.no = Prompt.inputInt("번호? ");
    community.title = Prompt.inputString("제목? ");
    community.content = Prompt.inputString("내용? ");
    community.writer = Prompt.inputString("작성자? ");
    community.registeredDate = new Date(System.currentTimeMillis());

    this.communities[this.size++] = community;
  }

  // 커뮤니티 조회
  public void list() {
    System.out.println("[커뮤니티 조회]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", this.communities[i].no, this.communities[i].title,
          this.communities[i].writer, this.communities[i].registeredDate, this.communities[i].viewCount,
          this.communities[i].like);
    }
  }

  // 커뮤니티 상세보기
  public void detail() {
    System.out.println("[커뮤니티 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Board community = findByNo(no);

    if (community == null) {
      System.out.println("해당 번호의 커뮤니티 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", community.title);
    System.out.printf("내용: %s\n", community.content);
    System.out.printf("작성자: %s\n", community.writer);
    System.out.printf("등록일: %s\n", community.registeredDate);
    System.out.printf("조회수: %d\n", ++community.viewCount);
  }

  // 커뮤니티 변경
  public void update() {
    System.out.println("[커뮤니티 변경]");
    int no = Prompt.inputInt("번호? ");

    Board community = findByNo(no);

    if (community == null) {
      System.out.println("해당 번호의 커뮤니티 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정된 제목: ", community.title));
    String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", community.content));

    String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("커뮤니티 게시글 변경을 취소하였습니다.");
      return;
    }

    community.title = title;
    community.content = content;
    System.out.println("커뮤니티 게시글을 변경하였습니다.");
  }

  // 커뮤니티 삭제
  public void delete() {
    System.out.println("[커뮤니티 게시글 삭제]");
    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 커뮤니티 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("커뮤니티 게시글 삭제를 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.communities[i - 1] = this.communities[i];
    }
    this.communities[--this.size] = null;

    System.out.println("커뮤니티 게시글을 삭제하였습니다.");
  }

  // 커뮤니티 게시글 번호 조회
  private Board findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.communities[i].no == no) {
        return this.communities[i];
      }
    }
    return null;
  }

  // 커뮤니티 게시글 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.communities[i].no == no) {
        return i;
      }
    }
    return -1;
  }
}