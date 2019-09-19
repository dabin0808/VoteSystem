package xyz.peter666.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.service.UserService;
import xyz.peter666.util.JsonUtils;
import xyz.peter666.util.PageBean;
import xyz.peter666.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {

    @Autowired
    private UserService userService;
    private User user;
    //分页
    private PageBean<User> page = new PageBean();
    //查找用户的条件
    private Map<String, String> conditions = new HashMap<>();

    public Map<String, String> getConditions() {
        return conditions;
    }

    //得到验证码
    private String code;

    public void setCode(String code) {
        this.code = code;
    }

    public void setConditions(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public UserAction() {
        user = new User();
        page.setCurrentPage(1);
        page.setRows(10);
        System.out.println("执行了构造方法");
    }

    public User getModel() {
        return user;

    }

    public void setModel(User user) {
        this.user = user;

    }

    public void setCurrentPage(Integer currentPage) {
        page.setCurrentPage(currentPage);

    }

    public void setRows(Integer rows) {
        page.setRows(rows);

    }

    public PageBean getPage() {
        return page;

    }

    /**
     * 分页查询与搜索
     * @return
     */
    public String findPage() {

        DetachedCriteria detachedCriteria = null;
        if (conditions != null) {
            detachedCriteria = DetachedCriteria.forClass(User.class);
            Set<String> keySet = conditions.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {

                String key = iterator.next();
                String value = conditions.get(key);
                if (value != null && !"".equals(value)) {
                    //detachedCriteria.add(Restrictions.like(key,"%"+value+"%"));
                    if (key.equals("username")) {
                        detachedCriteria.add(Restrictions.like(key, "%" + value + "%"));
                    } else {
                        Integer intValue = Integer.parseInt(value);
                        detachedCriteria.add(Restrictions.eq(key, intValue));
                    }
                }

            }
            page.setDetachedCriteria(detachedCriteria);
        }
        userService.findPage(page);

        return "list";

    }

    /**
     * 删除用户
     * @return
     */
    public String deleteUser() {
        userService.deleteUser(user);
        return "manager";
    }

    /**
     * 登录
     * @throws IOException
     */
    public void login() throws IOException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        //得到验证码
        String validateCode = (String) session.getAttribute("validateCode");
        Result result = null;
        if(!validateCode.equalsIgnoreCase(code)){
            result = Result.build(500,"验证码错误",null);

        }else{
             user = userService.login(user);
            if(user==null){
                result = Result.build(500,"用户名或密码有误",null);
            }else{
                result = Result.ok();
                //将会话保存在session中
                session.setAttribute("loginUser",user);
            }
        }


        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");

        String json = JsonUtils.toJson(result);
        System.out.println("提示："+json);
        response.getWriter().println(json);
        // ResponseUtils.response(ServletActionContext.getResponse(),result);

    }

    /**
     * 注册用户
     */
    public void register() throws IOException {
        System.out.println(user);
        user.setVersion(0);
        user.setStatus(0);
        HttpSession session = ServletActionContext.getRequest().getSession();
        //得到验证码
        String validateCode = (String) session.getAttribute("validateCode");
        Result result = null;
        if(!validateCode.equalsIgnoreCase(code)){
            result = Result.build(500,"验证码错误",null);
        }else{

            result = userService.register(user);
        }
        String json = JsonUtils.toJson(result);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().println(json);
    }
    /**
     * 更改用户信息
     */
    public String update(){
        userService.updateUser(user);
        return "manager";
    }
    /**
     * 注销用户
     */
    public String logout(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.invalidate();
        return "logout";
    }

    /**
     * 查找用户
     * @return
     */
    public String findUser(){
        user = userService.findById(user.getUserId());

        //HttpServletRequest request = ServletActionContext.getRequest();
        //String method = request.getParameter("method");
        return "update";
    }

    public String info(){

        return "info";
    }









}
