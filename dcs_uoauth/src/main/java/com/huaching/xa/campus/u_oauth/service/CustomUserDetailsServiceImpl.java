package com.huaching.xa.campus.u_oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserSevice userSevice;

//    @Autowired
//    private  RoleService roleService;

    @Autowired
    private Test1Impl test1;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

        System.out.println(test1.test());

        //--------------------认证账号
//        SysUser sysUser = userSevice.loadUserByLoginName(loginName);
//        if (sysUser == null) {
//            throw new UsernameNotFoundException("账号不存在");
//        }

        //-------------------开始授权
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;

//        User user = new User(sysUser.getLoginName(), sysUser.getPassword(),
//                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, obtainUserAuths(sysUser));
        User user = new User("qiuhang","$2a$10$to4ghEsqz/V.qrhV5GHan.JDa8mtQw3ZLnoYgurFjrbewbirGg5jy", obtainUserAuths());
        return user;
    }

    /**
     * 赋值权限
     */
    private List<GrantedAuthority> obtainUserAuths(){
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("测试"));
        return auths;
    }

}
