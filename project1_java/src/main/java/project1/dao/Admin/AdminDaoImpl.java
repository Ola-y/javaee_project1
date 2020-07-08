package project1.dao.Admin;
;
import com.alibaba.druid.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import project1.model.Admin;
import project1.utils.DruidUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @return
 */
public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin login(Admin admin) {
        //数据库查询
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query= null;
        try {
            query = runner.query("select*from admin where email = ? and password = ?",new BeanHandler<>(Admin.class),admin.getEmail(),admin.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins= null;
        try {
            admins = runner.query("select*from admin",new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    /**
     * 添加
     * @param admin
     * @return
     */
    @Override
    public Admin addAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admins= null;
        try {
            admins = runner.insert("insert into admin(email,password,nickname) values(?,?,?)",new BeanHandler<>(Admin.class), admin.getEmail(), admin.getPassword(), admin.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public int changePwd(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            int query=runner.update("update admin set password=? where nickname=? ",admin.getNewPwd(),admin.getNickname());
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            int query=runner.update("update admin set email=?,password=?,nickname=? where id=?",admin.getEmail(),admin.getPassword(),admin.getNickname(),admin.getId());
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deletedmins(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from admin where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 1.当账号搜索栏有数据：select * from admin where email like ?
     * 2.当昵称搜索栏有数据: select * from admin where nickname like ?
     * 3.当账号和昵称搜索栏都有数据：select * from admin where email like ? and nickname like ?
     * @param admin
     * @return
     */
    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        Map<String,Object> result=getDynamicSql(admin);
        String sql= (String) result.get("sql");
        List<String> params= (List<String>) result.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins= null;
        try {
            admins = runner.query(sql,new BeanListHandler<Admin>(Admin.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    /**
     * 动态sql
     * @param admin
     * @return
     */
    private Map<String, Object> getDynamicSql(Admin admin) {
        String base="select * from admin where 1=1 ";
        List<String> params =new ArrayList<>();
        if (!StringUtils.isEmpty(admin.getEmail())){
            base=base+"and email like ?";
            params.add("%"+admin.getEmail()+"%");
        }
        if (!StringUtils.isEmpty(admin.getNickname())){
            base=base+"and nickname like ?";
            params.add("%"+admin.getNickname()+"%");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("sql",base);
        map.put("params",params);
        return map;
    }
}
