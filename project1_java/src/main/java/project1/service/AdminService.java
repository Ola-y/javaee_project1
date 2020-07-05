package project1.service;

import project1.model.Admin;
import project1.model.bo.*;

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

    int deleteAdmins(AdminDeleteBO deleteBO);
}
