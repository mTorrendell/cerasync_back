package node_value.projects.cerasync_back.util.dto.respDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import node_value.projects.cerasync_back.model.Event;

@Data @Builder
public class EventResponse {
    @NonNull Event event;
}