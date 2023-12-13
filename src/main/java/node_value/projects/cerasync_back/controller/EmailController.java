package node_value.projects.cerasync_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import node_value.projects.cerasync_back.service.EmailSenderService;
import node_value.projects.cerasync_back.util.dto.reqDTO.EmailCheckDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/email")
@CrossOrigin
public class EmailController {
    
    @Autowired private EmailSenderService emailService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> postEmail(@RequestBody EmailCheckDTO dto) {
        emailService.sendSimpleMessage(
            dto.getEmail(), "CeraSync Newsletter Subscription", "Thank you for your interest in CeraSync!");
        return ResponseEntity.ok("Subscribed successfully!");
    }
    
}
