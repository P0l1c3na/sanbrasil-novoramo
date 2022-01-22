package br.ifgoiano.sanbrasil.novoramo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();

        // require all requests to be authenticated except for the resources
        http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/publico/**", "/index.xhtml", "/*",
                "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**", "/v2/api-docs", "/oauth/token",
                "/login.xhtml", "/api/usuario/salvar-externo")
                .permitAll().anyRequest().authenticated();
        // login
        http.formLogin()
                .loginPage("/login.xhtml").permitAll()
                .defaultSuccessUrl("/privado/principal.xhtml", true)
                .failureUrl("/login.xhtml?error=true");
        // logout
        http.logout().logoutSuccessUrl("/login.xhtml");
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/api/usuario/salvar-externo", "/swagger-resources/**",
                "/configuration/**", "/swagger-ui.html", "/webjars/**", "/v2/api-docs", "/publico/**",
                "/index.xhtml","/login.xhtml", "/*");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("Select email, senha, ativo " +
                        "From usuario " +
                        "Where email Like ?")
                .authoritiesByUsernameQuery("Select u.email, p.nome " +
                        "From perfil p " +
                        "Inner Join perfil_usuario pu " +
                        "On p.id = pu.perfil_id " +
                        "Inner Join usuario u " +
                        "on pu.usuario_email = u.email " +
                        "Where u.email Like ?");

        log.info("Usuario realizou Login");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
