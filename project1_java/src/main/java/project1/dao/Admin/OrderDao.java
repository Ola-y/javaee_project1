package project1.dao.Admin;

import project1.model.bo.admin.OrdersChangeBO;
import project1.model.bo.admin.OrdersPageBO;
import project1.model.vo.admin.OrderVO;
import project1.model.vo.admin.PageOrdersInfoVO;

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
