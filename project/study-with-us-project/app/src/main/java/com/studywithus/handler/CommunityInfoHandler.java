package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityInfoHandler {

  static final int MAX_LENGTH = 5;

  Community[] communities = new Community[MAX_LENGTH];
  int size = 0;

  // 정보 게시글 생성
  public void add() {
    System.out.println("[새 정보 게시글]");

    Community community = new Community();

    community.setNo(Prompt.inputInt("번호? "));
    community.setTitle(Prompt.inputString("제목? "));
    community.setContent(Prompt.inputString("내용? "));
    community.setWriter(Prompt.inputString("작성자? "));
    community.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.communities[this.size++] = community;
  }

  // 정보 게시글 조회
  public void list() {
    System.out.println("[정보 게시글 조회]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", this.communities[i].getNo(), this.communities[i].getTitle(),
          this.communities[i].getWriter(), this.communities[i].getRegisteredDate(), this.communities[i].getViewCount(),
          this.communities[i].getLike());
    }
  }

  // 정보 게시글 상세보기
  public void detail() {
    System.out.println("[정보 게시글 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Community community = findByNo(no);

    if (community == null) {
      System.out.println("해당 번호의 정보 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", community.getTitle());
    System.out.printf("내용: %s\n", community.getContent());
    System.out.printf("작성자: %s\n", community.getWriter());
    System.out.printf("등록일: %s\n", community.getRegisteredDate());

    community.setViewCount(community.getViewCount() + 1);
    System.out.printf("조회수: %d\n", community.getViewCount());
  }

  // 정보 게시글 수정
  public void update() {
    System.out.println("[정보 게시글 수정]");
    int no = Prompt.inputInt("번호? ");

    Community community = findByNo(no);

    if (community == null) {
      System.out.println("해당 번호의 정보 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", community.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", community.getContent()));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("정보 게시글 수정을 취소하였습니다.");
      return;
    }

    community.setTitle(title);
    community.setContent(content);
    System.out.println("정보 게시글을 수정하였습니다.");
  }

  // 정보 게시글 삭제
  public void delete() {
    System.out.println("[정보 게시글 삭제]");
    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 정보 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("정보 게시글 삭제를 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.communities[i - 1] = this.communities[i];
    }
    this.communities[--this.size] = null;

    System.out.println("정보 게시글을 삭제하였습니다.");
  }

  // 정보 게시글 번호 조회
  private Community findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.communities[i].getNo() == no) {
        return this.communities[i];
      }
    }
    return null;
  }

  // 정보 게시글 조회용
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.communities[i].getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}