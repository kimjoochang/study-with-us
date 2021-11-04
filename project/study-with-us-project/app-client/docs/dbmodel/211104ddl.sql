-- 회원
DROP TABLE IF EXISTS member RESTRICT;

-- 스터디
DROP TABLE IF EXISTS study RESTRICT;

-- 일정
DROP TABLE IF EXISTS calendar RESTRICT;

-- 커뮤니티
DROP TABLE IF EXISTS cmnt RESTRICT;

-- 유료스터디후기
DROP TABLE IF EXISTS review RESTRICT;

-- 관심스터디
DROP TABLE IF EXISTS interest RESTRICT;

-- 멘토
DROP TABLE IF EXISTS mentor RESTRICT;

-- 스터디멤버
DROP TABLE IF EXISTS study_member RESTRICT;

-- 회원사진 첨부파일
DROP TABLE IF EXISTS member_file RESTRICT;

-- 커뮤니티 첨부파일
DROP TABLE IF EXISTS cmnt_file RESTRICT;

-- 댓글
DROP TABLE IF EXISTS comment RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS cmnt_like RESTRICT;

-- 결제
DROP TABLE IF EXISTS payment RESTRICT;

-- 블랙리스트
DROP TABLE IF EXISTS black_list RESTRICT;

-- 신고
DROP TABLE IF EXISTS report RESTRICT;

-- 삭제요청서
DROP TABLE IF EXISTS delete_request RESTRICT;

-- 멘토신청서
DROP TABLE IF EXISTS application RESTRICT;

-- 회원
CREATE TABLE member (
  member_no    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name         VARCHAR(80)  NOT NULL COMMENT '이름', -- 이름
  email        VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
  password     VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
  phone_number VARCHAR(30)  NOT NULL COMMENT '휴대폰번호', -- 휴대폰번호
  nickname     VARCHAR(80)  NOT NULL COMMENT '닉네임', -- 닉네임
  join_date    DATE         NOT NULL DEFAULT curdate() COMMENT '가입일', -- 가입일
  status       INTEGER      NOT NULL DEFAULT 0 COMMENT '상태', -- 상태
  last_date    DATE         NULL     COMMENT '마지막접속일', -- 마지막접속일
  access_level INTEGER      NOT NULL DEFAULT 2 COMMENT '권한' -- 권한
)
COMMENT '회원';

-- 회원
ALTER TABLE member
  ADD CONSTRAINT PK_member -- 회원 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
  ON member ( -- 회원
    email ASC,    -- 이메일
    nickname ASC  -- 닉네임
  );

-- 회원 인덱스
CREATE INDEX IX_member
  ON member( -- 회원
    name ASC -- 이름
  );

ALTER TABLE member
  MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 스터디
CREATE TABLE study (
  study_no      INTEGER      NOT NULL COMMENT '스터디번호', -- 스터디번호
  member_no     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title         VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content       TEXT         NOT NULL COMMENT '내용', -- 내용
  area          VARCHAR(255) NOT NULL COMMENT '지역', -- 지역
  on_off        INTEGER      NOT NULL DEFAULT 0 COMMENT '온오프라인', -- 온오프라인
  register_date DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  view_count    INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  max_member    INTEGER      NOT NULL COMMENT '모집인원', -- 모집인원
  start_date    DATE         NOT NULL COMMENT '시작일', -- 시작일
  end_date      DATE         NOT NULL COMMENT '종료일', -- 종료일
  del_status    INTEGER      NOT NULL DEFAULT 0 COMMENT '삭제상태', -- 삭제상태
  price         INTEGER      NULL     COMMENT '금액', -- 금액
  status        INTEGER      NOT NULL DEFAULT 0 COMMENT '상태' -- 상태
)
COMMENT '스터디';

-- 스터디
ALTER TABLE study
  ADD CONSTRAINT PK_study -- 스터디 기본키
    PRIMARY KEY (
      study_no -- 스터디번호
    );

ALTER TABLE study
  MODIFY COLUMN study_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '스터디번호';

-- 일정
CREATE TABLE calendar (
  calendar_no INTEGER      NOT NULL COMMENT '일정번호', -- 일정번호
  title       VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content     TEXT         NOT NULL COMMENT '내용', -- 내용
  start_date  DATE         NOT NULL COMMENT '시작일', -- 시작일
  end_date    DATE         NULL     COMMENT '종료일' -- 종료일
)
COMMENT '일정';

-- 일정
ALTER TABLE calendar
  ADD CONSTRAINT PK_calendar -- 일정 기본키
    PRIMARY KEY (
      calendar_no -- 일정번호
    );

