package kyonggi_girls.kgu_babmat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final OAuth2UserService oAuthService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/user/**").hasAnyRole("USER")
                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃시
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/signup") // 로그인 성공시
                .failureUrl("/") // 로그인 실패시
                .userInfoEndpoint()
                .userService(oAuthService);

        return http.build();
    }
}
