package project1.dao;

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
}
