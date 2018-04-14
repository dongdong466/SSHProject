package com.dong1990.service.impl;

import com.dong1990.dao.BaseDictDao;
import com.dong1990.domain.BaseDict;
import com.dong1990.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {

    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return baseDictDao.getListByTypeCode(dict_type_code);
    }
}
