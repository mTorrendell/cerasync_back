package node_value.projects.cerasync_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import node_value.projects.cerasync_back.service.UserService;
import node_value.projects.cerasync_back.util.dto.reqDTO.EmailCheckDTO;
import node_value.projects.cerasync_back.util.dto.reqDTO.UserDTO;
import node_value.projects.cerasync_back.util.dto.respDTO.EmailCheckResponse;
import node_value.projects.cerasync_back.util.exceptions.UserExistsException;
import node_value.projects.cerasync_back.util.exceptions.UserNotFoundException;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class UserController {

    @Autowired private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserDTO uDTO) {
        try {
            return ResponseEntity.ok(service.register(uDTO));
        } catch (UserExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Validated @RequestBody UserDTO uDTO) {
        try {
            return ResponseEntity.ok(service.authenticate(uDTO));
        } catch (BadCredentialsException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/email_check")
    public ResponseEntity<EmailCheckResponse> checkEmail(@Validated @RequestBody EmailCheckDTO ecDTO) {
        return ResponseEntity.ok(service.isUserExists(ecDTO));
    }
}
