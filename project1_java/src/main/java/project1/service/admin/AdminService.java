package project1.service.admin;

import project1.model.Admin;
import project1.model.bo.admin.*;

import java.util.List;

/**
 * @param
 * @return
 */
public interface AdminService {
    List<Admin> allAdmins();

    Admin login(AdminLoginBO adminLoginBO);

    List<Admin> getSearchAdmins(AdminSearchBO adminSearchBO);

    Admin addAdminss(AdminAddBO adminAddBO);


    int changePwd(AdminChangeBO changeBO);

    int updateAdminss(AdminUpdateBO updateBO);

    void deletedmins(int id);
}
