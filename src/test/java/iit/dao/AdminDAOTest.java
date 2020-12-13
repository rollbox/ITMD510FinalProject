package iit.dao;

import iit.models.Admin;
import iit.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class AdminDAOTest {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();

    @Test
    public void selectAdmins() throws SQLException {
        List<Admin> adminList = adminDAO.selectAdmins();
        adminList.forEach(admin -> System.out.println(admin));
    }

    @Test
    public void getAdminByAccount() throws SQLException {
        Admin admin = adminDAO.getAdminByAccount("aaa@qq.com");
        System.out.println(admin);
    }

    @Test
    public void countAdmins() throws SQLException {
        int count = adminDAO.countAdmins();
        System.out.println(count);
    }
    @Test
    public void updateAdmin()throws SQLException{
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setPassword("99999999");
        adminDAO.updateAdmin(admin);
    }
}