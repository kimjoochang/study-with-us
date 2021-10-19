package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

public class MariadbMemberDaoSY implements MemberDao {

	Connection con;

	public MariadbMemberDaoSY(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Member member) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"insert into member(name,email,password,phone_number) values(?,?,password(?),?)")) {

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getPhoneNumber());

			if (stmt.executeUpdate() == 0) {
				throw new Exception("회원 데이터 저장 실패!");
			}
		}
	}

	@Override
	public List<Member> findAll() throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member order by name asc");
				ResultSet rs = stmt.executeQuery()) {

			ArrayList<Member> list = new ArrayList<>();

			while (rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setRegisteredDate(rs.getDate("join_date"));
				member.setStatus(rs.getInt("status"));
				member.setLastDate(rs.getDate("last_date"));
				member.setUserAccessLevel(rs.getInt("access_level"));

				list.add(member);
			}

			return list;
		}
	}

	@Override
	public Member findByName(String name) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member"
						+ " where name=?")) {

			stmt.setString(1, name);

			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				Member member = new Member();
				member.setNo(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setRegisteredDate(rs.getDate("join_date"));
				member.setStatus(rs.getInt("status"));
				member.setLastDate(rs.getDate("last_date"));
				member.setUserAccessLevel(rs.getInt("access_level"));
				return member;
			}
		}	
	}

	// 차지 스터디
	// 회원가입 / 아이디 중복 검사
	@Override
	public Member findByEmail(String email) throws Exception {

		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member"
						+ " where email=?")) {

			stmt.setString(1, email);

			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				Member member = new Member();
				member.setNo(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setRegisteredDate(rs.getDate("join_date"));
				member.setStatus(rs.getInt("status"));
				member.setLastDate(rs.getDate("last_date"));
				member.setUserAccessLevel(rs.getInt("access_level"));
				return member;
			}
		}
	}

	@Override
	public Member findMemberByEmailPassword(String email, String password) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member"
						+ " where email=? and password=password(?)")) {

			stmt.setString(1, email);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				Member member = new Member();
				member.setNo(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setRegisteredDate(rs.getDate("join_date"));
				member.setStatus(rs.getInt("status"));
				member.setLastDate(rs.getDate("last_date"));
				member.setUserAccessLevel(rs.getInt("access_level"));
				return member;
			}
		}
	}

	// 아이디 찾기
	@Override
	public Member findMemberByNamePhoneNumber(String name, String phoneNumber) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member"
						+ " where name=? and phone_number=?")) {

			stmt.setString(1, name);
			stmt.setString(2, phoneNumber);

			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				Member member = new Member();
				member.setNo(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setRegisteredDate(rs.getDate("join_date"));
				member.setStatus(rs.getInt("status"));
				member.setLastDate(rs.getDate("last_date"));
				member.setUserAccessLevel(rs.getInt("access_level"));
				return member;
			}
		}
	}

	// 비밀번호 재설정
	@Override
	public Member findMember(String name, String email, String phoneNumber) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member"
						+ " where name=? and email=? and phone_number=?")) {

			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, phoneNumber);

			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				Member member = new Member();
				member.setNo(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setRegisteredDate(rs.getDate("join_date"));
				member.setStatus(rs.getInt("status"));
				member.setLastDate(rs.getDate("last_date"));
				member.setUserAccessLevel(rs.getInt("access_level"));
				return member;
			}
		}

	}

	@Override
	public void update(Member member) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"update member set"
						+ " name=?,email=?,password=password(?),phone_number=?"
						+ " where member_no=?")) {

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getPhoneNumber());
			stmt.setInt(5, member.getNo());

			if (stmt.executeUpdate() == 0) {
				throw new Exception("회원 데이터 변경 실패!");
			}
		}
	}

	@Override
	public void delete(int no) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"delete from member where member_no=?")) {

			stmt.setInt(1, no);

			if (stmt.executeUpdate() == 0) {
				throw new Exception("회원 데이터 삭제 실패!");
			}
		}
	}

	@Override
	public Member findByNo(int no) throws Exception {
		try (PreparedStatement stmt = con.prepareStatement(
				"select member_no,name,email,password,phone_number,join_date,status,last_date,access_level from member"
						+ " where member_no=" + no);
				ResultSet rs = stmt.executeQuery()) {

			if (!rs.next()) {
				return null;
			}

			Member member = new Member();
			member.setNo(rs.getInt("member_no"));
			member.setName(rs.getString("name"));
			member.setEmail(rs.getString("email"));
			member.setPassword(rs.getString("password"));
			member.setPhoneNumber(rs.getString("phone_number"));
			member.setRegisteredDate(rs.getDate("join_date"));
			member.setStatus(rs.getInt("status"));
			member.setLastDate(rs.getDate("last_date"));
			member.setUserAccessLevel(rs.getInt("access_level"));

			return member;
		}
	}
}
