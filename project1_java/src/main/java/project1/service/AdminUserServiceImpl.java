package project1.service;

import project1.dao.AdminUserDao;
import project1.dao.AdminUserDaoImpl;
import project1.model.User;
import project1.model.bo.*;

import java.util.List;

/**
 * @param
 * @return
 */
public class AdminUserServiceImpl implements AdminUserService {

    private AdminUserDao adminUserDao=new AdminUserDaoImpl();

    @Override
    public List<User> allUser() {
        return adminUserDao.allUser();
    }

    @Override
    public List<User> searchUser(UserSearchBO userSearchBO) {
        User user=new User();
        user.setNickname(userSearchBO.getNickname());
        return adminUserDao.searchUser(user);
    }
}
