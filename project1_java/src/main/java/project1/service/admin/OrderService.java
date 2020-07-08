package project1.service.admin;

import project1.model.bo.admin.OrdersChangeBO;
import project1.model.bo.admin.OrdersPageBO;
import project1.model.vo.admin.OrderVO;
import project1.model.vo.admin.PageOrdersVO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface OrderService {

    PageOrdersVO odersByPage(OrdersPageBO orderBO);

    void changeOrder(OrdersChangeBO ordersChangeBO);

    List<OrderVO> order(String id);

    void deleteOrder(String id);
}
