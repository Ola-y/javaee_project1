package project1.dao;

import project1.model.User;

import java.util.List;

/**
 * @param
 * @return
 */
public interface AdminUserDao {

    List<User> searchUser(User user);


    List<User> allUser();
}
