package xyz.peter666.service.Impl;

import org.springframework.stereotype.Service;
import xyz.peter666.dao.ItemDao;
import xyz.peter666.dao.OptionDao;
import xyz.peter666.entity.Item;
import xyz.peter666.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Peter
 * @Date: 2019/7/30 18:57
 * Explain:
 */
@Service(value = "itemServicePeter")
public class IItemServiceFormPeter implements ItemService {
    @Resource(name = "itemDaoFromPeter")
    ItemDao dao;
    @Resource(name = "optionDaoFromPeter")
    OptionDao Optiondao;
    @Override
    public List<Item> findItemBySubjectidAndUserid(int subjectId, int userId) {
        return dao.findItemBySubjectidAndUserid(subjectId,userId, "");
    }

    @Override
    public void addItemAndUpDateOption(Item item) {
        dao.upDateItem(item);
        Optiondao.voteCountAddOne(item.getOptionId());
    }
}
