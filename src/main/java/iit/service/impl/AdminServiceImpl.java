package iit.service.impl;

import iit.LoginApp;
import iit.dao.AdminDAO;
import iit.models.Admin;
import iit.service.AdminService;
import iit.utils.DAOFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try {
            adminList = adminDAO.selectAdmins();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }


    @Override
    public boolean login(String account, String password) {
        boolean flag = false;
        Admin admin = null;
        try {
            admin = adminDAO.getAdminByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (admin != null) {
            String salt = admin.getSalt();
            String hash = DigestUtils.sha256Hex(salt + password);
            if (hash.equals(admin.getPassword())) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Admin getAdminByAccount(String account) {
        Admin admin = new Admin();
        try {
            admin = adminDAO.getAdminByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        try {
            Admin admin1 = adminDAO.getAdminByAccount(admin.getAccount());
            admin1.setPassword(admin.getPassword());
            admin1.setSalt(admin.getSalt());
            adminDAO.updateAdmin(admin1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSuper() {
        Admin admin = LoginApp.admin;
        return admin.getAccount().equals("admin");
    }

}
