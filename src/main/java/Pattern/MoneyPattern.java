package Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyPattern {
    public static boolean money(String money){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(money);
        return matcher.find();
    }
}
