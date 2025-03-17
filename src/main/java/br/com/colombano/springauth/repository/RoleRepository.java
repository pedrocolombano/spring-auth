package br.com.colombano.springauth.repository;

import br.com.colombano.springauth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
