package projet.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

//@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired(required=true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	protected void globalConfig(AuthenticationManagerBuilder auth) throws Exception{
	
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
				
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		 http.csrf().disable();
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 //http.formLogin();
		 http.authorizeRequests().antMatchers("/login/**").permitAll();
		 http.authorizeRequests().antMatchers("/quizzs/**");
		 http.authorizeRequests().anyRequest().authenticated();
		 http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		 http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
            
	}

}
