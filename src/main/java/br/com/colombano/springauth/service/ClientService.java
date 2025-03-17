package br.com.colombano.springauth.service;

import br.com.colombano.springauth.dto.response.ClientDto;
import br.com.colombano.springauth.exception.NotFoundException;
import br.com.colombano.springauth.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        return clientRepository.findById(id)
                               .map(ClientDto::new)
                               .orElseThrow(() -> new NotFoundException("Client not found."));
    }

    @Transactional(readOnly = true)
    public List<ClientDto> getAll() {
        return clientRepository.findAll()
                               .stream()
                               .map(ClientDto::new)
                               .toList();
    }
}
