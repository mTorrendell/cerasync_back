package node_value.projects.cerasync_back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import node_value.projects.cerasync_back.service.JwtService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
    @Autowired private JwtService jwtService;

    @Autowired private UserDetailsService uDService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        
        final String authHeader = req.getHeader("Authorization");
        final String jwt, username;
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(req, resp);
            return;
        }

        jwt      = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            UserDetails uD = this.uDService.loadUserByUsername(username);
        
            if (jwtService.isTokenValid(jwt, uD)) {
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(uD, null, uD.getAuthorities());    
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                chain.doFilter(req, resp);
            }

        }
    }
}
