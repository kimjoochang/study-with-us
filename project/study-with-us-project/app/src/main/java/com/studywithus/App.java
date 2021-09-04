package com.studywithus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteHandler;
import com.studywithus.handler.ChargeStudyDetailHandler;
import com.studywithus.handler.ChargeStudyListHandler;
import com.studywithus.handler.ChargeStudyUpdateHandler;
import com.studywithus.handler.InterestChargeAddHandler;
import com.studywithus.handler.InterestChargeDeleteHandler;
import com.studywithus.handler.InterestFreeAddHandler;
import com.studywithus.handler.InterestFreeDeleteHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class App {
	List<NewMember> memberList = new LinkedList<>();
	List<FreeStudy> freeInterestList = new ArrayList<>();
	List<ChargeStudy> chargeInterestList = new ArrayList<>();
	List<ChargeStudy> chargeStudyList = new ArrayList<>();

	AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
	AuthLogoutHandler authLogoutHandler = new AuthLogoutHandler();

	InterestFreeAddHandler interestFreeAddHandler = new InterestFreeAddHandler(freeInterestList);
	InterestFreeDeleteHandler interestFreeDeleteHandler = new InterestFreeDeleteHandler(freeInterestList);
	InterestChargeAddHandler interestChargeAddHandler = new InterestChargeAddHandler(chargeInterestList);
	InterestChargeDeleteHandler interestChargeDeleteHandler = new InterestChargeDeleteHandler(chargeInterestList);

	ChargeStudyAddHandler chargeStudyAddHandler = new ChargeStudyAddHandler(chargeStudyList);
	ChargeStudyListHandler chargeStudyListHandler = new ChargeStudyListHandler(chargeStudyList);
	ChargeStudyUpdateHandler chargeStudyUpdateHandler = new ChargeStudyUpdateHandler(chargeStudyList);
	ChargeStudyDetailHandler chargeStudyDetailHandler = new ChargeStudyDetailHandler(chargeStudyList);
	ChargeStudyDeleteHandler chargeStudyDeleteHandler = new ChargeStudyDeleteHandler(chargeStudyList);


	public static void main(String[] args) {
		App app = new App(); 
		app.service();
	}

	void service() {
		createMenu().execute();
		Prompt.close();
	}
	Menu createMenu() {
		MenuGroup mainMenuGroup = new MenuGroup("메인");
		mainMenuGroup.setPrevMenuTitle("종료");

		mainMenuGroup.add(new Menu("로그인", Menu.ENABLE_LOGOUT) {
			@Override
			public void execute() {
				authLoginHandler.execute(); 
			}
		});

		mainMenuGroup.add(new Menu("로그아웃", Menu.ENABLE_LOGIN) {
			@Override
			public void execute() {
				authLogoutHandler.execute(); 
			}
		});

		MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");
		mainMenuGroup.add(freeStudyMenu);

		freeStudyMenu.add(new Menu("생성") {
			@Override 
			public void execute() {
				freeStudyAddHandler.execute(); 
			}});

		freeStudyMenu.add(new Menu("조회") {
			@Override 
			public void execute() {
				freeStudyListHandler.execute(); 
			}});

		freeStudyMenu.add(new Menu("상세보기") {
			@Override 
			public void execute() {
				freeStudyDetailHandler.execute(); 
			}});

		freeStudyMenu.add(new Menu("수정") {
			@Override 
			public void execute() {
				freeStudyUpdateHandler.execute(); 
			}});

		freeStudyMenu.add(new Menu("삭제") {
			@Override 
			public void execute() {
				freeStudyDeleteHandler.execute(); 
			}});


		MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");
		mainMenuGroup.add(chargeStudyMenu);

		chargeStudyMenu.add(new Menu("생성") {
			@Override
			public void execute() {
				chargeStudyAddHandler.execute(); 
			}});

		chargeStudyMenu.add(new Menu("조회") {
			@Override
			public void execute() {
				chargeStudyListHandler.execute(); 
			}});

		chargeStudyMenu.add(new Menu("상세보기") {
			@Override
			public void execute() {
				chargeStudyDetailHandler.execute(); 
			}});

		chargeStudyMenu.add(new Menu("수정") {
			@Override
			public void execute() {
				chargeStudyUpdateHandler.execute(); 
			}});

		chargeStudyMenu.add(new Menu("삭") {
			@Override
			public void execute() {
				chargeStudyDeleteHandler.execute(); 
			}});

		MenuGroup interestMenu = new MenuGroup("관심목록");
		mainMenuGroup.add(interestMenu);

		MenuGroup freeInterestMenu = new MenuGroup("무료스터디 관심목록");
		interestMenu.add(freeInterestMenu);

		freeInterestMenu.add(new Menu("조회") {
			@Override
			public void execute() {
				interestFreeAddHandler.execute(); 
			}});

		freeInterestMenu.add(new Menu("삭제") {
			@Override
			public void execute() {
				interestFreeDeleteHandler.execute(); 
			}});

		MenuGroup chargeInterestMenu = new MenuGroup("유료스터디 관심목록");
		interestMenu.add(chargeInterestMenu);

		chargeInterestMenu.add(new Menu("조회") {
			@Override
			public void execute() {
				interestFreeAddHandler.execute(); 
			}});

		chargeInterestMenu.add(new Menu("삭제") {
			@Override
			public void execute() {
				interestFreeDeleteHandler.execute(); 
			}});


		return mainMenuGroup;
	}
}