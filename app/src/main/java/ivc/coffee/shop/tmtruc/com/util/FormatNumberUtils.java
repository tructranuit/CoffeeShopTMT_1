package ivc.coffee.shop.tmtruc.com.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by tmt on 02/05/2017.
 */

public class FormatNumberUtils {

    //format money
    public static String formatNumber(double number) {
        if (number < 1000) {
            return String.valueOf(number);
        }
        try {
            NumberFormat formatter = new DecimalFormat("###,###");
            String resp = formatter.format(number);
            return resp;
        } catch (Exception e) {
            return "";
        }
    }

}
