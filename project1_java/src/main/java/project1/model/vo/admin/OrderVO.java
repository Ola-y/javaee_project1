package project1.model.vo.admin;

import java.util.List;

/**
 * @param
 * @return
 */
public class OrderVO {
    private Integer id;

    private Double amount;

    private Integer goodsDetailId;

    private String goods;

    private List<OrderStateVO> state;

    private List<OrderSpecVO> spec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public List<OrderStateVO> getStates() {
        return state;
    }

    public void setState(List<OrderStateVO> state) {
        this.state = state;
    }

    public List<OrderSpecVO> getSpec() {
        return spec;
    }

    public void setSpec(List<OrderSpecVO> spec) {
        this.spec = spec;
    }
}
