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
                .and().oauth2Login()
                .loginPage("/loginForm")          // 인증이 필요한 URL에 접근하면 /loginForm으로 이동
                .defaultSuccessUrl("/signup")     // 로그인 성공시
                .failureUrl("/loginForm")         // 로그인 실패 시
                .userInfoEndpoint()               // 로그인 성공 후 사용자정보 가져옴
                .userService(oAuthService);

        return http.build();
    }
}
