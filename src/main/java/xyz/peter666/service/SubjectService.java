package xyz.peter666.service;

import org.springframework.stereotype.Service;
import xyz.peter666.dao.impl.ISubjectDao;
import xyz.peter666.entity.Subject;
import xyz.peter666.util.PageBean;

import javax.annotation.Resource;
import java.util.List;


public interface SubjectService {
    //投票分页查询
    List<Subject> showASubjectForPage(int page ,int pageSize,String key);

    public PageBean<Subject> getPageBean(String currentPage, String rows, String condition);
}
