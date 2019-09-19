package xyz.peter666.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import xyz.peter666.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Set;


@Component("loginInterceptor")
public class LoginInterceptor extends MethodFilterInterceptor {






    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        String method = invocation.getProxy().getMethod();
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("loginUser");
        Set<String> set = getExcludeMethodsSet();
        if(user!=null){
            return invocation.invoke();
        }else {
            return "login";
        }
    }
}
