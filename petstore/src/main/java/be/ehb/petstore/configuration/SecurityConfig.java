package be.ehb.petstore.configuration;

import be.ehb.petstore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Configuring the security filter chain
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth
                                // Configure access rules for specific paths
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/account")).authenticated()
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/account/**")).authenticated()
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/checkout/**")).authenticated()
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/**")).permitAll()
                                .anyRequest().authenticated()
                ).formLogin(formLogin -> formLogin
                        // Configure form-based login - TODO
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login")
                        .permitAll()
                )

                .oauth2Login(oauth -> oauth
                        // Configure OAuth2 login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login")
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                // Extract OAuth2 user details and call a method to process the login (
                                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                                String googleNameId = oAuth2User.getName();
                                String name = oAuth2User.getAttribute("name");
                                String givenName = oAuth2User.getAttribute("given_name");
                                String email = oAuth2User.getAttribute("email");

                                userService.processOAuthPostLogin(googleNameId, name, givenName, email);

                                response.sendRedirect("/account");
                            }
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        // Configure logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                HttpSession session = request.getSession();
                                session.invalidate();

                                response.sendRedirect("/index");
                            }
                        })
                );

        // Build and return the configured security filter chain
        return http.build();
    }

}