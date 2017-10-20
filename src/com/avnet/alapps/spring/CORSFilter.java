package com.avnet.alapps.spring;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Enabling CORS support  - Access-Control-Allow-Origin
 * @author zeroows@gmail.com
 * 
 * <code>
 	<!-- Add this to your web.xml to enable "CORS" -->
	<filter>
	  <filter-name>cors</filter-name>
	  <filter-class>com.avnet.alapps.spring.CORSFilter</filter-class>
	</filter>
	  
	<filter-mapping>
	  <filter-name>cors</filter-name>
	  <url-pattern>/*</url-pattern>
	</filter-mapping>
 * </code>
 */
public class CORSFilter extends OncePerRequestFilter {
	private static final Log LOG = LogFactory.getLog(CORSFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type");
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			LOG.info("Sending CORS header.");
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			////response.addHeader("Access-Control-Allow-Headers", "Authorization");
            //response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
			response.addHeader("Access-Control-Max-Age", "151200");
		}
		filterChain.doFilter(request, response);
	}

}