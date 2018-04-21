package com.dong1990.web.action;

import com.dong1990.domain.Customer;
import com.dong1990.domain.SaleVisit;
import com.dong1990.domain.User;
import com.dong1990.service.SaleVisitService;
import com.dong1990.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    private SaleVisit saleVisit = new SaleVisit();
    private SaleVisitService saleVisitService;

    private Integer currentPage;
    private Integer pageSize;


    public void setSaleVisitService(SaleVisitService saleVisitService) {
        this.saleVisitService = saleVisitService;
    }

    public String add() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        saleVisit.setUser(user);
        saleVisitService.save(saleVisit);
        return "toList";
    }

    public String update() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        saleVisit.setUser(user);
        saleVisitService.update(saleVisit);
        return "toList";
    }

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

    public String list(){

        // 封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
        if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null && StringUtils.isNotBlank(saleVisit.getCustomer().getCust_name())) {
            dc.add(Restrictions.eq("customer.cust_id",saleVisit.getCustomer().getCust_id()));
        }
        // 1.调用Service查询分页数据（PageBean）
        PageBean pageBean = saleVisitService.getPageBean(dc,currentPage,pageSize);
        // 2将PageBean放入到request域，转发到页面列表显示
        ActionContext.getContext().put("pageBean",pageBean);
        return "list";
    }


    public String toEdit() throws Exception {
        SaleVisit sv = saleVisitService.getById(saleVisit.getVisit_id());
        ActionContext.getContext().put("saleVisit",sv);
        return "edit";
    }

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }


}
