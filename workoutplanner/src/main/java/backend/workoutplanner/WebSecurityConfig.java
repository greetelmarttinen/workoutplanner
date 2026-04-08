package backend.workoutplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import backend.workoutplanner.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // metoditasoinen suojaus
public class WebSecurityConfig {

    private final UserDetailServiceImpl userDetailServiceImpl;
    // käyttäjien haku tietokannasta

    WebSecurityConfig(UserDetailServiceImpl userDetailServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // salasanan hashays, käytetään kirjautumisen yhteydessä
        return new BCryptPasswordEncoder();
    }

    // sovelluksen suojaus, http
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/css/**", "/h2-console/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(formlogin -> formlogin // springin oletus login-sivu
                        .defaultSuccessUrl("/index", true) // kirjautumisen jälkeen ohjataan index-sivulle
                        .permitAll())
                .logout(logout -> logout // uloskirjautuminen on sallittu kaikille
                        .permitAll()
                        .invalidateHttpSession(true))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()));
        return http.build();
    }

    // autentikointi
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

}
