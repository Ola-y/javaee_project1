package project1.service;

import project1.model.Type;
import project1.model.bo.GoodsAddBO;
import project1.model.bo.TypeAddBO;
import project1.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface GoodsService {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(GoodsAddBO goodsAddBO);

    Type addType(TypeAddBO typeAddBO);

    void deleteType(int typeId);
}
