package project1.controller;

import com.google.gson.Gson;
import project1.model.Result;
import project1.model.User;
import project1.model.bo.UserLoginBO;
import project1.model.vo.UserLoginVO;
import project1.service.UserService;
import project1.service.UserServiceImpl;
import project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @param
 * @return
 */
@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

     Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");
        if ("login".equals(action)){
            login(request,response);
        }
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        String requestBody = HttpUtils.getRequestBody(request);
        UserLoginBO loginBO = gson.fromJson(requestBody, UserLoginBO.class);
        User login=userService.login(loginBO);
        if (login!=null){
            UserLoginVO userLoginVO = new UserLoginVO();
            userLoginVO.setToken(login.getNickname());
            userLoginVO.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(userLoginVO)));
        }else {
            response.getWriter().println(gson.toJson(Result.error("账户密码错误")));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
