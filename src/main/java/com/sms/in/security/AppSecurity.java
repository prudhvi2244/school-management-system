package com.sms.in.security;

import com.sms.in.entity.UserRegistration;
import com.sms.in.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class AppSecurity  extends WebSecurityConfigurerAdapter
{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRegistrationRepository urepo;


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

               Optional<UserRegistration> opt= urepo.findByUsername(username);
                if(opt.isPresent())
                {
                   UserRegistration userRegistration=opt.get();
                   return new org.springframework.security.core.userdetails.User(username,
                                        userRegistration.getPassword()
                                        ,userRegistration.getRoles().stream().
                           map(role->new SimpleGrantedAuthority(role.toString()))
                           .collect(Collectors.toSet()));
                }
                else
                {
                    throw  new UsernameNotFoundException("User Not Exist!");
                }

            }
        };

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/","/about","/admissions","/register","/login","/logout")
                .permitAll()
                .antMatchers("/user/allStudents").hasAuthority("STUDENT")
                .antMatchers("/user/allTeachers").hasAuthority("TEACHER")
                .antMatchers("/user/allTeachers","/user/allStudents").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/welcome",true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/access-denied")
                 ;


    }
}
