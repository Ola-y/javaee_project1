package project1.service;

import project1.model.User;
import project1.model.bo.UserSearchBO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface AdminUserService {
    List<User> allUser();

    List<User> searchUser(UserSearchBO userSearchBO);
}
