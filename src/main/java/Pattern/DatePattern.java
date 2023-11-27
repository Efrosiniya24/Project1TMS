package Pattern;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePattern {
    public static boolean datePatternType(String date){
        Pattern pattern = Pattern.compile("\\b\\d{2}-\\d{2}-\\d{4}\\b");
        Matcher matcher = pattern.matcher(date);
        if(!matcher.find())
            return false;
        else {
            String[] dateDayMounthYear = date.split("-");
            if((Integer.parseInt(dateDayMounthYear[0]))>31)
                return false;
            else if ((Integer.parseInt(dateDayMounthYear[1]))>12)
                return false;
            else if  (Integer.parseInt(dateDayMounthYear[2]) < 2000 || Integer.parseInt(dateDayMounthYear[2]) > 2024)
                return false;
            else
                return true;
        }

    }
}
