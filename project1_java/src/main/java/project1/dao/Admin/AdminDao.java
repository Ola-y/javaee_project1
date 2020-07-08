package project1.dao.Admin;

import project1.model.Admin;

import java.util.List;

/**
 * @param
 * @return
 */
public interface AdminDao {
    Admin login(Admin admin);

    List<Admin> allAdmins();
    
    List<Admin> getSearchAdmins(Admin admin);

    Admin addAdminss(Admin admin);


    int changePwd(Admin admin);

    int updateAdminss(Admin admin);

    void deletedmins(int id);
}
