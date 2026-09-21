package com.phisafe.security;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
public class JwtFilter extends OncePerRequestFilter {
  private final JwtService jwt; public JwtFilter(JwtService j){ jwt=j; }
  @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain) throws ServletException,IOException {
    String h=req.getHeader("Authorization");
    if(h!=null&&h.startsWith("Bearer ")){
      try{
        Claims c=jwt.parse(h.substring(7));
        @SuppressWarnings("unchecked") List<String> roles=c.get("roles",List.class);
        var auth=new UsernamePasswordAuthenticationToken(c.getSubject(),null,roles.stream().map(r->new SimpleGrantedAuthority("ROLE_"+r)).toList());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }catch(JwtException|IllegalArgumentException ignored){ SecurityContextHolder.clearContext(); }
    }
    chain.doFilter(req,res);
  }
}