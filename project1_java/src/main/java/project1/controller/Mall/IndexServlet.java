package project1.controller.Mall;

import com.google.gson.Gson;
import project1.model.Result;
import project1.service.admin.GoodsService;
import project1.service.admin.GoodsServiceImpl;

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
@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {

    private GoodsService goodsService=new GoodsServiceImpl();

    private Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index/", "");
        if ("getType".equals(action)){
            getType(request,response);
        }
    }

    private void getType(HttpServletRequest request, HttpServletResponse response)throws IOException {
        goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok()));
    }
}
