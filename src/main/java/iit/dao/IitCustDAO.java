package iit.dao;


import iit.models.*;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO
 */
public interface IitCustDAO {


    /**
     * 查询所有
     *
     * @return
     */
    List<Cust> selectAllCusts() throws SQLException;

    List<Cust> selectAllCustsByPhone(String phone) throws SQLException;

    List<Cust> selectAllCustsByEventIdOrName(String name) throws SQLException;

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    Cust getCustById(String id) throws SQLException;

    List<Group> selectAllGroups(String keywords)throws SQLException  ;

    void addAdmin(Admin admin) throws SQLException;


    void insertProduct(Product type) throws SQLException;

     
    int deleteProductById(long id) throws SQLException;

    List<Product> selectAllProducts() throws SQLException;

    List<Product> selectAllProductsByIdOrName(String name) throws SQLException;

    Product getProductById(long id) throws SQLException;

    int countProducts() throws SQLException;

    String saveCustList(List<Cust> custList)throws SQLException;

    void addGroup(Group group) throws SQLException;

    void updateGroup(Group group) throws SQLException;

    void deleteGroupById(String groupId);
}
