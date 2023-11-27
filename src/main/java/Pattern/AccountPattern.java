package Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountPattern {
    public static boolean accountPatternSize(String line) {
        int i = 0;
        int u = 0;
        Pattern account = Pattern.compile("\\b\\d{5}-\\d{5}\\b");
        Matcher matcher = account.matcher(line);
        return matcher.find();
    }

    public static boolean moneyPattern(String line) {
        Pattern money = Pattern.compile("\\b\\d+");
        Matcher matcher = money.matcher(line);
        if(matcher.find())
            return Integer.parseInt(line) > 0;
        return false;
    }
}
