package com.tsrs.webedi.modular.biz.service.impl;

import com.tsrs.webedi.common.annotion.DataSource;
import com.tsrs.webedi.common.constant.DSEnum;
import com.tsrs.webedi.common.persistence.dao.TestMapper;
import com.tsrs.webedi.common.persistence.model.Test;
import com.tsrs.webedi.modular.biz.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试服务
 *
 * @author Tsrs-WebEDI
 * @date 2017-06-23 23:02
 */
@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    TestMapper testMapper;

    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public void testBiz() {
        Test test = testMapper.selectById(1);
        test.setId(22);
        test.insert();
    }


    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_WEBEDI)
    public void testWebEDI() {
        Test test = testMapper.selectById(1);
        test.setId(33);
        test.insert();
    }

    @Override
    @Transactional
    public void testAll() {
        testBiz();
        testWebEDI();
        //int i = 1 / 0;
    }

}
