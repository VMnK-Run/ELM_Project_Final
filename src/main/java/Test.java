import com.tju.elmboot.util.CommonUtil;
import com.tju.elmboot.util.SQLTimeUtil;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Date date = SQLTimeUtil.getNowDate();
        Date newDate = SQLTimeUtil.addDays(date, 4);
        System.out.println(date.getTime() < newDate.getTime());
    }
}
