package com.adjust.adjusthomework.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AdjustUtil {

    /**
     * Convert Hashset values to Integer static array
     *
     * @param integers Hashset values
     * @return Integer static array
     */
    public static Integer[] convertIntegers(HashSet<Integer> integers) {
        List<Integer> values = new ArrayList<>(integers);
        Integer[] ret = new Integer[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = values.get(i);
        }
        return ret;
    }

    /**
     * Convert string values to Hashset
     *
     * @param values cached values in String
     * @return Hashset of Cached values
     */

    public static HashSet<Integer> convertStringToIntegerList(String values) {
        HashSet<Integer> setList = new HashSet<>();
        if (values != null && !values.isEmpty()) {
            String[] splitList = values.split(",");
            for (String s : splitList) {
                setList.add(Integer.valueOf(s));
            }
        }
        return setList;
    }

    /**
     * Convert Hashset values to String
     * for saving in SharedPreferences
     *
     * @param list Hashset
     * @return String
     */
    public static String covertIntegerListToString(HashSet<Integer> list) {
        StringBuilder strbul = new StringBuilder();
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            strbul.append(iter.next());
            if (iter.hasNext()) {
                strbul.append(",");
            }
        }
        return strbul.toString();
    }


}
