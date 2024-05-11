package com.express.grocery.Express.Grocery.config;

import com.express.grocery.Express.Grocery.security.AuthenticateUserService;
import com.express.grocery.Express.Grocery.security.JwtAuthenticationEntryPoint;
import com.express.grocery.Express.Grocery.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("api/**").authenticated()
                .requestMatchers("auth/**").permitAll()
                .requestMatchers("api/user/**").hasRole("ADMIN")
                .requestMatchers("api/user/**").hasAuthority("ADMIN")
                .requestMatchers("api/category/**").hasRole("ADMIN")
                .requestMatchers("api/category/**").hasAuthority("ADMIN")
                .requestMatchers("api/coupon/addUpdateCoupon").hasRole("ADMIN")
                .requestMatchers("api/coupon/addUpdateCoupon").hasAuthority("ADMIN")
                .requestMatchers("api/coupon/assignCoupon").hasRole("ADMIN")
                .requestMatchers("api/coupon/assignCoupon").hasAuthority("ADMIN")
                .requestMatchers("api/product/addUpdateProduct").hasRole("ADMIN")
                .requestMatchers("api/product/addUpdateProduct").hasAuthority("ADMIN")
                .requestMatchers("api/product/excelUploadProducts").hasRole("ADMIN")
                .requestMatchers("api/product/excelUploadProducts").hasAuthority("ADMIN")

                .anyRequest().authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

//        http.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.authenticateUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
