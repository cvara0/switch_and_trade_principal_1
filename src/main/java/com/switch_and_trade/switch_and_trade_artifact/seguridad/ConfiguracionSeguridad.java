package com.switch_and_trade.switch_and_trade_artifact.seguridad;

import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//para establece configuraciones en le manejo de roles
@RequiredArgsConstructor
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {
    private final PerfilServicio perfilServicio;
    private final BCryptPasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(perfilServicio).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/","/autenticaciones/formulario-registrar", "/autenticaciones/registrar", "/css/*", "/js/*", "/img/*").permitAll()
                .antMatchers("/**").permitAll()//authenticated()
                .and()
                .formLogin()
                .loginPage("/autenticaciones/formulario-iniciar-sesion")
                .loginProcessingUrl("/logincheck")//maneja spring , se establece el metodo que procesa la informacion de nuestro login
                .usernameParameter("emailparam")//esto es el name= del input en el html
                .passwordParameter("claveparam")//esto es el name= del input en el html
                .defaultSuccessUrl("/", true)//una vez logueado a que ruta ir, eneste caso index, sin el true va a la ultima pagina visitada
                .failureUrl("/autenticaciones/formulario-iniciar-sesion?error=true")//no es necesario que sea true se peude poner cualquier cosa,es una query string, dato en la ruta
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")//esto es de spring es la ruta para salir
                .logoutSuccessUrl("/autenticaciones/formulario-iniciar-sesion?logout=true")//ruta donde ir luego de salir
                .permitAll()
                .and()
                .csrf()
                .disable();
        // @formatter:on
    }
}