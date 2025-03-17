package br.com.colombano.springauth.config;

import br.com.colombano.springauth.entity.Client;
import br.com.colombano.springauth.entity.Role;
import br.com.colombano.springauth.repository.ClientRepository;
import br.com.colombano.springauth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class Mock {

    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void mock() {
        Role r1 = new Role(null, "ADMIN");
        Role r2 = new Role(null, "CUSTOMER");

        roleRepository.saveAll(List.of(r1, r2));

        Client c1 = new Client(null, "Pedro Colombano", "pedro@email.com", passwordEncoder.encode("1234"), Set.of(r1, r2));
        Client c2 = new Client(null, "Renan Felix", "renan@email.com", passwordEncoder.encode("1234"), Set.of(r2));
        Client c3 = new Client(null, "Paulo Froes", "paulo@email.com", passwordEncoder.encode("1234"), Set.of(r2));

        clientRepository.saveAll(List.of(c1, c2, c3));
    }

}
