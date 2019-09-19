package xyz.peter666.action;

import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Item;
import xyz.peter666.entity.Option;
import xyz.peter666.entity.Subject;
import xyz.peter666.service.ItemService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

/**
 * @Author: Peter
 * @Date: 2019/7/31 8:15
 * Explain:
 */
@Controller
public class VotingAction extends BaseAction{

    private String subjectId;
    private String[] checkboxGroups;
    private String radioGroup;
    @Resource(name = "itemServicePeter")
    ItemService service;
//    @Resource(name = "optionServiceFromPeter")
//    OptionServiceFromPeter Optionservice;


    public String getSid() {
        return subjectId;
    }

    public void setSid(String sid) {
        this.subjectId = sid;
    }

    public String[] getCheckboxGroups() {
        return checkboxGroups;
    }

    public void setCheckboxGroups(String[] checkboxGroups) {
        this.checkboxGroups = checkboxGroups;
    }

    public String getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(String radioGroup) {
        this.radioGroup = radioGroup;
    }

    public String vote(){
        //从session中取出userId
//        int uid = Integer.parseInt((String) session.get("loginUser"));
        int uid = 34;
        //从session中取出subjec和id
        Subject sub = (Subject)session.get("participateSub");
        int sid = sub.getSubjectId();
        subjectId=sid+"";


        Option o = new Option();
        Set<Option> optionSet = sub.getOptionSet();


//        System.out.println("radioGroup"+radioGroup);

        if(sub.getType()==0){//单选
            Item item = new Item();
            int index = Integer.parseInt(radioGroup);

            o =(Option) optionSet.toArray()[index];
            //投票记录表中的对象
            item.setUserId(uid);
            item.setSubjectId(sid);
            item.setOptionId(o.getOptionId());
            item.setTime(new Date());
            //选项记录表中的内容
            service.addItemAndUpDateOption(item);

        }else if (sub.getType()==1){//多选


            for (String s : checkboxGroups) {
                System.out.println("s::::"+s+"========================");
                Item item = new Item();
                int index = Integer.parseInt(s);
                o =(Option) optionSet.toArray()[index];
                //投票记录表中的对象
                item.setUserId(uid);
                item.setSubjectId(sid);
                item.setOptionId(o.getOptionId());
                item.setTime(new Date());
                //选项记录表中的内容
                service.addItemAndUpDateOption(item);
            }

        }
        return "success";
    }
}
