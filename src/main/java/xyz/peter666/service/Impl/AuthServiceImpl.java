package xyz.peter666.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.peter666.dao.AuthDao;
import xyz.peter666.entity.Auth;
import xyz.peter666.service.AuthService;
import xyz.peter666.util.PageBean;

import java.util.List;

@Service("authService")
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthDao authDao;

    @Override
    public void addAuthMsg(Auth auth) {
        authDao.addAuthMsg(auth);
    }

    @Override
    public void findPage(PageBean<Auth> page) {
        authDao.findPage(page);
    }

    @Override
    public Auth findById(Integer id) {
        return authDao.findById(id);
    }

    @Override
    public Auth findByUserId(Integer userId) {
        return authDao.findByUserId(userId);
    }

    @Override
    public List<Auth> findAll() {
       return authDao.findAll();
    }

    @Override
    public void updateAuth(Auth auth) {
        authDao.updateAuth(auth);
    }
}
