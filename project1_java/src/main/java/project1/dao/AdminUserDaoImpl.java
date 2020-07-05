package project1.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import project1.model.User;
import project1.utils.DruidUtils2;

import java.sql.SQLException;
import java.util.List;

/**
 * @param
 * @return
 */
public class AdminUserDaoImpl implements AdminUserDao{
    @Override
    public List<User> allUser() {
        QueryRunner runner=new QueryRunner(DruidUtils2.getDataSource());
        List<User> users= null;
        try {
            users = runner.query("select*from user",new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
