package xyz.peter666.dao;



import xyz.peter666.entity.Item;

import java.util.List;

/**
 * 与投票表相关操作的接口
 */
public interface ItemDao {

    //peter
     List<Item> findItemBySubjectidAndUserid(int subjectId, int userId, String s);

    //用户投票后记录到数据库
      void upDateItem(Item item);

}
