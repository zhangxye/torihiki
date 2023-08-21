package com.tsrs.webedi.modular.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tsrs.webedi.common.exception.BizExceptionEnum;
import com.tsrs.webedi.common.exception.BussinessException;
import com.tsrs.webedi.modular.system.dao.DictDao;
import com.tsrs.webedi.modular.system.service.IDictService;
import com.tsrs.webedi.common.persistence.dao.DictMapper;
import com.tsrs.webedi.common.persistence.model.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.tsrs.webedi.common.constant.factory.MutiStrFactory.*;

@Service
@Transactional
public class DictServiceImpl implements IDictService {

    @Resource
    DictDao dictDao;

    @Resource
    DictMapper dictMapper;

    @Override
    public void addDict(String dictName, String dictValues) {
        //判断有没有该字典
        List<Dict> dicts = dictMapper.selectList(new QueryWrapper<Dict>().eq("name", dictName).eq("pid", 0));
        if(dicts != null && dicts.size() > 0){
            throw new BussinessException(BizExceptionEnum.DICT_EXISTED);
        }

        //解析dictValues
        List<Map<String, String>> items = parseKeyValue(dictValues);

        //新規字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setNum(0);
        dict.setPid(0);
        this.dictMapper.insert(dict);

        //新規字典条目
        for (Map<String, String> item : items) {
            String num = item.get(MUTI_STR_KEY);
            String name = item.get(MUTI_STR_VALUE);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            itemDict.setNum(Integer.valueOf(num));
            this.dictMapper.insert(itemDict);
        }
    }

    @Override
    public void editDict(Integer dictId, String dictName, String dicts) {
        //削除之前的字典
        this.delteDict(dictId);

        //重新新規新的字典
        this.addDict(dictName,dicts);
    }

    @Override
    public void delteDict(Integer dictId) {
        //削除这个字典的子词典
    	QueryWrapper<Dict> dictEntityWrapper = new QueryWrapper<>();
        dictEntityWrapper = dictEntityWrapper.eq("pid", dictId);
        dictMapper.delete(dictEntityWrapper);

        //削除这个词典
        dictMapper.deleteById(dictId);
    }
}
