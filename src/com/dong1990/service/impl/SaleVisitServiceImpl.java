package com.dong1990.service.impl;

import com.dong1990.dao.SaleVisitDao;
import com.dong1990.domain.SaleVisit;
import com.dong1990.service.SaleVisitService;
import com.dong1990.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class SaleVisitServiceImpl implements SaleVisitService {

    private SaleVisitDao saleVisitDao;


    public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
        this.saleVisitDao = saleVisitDao;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {

        // 1调用dao查询总记录
        Integer totalCount = saleVisitDao.getTotalCount(dc);
        // 2创建PageBean对象
        PageBean pageBean = new PageBean(currentPage,totalCount,pageSize);
        // 3调用dao查询分页列表数据
        List<SaleVisit> list = saleVisitDao.getPageList(dc,pageBean.getStart(),pageBean.getPageSize());
        // 4列表数据放入PageBean中，并返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public SaleVisit getById(String visit_id) {
        return saleVisitDao.getById(visit_id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(SaleVisit saleVisit) {
        //SaleVisit sv = saleVisitDao.getById(saleVisit.getVisit_id());

        saleVisitDao.update(saleVisit);
    }
}
