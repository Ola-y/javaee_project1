package project1.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import project1.model.User;
import project1.utils.DruidUtils;

import java.sql.SQLException;

/**
 * @param
 * @return
 */
public class UserDaoImpl implements UserDao {

    /**
     * 登录逻辑
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        User users= null;
        try {
            users = runner.query("select*from user where email = ? and password = ?",new BeanHandler<>(User.class),user.getEmail(),user.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
