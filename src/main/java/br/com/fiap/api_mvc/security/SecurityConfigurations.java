package br.com.fiap.api_mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // desativando uma config padrão de proteção contra ataque csrf
                // para facilitar nosso teste de login
                .csrf(csrf -> csrf.disable())
                // não armazena sessão do usuário
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // libera o POST de /auth/login para todos
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // libera o POST de /auth/register para todos
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // restringe a criação (POST) de livros apenas a usuários ADMIN
                        .requestMatchers(HttpMethod.POST, "/livros").hasRole("ADMIN")
                        // libera todos os outros métodos da API para qualquer usuário AUTENTICADO
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } // para trabalhar com hash de senha
}
