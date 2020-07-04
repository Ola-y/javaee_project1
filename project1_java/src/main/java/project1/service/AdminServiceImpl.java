package project1.service;

import project1.dao.AdminDao;
import project1.dao.AdminDaoImpl;
import project1.model.Admin;
import project1.model.bo.AdminLoginBO;
import project1.model.bo.AdminSearchBO;

import java.util.List;

/**
 * @param
 * @return
 */
public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao=new AdminDaoImpl();

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }

    @Override
    public Admin login(AdminLoginBO adminLoginBO) {
        Admin admin=new Admin();
        admin.setEmail(adminLoginBO.getEmail());
        admin.setPassword(adminLoginBO.getPassword());
        return adminDao.login(admin);
    }


    @Override
    public List<Admin> getSearchAdmins(AdminSearchBO adminSearchBO) {
        Admin admin=new Admin();
        admin.setEmail(adminSearchBO.getEmail());
        admin.setNickname(adminSearchBO.getNickname());
        return adminDao.getSearchAdmins(admin);
    }
}
