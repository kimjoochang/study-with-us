package com.studywithus.servlet.user;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  // 아이디 체크
  @PostMapping("/emailCheck")
  @ResponseBody
  public int idCheck(@RequestParam("email") String email){
    logger.info("userEmailCheck 진입"); //확인용(지울거)
    logger.info("전달받은 email:"+ email); //확인용(지울거)
    int cnt = userService.emailCheck(email);
    logger.info("확인 결과:"+ cnt); //확인용(지울거)
    return cnt;
  }
}
