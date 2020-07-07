package project1.service;

import project1.dao.OrderDao;
import project1.dao.OrderDaoImpl;
import project1.model.bo.OrdersChangeBO;
import project1.model.bo.OrdersPageBO;
import project1.model.vo.OrderChangeVO;
import project1.model.vo.OrderVO;
import project1.model.vo.PageOrdersInfoVO;
import project1.model.vo.PageOrdersVO;

import java.util.List;

/**
 * @param
 * @return
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();

    @Override
    public PageOrdersVO odersByPage(OrdersPageBO orderBO) {

        int totalCounts=orderDao.getTotalCounts(orderBO);

        //查询当前分页的结果 page1 1-5
        List<PageOrdersInfoVO> ordersInfoVOS=orderDao.ordersByPage(orderBO);

        PageOrdersVO pageOrdersVO = new PageOrdersVO();
        pageOrdersVO.setTotal(totalCounts);
        pageOrdersVO.setOrders(ordersInfoVOS);
        return pageOrdersVO;
    }

    @Override
    public void changeOrder(OrdersChangeBO ordersChangeBO) {

         orderDao.changeOrder(ordersChangeBO);
    }

    @Override
    public List<OrderVO> order(String id) {
        return orderDao.order(id);
    }

    @Override
    public void deleteOrder(String id) {
        orderDao.deleteOrder(id);
    }
}
