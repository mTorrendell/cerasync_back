package node_value.projects.cerasync_back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import node_value.projects.cerasync_back.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event>     findAll();
    Optional<Event> findById(Long id);
    boolean         existsByTitle(String title);
    void            deleteById(Long id);
    
}