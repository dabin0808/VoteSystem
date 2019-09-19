package xyz.peter666.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import xyz.peter666.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("authorInterceptor")
public class AuthorInterceptor extends MethodFilterInterceptor {

    private String msg;



    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        System.out.println("进行了拦截器");
        String method = invocation.getProxy().getActionName();
        HttpSession session = ServletActionContext.getRequest().getSession();
        HttpServletRequest request = ServletActionContext.getRequest();
        User user = (User) session.getAttribute("loginUser");
        System.out.println(user);
        Integer status = user.getStatus();

        if(status==2){
             invocation.invoke();
        }else{

            if(status == 0){
                msg = "您的权限不够，请先提升权限！";
            }else{
                msg = "您没有权限访问！";
            }
            request.setAttribute("msg",msg);
            return "404";

        }
        return null;

    }
}
