package com.yuu.ymall.web.admin.commons.shiro;

import com.yuu.ymall.domain.TbShiroFilter;
import com.yuu.ymall.web.admin.service.SystemService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Classname MyShiroFilterFactoryBean
 * @Date 2019/5/11 20:03
 * @Created by Yuu
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    private static final Logger log = LoggerFactory.getLogger(MyShiroFilterFactoryBean.class);

    /**
     * 配置中的过滤链
     */
    public static String definitions;

    // 权限 Service
    @Autowired
    private SystemService systemService;

    @Override
    public void setFilterChainDefinitions(String definitions) {

        MyShiroFilterFactoryBean.definitions = definitions;

        // 数据库动态权限
        List<TbShiroFilter> list = systemService.getShiroFilter();
        for (TbShiroFilter tbShiroFilter : list) {
            // 字符串拼接权限
            definitions = definitions + tbShiroFilter.getName() + "=" + tbShiroFilter.getPerms() + "\n";
        }

        // 从配置文件加载权限配置
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }

        this.setFilterChainDefinitionMap(section);
    }
}
