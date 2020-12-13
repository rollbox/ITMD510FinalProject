package iit.service;

import iit.models.Admin;

import java.util.List;

public interface AdminService {
    /**
     * @return List<Admin>
     */
    List<Admin> getAllAdmins();


    /**
     * @param account
     * @param password
     * @return boolean
     */
    boolean login(String account,String password);

    /**
     * @param account
     * @return amdin
     */
    Admin getAdminByAccount(String account);

    void updateAdmin(Admin admin);

    boolean isSuper();

}
