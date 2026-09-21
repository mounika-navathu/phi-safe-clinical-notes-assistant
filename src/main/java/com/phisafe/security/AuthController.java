package com.phisafe.security;
import java.util.*;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/auth")
public class AuthController {
  private final UserDetailsService users; private final PasswordEncoder enc; private final JwtService jwt;
  public AuthController(UserDetailsService u,PasswordEncoder e,JwtService j){ users=u; enc=e; jwt=j; }
  @PostMapping("/login") public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> b){
    try{
      UserDetails u=users.loadUserByUsername(b.getOrDefault("username",""));
      if(!enc.matches(b.getOrDefault("password",""),u.getPassword())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      List<String> roles=u.getAuthorities().stream().map(GrantedAuthority::getAuthority).map(r->r.replace("ROLE_","")).toList();
      return ResponseEntity.ok(Map.of("token",jwt.issue(u.getUsername(),roles)));
    }catch(UsernameNotFoundException e){ return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
  }
}