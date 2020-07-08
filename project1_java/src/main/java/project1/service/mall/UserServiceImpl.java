package project1.service.mall;

import project1.dao.Mall.UserDao;
import project1.dao.Mall.UserDaoImpl;
import project1.model.User;
import project1.model.bo.mall.UserLoginBO;
import project1.model.bo.mall.UserSignupBO;

import java.util.List;

/**
 * @param
 * @return
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public User login(UserLoginBO userLoginBO) {
            User user=new User();
            user.setEmail(userLoginBO.getEmail());
            user.setPwd(userLoginBO.getPwd());
            return userDao.login(user);

    }

    @Override
    public User signup(UserSignupBO signupBO) {
        User user=new User();
        user.setEmail(signupBO.getEmail());
        user.setAddress(signupBO.getAddress());
        user.setPhone(signupBO.getPhone());
        user.setNickname(signupBO.getNickname());
        user.setPwd(signupBO.getPwd());
        user.setRecipient(signupBO.getRecipient());
        return userDao.signup(user);
    }

}

