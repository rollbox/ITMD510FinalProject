package iit.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import iit.dao.IitCustDAO;
import iit.models.*;
import iit.utils.IitUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IitCustDAOImpl implements IitCustDAO {

    @Override
    public List<Cust> selectAllCusts() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM qy_gx_all_cust ");
        List<Cust> custList = new ArrayList<>();
        for (Entity entity : entityList) {
            custList.add(convertCust(entity));
        }
        return custList;
    }

    @Override
    public List<Cust> selectAllCustsByPhone(String phone) throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM qy_gx_all_cust WHERE phone LIKE ? ", "%" + phone + "%");
        List<Cust> custList = new ArrayList<>();
        for (Entity entity : entityList) {
            custList.add(convertCust(entity));
        }
        return custList;
    }

    @Override
    public List<Cust> selectAllCustsByEventIdOrName(String name) throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT qy_gx_all_cust.* " +
                "FROM qy_gx_market_event " +
                "JOIN qy_gx_all_cust ON qy_gx_all_cust.group_id = qy_gx_market_event.group_id " +
                "WHERE qy_gx_market_event.event_id LIKE ? or qy_gx_market_event.event_name LIKE ? ",
                "%" + name + "%", "%" + name + "%");
        List<Cust> custList = new ArrayList<>();
        for (Entity entity : entityList) {
            custList.add(convertCust(entity));
        }
        return custList;
    }

    @Override
    public Cust getCustById(String id) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM qy_gx_all_cust WHERE bill_No = ? ", id);
        return convertCust(entity);
    }


    private Cust convertCust(Entity entity) {
        Cust cust = new Cust();
        cust.setName(entity.getStr("name"));
        cust.setPhone(entity.getStr("phone"));
        cust.setAddress(entity.getStr("address"));
        cust.setProducts(entity.getStr("products"));
        cust.setSex(entity.getStr("sex"));
        return cust;
    }

    @Override
    public List<Group> selectAllGroups(String keywords) throws SQLException {
        String sql="SELECT * FROM qy_gx_group ";
        if(IitUtil.isNotEmpty(keywords))
        {
            sql=sql+ " where group_id like  '%"+keywords+"%' or group_name like  '%"+keywords+"%' ";
        }

        List<Entity> entityList = Db.use().query(sql);
        List<Group> GroupList = new ArrayList<>();
        for (Entity entity : entityList) {
            GroupList.add(convertGroup(entity));
        }
        return GroupList;
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
          Db.use().insertForGeneratedKey(
                Entity.create("qy_gx_admin")
                        .set("account", admin.getAccount())
                        .set("role", "CustomerService")
                        .set("password", admin.getPassword())
                        .set("salt", admin.getSalt())
        );
    }

    private Group convertGroup(Entity entity) {
        Group group = new Group();
        group.setGroupId(entity.getStr("group_id"));
        group.setGroupName(entity.getStr("group_name"));
        group.setGroupDesc(entity.getStr("group_desc"));
        return group;
    }


    @Override
    public void insertProduct(Product type) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
          Db.use().insertForGeneratedKey(
                Entity.create("qy_gx_product")
                        .set("product_id", type.getProductId())
                        .set("product_name", type.getProductName())
        );
    }

    @Override
    public int deleteProductById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("qy_gx_product").set("product_id", id)
        );
    }


    @Override
    public List<Product> selectAllProducts() throws SQLException {
        //查询得到List<Entity>
        List<Entity> entityList = Db.use().query("SELECT * FROM qy_gx_product ");
        //创建一个List<Product>，存放具体的类别
        List<Product> typeList = new ArrayList<>();
        //遍历entityList，转换为typeList
        for (Entity entity : entityList) {
            typeList.add(convertProduct(entity));
        }
        return typeList;
    }

    @Override
    public List<Product> selectAllProductsByIdOrName(String name) throws SQLException {
        //查询得到List<Entity>
        List<Entity> entityList = Db.use().query("SELECT * " +
                "FROM qy_gx_product " +
                "WHERE product_id LIKE ? or product_name LIKE ? ",
                "%" + name + "%", "%" + name + "%");
        //创建一个List<Product>，存放具体的类别
        List<Product> typeList = new ArrayList<>();
        //遍历entityList，转换为typeList
        for (Entity entity : entityList) {
            typeList.add(convertProduct(entity));
        }
        return typeList;
    }

    @Override
    public Product getProductById(long id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        Entity entity = Db.use().queryOne("SELECT * FROM qy_gx_product WHERE id = ? ", id);
        //将Entity转换为Product类型返回
        return convertProduct(entity);
    }

    @Override
    public int countProducts() throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM qy_gx_product  ").intValue();
    }

    @Override
    public void addGroup(Group group) throws SQLException {
        Connection connection = Db.use().getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("INSERT INTO qy_gx_group(group_id ,group_name,group_desc)  VALUES (?,?,?)");
        PreparedStatement ps2 = connection.prepareStatement("INSERT INTO qy_gx_all_cust( phone ,name,sex,address,products,group_id)" +
                        "select  phone ,name,sex,address,products,group_id from qy_gx_big_cust where group_id=?");
        PreparedStatement ps3 = connection.prepareStatement("delete  from qy_gx_big_cust where group_id =?");

        try {
         ps.setLong(1, Long.parseLong(group.getGroupId()));
         ps.setString(2, group.getGroupName());
         ps.setString(3, group.getGroupDesc());
         ps.executeUpdate();

         ps2.setString(1,group.getGroupId());
         ps2.executeUpdate();
         ps3.setString(1,group.getGroupId());
         ps3.executeUpdate();

         connection.commit();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (ps2 != null) {
                    ps2.close();
                    ps2 = null;
                }
                if (ps3 != null) {
                    ps3.close();
                    ps3 = null;
                }
                if (connection != null) {
                    connection.close(); //close db connection
                    connection = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateGroup(Group group) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        Db.use().update(
                Entity.create("qy_gx_group")
                        .set("group_desc", group.getGroupDesc()),
                Entity.create("qy_gx_group")
                        .set("group_id", group.getGroupId())
        );
    }

    @Override
    public void deleteGroupById(String groupId) {

        try {
              Db.use().del(
                    Entity.create("qy_gx_group").set("group_id", groupId)
            );


            Db.use().del(
                    Entity.create("qy_gx_all_cust").set("group_id", groupId)
            );



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
        public String saveCustList(List<Cust> custList) throws SQLException {
        Connection connection = Db.use().getConnection();
        connection.setAutoCommit(false);

        String groupId= IitUtil.getNextTime();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO qy_gx_big_cust(phone ,name,sex,address,products,group_id)  VALUES (?,?,?,?,?,?)");

        try {
            // Include all object data to the database table
            for (int i = 0; i < custList.size(); ++i) {
                ps.setString(1, custList.get(i).getPhone());
                ps.setString(2, custList.get(i).getName());
                ps.setString(3, custList.get(i).getSex());
                ps.setString(4, custList.get(i).getAddress());
                ps.setString(5, custList.get(i).getProducts());
                ps.setString(6, groupId);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (Exception se) {
            se.printStackTrace();
            connection.rollback();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (connection != null) {
                    connection.close(); //close db connection
                    connection = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return groupId;
    }

    /**
     * 将Entity转换为Product类型
     *
     * @param entity
     * @return Product
     */
    private Product convertProduct(Entity entity) {
        Product type = new Product();
        type.setProductId(entity.getStr("product_id"));
        type.setProductName(entity.getStr("product_name"));
        return type;
    }
    
}