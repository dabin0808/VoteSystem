package xyz.peter666.action;

import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Item;
import xyz.peter666.entity.Subject;
import xyz.peter666.entity.User;
import xyz.peter666.service.ItemService;
import xyz.peter666.service.OptionServiceFromPeter;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Peter
 * @Date: 2019/7/30 16:56
 * Explain:
 */
@Controller
public class OptionShowAction extends BaseAction {
    private  Integer subjectId;
    private Subject participateSub = new Subject();

    @Resource(name = "itemServicePeter")
    private ItemService itemService;

    @Resource(name = "optionServiceFromPeter")
    private OptionServiceFromPeter optionService;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String showOption(){
        ArrayList<Integer> orders = new ArrayList<>();


        PageBean<Subject> subjectPageBean = (PageBean<Subject>) session.get("subjectPageBean");
        List<Subject> list = subjectPageBean.getList();
        //默认显示第一个
        if (subjectId==null){
            if (list.size()!=0){
                Subject s = list.get(0);
//                Set<Option> optionSet = s.getOptionSet();
//                participateSub.addAll(optionSet);
                participateSub=s;
            }else{
                participateSub=null;
                return "success";
            }
        }else{
            for(Subject sub : list){
                if(sub.getSubjectId()==subjectId.intValue()){
                    participateSub=sub;

                }
            }
        }


         User user = (User) session.get("loginUser");
        System.out.println("nowSubjectId:"+participateSub.getSubjectId());

        //由于分模块开发，所以这里的userid先使用固定的
//        List<Item> items = itemService.findItemBySubjectidAndUserid(subjectId, user.getUserId());
        List<Item> items = itemService.findItemBySubjectidAndUserid(participateSub.getSubjectId(),34);
        System.out.println("itemSize:"+items.size());

        for (Item item: items) {
            int i =optionService.findOrderByOptionId(item.getOptionId());
            System.out.println("order:"+i);
            orders.add(i);
        }


        session.put("orders",orders);
        System.out.println("orderssize:"+orders.size());

        session.put("participateSub",participateSub);
        System.out.println("option:"+participateSub);
        return "success";
    }
}
