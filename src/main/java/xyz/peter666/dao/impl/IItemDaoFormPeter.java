package xyz.peter666.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import xyz.peter666.dao.ItemDao;
import xyz.peter666.entity.Item;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Peter
 * @Date: 2019/7/30 19:00
 * Explain:
 */
@Repository(value = "itemDaoFromPeter")
public class IItemDaoFormPeter extends HibernateDaoSupport implements ItemDao {

    @Resource
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

    public List<Item> findItemBySubjectidAndUserid(int subjectId, int userId, String s){
        String hql = "from Item as i  where i.subjectId=? and  i.userId=?";
        List<Item> items = (List<Item>) getHibernateTemplate().find(hql, subjectId, userId);
        return items;
    }

    @Override
    public void upDateItem(Item item) {
        getHibernateTemplate().save(item);
    }


}
