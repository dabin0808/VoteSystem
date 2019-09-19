package xyz.peter666.action;

import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Subject;
import xyz.peter666.service.SubjectService;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;

/**
 * @Author: Peter
 * @Date: 2019/7/30 9:04
 * Explain:
 */
@Controller
public class CanYuVote extends BaseAction {

    String currentPage="1";
    String rows="6";
    String searchKey="";
    PageBean<Subject> subjectPageBean;
    @Resource(name = "service")
    SubjectService service;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String pageData(){
        //得到要显示的所有记录
        subjectPageBean=service.getPageBean(currentPage, rows, searchKey);
        session.put("subjectPageBean",subjectPageBean);
        session.put("participateCondition",searchKey);
//        session.put("participateSub",null);

        //页面右侧默认展示的内容

        return "success";
    }


}
