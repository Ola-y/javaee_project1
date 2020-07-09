package project1.dao.Admin;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import project1.model.*;
import project1.model.enumaration.MsgState;
import project1.model.vo.admin.GoodsInfoVO;
import project1.model.vo.admin.GoodsSpecVO;
import project1.model.vo.admin.TypeGoodsVO;
import project1.utils.DruidUtils;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

/**
 * @param
 * @return
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> typeList = null;
        try {
            typeList = runner.query("select*from type", new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<TypeGoodsVO> goodsVOList = null;
        try {
            goodsVOList = runner.query("select id,img,name,price,stockNum  from goods where typeId = ?", new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class), typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsVOList;
    }

    @Override
    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            //返回值表示影响的行数
            runner.update("insert into goods values (null,?,?,?,?,?,?)",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int lastInsertId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query=null;
        try {
            query = runner.query("select last_insert_id()", new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }


    /**
     *
     * 添加规格表
     * @param specs
     */
    @Override
    public void addSpecs(List<Spec> specs) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specs) {
            try {
                runner.update("insert into spec values (null,?,?,?,?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Type addType(Type type) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Type types= null;
        try {
            types = runner.insert("insert into type (name) values (?)",new BeanHandler<Type>(Type.class),
                    type.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    /**
     * 删除类目
     * @param typeId
     */
    @Override
    public void deleteType(int typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from type where id=?",typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商品
     * @param id
     */
    @Override
    public void deleteGoods(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from goods where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得规格表spec信息
     * @return
     */
    @Override
    public List<GoodsSpecVO> getSpecs(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<GoodsSpecVO> goodsSpecVOS= null;
        try {
            goodsSpecVOS = runner.query("select*from spec where id = ?", new BeanListHandler<GoodsSpecVO>(GoodsSpecVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsSpecVOS;
    }

    @Override
    public List<GoodsInfoVO> getGoodsInfo(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<GoodsInfoVO> goodsInfoVOS= null;
        try {
            goodsInfoVOS = runner.query("select*from goods where id = ?", new BeanListHandler<GoodsInfoVO>(GoodsInfoVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsInfoVOS;
    }

    /**
     * 更新spec规格表
     * @param specs
     */
    @Override
    public void updateSpecs(List<Spec> specs) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specs) {
            try {
                runner.update("update spec set specName = ? ,stockName = ?, unitPrice = ? where goodsId=? ",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update goods set img = ? ,name = ?, price = ? ,typeId = ? ,stockNum = ?, `desc` = ? where id=?",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc(),
                    goods.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 未回复页面user的名字
     * @param userId
     * @return
     */
    @Override
    public String userMsg(int userId) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select nickname from user where id = ?", new BeanHandler<User>(User.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.getNickname();
    }

    @Override
    public String goodsMsg(int goodsId) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        Goods goods = null;
        try {
            goods = runner.query("select name from goods where id = ?", new BeanHandler<Goods>(Goods.class), goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods.getName();
    }



    /**
     * 未回复页面的显示
     * @return
     */
    @Override
    public List<Msg> ReplyMsg(Integer code) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        List<Msg> msgList = null;
        try {
            msgList = runner.query("select * from msg where state = ?", new BeanListHandler<Msg>(Msg.class), code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msgList;
    }

    /**
     * 信息回复功能
     * @param msg
     */
    @Override
    public void reply(Msg msg) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update msg set `replyContent` = ? , `state` = ? where `id` = ?",
                    msg.getReplyContent(),
                    MsgState.REPLIED.getCode(),
                    msg.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
