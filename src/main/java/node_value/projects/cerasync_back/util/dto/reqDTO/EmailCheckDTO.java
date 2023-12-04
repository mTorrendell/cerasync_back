package node_value.projects.cerasync_back.util.dto.reqDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor
public class EmailCheckDTO {
    @NonNull private String email;
}