ALTER TABLE calendar
  MODIFY COLUMN calendar_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '일정번호';

-- 커뮤니티
CREATE TABLE cmnt (
  cmnt_no       INTEGER      NOT NULL COMMENT '커뮤니티번호', -- 커뮤니티번호
  category      INTEGER      NOT NULL COMMENT '카테고리', -- 카테고리
  title         VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content       TEXT         NOT NULL COMMENT '내용', -- 내용
  register_date DATETIME     NOT NULL DEFAULT now() COMMENT '작성일', -- 작성일
  view_count    INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  member_no     INTEGER      NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '커뮤니티';

-- 커뮤니티
ALTER TABLE cmnt
  ADD CONSTRAINT PK_cmnt -- 커뮤니티 기본키
    PRIMARY KEY (
      cmnt_no -- 커뮤니티번호
    );

ALTER TABLE cmnt
  MODIFY COLUMN cmnt_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '커뮤니티번호';

-- 유료스터디후기
CREATE TABLE review (
  review_no     INTEGER      NOT NULL COMMENT '후기번호', -- 후기번호
  member_no     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  study_no      INTEGER      NOT NULL COMMENT '스터디번호', -- 스터디번호
  title         VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content       TEXT         NOT NULL COMMENT '내용', -- 내용
  score         INTEGER      NOT NULL COMMENT '평점', -- 평점
  register_date DATETIME     NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '유료스터디후기';

-- 유료스터디후기
ALTER TABLE review
  ADD CONSTRAINT PK_review -- 유료스터디후기 기본키
    PRIMARY KEY (
      review_no -- 후기번호
    );

ALTER TABLE review
  MODIFY COLUMN review_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기번호';

-- 관심스터디
CREATE TABLE interest (
  member_no INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  study_no  INTEGER NOT NULL COMMENT '스터디번호' -- 스터디번호
)
COMMENT '관심스터디';

-- 관심스터디
ALTER TABLE interest
  ADD CONSTRAINT PK_interest -- 관심스터디 기본키
    PRIMARY KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 멘토
CREATE TABLE mentor (
  member_no    INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  introduction TEXT     NOT NULL COMMENT '자기소개', -- 자기소개
  subject      TEXT     NOT NULL COMMENT '주제', -- 주제
  apply_date   DATETIME NOT NULL DEFAULT now() COMMENT '신청일' -- 신청일
)
COMMENT '멘토';

-- 멘토
ALTER TABLE mentor
  ADD CONSTRAINT PK_mentor -- 멘토 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 스터디멤버
CREATE TABLE study_member (
  member_no INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  study_no  INTEGER NOT NULL COMMENT '스터디번호', -- 스터디번호
  status    INTEGER NOT NULL COMMENT '멤버상태' -- 멤버상태
)
COMMENT '스터디멤버';

-- 스터디멤버
ALTER TABLE study_member
  ADD CONSTRAINT PK_study_member -- 스터디멤버 기본키
    PRIMARY KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 스터디멤버 유니크 인덱스
CREATE UNIQUE INDEX UIX_study_member
  ON study_member ( -- 스터디멤버
    member_no ASC, -- 회원번호
    study_no ASC   -- 스터디번호
  );

-- 회원사진 첨부파일
CREATE TABLE member_file (
  file_no   INTEGER     NOT NULL COMMENT '회원사진 첨부파일번호', -- 회원사진 첨부파일번호
  member_no INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  name      VARCHAR(80) NOT NULL COMMENT '첨부파일명' -- 첨부파일명
)
COMMENT '회원사진 첨부파일';

-- 회원사진 첨부파일
ALTER TABLE member_file
  ADD CONSTRAINT PK_member_file -- 회원사진 첨부파일 기본키
    PRIMARY KEY (
      file_no -- 회원사진 첨부파일번호
    );

ALTER TABLE member_file
  MODIFY COLUMN file_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원사진 첨부파일번호';

-- 커뮤니티 첨부파일
CREATE TABLE cmnt_file (
  cmnt_file_no INTEGER     NOT NULL COMMENT '커뮤니티 첨부파일번호', -- 커뮤니티 첨부파일번호
  name         VARCHAR(80) NOT NULL COMMENT '첨부파일명', -- 첨부파일명
  cmnt_no      INTEGER     NOT NULL COMMENT '커뮤니티번호' -- 커뮤니티번호
)
COMMENT '커뮤니티 첨부파일';

-- 커뮤니티 첨부파일
ALTER TABLE cmnt_file
  ADD CONSTRAINT PK_cmnt_file -- 커뮤니티 첨부파일 기본키
    PRIMARY KEY (
      cmnt_file_no -- 커뮤니티 첨부파일번호
    );

ALTER TABLE cmnt_file
  MODIFY COLUMN cmnt_file_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '커뮤니티 첨부파일번호';

-- 댓글
CREATE TABLE comment (
  comment_no    INTEGER  NOT NULL COMMENT '댓글번호', -- 댓글번호
  content       TEXT     NOT NULL COMMENT '내용', -- 내용
  register_date DATETIME NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  member_no     INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  cmnt_no       INTEGER  NOT NULL COMMENT '커뮤니티번호' -- 커뮤니티번호
)
COMMENT '댓글';

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT PK_comment -- 댓글 기본키
    PRIMARY KEY (
      comment_no -- 댓글번호
    );

ALTER TABLE comment
  MODIFY COLUMN comment_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 좋아요
CREATE TABLE cmnt_like (
  cmnt_no   INTEGER NOT NULL COMMENT '커뮤니티번호', -- 커뮤니티번호
  member_no INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '좋아요';

-- 좋아요
ALTER TABLE cmnt_like
  ADD CONSTRAINT PK_cmnt_like -- 좋아요 기본키
    PRIMARY KEY (
      cmnt_no,   -- 커뮤니티번호
      member_no  -- 회원번호
    );

-- 결제
CREATE TABLE payment (
  member_no     INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  study_no      INTEGER  NOT NULL COMMENT '스터디번호', -- 스터디번호
  pay_method    INTEGER  NOT NULL COMMENT '결제수단', -- 결제수단
  status        INTEGER  NOT NULL DEFAULT 0 COMMENT '상태', -- 상태
  register_date DATETIME NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '결제';

-- 결제
ALTER TABLE payment
  ADD CONSTRAINT PK_payment -- 결제 기본키
    PRIMARY KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 결제 유니크 인덱스
CREATE UNIQUE INDEX UIX_payment
  ON payment ( -- 결제
    member_no ASC, -- 회원번호
    study_no ASC   -- 스터디번호
  );

-- 블랙리스트
CREATE TABLE black_list (
  member_no     INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  study_no      INTEGER NOT NULL COMMENT '스터디번호', -- 스터디번호
  warning_count INTEGER NOT NULL DEFAULT 0 COMMENT '경고수' -- 경고수
)
COMMENT '블랙리스트';

-- 블랙리스트
ALTER TABLE black_list
  ADD CONSTRAINT PK_black_list -- 블랙리스트 기본키
    PRIMARY KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 신고
CREATE TABLE report (
  report_no   INTEGER  NOT NULL COMMENT '신고번호', -- 신고번호
  member_no   INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  study_no    INTEGER  NOT NULL COMMENT '스터디번호', -- 스터디번호
  status      INTEGER  NOT NULL DEFAULT 0 COMMENT '상태', -- 상태
  content     TEXT     NOT NULL COMMENT '내용', -- 내용
  report_date DATETIME NOT NULL DEFAULT now() COMMENT '신고일' -- 신고일
)
COMMENT '신고';

-- 신고
ALTER TABLE report
  ADD CONSTRAINT PK_report -- 신고 기본키
    PRIMARY KEY (
      report_no, -- 신고번호
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

ALTER TABLE report
  MODIFY COLUMN report_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 삭제요청서
CREATE TABLE delete_request (
  request_no    INTEGER NOT NULL COMMENT '삭제요청서번호', -- 삭제요청서번호
  study_no      INTEGER NOT NULL COMMENT '스터디번호', -- 스터디번호
  reason        TEXT    NOT NULL COMMENT '삭제사유', -- 삭제사유
  remarks       TEXT    NULL     COMMENT '비고', -- 비고
  register_date DATE    NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '삭제요청서';

-- 삭제요청서
ALTER TABLE delete_request
  ADD CONSTRAINT PK_delete_request -- 삭제요청서 기본키
    PRIMARY KEY (
      request_no -- 삭제요청서번호
    );

ALTER TABLE delete_request
  MODIFY COLUMN request_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '삭제요청서번호';

-- 멘토신청서
CREATE TABLE application (
  application_no INTEGER  NOT NULL COMMENT '신청서 번호', -- 신청서 번호
  member_no      INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  introduction   TEXT     NOT NULL COMMENT '자기소개', -- 자기소개
  subject        TEXT     NOT NULL COMMENT '주제', -- 주제
  apply_date     DATETIME NOT NULL DEFAULT now() COMMENT '신청일', -- 신청일
  status         INTEGER  NOT NULL DEFAULT 0 COMMENT '상태', -- 상태
  remarks        TEXT     NULL     COMMENT '비고' -- 비고
)
COMMENT '멘토신청서';

-- 멘토신청서
ALTER TABLE application
  ADD CONSTRAINT PK_application -- 멘토신청서 기본키
    PRIMARY KEY (
      application_no -- 신청서 번호
    );

ALTER TABLE application
  MODIFY COLUMN application_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '신청서 번호';

-- 스터디
ALTER TABLE study
  ADD CONSTRAINT FK_member_TO_study -- 회원 -> 스터디
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 커뮤니티
ALTER TABLE cmnt
  ADD CONSTRAINT FK_member_TO_cmnt -- 회원 -> 커뮤니티
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 유료스터디후기
ALTER TABLE review
  ADD CONSTRAINT FK_study_member_TO_review -- 스터디멤버 -> 유료스터디후기
    FOREIGN KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    )
    REFERENCES study_member ( -- 스터디멤버
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 관심스터디
ALTER TABLE interest
  ADD CONSTRAINT FK_study_TO_interest -- 스터디 -> 관심스터디
    FOREIGN KEY (
      study_no -- 스터디번호
    )
    REFERENCES study ( -- 스터디
      study_no -- 스터디번호
    );

-- 관심스터디
ALTER TABLE interest
  ADD CONSTRAINT FK_member_TO_interest -- 회원 -> 관심스터디
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 멘토
ALTER TABLE mentor
  ADD CONSTRAINT FK_member_TO_mentor -- 회원 -> 멘토
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 스터디멤버
ALTER TABLE study_member
  ADD CONSTRAINT FK_study_TO_study_member -- 스터디 -> 스터디멤버
    FOREIGN KEY (
      study_no -- 스터디번호
    )
    REFERENCES study ( -- 스터디
      study_no -- 스터디번호
    );

-- 스터디멤버
ALTER TABLE study_member
  ADD CONSTRAINT FK_member_TO_study_member -- 회원 -> 스터디멤버
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 회원사진 첨부파일
ALTER TABLE member_file
  ADD CONSTRAINT FK_member_TO_member_file -- 회원 -> 회원사진 첨부파일
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 커뮤니티 첨부파일
ALTER TABLE cmnt_file
  ADD CONSTRAINT FK_cmnt_TO_cmnt_file -- 커뮤니티 -> 커뮤니티 첨부파일
    FOREIGN KEY (
      cmnt_no -- 커뮤니티번호
    )
    REFERENCES cmnt ( -- 커뮤니티
      cmnt_no -- 커뮤니티번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_member_TO_comment -- 회원 -> 댓글
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_cmnt_TO_comment -- 커뮤니티 -> 댓글
    FOREIGN KEY (
      cmnt_no -- 커뮤니티번호
    )
    REFERENCES cmnt ( -- 커뮤니티
      cmnt_no -- 커뮤니티번호
    );

-- 좋아요
ALTER TABLE cmnt_like
  ADD CONSTRAINT FK_cmnt_TO_cmnt_like -- 커뮤니티 -> 좋아요
    FOREIGN KEY (
      cmnt_no -- 커뮤니티번호
    )
    REFERENCES cmnt ( -- 커뮤니티
      cmnt_no -- 커뮤니티번호
    );

-- 좋아요
ALTER TABLE cmnt_like
  ADD CONSTRAINT FK_member_TO_cmnt_like -- 회원 -> 좋아요
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 결제
ALTER TABLE payment
  ADD CONSTRAINT FK_study_member_TO_payment -- 스터디멤버 -> 결제
    FOREIGN KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    )
    REFERENCES study_member ( -- 스터디멤버
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 신고
ALTER TABLE report
  ADD CONSTRAINT FK_study_member_TO_report -- 스터디멤버 -> 신고
    FOREIGN KEY (
      member_no, -- 회원번호
      study_no   -- 스터디번호
    )
    REFERENCES study_member ( -- 스터디멤버
      member_no, -- 회원번호
      study_no   -- 스터디번호
    );

-- 삭제요청서
ALTER TABLE delete_request
  ADD CONSTRAINT FK_study_TO_delete_request -- 스터디 -> 삭제요청서
    FOREIGN KEY (
      study_no -- 스터디번호
    )
    REFERENCES study ( -- 스터디
      study_no -- 스터디번호
    );

-- 멘토신청서
ALTER TABLE application
  ADD CONSTRAINT FK_member_TO_application -- 회원 -> 멘토신청서
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );