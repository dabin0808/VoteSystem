package xyz.peter666.action;

import org.springframework.stereotype.Controller;
import xyz.peter666.entity.Subject;
import xyz.peter666.service.SubjectService;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;

/**
 * @Author: Peter
 * @Date: 2019/7/31 19:41
 * Explain:
 */
@Controller
public class VoteEcharLeftAction extends BaseAction {

    String currentPage="1";
    String rows="6";
    String viewVoteCondition="";
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
        return viewVoteCondition;
    }

    public void setSearchKey(String searchKey) {
        this.viewVoteCondition = searchKey;
    }

    public String pageData(){
        //得到要显示的所有记录
        subjectPageBean=service.getPageBean(currentPage, rows, viewVoteCondition);
        session.put("viewVoteSubject",subjectPageBean);
        session.put("viewVoteCondition",viewVoteCondition);
//        session.put("participateSub",null);

        //页面右侧默认展示的内容

        return "success";
    }
}
