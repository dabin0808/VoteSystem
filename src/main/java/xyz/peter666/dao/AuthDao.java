package xyz.peter666.dao;

import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.util.PageBean;

import java.util.List;

public interface AuthDao {
    public void addAuthMsg(Auth auth);
    public Auth findById(Integer id);
    public void findPage(PageBean<Auth> page);
    public Auth findByUserId(Integer userId);

    public List<Auth> findAll();

    public void updateAuth(Auth auth);
}
