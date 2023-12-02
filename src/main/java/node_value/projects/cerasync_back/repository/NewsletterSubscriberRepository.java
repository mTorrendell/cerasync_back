package node_value.projects.cerasync_back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import node_value.projects.cerasync_back.model.NewsletterSubscriber;

public interface NewsletterSubscriberRepository extends JpaRepository<NewsletterSubscriber, Long> {
    List<NewsletterSubscriber>     findAll();
    Optional<NewsletterSubscriber> findById(Long id);
    Optional<NewsletterSubscriber> findByEmail(String email);
}