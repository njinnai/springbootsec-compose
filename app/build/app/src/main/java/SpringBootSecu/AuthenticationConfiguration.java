package SpringBootSecu;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    // ユーザー情報を取得するサービス
    @Autowired
    LoginService loginService;
    
    // パスワードの暗号化方式
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //@Override
    //protected void configure(HttpSecurity http) throws Exception {
    //    http
    //        .authorizeRequests()
    //            .anyRequest().authenticated()
    //            .and()
    //        .formLogin()
    //            .loginPage("/login") // ログインのビュー
    //            .loginProcessingUrl("/sign_in") //認証処理が実行される
    //            .usernameParameter("username") 
    //            .passwordParameter("password")
    //            .successForwardUrl("/top")
    //            .failureUrl("/login?error")
    //            .permitAll()
    //            .and()
    //        .logout()
    //            .logoutUrl("/logout")
    //            .logoutSuccessUrl("/login?logout")
    //            .permitAll();
    //}


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }
}
