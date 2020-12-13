package iit.utils;
import iit.service.AdminService;
import javafx.scene.control.Alert;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IitUtil {


    private static  String lastTime  ;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String randSalt() {
        Random rand = new Random();
        String salt = "salt" + rand.nextInt(10000);
        return salt;
    }

    public static String hashPasswd(String salt, String passwd) {
        Random rand = new Random();
        String hash = DigestUtils.sha256Hex(salt + passwd);
        return hash;
    }


    public synchronized static String getNextTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        // 唯一性保证
        if (isNotEmpty(lastTime)
                && Long.valueOf(lastTime) >= Long.valueOf(dateStr)) {
            dateStr = getNextTime();
        } else {
            lastTime = dateStr;
        }
        return dateStr;
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() < 1;
    }
    public static boolean isNumeric(String str) {
        if ( isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isSuper() {
        AdminService service = ServiceFactory.getAdminServiceInstance();
        return service.isSuper();
    }

    public static boolean isSuperNotify() {
        AdminService service = ServiceFactory.getAdminServiceInstance();
        if(!service.isSuper()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("Just super admin can do this");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

}