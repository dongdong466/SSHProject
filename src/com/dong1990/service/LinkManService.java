package com.dong1990.service;

import com.dong1990.domain.LinkMan;
import com.dong1990.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    void save(LinkMan linkMan);

    // 分页业务查询
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    LinkMan getById(Long lkm_id);
}
