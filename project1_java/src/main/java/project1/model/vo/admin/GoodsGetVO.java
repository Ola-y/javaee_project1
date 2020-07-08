package project1.model.vo.admin;

import java.util.List;

/**
 * @param
 * @return
 */
public class GoodsGetVO {
    private List<GoodsSpecVO> specs;

    private List<GoodsInfoVO> goods;

    public List<GoodsSpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<GoodsSpecVO> specs) {
        this.specs = specs;
    }

    public List<GoodsInfoVO> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfoVO> goods) {
        this.goods = goods;
    }
}
