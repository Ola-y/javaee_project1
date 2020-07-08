package project1.controller.Mall;

import com.google.gson.Gson;
import project1.model.Result;
import project1.model.User;
import project1.model.bo.mall.UserLoginBO;
import project1.model.bo.mall.UserSignupBO;
import project1.model.vo.mall.UserLoginVO;
import project1.service.mall.UserService;
import project1.service.mall.UserServiceImpl;
import project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @param
 * @return
 */
@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService =new UserServiceImpl();

     Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");
        if ("login".equals(action)){
            login(request,response);
        }else if ("signup".equals(action)){
            signup(request,response);
        }
    }

    /**
     *前台用户注册
     * @param request
     * @param response
     */
    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttpUtils.getRequestBody(request);
        UserSignupBO signupBO = gson.fromJson(requestBody, UserSignupBO.class);
        User signup=userService.signup(signupBO);
        Result result = new Result();
        if (signup!=null) {
            result.setMessage("注册成功");
            response.getWriter().println(gson.toJson(Result.ok(result)));
        }else{
            result.setCode(10000);
            result.setMessage("该账号已被使用");
            response.getWriter().println(gson.toJson(result));
        }
    }

    /**
     * 前台用户登录
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String requestBody= HttpUtils.getRequestBody(request);
        UserLoginBO userLoginBO = gson.fromJson(requestBody, UserLoginBO.class);
        User login=userService.login(userLoginBO);
        if (login != null){
            request.getSession().setAttribute("admin",login);
            UserLoginVO  userLoginVO= new UserLoginVO();
            //token主要有效，name可有可无
            userLoginVO.setCode(0);
            userLoginVO.setName(login.getNickname());
            userLoginVO.setToken(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(userLoginVO)));
        }else{
            response.getWriter().println(Result.error("账户密码错误"));
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");
    }
}
