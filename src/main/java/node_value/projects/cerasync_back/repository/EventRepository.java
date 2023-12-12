package node_value.projects.cerasync_back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import node_value.projects.cerasync_back.model.Event;
import node_value.projects.cerasync_back.model.User;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event>     findAll();
    List<Event>     findByOwner(User owner);
    Optional<Event> findById(Long id);
    boolean         existsByTitle(String title);
    void            deleteById(Long id);
    
}