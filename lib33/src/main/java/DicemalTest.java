import java.text.DecimalFormat;

public class DicemalTest {
    public static void main(String[] args) {
        double aa = 123.35543434;
        System.out.println(new DecimalFormat("0.00").format(aa));
        System.out.println(new DecimalFormat("00.00").format(aa));
        System.out.println(new DecimalFormat("#.00").format(aa));
        System.out.println(new DecimalFormat("#.0000000000").format(aa));
        System.out.println(new DecimalFormat("####.0000000000").format(aa));
        System.out.println(new DecimalFormat("####%").format(aa));
    }
}
