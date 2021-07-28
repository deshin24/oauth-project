package sde24.post.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import sde24.post.entity.Role;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면 사용위해 해당 옵션들 disable
            .and()
                .authorizeRequests() // 권한 별 관리 설정의 시작점
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 전체 열람 권한
                .antMatchers("/admin").hasRole(Role.ADMIN.name()) // "/admin"주소를 가진 api는 ADMIN 권한을 가진 사람들만 접근 가능
                .anyRequest().authenticated() // 나머지 주소는 모든 로그인한 사용자 접근 가능
            .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 성공시 "/"주소로 이동
            .and()
                .oauth2Login()  // OAuth2 로그인 기능의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올때의 설정
                .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체등록
    }
}
