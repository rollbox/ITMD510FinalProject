package iit.service;

import iit.models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IitCustService {

    List<Cust> getAllCusts();

    List<Cust> getAllCustsByPhone(String phone);

    List<Cust> getAllCustsByEventIdOrName(String name);

    Cust getCust(String id);

    List<Event> getAllEvents();

    List<Event> getAllEventsByPhone(String phone);

    List<Event> getAllEventsByEventIdOrName(String name);

    List<Event> getEventsByProductId(String ProductId);
    Map<String, Integer> countEventsByProductId();

    void updateEvent(Event event);

    void deleteEvent(String id);

    void addEvent(Event event) throws SQLException;

    List<Group> getAllGroups(String keywords);

    void updateCustomerGroup(Group group);

    void DeleteCustomerGroup(String groupId);

    void addAdmin(Admin admin) throws SQLException;

    
    List<Product> getAllProducts();

    List<Product> getAllProductsByIdOrName(String name);

    Product getProduct(long id);

    void addProduct(Product type);

    void deleteProduct(long id);

    String saveCustList(List<Cust> custList);

    void addGroup(Group group);

}
