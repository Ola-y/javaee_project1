package project1.service;

import project1.model.bo.OrdersChangeBO;
import project1.model.bo.OrdersPageBO;
import project1.model.vo.OrderVO;
import project1.model.vo.PageOrdersVO;

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
