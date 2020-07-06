package project1.controller;

import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import project1.model.Admin;
import project1.model.Result;
import project1.model.User;
import project1.model.bo.UserSearchBO;
import project1.service.AdminService;
import project1.service.AdminServiceImpl;
import project1.service.AdminUserService;
import project1.service.AdminUserServiceImpl;
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
@WebServlet("/api/admin/user/*")
public class AdminUserServlet extends HttpServlet {

    private AdminUserService adminUserService=new AdminUserServiceImpl();

    Gson gson=new Gson();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        String word = null;
        word = request.getParameter(word);
        if (("searchUser?word="+word).equals(action)){
            searchUser(request,response);  //条件查询
        }
    }

    /**
     * 条件查询
     * @param request
     * @param response
     * @throws IOException
     */
    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        UserSearchBO userSearchBO = gson.fromJson(requestBody, UserSearchBO.class);
        List<User> users=adminUserService.searchUser(userSearchBO);
        response.getWriter().println(gson.toJson(Result.ok(users)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if ("allUser".equals(action)){
            allUser(request,response);
        }
    }

    /**
     *
     * 显示所有User用户信息
     * @param request
     * @param response
     */
    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<User> adminUserList=adminUserService.allUser();
        Result result=new Result();
        result.setCode(0);
        result.setData(adminUserList);
        response.getWriter().println(gson.toJson(result));
    }
}
