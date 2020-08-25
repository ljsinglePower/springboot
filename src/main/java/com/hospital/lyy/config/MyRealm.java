package com.hospital.lyy.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.lyy.moudle.login.children.authority.beans.Authority;
import com.hospital.lyy.moudle.login.children.authority.mapper.AuthorityMapper;
import com.hospital.lyy.moudle.login.children.roles.beans.Roles;
import com.hospital.lyy.moudle.login.children.roles.mapper.RolesMapper;
import com.hospital.lyy.moudle.login.children.user.beans.User;
import com.hospital.lyy.moudle.login.children.user.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RolesMapper rolesMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AuthorityMapper authorityMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        Object principal = principalCollection.getPrimaryPrincipal();

        //2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        Set<String> permissions = new HashSet<String>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",principal.toString());
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size()!=0){
            //获取用户角色
            QueryWrapper<Roles> rolesQueryWrapper=new QueryWrapper<>();
            rolesQueryWrapper.eq("uid",users.get(0).getUid());
            List<Roles> roleslist = rolesMapper.selectList(rolesQueryWrapper);
            if(roleslist.size()!=0){
                for (Roles roles:roleslist ){
                    //获取用户角色所对应的权限
                    Integer rolesRid = roles.getRid();
                    QueryWrapper<Authority> authorityQueryWrapper=new QueryWrapper<>();
                    authorityQueryWrapper.eq("rid",rolesRid);
                    List<Authority> authorityList = authorityMapper.selectList(authorityQueryWrapper);
                    if(authorityList.size()!=0){
                      for (Authority authority:authorityList) {
                          permissions.add(authority.getAuthority());
                      }
                    }
                }
            }
        }
        //3. 创建 SimpleAuthorizationInfo, 并设置其 roles 属性.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);

        //4. 返回 SimpleAuthorizationInfo 对象.
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("[FirstRealm] doGetAuthenticationInfo");

        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        //2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");

        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在!");
        }

        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }

        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = username;
        //2). credentials: 密码
        Object credentials = null;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size()!=0){credentials=users.get(0).getPassword();}
        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();

        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        return info;
    }
}
