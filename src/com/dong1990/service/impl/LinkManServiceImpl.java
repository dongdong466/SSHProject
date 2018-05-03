package com.dong1990.service.impl;

import com.dong1990.dao.LinkManDao;
import com.dong1990.domain.LinkMan;
import com.dong1990.service.LinkManService;
import com.dong1990.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("linkManService")
@Transactional(isolation= Isolation.REPEATABLE_READ,propagation= Propagation.REQUIRED,readOnly=true)
public class LinkManServiceImpl implements LinkManService {

    @Resource(name = "linkManDao")
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    @Override
    @Transactional(isolation= Isolation.REPEATABLE_READ,propagation= Propagation.REQUIRED,readOnly=false)
    public void save(LinkMan linkMan) {
        linkManDao.saveOrUpdate(linkMan);
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 1调用dao查询总记录
        Integer totalCount = linkManDao.getTotalCount(dc);
        // 2创建PageBean对象
        PageBean pageBean = new PageBean(currentPage,totalCount,pageSize);
        // 3调用dao查询分页列表数据
        List<LinkMan> list = linkManDao.getPageList(dc,pageBean.getStart(),pageBean.getPageSize());
        // 4列表数据放入PageBean中，并返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public LinkMan getById(Long lkm_id) {
        return linkManDao.getById(lkm_id);
    }
}
