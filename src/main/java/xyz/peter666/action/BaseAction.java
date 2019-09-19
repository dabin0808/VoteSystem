package xyz.peter666.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @Author: Peter
 * @Date: 2019/7/30 9:10
 * Explain:
 */
public class BaseAction extends ActionSupport implements SessionAware, RequestAware {
     Map<String,Object> session;
     Map<String,Object> request;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }


    @Override
    public void setRequest(Map<String, Object> map) {
        this.request=map;
    }


}
