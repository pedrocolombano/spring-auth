package br.com.colombano.springauth.service;

import br.com.colombano.springauth.dto.response.TokenDto;
import br.com.colombano.springauth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtService jwtService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public TokenDto authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails user = clientService.loadUserByUsername(email);

        Set<String> roles = user.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());

        String token = jwtService.generateToken(user.getUsername(), email, roles);
        return new TokenDto(token);
    }
}
