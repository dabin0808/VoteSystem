package xyz.peter666.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Option;
import xyz.peter666.entity.Subject;
import xyz.peter666.entity.User;
import xyz.peter666.service.YiSubjectService;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;


/**
 * @Author: dabin0808
 * @Date: 2019/7/29 20:12
 * Explain: Subject 的Action方法
 */
@Controller("subjecAction")
public class SubjectAction extends ActionSupport {
    Subject subject = new Subject();

    @Resource(name = "yisubjectService")      //注入Yiservice
            YiSubjectService yiSubjectService;

    /*
    显示所有主题
     */
    public String showAllSubject() {

        List<Subject> subjectLS = yiSubjectService.shouAllSubject();
        ServletActionContext.getRequest().getSession().setAttribute("subjectLS", subjectLS);
        return "showAllSubject";
    }

    /*
    进去维护，根据登录的用户iD，显示他的投票，然后进行修改，删除
     */
    public String showIdSubjcet() {
        System.out.println("showIdSubjcet.....action ");
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        List<Subject> subjectUS = yiSubjectService.shouIdSubject(user.getUserId());

        // 放入值栈中
        // ActionContext.getContext().getValueStack().set("subjectUS", subjectUS);
        ServletActionContext.getRequest().getSession().setAttribute("subjectUS", subjectUS);
        return "showIdSubjcet";

    }

    //添加主题函数
    public String addSubject() throws Exception {
        System.out.println("添加Subject方法执行");
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");

        //输出测试值
        Map<String, String[]> map = ServletActionContext.getRequest().getParameterMap();
        System.out.println(subject.toString());
        for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry element = (Map.Entry) iter.next();
            //key值
            Object strKey = element.getKey();
            //value,数组形式
            String[] value = (String[]) element.getValue();
            System.out.print(strKey.toString() + "=");
            for (int i = 0; i < value.length; i++) {

                System.out.print(value[i] + ",");
            }
        }

        //调用服务
        subject.setUser(user);

        subject.setCreateTime(yiSubjectService.parseStartTime(subject.getCreateTime()));
        subject.setEndTime(yiSubjectService.parseEndTime(subject.getCreateTime(), subject.getEndTime()));
        yiSubjectService.addSubject(subject);
        System.out.println("测试Subject Action");

        return SUCCESS;
    }

    //根据id 查找一个subject
    public Subject findOneSubject(int subjectId) {
        return yiSubjectService.findOneSubject(subjectId);
    }

    //删除主题
    public String delSubject() {
        System.out.println("delSubject + servlet.......");
        // 获取要删除的 主题id
        int subjectId = Integer.valueOf(ServletActionContext.getRequest().getParameter("subjectId"));
        Subject delSubject = findOneSubject(subjectId);

        if (delSubject == null) { //如果传过来id，对应的投票为空

        }
        yiSubjectService.deleteSubject(delSubject);

        return "IdAction";
    }

    //跳入修改主题页面，放入一个Subject数据
    public String modSubjectA() {
        System.out.println("modSubjectA + Action......A.subject：" + subject.toString());
        // 获取要修改的 主题id
        int subjectId = Integer.valueOf(ServletActionContext.getRequest().getParameter("subjectId"));
        Subject subject = findOneSubject(subjectId);
        //设置入值域
        //设置时间回显格式
        String creteStr = yiSubjectService.modTime(subject.getCreateTime());
        String endStr = yiSubjectService.modTime(subject.getEndTime());

        ServletActionContext.getRequest().setAttribute("creteStr",creteStr);
        ServletActionContext.getRequest().setAttribute("endStr",endStr);
        // 放入值栈中
        ActionContext.getContext().getValueStack().set("subject", subject);
        return "modSubjectA";
    }

    //修改主题
    public String modSubject() {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        //subject.setUser(user);
        System.out.println("modSubject + Action.....Mod..subject：" + subject.toString());
        // 获取要修改的 主题id
        int subjectId = Integer.valueOf(ServletActionContext.getRequest().getParameter("subjectId"));
        //根据subjectId查询
        Subject result = findOneSubject(subjectId);
        // 查询错误
        if (result != null) {
            yiSubjectService.deleteSubject(result);
        }
        Subject modSubject = new Subject();

        // 设置对应值
        if (subject.getTitle() != "") {
            modSubject.setTitle(subject.getTitle());
        }
        if (subject.getDiscription() != "") {
            modSubject.setDiscription(subject.getDiscription());
        }
        if (subject.getCreateTime() != null) {
            modSubject.setCreateTime(subject.getCreateTime());
        }
        if (subject.getEndTime() != null) {
            modSubject.setEndTime(subject.getEndTime());
        }
        modSubject.setType(subject.getType());

        HashSet<Option> newOP = new HashSet<>();
        for (Option p : subject.getOptionSet()) {
            if ((p.getContent() != "" && p != null)) {
                p.setSubject(modSubject);
                newOP.add(p);
            }
        }
        modSubject.setOptionSet(newOP);
        modSubject.setUser(user);

        yiSubjectService.addSubject(modSubject);


        return "IdAction";
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void test() {
        System.out.println("测试函数");

        Subject subject = new Subject();
        //subject.setSubjectId(24);
        subject = findOneSubject(28);
        //System.out.println(subject.getOptionSet().size());
        subject.getOptionSet().forEach(System.out::println);

        subject.setDiscription("测试藐视");
        subject.setTitle("测试标题");
        Set<Option> so = new HashSet<>();
        for (Option o : subject.getOptionSet()) {
            o.setSubject(subject);
            o.setContent("123" + new Random().nextInt(10000));
            so.add(o);
        }
        Option option = new Option();
        option.setContent("修改新内容");
        so.add(option);
        subject.setOptionSet(so);

        yiSubjectService.updSubject(subject);
    }

}
