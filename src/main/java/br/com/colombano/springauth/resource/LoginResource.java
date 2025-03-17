package br.com.colombano.springauth.resource;

import br.com.colombano.springauth.dto.request.LoginDto;
import br.com.colombano.springauth.dto.response.TokenDto;
import br.com.colombano.springauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginResource {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody LoginDto loginDto) {
        TokenDto token = loginService.authenticate(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(token);
    }

}
