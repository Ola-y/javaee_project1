package project1.controller.Admin;

import com.google.gson.Gson;
import project1.model.Result;
import project1.model.User;
import project1.service.admin.AdminUserService;
import project1.service.admin.AdminUserServiceImpl;

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if ("allUser".equals(action)){
            allUser(request,response);
        }else if (("searchUser").equals(action)){
            searchUser(request,response);  //条件查询
        }else if (("deleteUser").equals(action)){
            deleteUser(request,response);  //删除用户
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

    /**
     * 条件查询
     * @param request
     * @param response
     * @throws IOException
     */
    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String word=request.getParameter("word");
        List<User> user=adminUserService.searchUser(word);
        response.getWriter().println(gson.toJson(Result.ok(user)));
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id= Integer.parseInt(request.getParameter("id"));
        adminUserService.deleteUser(id);
        Result result=new Result();
        result.setMessage("删除成功!");
        response.getWriter().println(gson.toJson(Result.ok()));
    }
}
