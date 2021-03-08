package learn.spring.springsecure.config;




/*
 * @author
 * @version
 * @return
 */

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/authenticated/**")
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/hellologin")
                .and()
                .logout().logoutSuccessUrl("/");
    }


   //In memory AUTH
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2y$12$IcKJP8IyafGiGOtwWwmL3.fdLtlNTnnrsRAZUGhpJmjQXcd1BksyS")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$IcKJP8IyafGiGOtwWwmL3.fdLtlNTnnrsRAZUGhpJmjQXcd1BksyS")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
    // JDBC AUTH
    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource){
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2y$12$IcKJP8IyafGiGOtwWwmL3.fdLtlNTnnrsRAZUGhpJmjQXcd1BksyS")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$IcKJP8IyafGiGOtwWwmL3.fdLtlNTnnrsRAZUGhpJmjQXcd1BksyS")
                .roles("ADMIN", "USER")
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        if(jdbcUserDetailsManager.userExists(user.getUsername())){
            jdbcUserDetailsManager.deleteUser(user.getUsername());
        }
        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
            jdbcUserDetailsManager.deleteUser(admin.getUsername());
        }
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;
    }
}
































