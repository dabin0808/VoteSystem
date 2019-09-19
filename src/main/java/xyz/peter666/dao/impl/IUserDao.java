package xyz.peter666.dao.impl;

import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import xyz.peter666.dao.UserDao;
import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Repository("userDao")
public class IUserDao extends HibernateDaoSupport implements UserDao {

    private Class<User> entityClass;
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    public IUserDao(){
        entityClass = User.class;
    }


    @Override
    public User findById(int id) {
        User user = this.getHibernateTemplate().get(entityClass, id);
        return user;
    }

    @Override
    public void delete(User user) {
        this.getHibernateTemplate().delete(user);
    }

    @Override
    public void add(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public void update(User user) {
        this.getHibernateTemplate().update(user);
    }

    @Override
    public void findPage(PageBean<User> page) {
        //先封装好page的属性
        getTotal(page);
        if(page.getTotalCount()!=0){
            if (page.getTotalCount() % page.getRows() == 0) {

                page.setTotalPage(page.getTotalCount() / page.getRows());
            } else {
                page.setTotalPage(page.getTotalCount() / page.getRows() + 1);
            }
        }else{
            page.setTotalPage(0);
        }

        int start = (page.getCurrentPage() - 1) * page.getRows();


        DetachedCriteria detachedCriteria = page.getDetachedCriteria();

        //detachedCriteria.addOrder(Order.desc("status"));

        detachedCriteria.setProjection(null);
        detachedCriteria.addOrder(Order.desc("status"));
        if (detachedCriteria == null) {
            detachedCriteria = DetachedCriteria.forClass(entityClass);
        }
        //指定hibernate框架封装对象的形式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        List list =  this.getHibernateTemplate().findByCriteria(detachedCriteria, start, page.getRows());
        page.setList(list);

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void getTotal(PageBean<User> page) {
        Session session = this.getSessionFactory().openSession();

        DetachedCriteria criteria = page.getDetachedCriteria();
        if(criteria!=null){
            criteria.setProjection(Projections.rowCount());
            //criteria.setResultTransformer(DetachedCriteria.PROJECTION);
            List<Object> list = (List<Object>) this.getHibernateTemplate().findByCriteria(criteria);
            Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()){
                Long count = (Long) iterator.next();
                page.setTotalCount(count.intValue());
                break;
            }

        }else{
            String hql = "select count(*) from User";
            Query query = session.createQuery(hql);
            Long result = (Long) query.uniqueResult();
            page.setTotalCount(result.intValue());
        }




    }

    @Override
    public User findByUsername(String username) {
        Session session = this.getSessionFactory().openSession();
        String hql = "from User where username=?";

        Query query = session.createQuery(hql);
        query.setParameter(0,username);
        User user = (User) query.uniqueResult();
        return  user;
    }

    @Override
    public void updateUserAuth(Integer userId) {
        Session session = this.getSessionFactory().openSession();
        String hql = "update User set status=? where userId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,1);
        query.setParameter(1,userId);
        query.executeUpdate();
    }


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Session session = this.getSessionFactory().openSession();
        String hql = "from User where username = ? and password = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,username);
        query.setParameter(1,password);
        User user = (User) query.uniqueResult();
        return  user;
    }


}
