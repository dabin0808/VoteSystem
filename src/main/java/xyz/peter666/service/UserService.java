package xyz.peter666.service;

import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.util.PageBean;
import xyz.peter666.util.Result;

import java.util.List;

public interface UserService {

    Result register(User user);
    User login(User user);
    User findById(int id);
    void updateUser(User user);
    void deleteUser(User user);
    void deleteUserBatch(List<User> user);
    void deleteUserBatch(String[] userIds);
    void findPage(PageBean page);
     List<Auth> findAll();
     void updateUserAuth(Integer userId);


}
