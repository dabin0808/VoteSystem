import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.peter666.dao.ItemDao;
import xyz.peter666.dao.OptionDao;
import xyz.peter666.dao.impl.ISubjectDao;
import xyz.peter666.entity.Item;
import xyz.peter666.entity.Option;
import xyz.peter666.entity.Subject;
import xyz.peter666.util.DateFormat;

import java.util.ArrayList;
import java.util.List;

public class SubjectTestForPeter {
    @Test
    public  void test(){

        ApplicationContext conn = new ClassPathXmlApplicationContext("applicationContext.xml");
 //       ItemDao dao = (ItemDao)conn.getBean("itemDaoFromPeter");
        ISubjectDao dao = (ISubjectDao)conn.getBean("subjectDao");
 //       OptionDao dao = (OptionDao)conn.getBean("optionDaoFromPeter");
//        dao.queryAllSubjectPageCount();
//        List<Item> i = dao.findItemBySubjectidAndUserid(1,2,"");
        List<Subject> i = dao.showASubjectForPage(1,6,"");
        System.out.println(i.get(1).getEndTime());




    }
    @Test
    public void test2(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("peter");
        strings.add("peter2");
        strings.add("peter3");

    }
    @Test
    public void test3(){
        int i = 20;
        int t = 374;
        int x = 16;
        int y = 21,z=24;
        for (int j = 0; j <i ; j++) {
            for (int k = 0; k <i ; k++) {
                for (int l = 0; l < i; l++) {
                    if(j+k+l==20){
                        if(j*x+k*y+z*l==t)

                            System.out.println(j+" "+k+" "+ " "+l);
                    }

                }
            }
        }
    }
}
