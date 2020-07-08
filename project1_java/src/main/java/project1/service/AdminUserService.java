package project1.service;

import project1.model.Admin;
import project1.model.User;

import java.util.List;

/**
 * @param
 * @return
 */
public interface AdminUserService {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String word);
}
