package xyz.peter666.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Auth;
import xyz.peter666.entity.User;
import xyz.peter666.service.AuthService;
import xyz.peter666.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("authAction")
public class AuthAction extends ActionSupport implements ModelDriven<Auth> {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    private List<Auth>  authList = new ArrayList<>();


    public List<Auth> getAuthList() {
        return authList;
    }



    /**
     * 申请权限
     */

    public String applyAuth(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        HttpServletRequest request = ServletActionContext.getRequest();
        User user = (User) session.getAttribute("loginUser");
        Auth auth = new Auth();
        auth.setUserId(user.getUserId());
        auth.setUsername(user.getUsername());
        auth.setStatus(0);
        auth.setCreateTime(new Date());

        Auth auth1 = authService.findByUserId(auth.getUserId());
        if (auth1==null){
            authService.addAuthMsg(auth);
            request.setAttribute("tips","申请权限发送成功，请等待管理员处理");

        }else{
            request.setAttribute("tips","您已提交过申请权限，请勿重复提交！");
        }
        return "auth";
    }


    /**
     * 得到所有申请权限的请求
     * @return
     */
    public String list(){

        authList = authService.findAll();

        return "list";
    }


    private Auth auth = new Auth();



    public String updateAuth(){
        //修改用户的角色
        userService.updateUserAuth(auth.getUserId());
        //修改用户申请的投票信息
        authService.updateAuth(auth);
        auth.setStatus(1);
        return  "userManager";
    }

    @Override
    public Auth getModel() {
        return auth;
    }
    public void setAuth(Auth auth){
        this.auth = auth;
    }



}
