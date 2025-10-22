package node_value.projects.cerasync_back.util.dto.respDTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import node_value.projects.cerasync_back.model.Event;

@Data @Builder
public class UserEventsResponse {
    @NonNull Integer     id;   
    @NonNull List<Event> events;
}