package xyz.peter666.action;

import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Item;
import xyz.peter666.entity.Option;
import xyz.peter666.entity.Subject;
import xyz.peter666.entity.User;
import xyz.peter666.service.ItemService;
import xyz.peter666.util.DateFormat;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @Author: Peter
 * @Date: 2019/7/31 19:22
 * Explain:
 */
@Controller
public class VoteEcharRightAction  extends BaseAction {

    String subjectId;
    @Resource(name = "itemServicePeter")
    ItemService itemService;

    //返回json的Map
    Map<String, Object> map = new HashMap<>();


    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String vote() {
        int subId;
        PageBean<Subject> viewVoteSubject = (PageBean<Subject>) session.get("viewVoteSubject");
        List<Subject> subjectList = viewVoteSubject.getList();
        if (subjectId==null){
            subId=subjectList.get(0).getSubjectId();
        }else{
          subId = Integer.parseInt(subjectId);
        }
//        User loginUser = (User) session.get("loginUser");
        User loginUser = new User();
        loginUser.setUserId(34);



        // 根据截至时间、用户权限等显示查询结果或其他提示信息
        for (Subject subject : subjectList) {
            System.out.println("title:"+subject.getTitle());
            System.out.println("id"+subId+"sid:"+subject.getSubjectId());
            if (subject.getSubjectId() == subId) {

                Date parse = subject.getEndTime();
                System.out.println("截止时间："+DateFormat.getFormat(parse));
                if (parse.getTime() < (new Date()).getTime()) {
                    System.out.println(1);
                    handleData(subject);
                } else if (loginUser != null) {
                    System.out.println(2);
                    List<Item> items = itemService.findItemBySubjectidAndUserid(subId, loginUser.getUserId());
                    System.out.println(items);
                    if (items.size() != 0) {
                        System.out.println(4);
                        handleData(subject);
                    } else {
                        System.out.println(3);
                        handleData(null);
                    }
                } else {
                    System.out.println(5);
                    handleData(null);
                }
            }
        }

        return "success";
    }

    // 把当前主题的投票记录以json的形式写回
    public void handleData(Subject subject) {
        List<String> optionNames = new ArrayList<>();
        List<Integer> optionCounts = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(subject);

        if (subject != null) {
            map.put("title", subject.getTitle());
            Set<Option> optionSet = (Set<Option>) subject.getOptionSet();
            List<Option> options = new ArrayList<>();
            options.addAll(optionSet);

            for (Option option : options) {
                optionNames.add(option.getContent());
                optionCounts.add(option.getCounts());
            }
            map.put("labels", optionNames);
            map.put("counts", optionCounts);
            map.put("datas", true);

        } else {
            map.put("datas", false);
        }


    }


}
