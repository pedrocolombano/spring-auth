package br.com.colombano.springauth.dto.response;

import br.com.colombano.springauth.entity.Client;

public record ClientDto(Long id, String name, String email) {

    public ClientDto(Client entity) {
        this(entity.getId(), entity.getName(), entity.getEmail());
    }

}
