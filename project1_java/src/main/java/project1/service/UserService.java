package project1.service;

import project1.model.User;
import project1.model.bo.UserLoginBO;

/**
 * @param
 * @return
 */
public interface UserService {
    User login(UserLoginBO loginBO);
}
