package project1.controller.Admin;

import com.google.gson.Gson;
import project1.model.Goods;
import project1.model.Reply;
import project1.model.bo.admin.GoodsAddBO;
import project1.model.Result;
import project1.model.Type;
import project1.model.bo.admin.GoodsUpdateBO;
import project1.model.bo.admin.TypeAddBO;
import project1.model.vo.admin.GoodsGetVO;
import project1.model.vo.admin.ReplyVO;
import project1.model.vo.admin.TypeGoodsVO;
import project1.service.admin.GoodsService;
import project1.service.admin.GoodsServiceImpl;
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
        }else if ("updateGoods".equals(action)){
            updateGoods(request,response);
        }
    }

    /**
     * 编辑商品
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsUpdateBO goodsUpdateBO=gson.fromJson(requestBody,GoodsUpdateBO.class);
        goodsService.updateGoods(goodsUpdateBO);
        response.getWriter().println(gson.toJson(Result.ok()));
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
        }else if ("deleteGoods".equals(action)){
            deleteGoods(request,response);
        }else if ("getGoodsInfo".equals(action)){
            getGoodsInfo(request,response);
        }else if ("noReplyMsg".equals(action)){
            noReplyMsg(request,response);
        }
    }

    /**
     * 获取未回复页面
     * @param request
     * @param response
     */
    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<ReplyVO> replyVOS = goodsService.noReplyMsg();
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 获取单个商品信息
     * @param request
     * @param response
     */
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id= request.getParameter("id");
        GoodsGetVO goodsGetVOS=goodsService.getGoodsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsGetVOS)));
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

    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        goodsService.deleteGoods(id);
        Result result=new Result();
        result.setMessage("删除成功!");
        response.getWriter().println(gson.toJson(Result.ok(result)));
    }
}
