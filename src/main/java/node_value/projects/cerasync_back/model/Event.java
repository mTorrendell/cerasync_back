package node_value.projects.cerasync_back.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "cerasync_back_event")
public class Event {

    @Id @SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private Long id;

    private String title, location, host, shorDescription, fullDescription;

    private LocalDateTime dateTime;

    @Lob
    private byte[] imageData;

    @OneToMany(mappedBy = "event")
    private List<EventSubscriber> subscribers;
    
    @ManyToOne @JoinColumn(name = "owner_id")
    private User owner;
}
