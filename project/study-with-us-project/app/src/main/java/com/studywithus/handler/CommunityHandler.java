package main.java.com.studywithus.handler;

import java.sql.Date;
<<<<<<< HEAD
import main.java.com.studywithus.domain.Board;
import main.java.com.studywithus.util.Prompt;
=======
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3

public class CommunityHandler {

  static final int MAX_LENGTH = 5;

<<<<<<< HEAD
  Board[] communities = new Board[MAX_LENGTH];
=======
  Community[] communities = new Community[MAX_LENGTH];
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
  int size = 0;

  // 커뮤니티 생성
  public void add() {
<<<<<<< HEAD
    System.out.println("[새 커뮤니티]");

    Board community = new Board();
=======
    System.out.println("[새 커뮤니티 게시글]");

    Community community = new Community();
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3

    community.no = Prompt.inputInt("번호? ");
    community.title = Prompt.inputString("제목? ");
    community.content = Prompt.inputString("내용? ");
    community.writer = Prompt.inputString("작성자? ");
    community.registeredDate = new Date(System.currentTimeMillis());

    this.communities[this.size++] = community;
  }

<<<<<<< HEAD
  // 커뮤니티 목록
=======
  // 커뮤니티 조회
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
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

<<<<<<< HEAD
    Board community = findByNo(no);
=======
    Community community = findByNo(no);
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3

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

<<<<<<< HEAD
  // 커뮤니티 변경
  public void update() {
    System.out.println("[커뮤니티 변경]");
    int no = Prompt.inputInt("번호? ");

    Board community = findByNo(no);
=======
  // 커뮤니티 게시글 수정
  public void update() {
    System.out.println("[커뮤니티 수정]");
    int no = Prompt.inputInt("번호? ");

    Community community = findByNo(no);
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3

    if (community == null) {
      System.out.println("해당 번호의 커뮤니티 게시글이 없습니다.");
      return;
    }

<<<<<<< HEAD
    String title = Prompt.inputString(String.format("[%s] 수정된 제목: ", community.title));
    String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", community.content));

    String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("커뮤니티 게시글 변경을 취소하였습니다.");
=======
    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", community.title));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", community.content));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("커뮤니티 게시글 수정을 취소하였습니다.");
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
      return;
    }

    community.title = title;
    community.content = content;
<<<<<<< HEAD
    System.out.println("커뮤니티 게시글을 변경하였습니다.");
=======
    System.out.println("커뮤니티 게시글을 수정하였습니다.");
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
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
<<<<<<< HEAD
  private Board findByNo(int no) {
=======
  private Community findByNo(int no) {
>>>>>>> 9a6bde6e9a7547409f79ba63cbefb8a60a6b51c3
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