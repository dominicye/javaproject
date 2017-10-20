package com.avnet.alapps.spring;
/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import com.avnet.alapps.security.PreAuthenticatedProcessingFilter;
import com.avnet.alapps.security.AvnetUserDetailsService;
*/




//Configuration
//EnableWebSecurity
//ComponentScan(basePackages = {"com.avnet.alapps", "com.avnet.alapps.spring", "com.avnet.alapps.security", "com.avnet.alapps.controllers", "com.avnet.alapps.common"})
public class SecurityConfig { // extends WebSecurityConfigurerAdapter {
/*
	@Autowired private AvnetUserDetailsService avnetUserDetailsService;
	//@Autowired private PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter;
	

	//<sec:http pattern="/**" auto-config="true" disable-url-rewriting="true"  use-expressions="true"> 
    //    <sec:custom-filter ref="preAuthenticatedProcessingFilter" position="PRE_AUTH_FILTER" />
    //</sec:http>   

	
	//Not working
	//@Order(1)
	//public class SpringSecurityFilterChain extends DelegatingFilterProxy {
	//	public SpringSecurityFilterChain() {
	//		super("springSecurityFilterChain");
	//		this.setBeanName("springSecurityFilterChain");
	//	}
	//}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

  		//Not working
		//auth
		//.parentAuthenticationManager(authenticationManager())
		//.authenticationProvider(preAuthenticatedAuthenticationProvider())
		//.userDetailsService(this.avnetUserDetailsService)
		//.and()
		//.build()
		//;
	
	}
	@Override //TODO: temp disable
    public void configure(HttpSecurity http) throws Exception {
        http
        .addFilter(sif())
        .addFilter(preAuthenticatedProcessingFilter())
        .authorizeRequests()
        .antMatchers("/**")
        .authenticated()
        ;
        
    }
    
    
  
    // <bean id="sif" class="org.springframework.security.context.HttpSessionContextIntegrationFilter"/>
    @Bean
    public SecurityContextPersistenceFilter sif() {
		//replaces HttpSessionContextIntegrationFilter
        return new SecurityContextPersistenceFilter(
                new HttpSessionSecurityContextRepository()
                );
    }
	

	//<bean id="securityContextPersistenceFilter" class="org.springframework.security.web.context.SecurityContextPersistenceFilter"/>
	@Bean SecurityContextPersistenceFilter securityContextPersistenceFilter() {
		return new SecurityContextPersistenceFilter();
	}

	
	//<sec:authentication-manager alias="authenticationManager">
	//	<sec:authentication-provider ref="preAuthenticatedAuthenticationProvider" />
	//</sec:authentication-manager>
	@Bean
    public AuthenticationManager authenticationManager() throws Exception {
		AuthenticationManager authenticationManager = new ProviderManager(
                Arrays.asList(preAuthenticatedAuthenticationProvider()));
        return authenticationManager;
		
    }
	
	
	// <bean id="preAuthenticatedProcessingFilter" class="com.avnet.alapps.security.PreAuthenticatedProcessingFilter">
	//	<property name="authenticationManager" ref="authenticationManager" />
	//	<property name="tier" ref="tier" />
	//</bean>
	@Bean
	public PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception {
		PreAuthenticatedProcessingFilter pf = new PreAuthenticatedProcessingFilter();
		pf.setAuthenticationManager(authenticationManager());
		pf.afterPropertiesSet();
		return pf;
	}
	

	//<bean id="preAuthenticatedAuthenticationProvider" class="org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider">
	//	<property name="preAuthenticatedUserDetailsService">
	//		<bean id="userDetailsServiceWrapper" class="org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper">
	//			<property name="userDetailsService" ref="userDetailsService" />
	//		</bean>
	//	</property>
	//</bean>
	@SuppressWarnings("unchecked")
	@Bean
	public UserDetailsByNameServiceWrapper userDetailsServiceWrapper() {
		UserDetailsByNameServiceWrapper wrap = new UserDetailsByNameServiceWrapper();
		wrap.setUserDetailsService(avnetUserDetailsService); //userDetailsService() is method on super
		return wrap;
	}
    @SuppressWarnings("unchecked")
	@Bean
    public AuthenticationProvider preAuthenticatedAuthenticationProvider() throws Exception {
    	PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
        //authenticationProvider.afterPropertiesSet();
        return authenticationProvider;
    }

	//<bean id="httpRequestAccessDecisionManager"  class="org.springframework.security.access.vote.AffirmativeBased">
    //    <constructor-arg>
    //        <list>
    //            <bean class="org.springframework.security.access.vote.RoleVoter"/>
    //        </list>
    //    </constructor-arg>
    //    <property name="allowIfAllAbstainDecisions" value="false"/>
    //</bean>
	
	@Bean public RoleVoter roleVoter() {
		return new RoleVoter();
	}
	@SuppressWarnings("unchecked")
	@Bean public AffirmativeBased httpRequestAccessDecisionManager() {
		List voters = new ArrayList<AccessDecisionVoter>();
        voters.add(new RoleVoter());
        AffirmativeBased ab = new AffirmativeBased(voters);
        ab.setAllowIfAllAbstainDecisions(false);
        return ab;
	}

    //<bean id="userDetailsService" class="com.avnet.alapps.security.AvnetUserDetailsService">
	//	<property name="securityService" ref="securityService"/>
	//	<property name="enterpriseLDAPReader" ref="enterpriseLDAPReader" />
	//</bean>
	//<bean id="preAuthenticatedProcessingFilter" class="com.avnet.alapps.security.PreAuthenticatedProcessingFilter">
	//	<property name="authenticationManager" ref="authenticationManager" />
	//	<property name="tier" ref="tier" />
	//</bean>
	@Autowired
	public void setAvnetUserDetailsService(AvnetUserDetailsService avnetUserDetailsService) {
		this.avnetUserDetailsService = avnetUserDetailsService;
	}
	
	//@Autowired
	//public void setPreAuthenticatedProcessingFilter(PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter) {
	//	this.preAuthenticatedProcessingFilter = preAuthenticatedProcessingFilter;
	//}
	
*/	
	
/*	
	//All this stuff does not work
	@Bean
    public FilterChainProxy springSecurityFilterChain()
            throws ServletException, Exception {
        
        return new FilterChainProxy(getFilterChains());
    }
	

	private List<SecurityFilterChain> getFilterChains() throws Exception {
		List<SecurityFilterChain> securityFilterChains = new ArrayList<SecurityFilterChain>();
        securityFilterChains.add(
        		new DefaultSecurityFilterChain(
	                new AntPathRequestMatcher("/**"),
	                sif(), 
	                preAuthenticatedProcessingFilter()
                )
        );
		return securityFilterChains;
	}
	
	@Bean
    public SecurityContextPersistenceFilter sif() {
		//replaces HttpSessionContextIntegrationFilter
        return new SecurityContextPersistenceFilter(
                new HttpSessionSecurityContextRepository());
    }
	
	@Bean
	public PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception {
		PreAuthenticatedProcessingFilter papf = new PreAuthenticatedProcessingFilter();
		papf.setAuthenticationManager(authenticationManager());
		papf.afterPropertiesSet();
		return papf;
	}

	@Bean
    public FilterSecurityInterceptor fsi()
            throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor
                .setAuthenticationManager(authenticationManager());
        filterSecurityInterceptor
                .setAccessDecisionManager(accessDecisionManager());
        filterSecurityInterceptor.afterPropertiesSet();
        return filterSecurityInterceptor;
    }
*/
	

}
