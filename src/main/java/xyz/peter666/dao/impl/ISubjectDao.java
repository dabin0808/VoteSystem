package xyz.peter666.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import xyz.peter666.dao.SubjectDao;
import xyz.peter666.entity.Subject;

import javax.annotation.Resource;
import java.util.List;

@Repository("subjectDao")
public class ISubjectDao extends HibernateDaoSupport implements SubjectDao {

    @Resource
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

    @Override
    public List<Subject> showASubjectForPage(int page, int pageSize ,String key) {

        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        key = key.trim();
        String hql="from Subject as s where s.title like '%"+key+"%'";


        Query query = session.createQuery(hql);

        query.setFirstResult((page-1)*pageSize);
        query.setMaxResults(pageSize);
        List<Subject> subjects = query.list();

        return subjects;


    }

    @Override
    public int queryAllSubjectPageCount(String key) {
        String hql = "select count(*) from  Subject as s where s.title like '%"+key+"%'";
        List<Object> objs= (List<Object>)getHibernateTemplate().find(hql);
        System.out.println(hql);
        int num=Integer.parseInt( objs.get(0).toString());
        return num;

    }



    @Override
    public List<Subject> showMyPartTakeSubject(int id) {
        return null;
    }

    @Override
    public void deleteSubejct(int subjectId) {

    }
}
