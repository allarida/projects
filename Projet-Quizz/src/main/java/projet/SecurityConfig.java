package projet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception{
		auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		.dataSource(dataSource)
		
		.usersByUsernameQuery("SELECT prenom, password, true FROM users WHERE prenom=?")
		.authoritiesByUsernameQuery("select prenom, status from users where prenom=?")
		.rolePrefix("ROLE_");
		/*auth
		.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("user").password("password").roles("USER")
			.and().withUser("rida").password("123").roles("ADMIN");*/
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		
		 http
         .authorizeRequests()
             .antMatchers("/", "/home").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .permitAll()
             .and()
         .logout()
         //.invalidateHttpSession(true)
         //.logoutUrl("")
             .permitAll();
            
	}

}
