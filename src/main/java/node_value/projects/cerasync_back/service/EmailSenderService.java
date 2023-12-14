package node_value.projects.cerasync_back.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService  {

    @Autowired private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("nikita.vladimirov@code.berlin");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);

        mailSender.send(msg);
    }

    public void sendMsgWithAttachment(
            String to, String subject, String text, String pathToAttachment) 
                throws MessagingException, IOException {
                    
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setFrom("nikita.vladimirov@code.berlin");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        ClassPathResource file = new ClassPathResource(pathToAttachment);
      
        helper.addAttachment("CeraSync_Magazine_Edition_1.pdf", file);

        mailSender.send(msg);
    }
    
}
