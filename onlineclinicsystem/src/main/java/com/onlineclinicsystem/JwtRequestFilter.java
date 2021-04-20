//it will intercept every request just once then exmaine the header

package com.onlineclinicsystem;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{


	@Autowired
	private CustomerUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//takes the filter chain bcoz it has the option to pass it to the next filter chain or ending the request there
	//examine the header and see if it has teh valid jwt[get the user deatils if valid jwt and save it in security context]
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//get the header for authorisation
		//authorizationHeader --> it will contain the "Bearer fvfvf.cfdvfvf.vvfvf[types]" 
		final String authorizationHeader = request.getHeader("Authorization");
		
		 String email = null;
	     String jwt = null;
	     
	     //verify if authorisation header is not null and if it starts with "Bearer<space>"
	     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            email = jwtUtil.extractUsername(jwt);  //got the username 
	        }
	     //extract the user details from user name
	     if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	    	    //got the user details
	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
	            
	            //validate the token with the user details 
	            if (jwtUtil.validateToken(jwt, userDetails)) {

	            	//create a default userpassauth token and set the details 
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	     //continuing the chain
	     filterChain.doFilter(request, response);
	}

}
