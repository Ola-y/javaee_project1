package project1.dao.Mall;

import project1.model.User;

import java.util.List;

/**
 * @param
 * @return
 */
public interface UserDao {
     User login(User user);

    User signup(User user);
}
