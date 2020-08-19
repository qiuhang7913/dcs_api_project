package com.huaching.xa.campus.u_oauth.config;

<<<<<<< HEAD
=======
import com.huaching.xa.campus.u_oauth.filter.DBEnvironmentChangeFilter;
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
import com.huaching.xa.campus.u_oauth.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
<<<<<<< HEAD

/**
 * <p>spring security配置类</p>
 *
 * @author qiuhang
 * @version v1.0 2019/10/5/005
=======
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: qiuhang
 * @date: 2019/9/18
 * @description: spring security配置类
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsServiceImpl userDetailsService;

    // @Autowired
    // JwtManagerAuthenticationTokenFilter jwtSecurityAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
<<<<<<< HEAD

=======
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/druid/**");
        http.requestMatchers().antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
<<<<<<< HEAD
=======

        //http.addFilterBefore(new DBEnvironmentChangeFilter(), UsernamePasswordAuthenticationFilter.class);
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
    }

    /**
     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
<<<<<<< HEAD
=======
        //return new BCryptPasswordEncoder();
>>>>>>> a59694efc18f7c3cd80d479c9116b8584be62b21
        return new BCryptPasswordEncoder();
    }
}
