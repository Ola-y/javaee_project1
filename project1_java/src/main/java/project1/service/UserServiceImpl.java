package project1.service;

import project1.dao.UserDao;
import project1.dao.UserDaoImpl;
import project1.model.User;
import project1.model.bo.UserLoginBO;

/**
 * @param
 * @return
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public User login(UserLoginBO loginBO) {
        User user=new User();
        user.setEmail(loginBO.getEmail());
        user.setPwd(loginBO.getPwd());
        return userDao.login(user);
    }
}
