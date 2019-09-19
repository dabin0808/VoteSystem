package xyz.peter666.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import xyz.peter666.dao.OptionDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Peter
 * @Date: 2019/7/30 19:20
 * Explain:
 */
@Repository(value = "optionDaoFromPeter")
public class IOptionDaoFromPeter extends HibernateDaoSupport  implements OptionDao {

    @Resource
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

    public  int findOrderByOptionId(int id){

            String sql = "select optionId from Option where optionId = ?";
        List<Object> objs= (List<Object>) getHibernateTemplate().find(sql, id);
        int num=Integer.parseInt( objs.get(0).toString());
        return num;
    }

    @Override
    public void voteCountAddOne(int oid) {
        String hql = "update Option o set o.counts=o.counts+1 where optionId = ?";
        getHibernateTemplate().bulkUpdate(hql,new Object[]{oid});
    }
}
