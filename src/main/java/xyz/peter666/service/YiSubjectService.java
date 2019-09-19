package xyz.peter666.service;

import xyz.peter666.entity.Subject;

import java.util.Date;
import java.util.List;

/**
 * @Author: dabin0808
 * @Date: 2019/7/30 9:04
 * Explain: Service接口
 */
public interface YiSubjectService {
    /*
      将subject添加入数据库中
    */
    public void addSubject(Subject subject);


    // 设置时间
    Date parseStartTime(Date createTime);

    Date parseEndTime(Date createTime, Date endTime);

    //删除主题
    void deleteSubject(Subject delSubject);

    //查询一个主题
    Subject findOneSubject(int subjectId);

    //显示所有主题
    List<Subject> shouAllSubject();

    //显示用户id
    List<Subject> shouIdSubject(Integer userId);

    //更新修改用户
    void updSubject(Subject subject);

    //修改时间
    public String modTime(Date createTime);
}
