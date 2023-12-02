package node_value.projects.cerasync_back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import node_value.projects.cerasync_back.model.Event;
import node_value.projects.cerasync_back.model.EventSubscriber;

public interface EventSubscriberRepository extends JpaRepository<EventSubscriber, Long> {
    List<EventSubscriber>     findByEvent(Event event);
    Optional<EventSubscriber> findByEmail(String email);
}
