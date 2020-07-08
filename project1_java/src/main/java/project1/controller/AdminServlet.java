package project1.controller;

import com.google.gson.Gson;
import project1.model.Admin;
import project1.model.Result;
import project1.model.bo.*;
import project1.model.vo.AdminLoginVO;
import project1.service.AdminService;
import project1.service.AdminServiceImpl;
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
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService=new AdminServiceImpl();

    Gson gson=new Gson();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if ("login".equals(action)){
            login(request,response);
        }else if ("getSearchAdmins".equals(action)){ //条件查询
            getSearchAdmins(request,response);
        }else if ("addAdminss".equals(action)){      //添加管理员
            addAdminss(request,response);
        }else if ("updateAdminss".equals(action)){   //修改全部信息
            updateAdminss(request,response);
        }else if ("changePwd".equals(action)){       //修改密码
            changePwd(request,response);
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
     * 添加admin管理员信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminAddBO adminAddBO = gson.fromJson(requestBody, AdminAddBO.class);
        Admin add=adminService.addAdminss(adminAddBO);
        Result result = new Result();
        if (add!=null) {
            result.setCode(0);
            response.getWriter().println(gson.toJson(result));
        }else{
            result.setCode(10000);
            result.setMessage("该账号不允许重复使用");
            response.getWriter().println(gson.toJson(result));
        }
    }


    /**
     * 修改admin管理员密码信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminChangeBO changeBO = gson.fromJson(requestBody, AdminChangeBO.class);
        int changePwd=adminService.changePwd(changeBO);
        String newPwd=changeBO.getNewPwd();
        String confirmPwd=changeBO.getConfirmPwd();
        Result result = new Result();
        if (newPwd.equals(confirmPwd)) {
            if (changePwd != 0) {
                result.setCode(0);
                response.getWriter().println(gson.toJson(result));
            } else {
                result.setCode(10000);
                result.setMessage("旧密码错误！");
                response.getWriter().println(gson.toJson(result));
            }
        }else {
            result.setCode(10000);
            result.setMessage("新密码需要一致！");
            response.getWriter().println(gson.toJson(result));
        }

    }

    /**
     * 修改admin管理员信息
     * 1.查询数据库，修改数据返回
     * 2.做出响应
     * @param request
     * @param response
     */
    private void updateAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminUpdateBO updateBO = gson.fromJson(requestBody, AdminUpdateBO.class);
        int update=adminService.updateAdminss(updateBO);
        Result result = new Result();
        if (update!=0){
            result.setCode(0);
            response.getWriter().println(gson.toJson(result));
        }else{
            result.setCode(10000);
            result.setMessage("修改错误！");
            response.getWriter().println(gson.toJson(result));
        }
    }

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
        response.getWriter().println(gson.toJson(Result.ok(admins)));
    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if ("allAdmins".equals(action)){
            allAdmins(request,response);
        }else if ("deleteAdmins".equals(action)){
            deleteAdmins(request,response);
        }
    }

    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response)throws  IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        adminService.deletedmins(id);
        response.getWriter().println(Result.ok());
        response.getWriter().println("删除成功！");
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