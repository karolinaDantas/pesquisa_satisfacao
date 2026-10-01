package br.com.aweb.pesquisa_satisfacao.security;

import br.com.aweb.pesquisa_satisfacao.PesquisaSatisfacaoApplication;
import br.com.aweb.pesquisa_satisfacao.controller.AvaliacaoController;
import br.com.aweb.pesquisa_satisfacao.controller.SetorController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracoesDeSeguranca {

    private final SetorController setorController;

    private final PesquisaSatisfacaoApplication pesquisaSatisfacaoApplication;

    private final AvaliacaoController avaliacaoController;

    ConfiguracoesDeSeguranca(
            AvaliacaoController avaliacaoController,
            PesquisaSatisfacaoApplication pesquisaSatisfacaoApplication,
            SetorController setorController) {

        this.avaliacaoController = avaliacaoController;

        this.pesquisaSatisfacaoApplication = pesquisaSatisfacaoApplication;

        this.setorController = setorController;
    }

    @Bean
    public UserDetailsService dadosUsuarios() {

        UserDetails usuario1 = User.builder()
                .username("admin")
                .password("{noop}123456")
                .build();

        UserDetails usuario2 = User.builder()
                .username("user")
                .password("{noop}0123456")
                .build();

        UserDetails usuario3 = User.builder()
                .username("bobocabreno@gmai.com")
                .password("{noop}456")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3);
    }

    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(req -> req
            .requestMatchers("/", "/css/**", "/js/**", "/img/**")
            .permitAll()
    )
    .formLogin(form -> form
            .defaultSuccessUrl("/")
            .permitAll()
    )
    .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
    );

        return http.build();
    }
}