// package abc.carportal.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.annotation.Order;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.builders.WebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import abc.carportal.services.UserService;

// @EnableWebSecurity
// @Configuration
// @Order(2)
// public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
// @Autowired
// BCryptPasswordEncoder bCryptPasswordEncoder;
// @Autowired
// private UserService userService;

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// // http.authorizeRequests().antMatchers("/registration**", "/js/**",
// "/css/**",
// // "/img/**", "/webjars/**").permitAll()
// //
// .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
// // .invalidateHttpSession(true).clearAuthentication(true)
// // .logoutRequestMatcher(new
// //
// AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
// //
// http.antMatcher("/**").authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/user/login")
// // .defaultSuccessUrl("/",
// //
// true).failureUrl("/accessdenied").permitAll().and().logout().logoutSuccessUrl("/login");
// http.authorizeRequests().antMatchers("/user/**", "/user/profile",
// "/upload").hasAuthority("ROLE_USER").and()
// .formLogin().loginPage("/user/login").defaultSuccessUrl("/user/profile").failureUrl("/accessdenied")
// // .successHandler(successHandler)
// .permitAll().and().logout().deleteCookies("remove").logoutUrl("/user/logout").logoutSuccessUrl("/user/login")
// .and().exceptionHandling().accessDeniedPage("/accessdenied");
// http.csrf().disable();
// }

// // @Bean
// // public BCryptPasswordEncoder passwordEncoder() {
// // return new BCryptPasswordEncoder();
// // }

// // @Bean
// // public DaoAuthenticationProvider authenticationProvider() {
// // DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
// // auth.setUserDetailsService(userService);
// // auth.setPasswordEncoder(passwordEncoder());
// // return auth;
// // }
// public DaoAuthenticationProvider authenticationProvider() {
// DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
// auth.setUserDetailsService(userService);
// auth.setPasswordEncoder(bCryptPasswordEncoder);
// return auth;
// }

// @Override
// public void configure(WebSecurity web) throws Exception {
// web.ignoring().antMatchers("/scripts/**", "/styles/**", "/images/**",
// "/error/**", "/registration**", "/js/**",
// "/css/**", "/img/**", "/webjars/**");
// }

// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.authenticationProvider(authenticationProvider());
// }

// }
