package node_value.projects.cerasync_back.util.dto.reqDTO;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data @Builder
public class EventDTO {
    //@NonNull Long          id;
    @NonNull String        title, location, host, shortDesc, fullDesc;
    @NonNull LocalDateTime dateTime;
    @NonNull byte[]        imageData;
}
