package node_value.projects.cerasync_back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import node_value.projects.cerasync_back.model.Event;
import node_value.projects.cerasync_back.model.User;
import node_value.projects.cerasync_back.repository.EventRepository;
import node_value.projects.cerasync_back.repository.UserRepository;
import node_value.projects.cerasync_back.util.dto.DTOtoObj;
import node_value.projects.cerasync_back.util.dto.reqDTO.EventDTO;
import node_value.projects.cerasync_back.util.dto.respDTO.AllEventsResponse;
import node_value.projects.cerasync_back.util.dto.respDTO.EventResponse;
import node_value.projects.cerasync_back.util.dto.respDTO.UserEventsResponse;
import node_value.projects.cerasync_back.util.exceptions.EventAlreadyExistsException;
import node_value.projects.cerasync_back.util.exceptions.EventNotFoundException;
import node_value.projects.cerasync_back.util.exceptions.UserNotFoundException;

@Service
public class EventService {

    @Autowired EventRepository repo;
    @Autowired UserRepository  userRepo;

    public AllEventsResponse getAllEvents() {
        return AllEventsResponse.builder().events(repo.findAll()).build();
    }

    public EventResponse getEventById(Long id) throws EventNotFoundException {
        return EventResponse.builder().event(
            repo.findById(id).orElseThrow(() -> new EventNotFoundException("Event with id " + id + " not found"))
        ).build();
    }

    public UserEventsResponse getEventByUserId(Integer id) throws UserNotFoundException {
        User user = userRepo.findById(id).orElseThrow(
            () -> new UserNotFoundException("Unable to find user by id " + id));
        
        return UserEventsResponse.builder().id(id).events(user.getEvents()).build();
    }

    public EventResponse addEvent(EventDTO eventDTO, String email) throws EventAlreadyExistsException {
        if (repo.existsByTitle(eventDTO.getTitle()))
            throw new EventAlreadyExistsException(
                "Event with title: " + System.lineSeparator() + 
                   "\"" + eventDTO.getTitle() + "\"" + System.lineSeparator() +
                "already exists, please use another one");
    
        Event event = DTOtoObj.EventDTOtoEvent(eventDTO); 
        event.setOwner(userRepo.findByEmail(email).get());
        repo.save(event);
        return EventResponse.builder().event(event).build();
    }

    public void updateEvent(EventDTO eventDTO) throws EventNotFoundException, NullPointerException {
        if (eventDTO.getId() == null) 
            throw new NullPointerException("Field id of eventDTO instance is null");
        Optional<Event> eOp = repo.findById(eventDTO.getId());
        if (eOp.isPresent()) {
            Event e = eOp.get();
            e.setDateTime(       eventDTO.getDateTime());
            e.setHost(           eventDTO.getHost());
            e.setFullDescription(eventDTO.getFullDesc());
            e.setShorDescription(eventDTO.getShortDesc());
            e.setLocation(       eventDTO.getLocation());
            e.setTitle(          eventDTO.getTitle());
            e.setImageData(      eventDTO.getImageData());
            repo.save(e);
        } else throw new EventNotFoundException("Event with id " + eventDTO.getId() + " not found");
    }

    public void deleteEventById(Long id) {
        repo.deleteById(id);
    }
}
