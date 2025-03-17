package br.com.colombano.springauth.config;

import br.com.colombano.springauth.entity.Client;
import br.com.colombano.springauth.repository.ClientRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Mock {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void mock() {
        Client c1 = new Client(null, "Pedro Colombano", "pedro@email.com", passwordEncoder.encode("1234"));
        Client c2 = new Client(null, "Renan Felix", "renan@email.com", passwordEncoder.encode("1234"));
        Client c3 = new Client(null, "Paulo Froes", "paulo@email.com", passwordEncoder.encode("1234"));

        clientRepository.saveAll(List.of(c1, c2, c3));
    }

}
