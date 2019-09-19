package xyz.peter666.service.Impl;

import org.springframework.stereotype.Service;
import xyz.peter666.dao.impl.ISubjectDao;
import xyz.peter666.entity.Subject;
import xyz.peter666.service.SubjectService;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;
import java.util.List;


@Service(value = "service")
public class ISubjectService implements SubjectService {

    @Resource(name = "subjectDao")
    ISubjectDao dao;

    /**
     * form Peter
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<Subject> showASubjectForPage(int page ,int pageSize,String key) {
        List<Subject> subjects = dao.showASubjectForPage(page, pageSize,key);
        return subjects;
    }





    //peter查询投票

    /**
     * from Peter
     * @param currentPage  当前页
     * @param rows      记录数、每页的
     * @param condition  条件
     * @return
     */
    public PageBean<Subject> getPageBean(String currentPage, String rows,String condition) {
        //赋初值
        PageBean<Subject> pageBean=new PageBean<>();
        //类型转换
        int page = Integer.parseInt(currentPage);
        int pageSize = Integer.parseInt(rows);

        int totalCount = dao.queryAllSubjectPageCount(condition);
        int totalPage = 1;
        if(totalCount%pageSize==0)
            totalPage=totalCount/pageSize;
        else
            totalPage=totalCount/pageSize+1;
        //得到要显示的封装类
        pageBean.setList(showASubjectForPage(page,pageSize,condition));
        //总记录
        pageBean.setTotalCount(totalCount);
        //总页数
        pageBean.setTotalPage(totalPage);
        //当前页数
        pageBean.setCurrentPage(page);
        //每页记录数
        pageBean.setRows(pageSize);


        return pageBean;
    }
}
