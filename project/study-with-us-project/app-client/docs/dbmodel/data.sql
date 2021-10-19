-- 회원 생성
insert into member(name,email,password,phone_number) values('GR','1@test.com',password('1234567!'),'01012341234')

-- 일정 생성
insert into schedule(calendar_no,title,content,start_date,end_date)values('1','test','testcontent','2021-11-24','2021-12-25')