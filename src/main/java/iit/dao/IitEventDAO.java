package iit.dao;


import iit.models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * DAO
 */
public interface IitEventDAO {

    public List<Event> selectAllEvents() throws SQLException  ;

    public List<Event> selectAllEventsByPhone(String phone) throws SQLException  ;

    public List<Event> selectAllEventsByEventIdOrName(String name) throws SQLException  ;

    void addEvent(Event event) throws SQLException;

    void updateEvent(Event event) throws SQLException;

    void insertEvent(Event event) throws SQLException;
     
    int deleteEventById(String id) throws SQLException;

    Event getEventById(String id) throws SQLException;

    List<Event> getEventsByProductId(String id) throws SQLException;

    int countEvents() throws SQLException;

    Map<String, Integer> countEventsByProductId()throws SQLException ;
}
