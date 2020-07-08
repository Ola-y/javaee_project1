package project1.service.mall;

import project1.model.User;
import project1.model.bo.mall.UserLoginBO;
import project1.model.bo.mall.UserSignupBO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface UserService {

    User login(UserLoginBO userLoginBO);

    User signup(UserSignupBO signupBO);
}
