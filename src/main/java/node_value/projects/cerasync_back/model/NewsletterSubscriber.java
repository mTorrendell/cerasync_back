package node_value.projects.cerasync_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "cerasync_back_newsletter_subscriber")
public class NewsletterSubscriber {
    
    @Id @GeneratedValue
    private Long id;

    private String email;

}
