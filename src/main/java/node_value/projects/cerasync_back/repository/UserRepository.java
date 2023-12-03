package node_value.projects.cerasync_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import node_value.projects.cerasync_back.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean        existsByEmail(String email);
}
