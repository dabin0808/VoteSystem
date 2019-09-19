package xyz.peter666.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.peter666.dao.UserDao;
import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.service.UserService;
import xyz.peter666.util.PageBean;
import xyz.peter666.util.Result;

import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result register(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if(user1==null){
            userDao.add(user);
            return  Result.ok();
        }

        return Result.build(500,"该用户已注册，可直接登录",null);
    }

    @Override
    public User login(User user) {

        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public User findById(int id) {

        return userDao.findById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void deleteUserBatch(List<User> user) {

    }

    @Override
    public void deleteUserBatch(String[] userIds) {

    }

    @Override
    public void findPage(PageBean page) {
        userDao.findPage(page);
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public void updateUserAuth(Integer userId) {
        userDao.updateUserAuth(userId);
    }


}
