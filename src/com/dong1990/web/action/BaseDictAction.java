package com.dong1990.web.action;


import com.dong1990.domain.BaseDict;
import com.dong1990.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
@Scope("prototype")
@Controller("baseDictAction")
public class BaseDictAction extends ActionSupport {

    // 使用属性驱动获得条件
    private String dict_type_code;

    @Resource(name = "baseDictService")
    private BaseDictService baseDictService;

    @Override
    public String execute() throws Exception {
        // 1调用Service层根据typecode获得数据字典对象list
        List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
        // 2将list对象转换为json对象
        String json = JSONArray.fromObject(list).toString();
        // 3将json发送给浏览器
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return null;//告诉struts2不需要进行结果处理
    }


    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
}
