package project1.dao.Mall;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import project1.model.User;
import project1.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

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
            users = runner.query("select * from user where email = ? and pwd = ?",new BeanHandler<User>(User.class),user.getEmail(),user.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User signup(User user) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        User users= null;
        try {
            users = runner.insert("insert into user(email,pwd,nickname,recipient,address,phone) values(?,?,?,?,?,?)",new BeanHandler<>(User.class),
                    user.getEmail(),
                    user.getPwd(),
                    user.getNickname(),
                    user.getRecipient(),
                    user.getAddress(),
                    user.getPhone());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
