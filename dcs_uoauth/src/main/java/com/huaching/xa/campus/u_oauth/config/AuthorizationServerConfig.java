package com.huaching.xa.campus.u_oauth.config;

import com.huaching.xa.campus.u_oauth.oauth_extend.RedisTokenStore;
import com.huaching.xa.campus.u_oauth.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * <p>spring OAuth2.0认证中心配置</p>
 *
 * @author qiuhang
 * @version v1.0 2019/10/5/005
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // @Autowired
    // private DataSource dataSource;

    //@Autowired
    //private MyWebResponseExceptionTranslator myWebResponseExceptionTranslator;

    @Autowired
    private AuthenticationManager authenticationManager;


    // @Autowired
    // private DataSource dataSource;


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
    //private MyWebResponseExceptionTranslator myWebResponseExceptionTranslator;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    // @Primary
    // @Bean
    // public TokenStore jdbcTokenStore(){
    //     return new JdbcTokenStore(dataSource);
    // }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients.withClientDetails(clientDetails());
       clients.inMemory()

               .withClient("campus-ym")
               .scopes("read")
               .secret(passwordEncoder.encode("campus-ym-001"))
               //.authorizedGrantTypes("password", "authorization_code", "refresh_token")
               .authorizedGrantTypes("password", "refresh_token")

               .and()

               .withClient("campus-jy")
               .scopes("read")
               .secret(passwordEncoder.encode("campus-jy-001"))
               //.authorizedGrantTypes("password", "authorization_code", "refresh_token")
               .authorizedGrantTypes("password", "refresh_token");
//               .and()
//               .withClient("webapp")
//               .scopes("read")
//               .authorizedGrantTypes("implicit")
//               .and()
//               .withClient("browser")
//               .authorizedGrantTypes("refresh_token", "password")
//               .scopes("read");
    }

    // @Bean
    // public ClientDetailsService clientDetails() {
    //     return new JdbcClientDetailsService(dataSource);
    // }

    // @Bean
    // public WebResponseExceptionTranslator webResponseExceptionTranslator(){
    //     return new MssWebResponseExceptionTranslator();
    // }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(customUserDetailsService)
                .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());

        // 添加额外的token信息
        //endpoints.tokenEnhancer();
        //认证异常翻译
        //endpoints.exceptionTranslator(myWebResponseExceptionTranslator);
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60*60*12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
