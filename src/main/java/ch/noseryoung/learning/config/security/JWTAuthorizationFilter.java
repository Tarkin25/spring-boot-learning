package ch.noseryoung.learning.config.security;

import ch.noseryoung.learning.domain.user.User;
import ch.noseryoung.learning.domain.user.UserDetailsImpl;
import ch.noseryoung.learning.domain.user.UserServiceImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private UserServiceImpl userService;

    private JWTProperties jwtProperties;

    public JWTAuthorizationFilter(UserServiceImpl userService, JWTProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, JwtException {
        String header = request.getHeader(jwtProperties.getHeader());

        if (header != null && header.startsWith(jwtProperties.getHeaderPrefix())) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(header, response));
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String header, HttpServletResponse response) throws JwtException, IOException {
        String subject;

        try {
            subject = Jwts.parser()
                    .setSigningKey(jwtProperties.getKey().getBytes())
                    .parseClaimsJws(header.replace(jwtProperties.getHeaderPrefix() + " ", "")).getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }

        if (subject != null) {
            User user = userService.findById(Long.parseLong(subject));

            if (user != null) {
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
        }

        return null;
    }
}
