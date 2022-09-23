import com.tju.elmboot.util.CommonUtil;

public class Test {
    public static void main(String[] args) {
        int hour = Integer.parseInt(CommonUtil.getCurrentDate().substring(11, 13));
        System.out.println(hour);
    }
}
