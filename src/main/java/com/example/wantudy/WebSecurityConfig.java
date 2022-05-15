package com.example.wantudy;//package com.example.wantudy;
//
//import com.example.wantudy.jwt.TokenAuthenticationFilter;
//import com.example.wantudy.jwt.TokenProvider;
////import com.example.wantudy.oauth.CustomOAuth2UserService;
//import com.example.wantudy.oauth.CustomSuccessHandler;
//import com.example.wantudy.oauth.User;
//import com.example.wantudy.oauth.UserDetailsServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    private final CustomOAuth2UserService customOAuth2UserService;
//    private final TokenAuthenticationFilter tokenAuthenticationFilter;
//    private final TokenProvider tokenProvider;
//
//    public WebSecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter, TokenProvider tokenProvider) {
//        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
//        this.tokenProvider = tokenProvider;
//    }
//
////    public WebSecurityConfig(boolean disableDefaults, TokenAuthenticationFilter tokenAuthenticationFilter, TokenProvider tokenProvider) {
////        super(disableDefaults);
////        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
////        this.tokenProvider = tokenProvider;
////    }
//
////    public WebSecurityConfig(CustomOAuth2UserService customOAuth2UserService, TokenAuthenticationFilter tokenAuthenticationFilter, TokenProvider tokenProvider) {
////        this.customOAuth2UserService = customOAuth2UserService;
////        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
////        this.tokenProvider = tokenProvider;
////    }
//
////    private final CustomOAuth2Service customOAuth2UserService;
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////    private final CustomSuccessHandler customSuccessHandler;
////    private final JwtTokenProvider jwtTokenProvider;
////
////    public WebSecurityConfig(@Lazy CustomOAuth2Service customOAuth2UserService, JwtAuthenticationFilter jwtAuthenticationFilter, CustomSuccessHandler customSuccessHandler, JwtTokenProvider jwtTokenProvider) {
////        this.customOAuth2UserService = customOAuth2UserService;
////        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
////        this.customSuccessHandler = customSuccessHandler;
////        this.jwtTokenProvider = jwtTokenProvider;
////    }
//
//    /**
//     * SecurityConfig의 configure method에서 UserService를 참조하고 있고,
//     * UserService에서 BCryptPasswordEncoder를 사용하기 위해
//     * 다시 SecurityConfig를 참조하기 때문에 참조 사이클이 생겨
//     * 에러가 발생한 것
//     * */
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
////    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        SimpleUrlAuthenticationSuccessHandler handler = new CustomSuccessHandler(tokenProvider);
//        handler.setUseReferer(true);
//        return handler;
//    }
//
//
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/**").permitAll();
////                .and()
////                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService)
////                .and()
////                .successHandler(successHandler())
//////                .defaultSuccessUrl("/nickname",true) // GetMapping의 /nickname으로 가서 깃허브 유저네임 중복 체크!
////                .and()
////                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//}

import com.example.wantudy.jwt.TokenAuthenticationFilter;
import com.example.wantudy.jwt.TokenProvider;
import com.example.wantudy.oauth.CustomOAuth2UserService;
import com.example.wantudy.oauth.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CustomSuccessHandler customSuccessHandler;
    private final TokenProvider tokenProvider;

    public WebSecurityConfig(@Lazy CustomOAuth2UserService customOAuth2UserService, TokenAuthenticationFilter tokenAuthenticationFilter, CustomSuccessHandler customSuccessHandler, TokenProvider tokenProvider) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.customSuccessHandler = customSuccessHandler;
        this.tokenProvider = tokenProvider;
    }

    //AuthenticationManagerBean 등록 -> 하단의 configure에서 LoginForm을 이용한 자동 처리 사용 X, 수동으로 Authentication을 만들어서 SecurityContext에 저장
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()  throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new CustomSuccessHandler(tokenProvider);
        handler.setUseReferer(true);
        return handler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()// 로그인 성공 후 사용자 정보를 가져옴
                .userService(customOAuth2UserService)// userInfoEndpoint()로 가져온 사용자 정보를 처리할 때 사용
                .and()
                .successHandler(successHandler())
////                .defaultSuccessUrl("/nickname",true) // GetMapping의 /nickname으로 가서 깃허브 유저네임 중복 체크!
                .and()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
