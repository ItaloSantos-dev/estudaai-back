package br.com.italo.estuda_ai.repository;

import br.com.italo.estuda_ai.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
