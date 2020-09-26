package abc.carportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import abc.carportal.services.AdminService;
import abc.carportal.services.UserService;

@EnableWebSecurity
@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private AdminService AdminService;
  @Autowired
  private UserService UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // http.antMatcher("/admin/**").authorizeRequests().anyRequest().authenticated().and().formLogin()
    // .loginPage("/admin/login").defaultSuccessUrl("/admin/dashboard",
    // true).failureUrl("/admin/accessdenied")
    // .permitAll().and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").and()
    // .exceptionHandling().accessDeniedPage("/admin/accessdenied");
    // http.authorizeRequests().antMatchers("/admin/**", "/admin/dashboard",
    // "/upload").hasAuthority("ROLE_ADMIN").and()
    // .formLogin().loginPage("/admin/login").defaultSuccessUrl("/admin/dashboard").failureUrl("/accessdenied")
    // // .successHandler(successHandler)
    // .permitAll().and().logout().deleteCookies("remove").logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login")
    // .and().exceptionHandling().accessDeniedPage("/accessdenied");
    // http.csrf().disable();
    // http.authorizeRequests().antMatchers("/user/**", "/user/profile",
    // "/upload").hasAuthority("ROLE_USER").and()
    // .formLogin().loginPage("/user/login").defaultSuccessUrl("/user/profile").failureUrl("/accessdenied")
    // // .successHandler(successHandler)
    // .permitAll().and().logout().deleteCookies("remove").logoutUrl("/user/logout").logoutSuccessUrl("/user/login")
    // .and().exceptionHandling().accessDeniedPage("/accessdenied");

    http.formLogin().loginPage("/user/login").defaultSuccessUrl("/").failureUrl("/user/login?error=true").permitAll()
        .and().authorizeRequests().antMatchers("/user/profile").access("hasRole('ROLE_USER')")
        .antMatchers("/admin/dashboard").access("hasRole('ROLE_ADMIN')").antMatchers("/posts/view/**")
        .access("hasRole('ROLE_USER')").antMatchers("/bid/edit/**").access("hasRole('ROLE_USER')")
        .antMatchers("/", "/user/logout").permitAll().and().logout().deleteCookies("remove").logoutUrl("/user/logout")
        .logoutSuccessUrl("/user/login?logout").logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).and()
        .exceptionHandling().accessDeniedPage("/error/403");

    // http.authorizeRequests().antMatchers("/admin/dashboard",
    // "/").access("hasRole('ROLE_ADMIN')").and().formLogin()
    // .loginPage("/user/login").defaultSuccessUrl("/").failureUrl("/accessdenied").permitAll().and().logout()
    // .deleteCookies("remove").logoutUrl("/admin/logout").logoutSuccessUrl("/user/login?logout").and()
    // .exceptionHandling().accessDeniedPage("/accessdenied");

    // http.formLogin().loginPage("/user/login").defaultSuccessUrl("/").failureUrl("/accessdenied").permitAll().and()
    // .csrf().and().authorizeRequests().antMatchers("/resources/**").permitAll().antMatchers("/user/login")
    // .permitAll().antMatchers("/user/profile").hasRole("ROLE_USER").antMatchers("/admin/dashboard")
    // .hasRole("ROLE_ADMIN").antMatchers("/posts").hasRole("ROLE_USER").antMatchers("/posts").hasRole("ROLE_ADMIN")
    // .and().logout().deleteCookies("remove").logoutUrl("/admin/logout").logoutSuccessUrl("/user/login?logout").and()
    // .exceptionHandling().accessDeniedPage("/accessdenied");
    // http.csrf().disable();

  }

  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(AdminService);
    auth.setUserDetailsService(UserService);
    auth.setPasswordEncoder(bCryptPasswordEncoder);
    return auth;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/scripts/**", "/styles/**", "/images/**", "/error/**", "/admin/registration**",
        "/js/**", "/css/**", "/img/**", "/webjars/**", "/resources/**", "/static/**", "/css/**", "/img/**", "/js/**");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }
}
