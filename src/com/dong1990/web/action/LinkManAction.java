package com.dong1990.web.action;

import com.dong1990.domain.Customer;
import com.dong1990.domain.LinkMan;
import com.dong1990.service.LinkManService;
import com.dong1990.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;

@Scope("prototype")
@Controller("linkManAction")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{


    private LinkMan linkMan = new LinkMan();
    @Resource(name = "linkManService")
    private LinkManService linkManService;
    private Integer currentPage;
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    public String list(){

        // 封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
            if (StringUtils.isNoneBlank(linkMan.getLkm_name())) {
            dc.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }
        if (linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() != null && StringUtils.isNotBlank(linkMan.getCustomer().getCust_name())) {
            dc.add(Restrictions.eq("customer.cust_id",linkMan.getCustomer().getCust_id()));// cust_id 是long类型的
        }

        // 1.调用Service查询分页数据（PageBean）
        PageBean pageBean = linkManService.getPageBean(dc,currentPage,pageSize);
        // 2将PageBean放入到request域，转发到页面列表显示
        ActionContext.getContext().put("pageBean",pageBean);
        return "list";
    }

    public String add() throws Exception {
        linkManService.save(linkMan);
        return "toList";
    }

    public String toEdit() throws Exception {
        LinkMan lkm = linkManService.getById(linkMan.getLkm_id());
        ActionContext.getContext().put("linkMan",lkm);
        return "edit";
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }
}
