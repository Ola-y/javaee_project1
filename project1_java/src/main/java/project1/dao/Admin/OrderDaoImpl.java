package project1.dao.Admin;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import project1.model.bo.admin.OrdersChangeBO;
import project1.model.bo.admin.OrdersPageBO;
import project1.model.vo.admin.OrderVO;
import project1.model.vo.admin.PageOrdersInfoVO;
import project1.utils.DruidUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @return
 */
public class OrderDaoImpl implements OrderDao {

    /**
     * 分页查询的sql语句
     * select*from where....limit ? offset ?
     * @param orderBO
     * @return
     */
    @Override
    public List<PageOrdersInfoVO> ordersByPage(OrdersPageBO orderBO) {
        Map<String,Object> results=getDynamicSql(orderBO);
        String sql= (String) results.get("sql");
        List<Object> params= (List<Object>) results.get("params");
        String prefix_sql="select id,userId,goodsDetailId,goods,spec,num as goodsNum,amount,stateId,nickname,name,address,phone ";
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        //当前页数个数
        //偏移量(当前页数1，偏移量0，当前页数2，偏移量是第一页的个数)
        params.add(orderBO.getPagesize());
        params.add((orderBO.getCurrentPage()-1)*orderBO.getPagesize());
        List<PageOrdersInfoVO> ordersInfoVOS= null;
        try {
            ordersInfoVOS = runner.query(prefix_sql+sql+" limit ? offset ?",new BeanListHandler<PageOrdersInfoVO>(PageOrdersInfoVO.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersInfoVOS;
    }

    /**
     * select count(id) from orders where .....
     * @param orderBO
     * @return
     */
    @Override
    public int getTotalCounts(OrdersPageBO orderBO) {
        Map<String, Object> results = getDynamicSql(orderBO);
        String sql= (String) results.get("sql");
        List<Object> params = (List<Object>) results.get("params");
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        String prefix_sql="select count(id) ";
        Long query= null;
        try {
            query = runner.query(prefix_sql+sql,new ScalarHandler<>(),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public void changeOrder(OrdersChangeBO orderChangeBO) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update orders set num = ?,goodsId =?, stateId = ? where id = ?",
                    orderChangeBO.getNum(),
                    orderChangeBO.getSpec(),
                    orderChangeBO.getState(),
                    orderChangeBO.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderVO> order(String id) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        List<OrderVO> orderVOList= null;
        try {
            orderVOList = runner.query("select * from orders where id = ?",new BeanListHandler<OrderVO>(OrderVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderVOList;
    }

    @Override
    public void deleteOrder(String id) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from orders where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getDynamicSql(OrdersPageBO orderBO) {
        String base=" from orders where 1=1 ";
        List<Object> list=new ArrayList<>();
        if (orderBO.getState()!=-1){
            base=base+" and stateId = ?";
            list.add(orderBO.getState());
        }
        if (!StringUtils.isEmpty(orderBO.getMoneyLimit1())){
            base=base+" and amount <= ?";
            list.add(Double.parseDouble(orderBO.getMoneyLimit1()));
        }
        if (!StringUtils.isEmpty(orderBO.getMoneyLimit2())){
            base=base+" and amount >= ?";
            list.add(Double.parseDouble(orderBO.getMoneyLimit2()));
        }
        if (!StringUtils.isEmpty(orderBO.getGoods())){
            base=base+" and goods like ?";
            list.add("%"+orderBO.getGoods()+"%");
        }
        if (!StringUtils.isEmpty(orderBO.getAddress())){
            base=base+" and address like ?";
            list.add("%"+orderBO.getAddress()+"%");
        }
        if (!StringUtils.isEmpty(orderBO.getName())){
            base=base+" and name like ?";
            list.add("%"+orderBO.getName()+"%");
        }
        if (!StringUtils.isEmpty(orderBO.getGoods())){
            base=base+" and id = ?";
            list.add(Integer.parseInt(orderBO.getId()));
        }

        Map<String,Object> map=new HashMap<>();
        map.put("sql",base);
        map.put("params",list);
        return map;
    }
}
