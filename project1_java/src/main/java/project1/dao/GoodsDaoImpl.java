package project1.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import project1.model.Goods;
import project1.model.Spec;
import project1.model.Type;
import project1.model.vo.TypeGoodsVO;
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
     * 规格表
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

    @Override
    public void deleteType(int typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from type where id=?",typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
