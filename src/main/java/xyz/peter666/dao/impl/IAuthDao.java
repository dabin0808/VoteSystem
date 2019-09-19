package xyz.peter666.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import xyz.peter666.dao.AuthDao;
import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.util.DateUtils;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;
import java.util.List;


@Repository("authDao")
public class IAuthDao extends HibernateDaoSupport implements AuthDao {

    public IAuthDao(){

    }

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void addAuthMsg(Auth auth) {


        this.getHibernateTemplate().save(auth);

       /* String time = DateUtils.parse(auth.getCreateTime());
        Session session = this.getSessionFactory().openSession();
        String hql = "insert into Auth(createTime,userId)values(?,?)";
        Query query = session.createQuery(hql);
        query.setParameter(0,time);
        query.setParameter(1,auth.getUserId());
      *//* query.setParameter(2,auth.getCheck());*//*
        query.executeUpdate();*/
        //auth.getCreateTime().toString();

    }

    @Override
    public Auth findById(Integer id) {
        return  this.getHibernateTemplate().get(Auth.class,id);
    }

    @Override
    public void findPage(PageBean<Auth> page) {
        Session session = this.getSessionFactory().openSession();
        String hql = "from Auth";
        Query query = session.createQuery(hql);

        int start = (page.getCurrentPage()-1)*page.getRows();
        //start = start > 0?start:0;
        query.setFirstResult(start);
        query.setMaxResults(page.getRows());
        List list = query.list();
        page.setList(list);

    }

    @Override
    public Auth findByUserId(Integer userId) {
        Session session = this.getSessionFactory().openSession();
        String hql = "from Auth where userId=?";

        Query query = session.createQuery(hql);
        query.setParameter(0,userId);
        Auth auth = (Auth) query.uniqueResult();
        return auth;
    }

    @Override
    public List<Auth> findAll() {
        Session session = this.getSessionFactory().openSession();
        String hql = "from Auth where status=0";

        Query query = session.createQuery(hql);
        List list = query.list();
        return list;

    }

    private Integer getTotal(){
        Session session = this.getSessionFactory().openSession();
        String hql = "select count(*) from Auth";
        Query query = session.createQuery(hql);
        Long result = (Long) query.uniqueResult();
        return result.intValue();
    }

    @Override
    public void updateAuth(Auth auth) {

        Session session = this.getSessionFactory().openSession();
        String hql = "update Auth set status=1 where userId=? and authId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,auth.getUserId());
        query.setParameter(1,auth.getAuthId());
        query.executeUpdate();

    }
}
