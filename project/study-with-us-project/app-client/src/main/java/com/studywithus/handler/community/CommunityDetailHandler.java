package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.dao.CommentDao;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityDetailHandler implements Command {

  CommunityDao communityDao;
  CommentDao commentDao;
  // String updateKey;
  // String deleteKey;

  public CommunityDetailHandler(CommunityDao communityDao, CommentDao commentDao) {
    this.communityDao = communityDao;
    this.commentDao = commentDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Community community = communityDao.findByNo(no);

    if (community == null) {
      System.out.println("해당 번호의 커뮤니티가 없습니다.");
      return;
    }

    System.out.println();
    community.setViewCount(community.getViewCount() + 1);
    System.out.printf("제목: %s\n", community.getTitle());
    System.out.printf("내용: %s\n", community.getContent());
    System.out.printf("작성자: %s\n", community.getWriter().getName());
    System.out.printf("등록일: %s\n", community.getRegisteredDate());
    System.out.printf("조회수: %d\n", community.getViewCount());
    System.out.println();

    System.out.println("-----------------------------------------------------------");
    List<Comment> comments = commentDao.findAll();

    for (Comment comment : comments) {

      if (comment.getCommunityNo() == no) {
        System.out.printf("번호 : %d | 작성자 이메일 : %s | 내용 : %s\n",
            comment.getNo(), comment.getEmail(), comment.getContent());
        System.out.println("-----------------------------------------------------------");
      }
    }

    request.setAttribute("communityNo", no);



    // 내가 쓴 글인 경우
    if (community.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail()) 
        || community.getWriter().getEmail().equals("root@test.com")) {
      while (true) {
        System.out.println("1. 수정");
        System.out.println("2. 삭제");
        System.out.println("3. 댓글 작성");
        if (findByEmail(comments)) {
          System.out.println("4. 댓글 삭제");
        }
        System.out.println("0. 이전");
        System.out.println();

        int num = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
        System.out.println();

        if (num == 1) {
          request.getRequestDispatcher("/community/update").forward(request);

        } else if (num == 2) {
          request.getRequestDispatcher("/community/delete").forward(request);

        } else if (num == 3) {
          request.getRequestDispatcher("/comment/add").forward(request);

        } else if (num == 4) {
          int input = Prompt.inputInt("삭제할 댓글 번호를 입력해주세요. > ");
          request.setAttribute("commentNo", input);
          request.getRequestDispatcher("/comment/delete").forward(request);

        }else if (num == 0) {
          return;

        } else {
          System.out.println("다시 입력하세요.\n");
          continue;
        }
        return;
      }

    } else {

      while (true) {
        System.out.println("1. 댓글 작성");
        if (findByEmail(comments)) {
          System.out.println("2. 댓글 삭제");
        }
        System.out.println("0. 이전");
        System.out.println();

        int num = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
        System.out.println();

        if (num == 1) {
          request.getRequestDispatcher("/comment/add").forward(request);

        } else if (num == 2) {
          int input = Prompt.inputInt("삭제할 댓글 번호를 입력해주세요. > ");
          request.setAttribute("commentNo", input);
          request.getRequestDispatcher("/comment/delete").forward(request);

        } else if (num == 0) {
          return;

        } else {
          System.out.println("다시 입력하세요.\n");
          continue;
        }
        return;
      }
    }

  }
  private boolean findByEmail(List<Comment> comments) {
    for(Comment comment : comments) {
      if (comment.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
        return true;
      } 
    }
    return false;
  }
}
