package iit.service.impl;

import iit.dao.IitCustDAO;
import iit.dao.IitEventDAO;
import iit.models.*;
import iit.service.IitCustService;
import iit.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IitCustServiceImpl implements IitCustService {

    private IitCustDAO iitCustDAO = DAOFactory.getIitCustDAOInstance();

    private IitEventDAO iitEventDAO = DAOFactory.getIitEventDAOInstance();

    @Override
    public List<Cust> getAllCusts() {
        List<Cust> lists = new ArrayList<>();
        try {
            lists = iitCustDAO.selectAllCusts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public List<Cust> getAllCustsByPhone(String phone) {
        List<Cust> lists = new ArrayList<>();
        try {
            lists = iitCustDAO.selectAllCustsByPhone(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public List<Cust> getAllCustsByEventIdOrName(String name) {
        List<Cust> lists = new ArrayList<>();
        try {
            lists = iitCustDAO.selectAllCustsByEventIdOrName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public Cust getCust(String id) {
        Cust cust = new Cust();
        try {
            cust = iitCustDAO.getCustById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cust;
    }

    @Override
    public List<Group> getAllGroups(String keywords) {

        List<Group> lists = new ArrayList<>();
        try {
            lists = iitCustDAO.selectAllGroups(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public void updateCustomerGroup(Group group) {
        try {

            iitCustDAO.updateGroup(group);
        } catch (SQLException e) {
            System.err.println("update group error!");
        }
    }

    @Override
    public void DeleteCustomerGroup(String groupId) {
        try {
            iitCustDAO.deleteGroupById(groupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> getAllEvents() {

        List<Event> lists = new ArrayList<>();
        try {
            lists = iitEventDAO.selectAllEvents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public List<Event> getAllEventsByPhone(String phone) {

        List<Event> lists = new ArrayList<>();
        try {
            lists = iitEventDAO.selectAllEventsByPhone(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public List<Event> getAllEventsByEventIdOrName(String name) {

        List<Event> lists = new ArrayList<>();
        try {
            lists = iitEventDAO.selectAllEventsByEventIdOrName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public List<Event> getEventsByProductId(String productId) {

        List<Event> lists = new ArrayList<>();
        try {
            lists = iitEventDAO.getEventsByProductId(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public Map<String, Integer> countEventsByProductId() {
        Map<String, Integer> num = null;
        try {
            num = iitEventDAO.countEventsByProductId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public void addEvent(Event event) throws SQLException {
        try {

            iitEventDAO.addEvent(event);
        } catch (SQLException e) {
            System.err.println("新增event出现异常!");
        }
    }

    @Override
    public void updateEvent(Event event) {
        try {

            iitEventDAO.updateEvent(event);
        } catch (SQLException e) {
            System.err.println("新增event出现异常!");
        }
    }

    @Override
    public void deleteEvent(String id) {
        try {
            //调用底层DAO的查询删除类别方法，薄层封装
            iitEventDAO.deleteEventById(id);
        } catch (SQLException e) {
            System.err.println("删除event出现异常!");
        }
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        iitCustDAO.addAdmin(admin);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> typeList = new ArrayList<>();
        try {
            //调用底层DAO的查询所有类别的方法，得到一个typeList，薄层封装
            typeList = iitCustDAO.selectAllProducts();
        } catch (SQLException e) {
            //友好处理异常
            System.err.println("查询所有类别出现异常!");
        }
        return typeList;
    }

    @Override
    public List<Product> getAllProductsByIdOrName(String name) {
        List<Product> typeList = new ArrayList<>();
        try {
            //调用底层DAO的查询所有类别的方法，得到一个typeList，薄层封装
            typeList = iitCustDAO.selectAllProductsByIdOrName(name);
        } catch (SQLException e) {
            //友好处理异常
            System.err.println("查询所有类别出现异常!");
        }
        return typeList;
    }

    @Override
    public Product getProduct(long id) {
        Product type = new Product();
        try {
            type = iitCustDAO.getProductById(id);
        } catch (SQLException e) {
            System.err.println("查询单个类别出现异常!");
        }
        return type;
    }

    @Override
    public void addProduct(Product type) {
        try {

            iitCustDAO.insertProduct(type);
        } catch (SQLException e) {
            System.err.println("新增类别出现异常!");
        }
    }

    @Override
    public void deleteProduct(long id) {
        try {
            //调用底层DAO的查询删除类别方法，薄层封装
            iitCustDAO.deleteProductById(id);
        } catch (SQLException e) {
            System.err.println("删除类别出现异常!");
        }
    }

    @Override
    public String saveCustList(List<Cust> custList) {
        String groupId="";
        try {
            groupId= iitCustDAO.saveCustList(custList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupId;

    }

    @Override
    public void addGroup(Group group) {
        try {
            iitCustDAO.addGroup(group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
