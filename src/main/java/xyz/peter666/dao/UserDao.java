package xyz.peter666.dao;

import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.util.PageBean;

import java.util.List;

/**
 * 与用户表相关操作的接口
 */
public interface UserDao {

    public User findById(int id);
    public void delete(User user);
    public void add(User user);
    public void update(User user);
    public void findPage(PageBean<User> page);
    public List<User> findAll();
    public void getTotal(PageBean<User>  page);
    public User findByUsernameAndPassword(String username, String password);
    public User findByUsername(String username);
    public void updateUserAuth(Integer userId);

}
