package project1.service.admin;

import project1.model.Reply;
import project1.model.Type;
import project1.model.bo.admin.GoodsAddBO;
import project1.model.bo.admin.GoodsUpdateBO;
import project1.model.bo.admin.TypeAddBO;
import project1.model.vo.admin.GoodsGetVO;
import project1.model.vo.admin.ReplyVO;
import project1.model.vo.admin.TypeGoodsVO;

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

    void deleteGoods(int id);

    GoodsGetVO getGoodsInfo(String id);

    void updateGoods(GoodsUpdateBO goodsUpdateBO);

    List<ReplyVO> noReplyMsg();
}
