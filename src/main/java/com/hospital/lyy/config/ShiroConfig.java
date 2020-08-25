package com.hospital.lyy.config;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
        @Bean
        public MyRealm myRealm() {
            return new MyRealm();
        }

        @Bean
        public DefaultWebSecurityManager securityManager() {
            DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            securityManager.setRealm(myRealm());
            return securityManager;
        }

        @Bean
        public ShiroFilterFactoryBean shiroFilterFactoryBean() {
            /*
             * 只有perms，roles，ssl，rest，port才是属于AuthorizationFilter，而anon，authcBasic，auchc，user是AuthenticationFilter，
             * 所以unauthorizedUrl设置后页面不跳转
             * */
            ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
            bean.setSecurityManager(securityManager());
            bean.setLoginUrl("/index");
            bean.setSuccessUrl("/success");
            bean.setUnauthorizedUrl("/noauth");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("/doLogin", "anon");
            map.put("/index","anon");
            map.put("/noauth","anon");
            map.put("/**", "authc");
            bean.setFilterChainDefinitionMap(map);
            return bean;
        }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @param
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
