package iit.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import iit.dao.IitEventDAO;
import iit.models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IitEventDAOImpl implements IitEventDAO {

    @Override
    public List<Event> selectAllEvents() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM qy_gx_market_event ");
        List<Event> EventList = new ArrayList<>();
        for (Entity entity : entityList) {
            EventList.add(convertEvent(entity));
        }
        return EventList;
    }

    @Override
    public List<Event> selectAllEventsByPhone(String phone) throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT qy_gx_market_event.* " +
                "FROM qy_gx_all_cust JOIN qy_gx_market_event " +
                "ON qy_gx_all_cust.group_id = qy_gx_market_event.group_id " +
                "WHERE qy_gx_all_cust.phone LIKE ? ", "%" + phone + "%");
        List<Event> EventList = new ArrayList<>();
        for (Entity entity : entityList) {
            EventList.add(convertEvent(entity));
        }
        return EventList;
    }

    @Override
    public List<Event> selectAllEventsByEventIdOrName(String name) throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * " +
                "FROM qy_gx_market_event " +
                "WHERE event_id LIKE ? or event_name LIKE ? ",
                "%" + name + "%", "%" + name + "%");
        List<Event> EventList = new ArrayList<>();
        for (Entity entity : entityList) {
            EventList.add(convertEvent(entity));
        }
        return EventList;
    }

    @Override
    public void addEvent(Event event) throws SQLException {
        insertEvent(event);
    }

    private Event convertEvent(Entity entity) {
        Event Event = new Event();
        Event.setEventId(entity.getStr("event_id"));
        Event.setEventName(entity.getStr("event_name"));
        Event.setProductId(entity.getStr("product_id"));
        Event.setProductName(entity.getStr("product_name"));
        Event.setGroupId(entity.getStr("group_id"));
        Event.setGroupName(entity.getStr("group_name"));
        Event.setSalesmanship(entity.getStr("salesmanship"));
        return Event;
    }

    @Override
    public void updateEvent(Event event) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        Db.use().update(
                Entity.create("qy_gx_market_event")
                        .set("salesmanship", event.getSalesmanship()),
                Entity.create("qy_gx_market_event")
                        .set("event_id", event.getEventId())
        );
    }

    @Override
    public void insertEvent(Event event) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
          Db.use().insertForGeneratedKey(
                Entity.create("qy_gx_market_event")
                        .set("event_id", event.getEventId())
                        .set("event_name", event.getEventName())
                        .set("product_id", event.getProductId())
                        .set("product_name", event.getProductName())
                        .set("group_id", event.getGroupId())
                        .set("group_name", event.getGroupName())
                        .set("salesmanship", event.getSalesmanship())

          );
    }

    @Override
    public int deleteEventById(String id) throws SQLException {
        return Db.use().del(
                Entity.create("qy_gx_market_event").set("event_id", id)
        );
    }

    @Override
    public Event getEventById(String id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        Entity entity = Db.use().queryOne("SELECT * FROM qy_gx_market_event WHERE event_id = ? ", id);
        //将Entity转换为Product类型返回
        return convertEvent(entity);
    }

    @Override
    public List<Event> getEventsByProductId(String id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        List<Entity> entityList = Db.use().query("SELECT * FROM qy_gx_market_event WHERE product_id = ? ", id);
        List<Event> EventList = new ArrayList<>();
        for (Entity entity : entityList) {
            EventList.add(convertEvent(entity));
        }
        return EventList;
    }

    @Override
    public int countEvents() throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM qy_gx_market_event  ").intValue();
    }

    @Override
    public Map<String, Integer> countEventsByProductId() throws SQLException {
        Map<String, Integer> m=new HashMap<>();
        List<Entity> entityList = Db.use().query("SELECT  product_name, count(*) c FROM qy_gx_market_event group by  product_name");
         for (Entity entity : entityList) {
            m.put(entity.getStr("product_name"),entity.getInt("c")) ;
        }
        return  m;
    }

}