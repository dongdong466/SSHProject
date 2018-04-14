package com.dong1990.web.action;

import com.dong1990.domain.Customer;
import com.dong1990.domain.User;
import com.dong1990.service.CustomerService;
import com.dong1990.service.UserService;
import com.dong1990.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;


public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    private Customer customer = new Customer();
    private CustomerService customerService;
    private Integer currentPage;
    private Integer pageSize;
    //上传的文件会自动封装到File对象
    //在后台提供一个与前台input type=file组件 name相同的属性
    private File photo;
    //在提交键名后加上固定后缀FileName,文件名称会自动封装到属性中
    private String photoFileName;
    //在提交键名后加上固定后缀ContentType,文件MIME类型会自动封装到属性中
    private String photoContentType;


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

    // 整合方案1：不推荐这样的方式
    // 不推荐理由:最好由spring完整管理action的生命周期.spring中功能才应用到Action上.
    // 这里的名字要和applicationContext.xml里面的配置名字一样才能实现自动装配
    // <bean name="userService" class="com.dong1990.service.impl.UserServiceImpl"></bean>
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }



    public String list(){

        // 封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if (StringUtils.isNoneBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
        }
        // 1.调用Service查询分页数据（PageBean）
        PageBean pageBean = customerService.getPageBean(dc,currentPage,pageSize);
        // 2将PageBean放入到request域，转发到页面列表显示
        ActionContext.getContext().put("pageBean",pageBean);
        return "list";
    }


    public String add() throws Exception{
        if(photo!=null){
            System.out.println("文件名称:"+photoFileName);
            System.out.println("文件类型:"+photoContentType);
            //将上传文件保存到指定位置
            photo.renameTo(new File("d:"+File.separator+"upload"+File.separator+"haha.jpg"));
        }

        //---------------------------------------------------------------------
        customerService.save(customer);
        return "toList";
    }

    public String toEdit() throws Exception{
        //1调用Service根据id获得客户对象
        Customer c = customerService.getById(customer.getCust_id());
        //2 将客户对象放置到request域,并转发到编辑页面
        ActionContext.getContext().put("customer", c);
        return "edit";
    }

    @Override
    public Customer getModel() {
        return customer;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getPhotoFileName() {

        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }
}
