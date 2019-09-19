package xyz.peter666.service;

import xyz.peter666.entity.Auth;
import xyz.peter666.util.PageBean;

import java.util.List;

public interface AuthService {

    public void addAuthMsg(Auth auth);
    public void findPage(PageBean<Auth> page);

    public Auth findById(Integer id);

    public Auth findByUserId(Integer userId);
    public List<Auth> findAll();
    public void updateAuth(Auth auth);
}
