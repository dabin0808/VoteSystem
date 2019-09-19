package xyz.peter666.service.Impl;

import javafx.scene.input.DataFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.peter666.dao.impl.ISubjectDao;
import xyz.peter666.entity.Subject;
import xyz.peter666.service.YiSubjectService;
import xyz.peter666.util.DateFormat;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: dabin0808
 * @Date: 2019/7/30 9:02
 * Explain:
 */
@Transactional
@Service("yisubjectService")
public class SubjectServiceImp implements YiSubjectService {

    @Resource(name = "subjectDao")
    ISubjectDao iSubjectDao;

    /*
        实现添加主题Service
     */
    @Override
    public void addSubject(Subject subject) {
        iSubjectDao.addSubject(subject);
    }

    /*
    判断返回时间
     */
    @Override
    public Date parseStartTime(Date createTime) {
        Date newDate = new Date();
        if ((createTime == null) || createTime.compareTo(newDate) < 0) {
            return newDate;
        }
        return createTime;
    }

    @Override
    public Date parseEndTime(Date createTime, Date endTime) {
        if (endTime == null || createTime.compareTo(endTime) > 0) {
            Calendar calendar = Calendar.getInstance();
            if (createTime == null)
                createTime = new Date();
            calendar.setTime(createTime);
            calendar.add(Calendar.DATE, 2);
            return calendar.getTime();
        }
        return endTime;
    }

    //修改时间格式
    public String modTime(Date modTime) {
        String newStr;
        newStr = DateFormat.getFormat1(modTime);
        newStr = newStr.replace(" ","T");
        return  newStr;
    }

    /*
    删除主题
     */
    @Override
    public void deleteSubject(Subject delSubject) {

        iSubjectDao.deleteSubejct(delSubject);
    }

    /*
    查找一个主题
     */
    @Override
    public Subject findOneSubject(int subjectId) {
        return iSubjectDao.findOneSubject(subjectId);
    }

    /*
    返回所有的主题投票
     */
    @Override
    public List<Subject> shouAllSubject() {
        return iSubjectDao.shouAllSubject();
    }

    /*
    根据登录用户的ID查询主题
     */
    @Override
    public List<Subject> shouIdSubject(Integer userId) {
        return iSubjectDao.shouIdSubject(userId);
    }

    @Override
    public void updSubject(Subject subject) {

        iSubjectDao.updSubject(subject);
    }

}
