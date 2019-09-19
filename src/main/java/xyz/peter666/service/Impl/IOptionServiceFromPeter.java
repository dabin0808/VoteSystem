package xyz.peter666.service.Impl;

import org.springframework.stereotype.Service;
import xyz.peter666.dao.OptionDao;
import xyz.peter666.dao.impl.IItemDaoFormPeter;
import xyz.peter666.service.OptionServiceFromPeter;

import javax.annotation.Resource;

/**
 * @Author: Peter
 * @Date: 2019/7/30 19:16
 * Explain:
 */

@Service(value = "optionServiceFromPeter")
public class IOptionServiceFromPeter implements OptionServiceFromPeter {

    @Resource(name = "optionDaoFromPeter")
    OptionDao dao;
    @Override
    public int findOrderByOptionId(int optionId) {
        return dao.findOrderByOptionId(optionId);
    }
}
