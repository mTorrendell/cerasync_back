package node_value.projects.cerasync_back.util.dto.reqDTO;

import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data @Builder
public class EventDTO {
    @Nullable Long         id;
    @NonNull String        title, location, host, shortDesc, fullDesc, imageData;
    @NonNull LocalDateTime dateTime;
}
