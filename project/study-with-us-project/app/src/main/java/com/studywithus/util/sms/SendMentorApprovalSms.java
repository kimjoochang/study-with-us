package com.studywithus.util.sms;

import java.util.HashMap;
import org.json.simple.JSONObject;
import com.studywithus.domain.MentorApplicationForm;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SendMentorApprovalSms {

  public static void execute(MentorApplicationForm mentorName) {

    String api_key = "NCSB18VU1KFIH5EX"; // 개인(개발자) 아이디
    String api_secret = "9MVAVYHXP23WHOXKZJ9MYXTXAFTXYMFX"; // 개인(개발자) 비밀번호

    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();

    params.put("to", mentorName.getMentorMember().getPhoneNumber()); // 수신인(회원)
    params.put("from", "01055293734"); // 발신인(개발자, 위의 api_key 정보와 일치해야 함)
    params.put("type", "SMS"); // 문자메시지 타입(ex. SMS, MMS 등 지정 가능)

    // 발신할 문자 내용
    params.put("text", "[스터디위더스] " + mentorName.getMentorMember().getName() + "님, 멘토 신청 승인이 완료되었습니다.");

    params.put("app_version", "test app 1.2"); // application name and version

    try {

      JSONObject obj = coolsms.send(params);
      System.out.println(obj.toString());

    } catch (CoolsmsException e) {

      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
