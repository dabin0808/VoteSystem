package xyz.peter666.service;

import xyz.peter666.entity.Item;

import java.util.List;

/**
 * @Author: Peter
 * @Date: 2019/7/30 18:55
 * Explain:
 */
public interface ItemService {
     List<Item> findItemBySubjectidAndUserid(int subjectId, int userId);

     void addItemAndUpDateOption(Item item);

}
