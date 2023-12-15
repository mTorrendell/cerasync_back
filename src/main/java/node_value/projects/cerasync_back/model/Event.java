package node_value.projects.cerasync_back.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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

    private String title, location, host, shorDescription;
    
    @Column(length = 1024)
    private String fullDescription;

    private LocalDateTime dateTime;

    @Lob
    @Column(columnDefinition = "text")
    private String imageData;

    @JsonIgnore 
    @OneToMany(mappedBy = "event")
    private List<EventSubscriber> subscribers;
    
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "owner_id")
    private User owner;

}
