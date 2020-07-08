package project1.controller.Admin;

import com.google.gson.Gson;
import project1.model.Result;
import project1.model.bo.admin.OrdersChangeBO;
import project1.model.bo.admin.OrdersPageBO;
import project1.model.vo.admin.OrderVO;
import project1.model.vo.admin.PageOrdersVO;
import project1.service.admin.OrderService;
import project1.service.admin.OrderServiceImpl;
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
@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService=new OrderServiceImpl();

    private Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");
        if ("ordersByPage".equals(action)){
            ordersByPage(request,response);
        }else if ("changeOrder".equals(action)){
            changeOrder(request,response);
        }
    }

    /**
     * 编辑订单
     * @param request
     * @param response
     * @throws IOException
     */
    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttpUtils.getRequestBody(request);
        OrdersChangeBO ordersChangeBO = gson.fromJson(requestBody, OrdersChangeBO.class);
        orderService.changeOrder(ordersChangeBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     *订单查询
     * @param request
     * @param response
     * @throws IOException
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttpUtils.getRequestBody(request);
        OrdersPageBO orderBO = gson.fromJson(requestBody, OrdersPageBO.class);
        PageOrdersVO orders=orderService.odersByPage(orderBO);
        response.getWriter().println(gson.toJson(Result.ok(orders)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");
        if ("order".equals(action)){
            order(request,response);
        }else if ("deleteOrder".equals(action)){
            deleteOrder(request,response);
        }
    }

    /**
     * 删除订单
     * @param request
     * @param response
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id=request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 显示编辑订单页面
     * @param request
     * @param response
     */
    private void order(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String id=request.getParameter("id");
        List<OrderVO> orderVOS=orderService.order(id);
        response.getWriter().println(gson.toJson(Result.ok(orderVOS)));
    }
}
