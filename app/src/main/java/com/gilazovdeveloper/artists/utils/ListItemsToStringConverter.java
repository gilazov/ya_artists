package com.gilazovdeveloper.artists.utils;

import java.util.List;

/**
 * Created by ruslan on 22.04.16.
 */
public  class ListItemsToStringConverter {
    public static String convert(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            for (String item : list) {
                builder.append(item + " ");
            }
            return builder.toString();
        } else
            return "";
    }
}
