package xyz.peter666.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.peter666.dao.OptionDao;
import xyz.peter666.dao.SubjectDao;
import xyz.peter666.dao.impl.ISubjectDao;
import xyz.peter666.entity.Option;
import xyz.peter666.entity.Subject;
import xyz.peter666.service.Impl.SubjectServiceImp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * @Author: dabin0808
 * @Date: 2019/7/29 20:25
 * Explain:这是一个测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSubjectAction {

    @Autowired
    SubjectAction subjectAction;


    @Test
    public void test() {
        //测试Action方法调用
      //  subjectAction.test();
        //subjectAction.delSubject();
       // subjectAction.showAllSubject();

        SubjectServiceImp subjectServiceImp = new SubjectServiceImp();
        String string =subjectServiceImp.modTime(new Date());
        System.out.println(string);
    }
}
