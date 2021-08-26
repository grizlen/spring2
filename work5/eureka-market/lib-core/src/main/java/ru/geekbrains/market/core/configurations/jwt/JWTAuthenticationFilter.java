package ru.geekbrains.market.core.configurations.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.geekbrains.market.core.dto.UserInfo;
import ru.geekbrains.market.core.repositories.RedisRepository;
import ru.geekbrains.market.core.service.ITokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final ITokenService tokenService;
    private final RedisRepository redisRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null ||
            !authHeader.startsWith("Bearer ") ||
            redisRepository.hasKey(authHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = createToken(authHeader);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken createToken(String authHeader) {
        UserInfo userInfo = tokenService.parseToken(authHeader.replace("Bearer ", ""));
        List<GrantedAuthority> authorities = userInfo.getRoles().stream().
                map(role -> {
                    return new SimpleGrantedAuthority(role);
                }).
                collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
    }
}
