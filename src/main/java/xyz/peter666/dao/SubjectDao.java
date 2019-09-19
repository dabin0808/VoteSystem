package xyz.peter666.dao;

import xyz.peter666.entity.Subject;

import java.util.List;

/**
 * 与投票主题表相关操作的接口
 */

public interface SubjectDao {

    //查看主题的分页
    public List<Subject> showASubjectForPage(int page, int pageSize,String key);

    //查询主题页数
    public  int queryAllSubjectPageCount(String key);

    //根据用户ID查看参与过的投票
    public  List<Subject> showMyPartTakeSubject(int id);

    //根据主题ID删除投票
    public void deleteSubejct(int subjectId);



}
