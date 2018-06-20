package com.minshenglife.config;

import com.minshenglife.entity.User;
import com.minshenglife.repo.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserRepo userRepo;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/login*", "/oauth/authorize*")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")

                .loginProcessingUrl("/login")

                .permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        return new UserDetailsService() {

            @Transactional
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                List<User> byUsername = userRepo.findByUsername(username.trim());
                if(byUsername!=null && byUsername.size()>0){
                    User user = byUsername.get(0);
                    return user;
                }else{
                    throw new UsernameNotFoundException("用户不存在");
                }
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
    }
}
