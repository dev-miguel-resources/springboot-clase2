package com.chilecompra.springboot.app;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.chilecompra.springboot.app.auth.handler.LoginSuccessHandler;
import com.chilecompra.springboot.app.models.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true) //configuración segura
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private LoginSuccessHandler successHandler; //Inyectamos clase al ser de tipo componente
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	//@Autowired 
	//private DataSource dataSource; //para la conexión de la bdd
	
	@Autowired
	private JpaUserDetailsService userDetailsService; //inyectamos la instancia de nuestra implementación
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**", "/locale", "/api/clientes/**").permitAll()
		/*.antMatchers("/ver/**").hasAnyRole("USER")*/
		/*.antMatchers("/uploads/**").hasAnyRole("USER")*/
		/*.antMatchers("/form/**").hasAnyRole("ADMIN")*/
		/*.antMatchers("/eliminar/**").hasAnyRole("ADMIN")*/
		/*.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.successHandler(successHandler) //pasamos el objeto de clase que ve el tema del manejo del login
		.loginPage("/login") //método que maneja el login
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403"); //también manejo del error
		}


	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsService) //con jpa es mucho mejor que con jdbc
		.passwordEncoder(passwordEncoder);
		
		/*build.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select username, password, enabled from users where username=?") //para el login
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?"); //para obtener los roles por usuario*/
		
		/*
		 * Deprecated
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * */
		
		//PasswordEncoder encoder = this.passwordEncoder;
		/*UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		build.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
		.withUser(users.username("miguel").password("12345").roles("USER"));*/
	}
}
