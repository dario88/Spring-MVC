package com.spring.step32.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/*
 * Questa classe configura due cose:
 * 1) gli utenti validi: l'unico utente valido è quello con username 'admin' e password 'admin',
 * 						 e i sui ruoli sono 'USER' e 'ADMIN'
 * 2) gli url che devono essere sottoposti a un controllo di sicurezza: devono subire un controllo tutti gli
 * 																		url contenente *esam*
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("USER", "ADMIN");
		// per aggiungere altri utenti basta aggiungere altri .withUser() e .password(), nel seguente modo:
		//		auth.inMemoryAuthentication()
		//			.withUser("admin").password("admin").roles("USER", "ADMIN")
		//			.and()
		//			.withUser("new user").password("new password").roles("new role")
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * per accedere a /*esam* bisogna avere il ruolo ' USER'
		 * Solo se un utente con ruolo 'USER' è loggato, Spring Security gli consentirà di accedere
		 * alle pagine *esam*. In caso contrario l'utente verrà rediretto alla pagina di login.
		 */ 
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()	// la pagina /login è accessibile da chiunque...
				.antMatchers("/", "/welcome*", "/*esam*/**").access("hasRole('ROLE_USER')")	// ...per il resto bisogna avere il ruolo 'USER'...
				.and()
			.formLogin();	// ...se non hai il ruolo 'USER' allora verrai riportato alla pagina di login
    }

}
