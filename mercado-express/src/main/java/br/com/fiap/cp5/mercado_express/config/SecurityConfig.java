package br.com.fiap.cp5.mercado_express.config; // (Seu package está correto)

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean para criptografar as senhas (continua igual)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- ESTE É O MÉTODO ATUALIZADO ---
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Autorização de requisições
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acesso público a estas URLs:
                        .requestMatchers(
                                "/",
                                "/login",
                                "/registrar",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // --- AQUI ESTÁ A MUDANÇA ---
                        // Qualquer rota /produtos/novo, /produtos/editar/*, /produtos/deletar/*
                        // DEVE ter o papel (authority) "ROLE_ADMIN"
                        .requestMatchers(
                                "/produtos/novo",
                                "/produtos/editar/**",
                                "/produtos/deletar/**"
                        ).hasRole("ADMIN") // <--- A LINHA IMPORTANTE!

                        // Qualquer outra requisição (ex: /produtos) exige autenticação
                        .anyRequest().authenticated()
                )
                // Configuração do formulário de login (continua igual)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login-action")
                        .defaultSuccessUrl("/produtos", true)
                        .permitAll()
                )
                // Configuração do logout (continua igual)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}