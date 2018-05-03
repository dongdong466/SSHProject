package com.dong1990.service.impl;

import com.dong1990.dao.BaseDictDao;
import com.dong1990.domain.BaseDict;
import com.dong1990.service.BaseDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {

    @Resource(name="baseDictDao")
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return baseDictDao.getListByTypeCode(dict_type_code);
    }
}
