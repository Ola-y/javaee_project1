package project1.controller;

import com.google.gson.Gson;
import project1.model.bo.GoodsAddBO;
import project1.model.Result;
import project1.model.Type;
import project1.model.bo.TypeAddBO;
import project1.model.vo.TypeGoodsVO;
import project1.service.GoodsService;
import project1.service.GoodsServiceImpl;
import project1.utils.FileUploadUtils;
import project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @return
 */
@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService=new GoodsServiceImpl();

    Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("imgUpload".equals(action)){
            imgUpload(request,response);
        }else if ("addGoods".equals(action)){
            addGoods(request,response);
        }else if ("addType".equals(action)){
            addType(request,response);
        }
    }


    /**
     * 新增类目的业务逻辑
     * @param request
     * @param response
     */
    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttpUtils.getRequestBody(request);
        TypeAddBO typeAddBO = gson.fromJson(requestBody, TypeAddBO.class);
        Type type=goodsService.addType(typeAddBO);
        response.getWriter().println(gson.toJson(Result.ok(type)));
    }

    /**
     * 保存商品的业务逻辑
     * 1.获取请求体中的参数
     * 2.处理业务逻辑(重点)
     * 3.做出响应
     * @param request
     * @param response
     * @throws IOException
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsAddBO goodsAddBO = gson.fromJson(requestBody, GoodsAddBO.class);
        goodsService.addGoods(goodsAddBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 新增商品图片的业务逻辑
     * @param request
     * @param response
     * @throws IOException
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file= (String) map.get("file");
        String domain = (String) getServletContext().getAttribute("domain");
        response.getWriter().println(gson.toJson(Result.ok(domain+file)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("getType".equals(action)){
            getType(request,response);
        }else if ("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }else if ("deleteType".equals(action)){
            deleteType(request,response);
        }
    }

    /**
     * 获取某个分类下的商品信息
     * 1.获取分类id-----request.getParameter()
     * 2.数据库查询
     * 3.按照一定的数据结构返回
     * @param request
     * @param response
     * @throws IOException
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String typeId=request.getParameter("typeId");
        List<TypeGoodsVO>  goodsVOS=goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsVOS)));
    }

    /**
     * 加载商品的所有类
     * @param request
     * @param response
     * @throws IOException
     */
    private void getType(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<Type>  typeList=goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }

    /**
     * 删除指定类目
     * @param request
     * @param response
     */
    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int typeId= Integer.parseInt(request.getParameter("typeId"));
        goodsService.deleteType(typeId);
        response.getWriter().println(gson.toJson(Result.ok()));
    }
}
