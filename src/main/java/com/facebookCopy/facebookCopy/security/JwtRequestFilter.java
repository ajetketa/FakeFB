package com.facebookCopy.facebookCopy.security;

import com.facebookCopy.facebookCopy.service.serviceImp.UserDetailsServiceImp;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImp userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private String userName;

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       final String requestTokenHeader=httpServletRequest.getHeader("Authorization");
       String username=null;
       String jwtToken=null;
       if(requestTokenHeader!=null &&requestTokenHeader.startsWith("Bearer ")){
           jwtToken=requestTokenHeader.substring(7);
           try{
               username=jwtTokenUtil.getUserNameFromToken(jwtToken);
               setUserName(username);
           }catch(IllegalArgumentException e){
               System.out.println("Unable to get JWT Token");
           }catch(ExpiredJwtException e){
               System.out.println("JWT Token has expired");
           }
       }else{

       }
       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
           if(jwtTokenUtil.validateToken(jwtToken, userDetails)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                       userDetails,null,userDetails.getAuthorities()
               );
               usernamePasswordAuthenticationToken
                       .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
       }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
