package project1.dao;

import project1.model.bo.OrdersChangeBO;
import project1.model.bo.OrdersPageBO;
import project1.model.vo.OrderChangeVO;
import project1.model.vo.OrderVO;
import project1.model.vo.PageOrdersInfoVO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface OrderDao {
    List<PageOrdersInfoVO> ordersByPage(OrdersPageBO orderBO);

    int getTotalCounts(OrdersPageBO orderBO);



    void changeOrder(OrdersChangeBO orderChangeBO);

    List<OrderVO> order(String id);

    void deleteOrder(String id);
}
