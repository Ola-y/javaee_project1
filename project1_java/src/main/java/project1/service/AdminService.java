package project1.service;

import project1.model.Admin;
import project1.model.bo.AdminLoginBO;
import project1.model.bo.AdminSearchBO;

import java.util.List;

/**
 * @param
 * @return
 */
public interface AdminService {
    List<Admin> allAdmins();

    Admin login(AdminLoginBO adminLoginBO);

    List<Admin> getSearchAdmins(AdminSearchBO adminSearchBO);
}
