package node_value.projects.cerasync_back.util.dto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import node_value.projects.cerasync_back.model.Event;
import node_value.projects.cerasync_back.model.Role;
import node_value.projects.cerasync_back.model.User;
import node_value.projects.cerasync_back.util.dto.reqDTO.EventDTO;
import node_value.projects.cerasync_back.util.dto.reqDTO.UserDTO;

public class DTOtoObj {

    @Autowired private static PasswordEncoder pswEncoder;

    public static User UserDTOtoUser(UserDTO dto) {
        return User.builder()
            .email(    dto.getEmail())
            .password( pswEncoder.encode(dto.getPassword()))
            .role(     Role.USER)
            .events(   new ArrayList<>())
            .build();
        
    }

    public static Event EventDTOtoEvent(EventDTO dto) {
        return Event.builder()
            .title(          dto.getTitle())
            .location(       dto.getLocation())
            .dateTime(       dto.getDateTime())
            .host(           dto.getHost())
            .shorDescription(dto.getShortDesc())
            .fullDescription(dto.getFullDesc())
            .imageData(      dto.getImageData())
            .subscribers(    new ArrayList<>())
            .build();    
    }
}
