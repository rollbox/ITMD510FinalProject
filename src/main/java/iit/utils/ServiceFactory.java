package iit.utils;

import iit.service.*;
import iit.service.impl.*;

/**
 */
public class ServiceFactory {

    public static IitCustService getIitCustServiceInstance() {
        return new IitCustServiceImpl();
    }

    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }
}
