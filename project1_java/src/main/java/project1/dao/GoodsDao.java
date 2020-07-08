package project1.dao;

import project1.model.Goods;
import project1.model.Spec;
import project1.model.Type;
import project1.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);


    int lastInsertId();

    void addSpecs(List<Spec> specs);

    Type addType(Type type);

    void deleteType(int typeId);

    void deleteGoods(int id);
}
