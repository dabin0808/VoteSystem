package xyz.peter666.dao;

/**
 * 与投票选项表相关操作的接口
 */
public interface OptionDao {
     int findOrderByOptionId(int id);

     void voteCountAddOne(int oid);
}
