package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Community;
import com.studywithus.domain.CommunityFile;
import com.studywithus.domain.Member;
import com.studywithus.domain.MemberFile;

// 역할
// - 커뮤니티 데이터를 서버를 통해 관리한다.
//
public class MariadbCommunityDao implements CommunityDao {

  Connection con;

  public MariadbCommunityDao( Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Community community) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into cmnt(category,title,content,member_no)" 
            + "values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

      stmt.setInt(1, community.getCategory());
      stmt.setString(2, community.getTitle());
      stmt.setString(3, community.getContent());
      stmt.setInt(4, community.getWriter().getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("커뮤니티 글 등록에 실패하였습니다.");
      }
    }
  }

  @Override
  public List<Community> findAll() throws Exception {
    // 커뮤 + 멤버 (작성자 갖고오기)
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.cmnt_no,"
            + " cm.category,"
            + " cm.title,"
            + " cm.content,"
            + " cm.register_date,"
            + " cm.view_count,"
            + " cf.cmnt_file_no,"
            + " cf.name,"
            + " m.member_no,"
            + " m.email"
            + " from"
            + " cmnt cm"
            + " inner join member m on cm.member_no=m.member_no"
            + " left outer join cmnt_file cf on cm.cmnt_no=cf.cmnt_no"
            + " order by member_no desc");
        // 좋아요 + 커뮤 ( 좋아요 개수 가져오기)
        PreparedStatement stmt2 = con.prepareStatement(
            "select"
                + " count(member_no) as likes"
                + " from"
                + " cmnt_like"
                + " where"
                + " cmnt_no=?");
        // 댓글 + 멤버 (댓글 내용과 댓글 작성자 가져오기)
        PreparedStatement stmt3 = con.prepareStatement(
            "select"
                + " c.comment_no,"
                + " c.content,"
                + " c.register_date,"
                + " m.member_no,"
                + " m.email,"
                + " mf.file_no,"
                + " mf.name"
                + " from"
                + " comment c"
                + " inner join member m on c.member_no=m.member_no"
                + " left outer join member_file mf on m.member_no=mf.member_no"
                + " where"
                + " cmnt_no=?");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Community> list = new ArrayList<>();

      while(rs.next()) {
        // 커뮤니티 정보 + 작성자 정보 가져오기
        Community community = new Community();
        community.setNo(rs.getInt("cmnt_no"));
        community.setCategory(rs.getInt("category"));
        community.setTitle(rs.getString("title"));
        community.setContent(rs.getString("content"));
        community.setRegisteredDate(rs.getDate("register_date"));
        community.setViewCount(rs.getInt("view_count"));

        // 커뮤니티 첨부파일이 있을때만 실행!
        if (rs.getString("name") != null) {
          CommunityFile communityFile = new CommunityFile();
          communityFile.setNo(rs.getInt("cmnt_file.no"));
          communityFile.setFileName(rs.getString("name"));
        }

        Member writer = new Member();
        writer.setNo(rs.getInt("member_no"));
        writer.setEmail(rs.getString("email"));

        community.setWriter(writer);

        // 좋아요 수 가져오기
        stmt2.setInt(1,community.getNo());
        try(ResultSet likes = stmt2.executeQuery()) {
          community.setLike(likes.getRow());
        }

        // 해당 커뮤니티의 댓글과 댓글 작성자 정보 가져오기
        stmt3.setInt(1, community.getNo());
        try(ResultSet c = stmt3.executeQuery()) {
          while(c.next()) {

            Comment comment = new Comment();
            comment.setContent(c.getString("content"));

            Member member = new Member();
            member.setNo(c.getInt("member_no"));
            member.setEmail(c.getString("email"));

            if (c.getString("name") != null) {
              MemberFile memberFile = new MemberFile();
              memberFile.setNo(c.getInt("file_no"));
              memberFile.setFileName(c.getString("name"));

              member.setMemberFile(memberFile);
            }

            comment.setWriter(member);

            community.getComments().add(comment);

          }
        }
        list.add(community);
      }
      return list;
    }
  }

  @Override
  public List<Community> findByKeyword(String keyword) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.cmnt_no,"
            + " cm.category,"
            + " cm.title,"
            + " cm.content,"
            + " cm.register_date,"
            + " cm.view_count,"
            + " cf.cmnt_file_no,"
            + " cf.name,"
            + " m.member_no,"
            + " m.email"
            + " from"
            + " cmnt cm"
            + " inner join member m on member_no=m.member_no"
            + " left outer join cmnt_file cf on cm.cmnt_no=cf.cmnt_no"
            + " where cm.title like (concat('%',?,'%'))"
            + " or cm.content like (concat('%',?,'%'))"
            + " or m.email like (concat('%',?,'%'))"
            + " order by b.board_no desc")) {

      stmt.setString(1, keyword);
      stmt.setString(2, keyword);
      stmt.setString(3, keyword);

      // 좋아요 + 커뮤 ( 좋아요 개수 가져오기)
      PreparedStatement stmt2 = con.prepareStatement(
          "select"
              + " count(member_no) as likes"
              + " from"
              + " cmnt_like"
              + " where"
              + " cmnt_no=?");
      // 댓글 + 멤버 (댓글 내용과 댓글 작성자 가져오기)
      PreparedStatement stmt3 = con.prepareStatement(
          "select"
              + " c.comment_no,"
              + " c.cmnt_no,"
              + " c.content,"
              + " c.register_date,"
              + " m.member_no,"
              + " m.email,"
              + " mf.file_no,"
              + " mf.name"
              + " from"
              + " comment c"
              + " inner join member m on c.member_no=m.member_no"
              + " left outer join member_file mf on m.member_no=mf.member_no"
              + " where"
              + " cmnt_no=?");
      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Community> list = new ArrayList<>();

        while(rs.next()) {
          // 커뮤니티 정보 + 작성자 정보 가져오기
          Community community = new Community();
          community.setNo(rs.getInt("cmnt_no"));
          community.setCategory(rs.getInt("category"));
          community.setTitle(rs.getString("title"));
          community.setContent(rs.getString("content"));
          community.setRegisteredDate(rs.getDate("register_date"));
          community.setViewCount(rs.getInt("view_count"));

          if (rs.getString("name") != null) {
            CommunityFile communityFile = new CommunityFile();
            communityFile.setNo(rs.getInt("cmnt_file.no"));
            communityFile.setFileName(rs.getString("name"));
          }

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setEmail(rs.getString("email"));

          community.setWriter(writer);

          // 좋아요 수 가져오기
          stmt2.setInt(1,community.getNo());
          try(ResultSet likes = stmt2.executeQuery()) {
            community.setLike(likes.getRow());
          }

          // 해당 커뮤니티의 댓글과 댓글 작성자 정보 가져오기
          stmt3.setInt(1, community.getNo());
          try(ResultSet c = stmt3.executeQuery()) {
            while(c.next()) {

              Comment comment = new Comment();
              comment.setContent(c.getString("content"));

              Member member = new Member();
              member.setNo(c.getInt("member_no"));
              member.setEmail(c.getString("email"));

              if (c.getString("name") != null) {
                MemberFile memberFile = new MemberFile();
                memberFile.setNo(c.getInt("file_no"));
                memberFile.setFileName(c.getString("name"));

                member.setMemberFile(memberFile);
              }

              comment.setWriter(member);

              community.getComments().add(comment);
            }
          }
          list.add(community);
        }
        return list;
      }
    }
  }

  @Override
  public Community findByNo(int no) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.cmnt_no,"
            + " cm.category,"
            + " cm.title,"
            + " cm.content,"
            + " cm.register_date,"
            + " cm.view_count,"
            + " cf.cmnt_file_no,"
            + " cf.name,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from"
            + " cmnt cm"
            + " inner join member m on cm.member_no=m.member_no"
            + " left outer join cmnt_file cf on cm.cmnt_no=cf.cmnt_no"
            + " where cm.cmnt_no=" + no
            + " order by member_no desc");
        // 좋아요 + 커뮤 ( 좋아요 개수 가져오기)
        PreparedStatement stmt2 = con.prepareStatement(
            "select"
                + " count(member_no) as likes"
                + " from"
                + " cmnt_like"
                + " where"
                + " cmnt_no=?");
        // 댓글 + 멤버 (댓글 내용과 댓글 작성자 가져오기)
        PreparedStatement stmt3 = con.prepareStatement(
            "select"
                + " c.comment_no,"
                + " c.cmnt_no,"
                + " c.content,"
                + " c.register_date,"
                + " m.member_no,"
                + " m.email,"
                + " mf.file_no,"
                + " mf.name"
                + " from"
                + " comment c"
                + " inner join member m on c.member_no=m.member_no"
                + " left outer join member_file mf on m.member_no=mf.member_no"
                + " where"
                + " cmnt_no=?");
        ResultSet rs = stmt.executeQuery()) {

      Community community = null;

      while(rs.next()) {
        // 커뮤니티 정보 + 작성자 정보 가져오기
        if (community == null) {
          community = new Community();
          community.setNo(rs.getInt("cmnt_no"));
          community.setCategory(rs.getInt("category"));
          community.setTitle(rs.getString("title"));
          community.setContent(rs.getString("content"));
          community.setRegisteredDate(rs.getDate("register_date"));
          community.setViewCount(rs.getInt("view_count"));

          if (rs.getString("name") != null) {
            CommunityFile communityFile = new CommunityFile();
            communityFile.setNo(rs.getInt("cmnt_file.no"));
            communityFile.setFileName(rs.getString("name"));
          }

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("m.name"));
          writer.setEmail(rs.getString("email"));

          community.setWriter(writer);
        }
        // 좋아요 수 가져오기
        stmt2.setInt(1,community.getNo());
        try(ResultSet likes = stmt2.executeQuery()) {
          community.setLike(likes.getRow());
        }

        // 해당 커뮤니티의 댓글과 댓글 작성자 정보 가져오기
        stmt3.setInt(1, community.getNo());
        try(ResultSet c = stmt3.executeQuery()) {
          List<Comment> comments = community.getComments();
          while(c.next()) {

            Comment comment = new Comment();
            comment.setContent(c.getString("content"));

            Member member = new Member();
            member.setNo(c.getInt("member_no"));
            member.setEmail(c.getString("email"));

            if (c.getString("name") != null) {
              MemberFile memberFile = new MemberFile();
              memberFile.setNo(c.getInt("file_no"));
              memberFile.setFileName(c.getString("name"));

              member.setMemberFile(memberFile);
            }

            comment.setWriter(member);

            comments.add(comment);
            community.setComments(comments);
          }
        }
      }
      return community;
    }
  }

  @Override
  public void update(Community community) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update cmnt set"
            + " category=?,"
            + " title=?,"
            + " content=?"
            + " where cmnt_no=?")) {

      stmt.setInt(1, community.getCategory());
      stmt.setString(2, community.getTitle());
      stmt.setString(3, community.getContent());
      stmt.setInt(4, community.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("커뮤니티 수정에 실패했습니다.");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from cmnt where project_no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "delete from comment where project_no=?");
        PreparedStatement stmt3 = con.prepareStatement(
            "delete from cmnt_file where project_no=?")) {

      stmt3.setInt(1, no);
      stmt3.executeUpdate();

      stmt2.setInt(1, no);
      stmt2.executeUpdate();

      // 프로젝트를 삭제한다.
      stmt.setInt(1, no);
      if (stmt.executeUpdate() == 0) {
        throw new Exception("커뮤니티 게시글 삭제에 실패했습니다.");
      }
    }
  }

  //  @Override
  //  public List<Community> findAll() throws Exception {
  //    try (PreparedStatement stmt = con.prepareStatement(
  //        "select"
  //            + " cm.cmnt_no,"
  //            + " cm.category,"
  //            + " cm.title,"
  //            + " cm.content,"
  //            + " cm.register_date,"
  //            + " cm.view_count,"
  //            + " c.comment_no,"
  //            + " c.content,"
  //            + " c.register_date,"
  //            + " m.member_no cmnt_writer_no"
  //            + " m.member_email cmnt_writer_email"
  //            + " m2.member_email comment_writer_no"
  //            + " m2.member_email comment_writer_email"
  //            + " l.member_no like_member_no"
  //            + " l.member_email like_member_email"
  //            + " from" 
  //            + " cmnt cm"
  //            + " inner join member m on cm.member_no=m.member_no"
  //            + " left outer join comment c on cm.cmnt_no=c.cmnt_no"
  //            + " inner join member m2 on c.member_no=m2.member_no"
  //            + " left outer join like l on cm.cmnt_no=l.cmnt_no"
  //            + " inner join member m3 on c.member_no=m3.member_no"
  //            + " order by" 
  //            + " project_no desc, m2.name asc");
  //        ResultSet rs = stmt.executeQuery()) {
  //
  //      ArrayList<Community> list = new ArrayList<>();
  //
  //      int communitytNo = 0;
  //      Community community = null;
  //
  //      while (rs.next()) {
  //        if (communitytNo != rs.getInt("cmnt_no")) {
  //          community = new Community();
  //          community.setNo(rs.getInt("project_no"));
  //          community.setTitle(rs.getString("title"));
  //          community.setContent(rs.getString("content"));
  //          community.setViewCount(rs.getInt("view_count"));
  //          community.setRegisteredDate(rs.getDate("register_date"));
  //
  //          Member writer = new Member();
  //          writer.setNo(rs.getInt("owner_no"));
  //          writer.setEmail(rs.getString("owner_email"));
  //
  //          community.setWriter(writer);
  //
  //          list.add(community);
  //          communitytNo = community.getNo();
  //        }
  //
  //        if (rs.getString("comment_writer") != null) {
  //          Member member = new Member();
  //          member.setNo(rs.getInt("member_no"));
  //          member.setEmail(rs.getString("member_email"));
  //          comment.getMembers().add(member);
  //        }
  //      }
  //
  //      return list;
  //    }
  //  }
}