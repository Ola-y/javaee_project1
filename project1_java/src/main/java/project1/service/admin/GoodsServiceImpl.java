package project1.service.admin;

import project1.dao.Admin.GoodsDao;
import project1.dao.Admin.GoodsDaoImpl;
import project1.model.*;
import project1.model.bo.admin.GoodsAddBO;
import project1.model.bo.admin.GoodsUpdateBO;
import project1.model.bo.admin.SpecBO;
import project1.model.bo.admin.TypeAddBO;
import project1.model.vo.admin.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @return
 */
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao=new GoodsDaoImpl();

    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    /**
     * 新增商品表price，stockNum需要通过specBOList运算得到
     * 1.保存数据到商品表
     * 2.拿到商品表刚刚插入的商品id
     * 3. 将该id以及spec数据保存到spec规格表
     * @param goodsAddBO
     */
    @Override
    public void addGoods(GoodsAddBO goodsAddBO) {
        List<SpecBO> specList = goodsAddBO.getSpecList();
        double price=specList.get(0).getUnitPrice();
        int stockNum=specList.get(0).getStockNum();
        for (int i=1;i<specList.size();i++){
            if (price>specList.get(i).getUnitPrice()){
                price=specList.get(i).getUnitPrice();
            }
            if (stockNum<specList.get(i).getStockNum()){
                stockNum=specList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(null,goodsAddBO.getImg(),goodsAddBO.getName(),price,goodsAddBO.getTypeId(),stockNum,goodsAddBO.getDesc());
        goodsDao.addGoods(goods);
        //该id值就是goods表新增的商品id
        int id = goodsDao.lastInsertId();
        //规格表部分业务逻辑
        List<Spec> specs=new ArrayList<>();
        for (SpecBO specBO : specList) {
            Spec spec = new Spec(null, specBO.getSpecName(), specBO.getStockNum(), specBO.getUnitPrice(), id);
            specs.add(spec);
        }
        goodsDao.addSpecs(specs);
    }

    @Override
    public Type addType(TypeAddBO typeAddBO) {
        Type type = new Type();
        type.setName(typeAddBO.getName());
        goodsDao.addType(type);
        return type;
    }

    @Override
    public void deleteType(int typeId) {
        goodsDao.deleteType(typeId);
    }

    @Override
    public void deleteGoods(int id) {
        goodsDao.deleteGoods(id);
    }

    @Override
    public GoodsGetVO getGoodsInfo(String id) {
        //spec规格表
        List<GoodsSpecVO> goodsSpecVOS = goodsDao.getSpecs(id);
        //goods表
        List<GoodsInfoVO> goodsInfoVOS=goodsDao.getGoodsInfo(id);

        GoodsGetVO goodsGetVO =new GoodsGetVO();
        goodsGetVO.setSpecs(goodsSpecVOS);
        goodsGetVO.setGoods(goodsInfoVOS);

       return goodsGetVO;
    }

    @Override
    public void updateGoods(GoodsUpdateBO goodsUpdateBO) {
        List<SpecBO> specList = goodsUpdateBO.getSpecList();
        double price=specList.get(0).getUnitPrice();
        int stockNum=specList.get(0).getStockNum();
        for (int i=1;i<specList.size();i++){
            if (price>specList.get(i).getUnitPrice()){
                price=specList.get(i).getUnitPrice();
            }
            if (stockNum<specList.get(i).getStockNum()){
                stockNum=specList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(goodsUpdateBO.getId(),goodsUpdateBO.getImg(),goodsUpdateBO.getName(),price,goodsUpdateBO.getTypeId(),stockNum,goodsUpdateBO.getDesc());
        goodsDao.updateGoods(goods);

        int id = goodsDao.lastInsertId();

        //规格表部分业务逻辑
        List<Spec> specs=new ArrayList<>();
        for (SpecBO specBO : specList) {
            Spec spec = new Spec(null, specBO.getSpecName(), specBO.getStockNum(), specBO.getUnitPrice(), id);
            specs.add(spec);
        }
        goodsDao.updateSpecs(specs);
    }

    @Override
    public List<ReplyVO> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }


}
