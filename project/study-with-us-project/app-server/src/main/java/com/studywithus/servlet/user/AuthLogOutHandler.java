package com.studywithus.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.menu.Menu;

@WebServlet("/member/logout")
public class AuthLogOutHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    AuthLogInHandler.loginUser.setUserAccessLevel(AuthLogInHandler.getUserAccessLevel());

    AuthLogInHandler.loginUser = null;
    AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    response.sendRedirect("http://localhost:8080/swu/index");
  }
}
