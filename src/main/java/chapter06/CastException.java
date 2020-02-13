package chapter06;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: su
 * @date: 2020/2/13
 */
public class CastException {
    public static Map m = new HashMap() {
        {
            m.put("a", "2");

        }
    };

    public static void main(String[] args) {
        Integer isInt = (Integer) m.get("a");
        System.out.println(isInt);
    }
}
