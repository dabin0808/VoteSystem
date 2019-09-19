package xyz.peter666.util;

import xyz.peter666.entity.Option;

import java.util.Comparator;

/**
 * @Author: Peter
 * @Date: 2019/7/31 14:53
 * Explain:
 */
public class OptionComparator implements Comparator<Option> {

        @Override
        public int compare(Option o1, Option o2) {
            System.out.println("-====================sort==================");
            return o1.getOrders() - o2.getOrders();
        }

}
