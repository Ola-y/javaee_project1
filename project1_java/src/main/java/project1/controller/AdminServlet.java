package project1.controller;

import com.google.gson.Gson;
import project1.model.Admin;
import project1.model.Result;
import project1.model.bo.AdminLoginBO;
import project1.model.bo.AdminSearchBO;
import project1.model.vo.AdminLoginVO;
import project1.service.AdminService;
import project1.service.AdminServiceImpl;
import project1.utils.HttpUtils;

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
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService=new AdminServiceImpl();

    Gson gson=new Gson();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if ("login".equals(action)){
            login(request,response);
        }else if ("updateAdmins".equals(action)){
            updateAdmins(request,response);
        }else if ("getSearchAdmins".equals(action)){
            getSearchAdmins(request,response);
        }
    }

    /**
     * 管理员登录的具体业务
     * @param request
     * @param response
     */

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody=HttpUtils.getRequestBody(request);
        AdminLoginBO adminLoginBO = gson.fromJson(requestBody,AdminLoginBO.class);
        //响应
        Admin login=adminService.login(adminLoginBO);
        Result result = new Result();
        if (login != null){
            AdminLoginVO adminLoginVO = new AdminLoginVO();
            //token主要有效，name可有可无
            adminLoginVO.setName(login.getNickname());
            adminLoginVO.setToken(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(adminLoginVO)));
        }else{
            response.getWriter().println(Result.error("账户密码错误"));
        }
    }

    /**
     * 修改admin管理员信息
     * 1.查询数据库，修改数据返回
     * 2.做出响应
     * @param request
     * @param response
     */
    private void updateAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {}

    /**
     * 条件查询admin管理员信息
     * 1.查询数据库，修改数据返回
     * 2.做出响应
     * @param request
     * @param response
     */
    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminSearchBO adminSearchBO=gson.fromJson(requestBody, AdminSearchBO.class);
        List<Admin> admins=adminService.getSearchAdmins(adminSearchBO);
        response.getWriter().println(Result.ok(admins));
    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if ("allAdmins".equals(action)){
            allAdmins(request,response);
        }
    }


    /**
     * 显示所有的admin账户信息
     * 1.查询数据库，返回数据
     * 2.做出响应
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList=adminService.allAdmins();
        Result  result=new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}