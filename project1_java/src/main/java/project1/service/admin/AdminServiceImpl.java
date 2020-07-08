package project1.service.admin;

import project1.dao.Admin.AdminDao;
import project1.dao.Admin.AdminDaoImpl;
import project1.model.Admin;
import project1.model.bo.admin.*;

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

    @Override
    public Admin addAdminss(AdminAddBO adminAddBO) {
        Admin admin=new Admin();
        admin.setEmail(adminAddBO.getEmail());
        admin.setNickname(adminAddBO.getNickname());
        admin.setPassword(adminAddBO.getPwd());
        return adminDao.addAdminss(admin);
    }

    @Override
    public int changePwd(AdminChangeBO changeBO) {
        Admin admin=new Admin();
        admin.setOldPwd(changeBO.getOldPwd());
        admin.setNewPwd(changeBO.getNewPwd());
        admin.setConfirmPwd(changeBO.getConfirmPwd());
        admin.setNickname(changeBO.getAdminToken());
        return adminDao.changePwd(admin);
    }

    @Override
    public int updateAdminss(AdminUpdateBO updateBO) {
        Admin admin=new Admin();
        admin.setEmail(updateBO.getEmail());
        admin.setNickname(updateBO.getNickanme());
        admin.setPassword(updateBO.getPwd());
        admin.setId(updateBO.getId());
        return adminDao.updateAdminss(admin);
    }

    @Override
    public void deletedmins(int id) {
        adminDao.deletedmins(id);
    }

}
