package node_value.projects.cerasync_back.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
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
        try {
            String attachmentPath = "classpath:magazine/CeraSync_Magazine_Edition_1.pdf";
            emailService.sendMsgWithAttachment(
                dto.getEmail(), " Welcome to CeraSync Newsletter!", 
                    "Hello and welcome to CeraSync!" 
                    + System.lineSeparator() + System.lineSeparator() + 
                    "We're thrilled to have you on board as a new subscriber to our newsletter. As a token of our appreciation, we've attached the first edition of our magazine to this email. We hope you enjoy the content it brings"
                    + System.lineSeparator() + System.lineSeparator() + 
                    "Feel free to reach out if you have any questions, feedback, or if there's anything specific you'd like to see in our upcoming editions. We value your presence in the CeraSync community and look forward to sharing more inspiring content with you."
                    + System.lineSeparator() + System.lineSeparator() + 
                    "Best regards,"
                    + System.lineSeparator() + System.lineSeparator() + 
                    "Nikita Vladimirov"
                    + System.lineSeparator() +
                    "CeraSync Team"
                    , attachmentPath );
        } catch (MessagingException | IOException e) {
            return ResponseEntity.badRequest().body("Unable to subscribe: " + e.getMessage() + "Cause: " + e.getCause() + "Stacktrace: " + e.getStackTrace());
        }
        return ResponseEntity.ok("Subscribed successfully!");
    }
    
}
