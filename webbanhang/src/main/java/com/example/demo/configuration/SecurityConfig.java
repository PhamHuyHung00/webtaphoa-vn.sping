package com.example.demo.configuration;

import com.example.demo.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new MyUserDetailService();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/user/register-form", "/user/login-form").permitAll()
                .antMatchers("/home/**", "/category/**", "/user/**", "/order/**").hasAnyAuthority("ADMIN")
                .antMatchers("/all-green").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login-form").permitAll()
                .defaultSuccessUrl("/all-green")
                .failureUrl("/user/log-out")
                .loginProcessingUrl("/j_spring_security_check")
                .and()
                .logout().logoutUrl("/user/log-out");



    }
}
