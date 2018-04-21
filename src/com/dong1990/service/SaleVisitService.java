package com.dong1990.service;

import com.dong1990.domain.SaleVisit;
import com.dong1990.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService {
    void save(SaleVisit saleVisit);

    // 分页业务查询
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    SaleVisit getById(String visit_id);

    void update(SaleVisit saleVisit);
}
