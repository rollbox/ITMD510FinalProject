package iit.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import iit.dao.AdminDAO;
import iit.models.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public List<Admin> selectAdmins() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM qy_gx_admin ");
        List<Admin> adminList = new ArrayList<>();
        for (Entity entity : entityList) {
            adminList.add(convertAdmin(entity));
        }
        return adminList;
    }

    @Override
    public Admin getAdminByAccount(String account) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM qy_gx_admin WHERE account = ? ", account);
        return convertAdmin(entity);
    }

    @Override
    public int countAdmins() throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM qy_gx_admin ").intValue();
    }

    @Override
    public int updateAdmin(Admin admin) throws SQLException {

            return Db.use().update(
                    Entity.create().set("password", admin.getPassword()).set("salt", admin.getSalt()),
                    Entity.create("qy_gx_admin").set("id", admin.getId())
            );
    }





    /**
     * 封装一个将Entity转换为Admin的方法
     *
     * @param entity
     * @return
     */
    private Admin convertAdmin(Entity entity) {
        if(entity == null)
            return null;
        Admin admin = new Admin();
        admin.setId(entity.getLong("id"));
        admin.setAccount(entity.getStr("account"));
        admin.setPassword(entity.getStr("password"));
        admin.setSalt(entity.getStr("salt"));
        admin.setRole(entity.getStr("role"));
        return admin;
    }
}
