package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.Community;
import com.studywithus.domain.ExamCalender;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.JobsCalender;
import com.studywithus.domain.Member;
import com.studywithus.domain.Mentor;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.ChargeInterestDeleteHandler;
import com.studywithus.handler.ChargeInterestListHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.ChargeStudyDetailHandler;
import com.studywithus.handler.ChargeStudyListHandler;
import com.studywithus.handler.ChargeStudySearchHandler;
import com.studywithus.handler.ChargeStudyUpdateHandler;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommunityAddHandler;
import com.studywithus.handler.CommunityDeleteHandler;
import com.studywithus.handler.CommunityDetailHandler;
import com.studywithus.handler.CommunityListHandler;
import com.studywithus.handler.CommunitySearchHandler;
import com.studywithus.handler.CommunityUpdateHandler;
import com.studywithus.handler.ExamCalendarAddHandler;
import com.studywithus.handler.ExamCalendarDeleteHandler;
import com.studywithus.handler.ExamCalendarDetailHandler;
import com.studywithus.handler.ExamCalendarUpdateHandler;
import com.studywithus.handler.FreeInterestDeleteHandler;
import com.studywithus.handler.FreeInterestListHandler;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudySearchHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.handler.JobsCalendarAddHandler;
import com.studywithus.handler.JobsCalendarDeleteHandler;
import com.studywithus.handler.JobsCalendarDetailHandler;
import com.studywithus.handler.JobsCalendarUpdateHandler;
import com.studywithus.handler.MemberPrompt;
import com.studywithus.handler.MentorApplicantAddHandler;
import com.studywithus.handler.MentorApplicantDetailHandler;
import com.studywithus.handler.MentorApplicantListHandler;
import com.studywithus.handler.SignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class AppHsy {
	List<Member> memberList = new LinkedList<>();
	List<FreeStudy> freeStudyList = new ArrayList<>();
	List<FreeStudy> freeInterestList = new ArrayList<>();
	List<JobsCalender> jobsCalenderList = new ArrayList<>();
	List<ExamCalender> examCalenderList = new ArrayList<>();
	List<Member> mentorApplicantList = new ArrayList<>();
	List<ChargeStudy> chargeStudyList = new ArrayList<>();
	List<ChargeStudy> chargeInterestList = new ArrayList<>();
	List<Community> communityInfoList = new ArrayList<>();
	List<Community> communityQaList = new ArrayList<>();
	List<Community> communityTalkList = new ArrayList<>();
	List<Mentor> mentorList = new ArrayList<>();

	HashMap<String, Command> commandMap = new HashMap<>();

	MemberPrompt memberPrompt = new MemberPrompt(memberList);

	class MenuItem extends Menu {
		String menuId;

		public MenuItem(String title, String menuId) {
			super(title);
			this.menuId = menuId;
		}

		public MenuItem(String title, int accessScope, String menuId) {
			super(title, accessScope);
			this.menuId = menuId;
		}

		@Override
		public void execute() {
			Command command = commandMap.get(menuId);
			command.execute();
		}
	}

	public static void main(String[] args) {
		AppHsy app = new AppHsy(); 
		app.service();
	}

	public AppHsy() {
		commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList));
		commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
		commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList, freeInterestList));
		commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
		commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));
		commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));

		commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList));
		commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
		commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(chargeStudyList, chargeInterestList));
		commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
		commandMap.put("/chargeStudy/delete", new ChargeStudyDeleteRequestHandler(chargeStudyList));
		commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));

		commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
		commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
		commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList));
		commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
		commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
		commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));

		commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
		commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
		commandMap.put("/communityQa/detail", new CommunityDetailHandler(communityQaList));
		commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
		commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
		commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));

		commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
		commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
		commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList));
		commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
		commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
		commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));

		commandMap.put("/jobsCalender/add", new JobsCalendarAddHandler(jobsCalenderList));
		commandMap.put("/jobsCalender/detail", new JobsCalendarDetailHandler(jobsCalenderList));
		commandMap.put("/jobsCalender/update", new JobsCalendarUpdateHandler(jobsCalenderList));
		commandMap.put("/jobsCalender/delete", new JobsCalendarDeleteHandler(jobsCalenderList));

		commandMap.put("/examCalender/add", new ExamCalendarAddHandler(examCalenderList));
		commandMap.put("/examCalender/detail", new ExamCalendarDetailHandler(examCalenderList));
		commandMap.put("/examCalender/update", new ExamCalendarUpdateHandler(examCalenderList));
		commandMap.put("/examCalender/delete", new ExamCalendarDeleteHandler(examCalenderList));

		commandMap.put("/mentorApplicant/add", new MentorApplicantAddHandler(mentorApplicantList));
		commandMap.put("/mentorApplicant/list", new MentorApplicantListHandler(mentorApplicantList));
		commandMap.put("/mentorApplicant/detail", new MentorApplicantDetailHandler(mentorApplicantList, mentorList));

		commandMap.put("/freeInterest/list", new FreeInterestListHandler(freeInterestList));
		commandMap.put("/freeInterest/delete", new FreeInterestDeleteHandler(freeInterestList));

		commandMap.put("/chargeInterest/list", new ChargeInterestListHandler(chargeInterestList));
		commandMap.put("/chargeInterest/delete", new ChargeInterestDeleteHandler(chargeInterestList));

		commandMap.put("/auth/login", new AuthLoginHandler(memberList));
		commandMap.put("/auth/logout", new AuthLogoutHandler());
		commandMap.put("/auth/signUp", new SignUpHandler(memberList));
	}

	void service() {


		loadChargeStudies();
		loadChargeInterests();

		createMainMenu().execute();
		Prompt.close();

		saveChargeStudies();
		saveChargeInterests();
	}

	@SuppressWarnings("unchecked")
	private void loadChargeStudies() {
		try (ObjectInputStream in = new ObjectInputStream(
				new FileInputStream("ChargeStudy.data"))) {

			chargeStudyList.addAll((List<ChargeStudy>) in.readObject());

			System.out.println("유료 스터디 정보 로딩이 완료되었습니다.");

		} catch (Exception e) {
			System.out.println("파일에서 유료 스터디 정보를 읽어 오는 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

	private void saveChargeStudies() {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("ChargeStudy.data"))) {

			out.writeObject(chargeStudyList);

			System.out.println("유료 스터디 정보 저장이 완료되었습니다.");

		} catch (Exception e) {
			System.out.println("유료 스터디 정보를 파일에 저장하던 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadChargeInterests() {
		try (ObjectInputStream in = new ObjectInputStream(
				new FileInputStream("chrageInterest.data"))) {

			chargeInterestList.addAll((List<ChargeStudy>) in.readObject());

			System.out.println("유료 스터디 관심목록 정보 로딩이 완료되었습니다.");

		} catch (Exception e) {
			System.out.println("파일에서 유료스터디 관심목록 정보를 읽어 오는 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

	private void saveChargeInterests() {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("chargeInterest.data"))) {

			out.writeObject(chargeInterestList);

			System.out.println("유료 스터디 관심목록 정보 저장이 완료되었습니다.");

		} catch (Exception e) {
			System.out.println("유료 스터디 관심 목록 정보 저장하던 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}
	Menu createMainMenu() {
		MenuGroup mainMenuGroup = new MenuGroup("메인");
		mainMenuGroup.setPrevMenuTitle("종료");

		mainMenuGroup.add(new MenuItem("로그인", ACCESS_LOGOUT, "/auth/login"));
		mainMenuGroup.add(new MenuItem("회원가입", ACCESS_LOGOUT, "/auth/signUp"));
		mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL, "/auth/logout"));

		//    mainMenuGroup.add(createAdminMenu());
		mainMenuGroup.add(createMemberMenu());
		mainMenuGroup.add(createInterestMenu());
		mainMenuGroup.add(createFreeStudyMenu());
		mainMenuGroup.add(createChargeStudyMenu());
		mainMenuGroup.add(createMentorApplyMenu());
		mainMenuGroup.add(createCommunityMenu());
		mainMenuGroup.add(createCalenderMenu());

		return mainMenuGroup;
	}

	//  private Menu createAdminMenu() {
	//    MenuGroup adminMenu = new MenuGroup("관리자", ACCESS_ADMIN);
	//
	//    adminMenu.add(createMemberMenu());
	//    adminMenu.add(createCalenderMenu());
	//
	//    return adminMenu;
	//  }

	private Menu createMemberMenu() {
		MenuGroup memberMenu = new MenuGroup("회원 관리", ACCESS_ADMIN);

		memberMenu.add(createMentorApplicantMenu());

		return memberMenu;
	}

	private Menu createMentorApplyMenu() {
		MenuGroup mentorApplyMenu = new MenuGroup("멘토 신청하기");
		mentorApplyMenu.add(new MenuItem("신청", ACCESS_GENERAL, "/mentorApplicant/add"));

		return mentorApplyMenu;
	}

	private Menu createCalenderMenu() {
		MenuGroup calenderMenu = new MenuGroup("캘린더 관리");

		calenderMenu.add(createJobsCalenderMenu());
		calenderMenu.add(createExamCalenderMenu());

		return calenderMenu;
	}

	//  private Menu createCalenderMenu() {
	//    MenuGroup calenderMenu = new MenuGroup("캘린더");
	//
	//    calenderMenu.add(createJobsCalenderMenu());
	//    calenderMenu.add(createExamCalenderMenu());
	//
	//    return calenderMenu;
	//  }

	private Menu createMentorApplicantMenu() {
		MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");

		mentorApplicantMenu.add(new MenuItem("조회", /*ACCESS_ADMIN,*/ "/mentorApplicant/list"));
		mentorApplicantMenu.add(new MenuItem("상세보기", /*ACCESS_ADMIN,*/ "/mentorApplicant/detail"));

		return mentorApplicantMenu;
	}

	private Menu createJobsCalenderMenu() {
		MenuGroup jobsCalenderMenu = new MenuGroup("이달의 채용공고 관리");

		jobsCalenderMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsCalender/add"));
		jobsCalenderMenu.add(new MenuItem("상세보기", "/jobsCalender/detail"));
		jobsCalenderMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/jobsCalender/update"));
		jobsCalenderMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsCalender/delete"));

		return jobsCalenderMenu;
	}

	private Menu createExamCalenderMenu() {
		MenuGroup examCalenderMenu = new MenuGroup("이달의 시험일정 관리");

		examCalenderMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examCalender/add"));
		examCalenderMenu.add(new MenuItem("상세보기", "/examCalender/detail"));
		examCalenderMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/examCalender/update"));
		examCalenderMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examCalender/delete"));

		return examCalenderMenu;
	}

	private Menu createInterestMenu() {
		MenuGroup interestMenu = new MenuGroup("관심목록");

		interestMenu.add(createFreeInterestMenu());
		interestMenu.add(createChargeInterestMenu());

		return interestMenu;
	}

	private Menu createFreeInterestMenu() {
		MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");

		freeInterestMenu.add(new MenuItem("조회", ACCESS_GENERAL, "/freeInterest/list"));
		freeInterestMenu.add(new MenuItem("삭제", ACCESS_GENERAL,"/freeInterest/delete"));

		return freeInterestMenu;
	}

	private Menu createChargeInterestMenu() {
		MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");

		chargeInterestMenu.add(new MenuItem("조회", ACCESS_GENERAL, "/chargeInterest/list"));
		chargeInterestMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/chargeInterest/delete"));

		return chargeInterestMenu;
	}

	private Menu createFreeStudyMenu() {
		MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

		freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
		freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/freeStudy/add"));
		freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
		freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
		freeStudyMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/freeStudy/update"));
		freeStudyMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/freeStudy/delete"));

		return freeStudyMenu;
	}

	private Menu createChargeStudyMenu() {
		MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

		chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
		chargeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/chargeStudy/add"));
		chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
		chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
		chargeStudyMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/chargeStudy/update"));
		chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_GENERAL, "/chargeStudy/delete"));

		return chargeStudyMenu;
	}

	private Menu createCommunityMenu() {
		MenuGroup communityMenu = new MenuGroup("커뮤니티");

		communityMenu.add(createCommunityInfoMenu());
		communityMenu.add(createCommunityQaMenu());
		communityMenu.add(createCommunityTalkMenu());

		return communityMenu;
	}

	private Menu createCommunityInfoMenu() {
		MenuGroup communityInfoMenu = new MenuGroup("정보");

		communityInfoMenu.add(new MenuItem("검색", "/communityInfo/search"));
		communityInfoMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityInfo/add"));
		communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
		communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
		communityInfoMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityInfo/update"));
		communityInfoMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/communityInfo/delete"));

		return communityInfoMenu;
	}

	private Menu createCommunityQaMenu() {
		MenuGroup communityQaMenu = new MenuGroup("질문");

		communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
		communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
		communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
		communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
		communityQaMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityQa/update"));
		communityQaMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/communityQa/delete"));

		return communityQaMenu;
	}

	private Menu createCommunityTalkMenu() {
		MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

		communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
		communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
		communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
		communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
		communityTalkMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityTalk/update"));
		communityTalkMenu.add(new MenuItem("삭제", ACCESS_GENERAL, "/communityTalk/delete"));

		return communityTalkMenu;
	}
}
