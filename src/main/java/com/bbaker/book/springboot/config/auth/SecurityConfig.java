package com.bbaker.book.springboot.config.auth;

import com.bbaker.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
* @EnableWebSecurity
*  - Spring Security 설정들을 활성화시켜 줌.
*
* .csrf().disable().headers().frameOptions().disable()
*  - h2-console 화면을 사용하기 위해 해당 옵션들을 disable 함.
*
* .authorizeRequests
*  - URL별 권한 관리를 설정하는 옵션의 시작점.
*
* .antMatchers
*  - 권한 관리 대상을 지정하는 옵션. (URL, HTTp 메소드별로 관리가 가능)
*
* .anyRequest
*  - 설정된 값을 이외 나머지 URL들을 나타냄.
*  - 여기서는 .authenticated()를 추가하여 나머지 URL들은 모두 인증된(로그인한) 사용자들에게만 허용함.
*
* .logout().logoutSuccessUrl("/")
*  - 로그아웃 성공 시 /주소로 이동.
*
* .oauth2Login()
*  - OAuth2 로그인 기능에 대한 여러 설정의 진입점.
*
* .userInfoEndpoint()
*  - OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당함.
*
* .userService
*  - 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스와 구현체를 등록함.
*  - 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.
*/
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/font-awesome/**", "/h2-console/**",
                            "/profile", "/admin/login", "/admin").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/admin")
                .and()
                    .oauth2Login()
                        .loginPage("/admin/login")
                        .defaultSuccessUrl("/admin")
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

}
