package iit.utils;

import iit.dao.*;
import iit.dao.impl.AdminDAOImpl;
import iit.dao.impl.IitEventDAOImpl;
import iit.dao.impl.IitCustDAOImpl;

/**
 */
public class DAOFactory {
    /**
     *
     * @return
     */
    public static IitCustDAO getIitCustDAOInstance() {
        return  new IitCustDAOImpl();
    }

    public static IitEventDAO getIitEventDAOInstance() {
        return  new IitEventDAOImpl();
    }

    public static AdminDAO getAdminDAOInstance() {
        return new AdminDAOImpl();
    }
}
