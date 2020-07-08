package project1.dao.Admin;

import project1.model.*;
import project1.model.vo.admin.*;

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

    List<GoodsSpecVO> getSpecs(String id);

    List<GoodsInfoVO> getGoodsInfo(String id);

    void updateSpecs(List<Spec> specs);

    void updateGoods(Goods goods);

    List<ReplyVO> noReplyMsg();

}
