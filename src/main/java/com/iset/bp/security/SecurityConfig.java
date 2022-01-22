package com.iset.bp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.iset.bp.service.UserService;

//Classe qui permet de personnaliser la configuration 
//la premier ficher lire au lancement de l'application pour la configuration
@Configuration
//activer la sécurity
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		 prePostEnabled = true,
		 securedEnabled = true,
		 jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String AUTHORITIES_CLAIM_NAME = "roles"; 

	    @Autowired
		UserService userDetailsService;
		
	    @Autowired
		private AccessDeniedHandler accessDeniedHandler;
		
	    @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService);
		}
	    	    
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			 http.csrf().disable()
						/*.exceptionHandling()
						.authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
						})
						.and()*/
					    //lancer le provider
						//donner au utilisateur d'entrer le username et le password pour verifier si il a acces pour authentifier
						/*.authenticationProvider(getProvider())
						.formLogin()
						.loginProcessingUrl("/login")
						.successHandler(new AuthentificationLoginSuccessHandler())
						.failureHandler(new SimpleUrlAuthenticationFailureHandler())
						.and()
						//pour fermer la session
						.logout()
						.logoutUrl("/logout")
						.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
						.invalidateHttpSession(true)
						.and()*/
						//les autorisation
						.authorizeRequests()
						.antMatchers("/login").permitAll()
						.antMatchers("/logout").permitAll()
						.antMatchers("/registre").permitAll()
						.antMatchers("/operation").authenticated()
						.antMatchers("/users").authenticated()
						.antMatchers("/comptes").authenticated()
						.antMatchers("/retrait").authenticated()
						.antMatchers("/virement").authenticated()
						.antMatchers("/versement").authenticated()
						.anyRequest().permitAll();
			
			 //Ajouter sessionManagement et le filter
			 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
			 http.addFilter(new JWTAuthenticationFilter(authenticationManager())); 
			 http.addFilterBefore(new JWTAuthorizationFiler(), UsernamePasswordAuthenticationFilter.class);
		}
		
		
		private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
			//retourner un statuts lors de l'authentifier
			@Override
				public void onAuthenticationSuccess(HttpServletRequest request,
				HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
						response.setStatus(HttpServletResponse.SC_OK);
				}
		}
		
		
		private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
				//retourner un statuts lors de déconnection
				@Override
				public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
						response.setStatus(HttpServletResponse.SC_OK);
				}
		}
		

		@Bean
		public AuthenticationProvider getProvider() {
				AppAuthenticationProvider provider = new AppAuthenticationProvider();
				provider.setUserDetailsService(userDetailsService);
				return provider;
		}
		
		@Bean
		public AccessDeniedHandler accessDeniedHandler(){
		    return new AccessDeniedHandlerImpl();
		}
		
		@Bean
		public BCryptPasswordEncoder encodePWD() {
		    return new BCryptPasswordEncoder();
		   
		}
		
}
