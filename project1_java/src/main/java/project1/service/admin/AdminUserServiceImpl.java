package project1.service.admin;

import project1.dao.Admin.AdminUserDao;
import project1.dao.Admin.AdminUserDaoImpl;
import project1.model.User;

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
    public void deleteUser(int id) {
        adminUserDao.deleteUser(id);
    }

    @Override
    public List<User> searchUser(String word) {
        return adminUserDao.searchUser(word);
    }


}
