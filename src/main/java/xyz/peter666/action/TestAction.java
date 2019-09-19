package xyz.peter666.action;

import org.springframework.stereotype.Controller;

/**
 * 用来测试的Action
 */

@Controller
public class TestAction {
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String test(){
        return "success";
    }
}
