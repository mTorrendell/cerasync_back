package node_value.projects.cerasync_back.util.dto;

import lombok.NonNull;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EmailCheckResponse {
    @NonNull Boolean isExists;
    @NonNull String  email;   
}
