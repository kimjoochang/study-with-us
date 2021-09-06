package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyPayHandler extends AbstractChargeStudyHandler{


	public ChargeStudyPayHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}


	ChargeStudy study;
	int input;
	// 유료 스터디 상세보기
	@Override
	public void execute() {
		while(true) {
			if (input == 1) {
				System.out.print("[유료 스터디 / 결제]");

				String input1 = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N)");
				if (input1.equalsIgnoreCase("n") || input1.length() == 0) {
					System.out.println(" 유료 스터디 결제를 취소하셨습니다.");
					continue;
				}
				else {
					StringBuffer dot = new StringBuffer("");

					System.out.print("------------------------------------");
					System.out.println("\n"
							+ "(＼(＼     \n"
							+ "(  -.- )~♥\n"
							+ " O_(\")(\")");
					System.out.println("------------------------------------");
					System.out.print("결제중");
					for(int i = 0; i < 5; i++) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
						System.out.print(dot.append("♥"));
					}
					System.out.println();
					System.out.println("유료 스터디 결제가 완료 되었습니다.\n");
				}
				return;
			} else if (input == 2) {
				chargeInterestAddHandler.execute(study);
				return;
			}
		}
	}
}
