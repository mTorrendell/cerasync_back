package node_value.projects.cerasync_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import node_value.projects.cerasync_back.model.User;
import node_value.projects.cerasync_back.repository.UserRepository;
import node_value.projects.cerasync_back.util.dto.DTOtoObj;
import node_value.projects.cerasync_back.util.dto.reqDTO.EmailCheckDTO;
import node_value.projects.cerasync_back.util.dto.reqDTO.UserDTO;
import node_value.projects.cerasync_back.util.dto.respDTO.AuthResponse;
import node_value.projects.cerasync_back.util.dto.respDTO.EmailCheckResponse;
import node_value.projects.cerasync_back.util.exceptions.UserExistsException;
import node_value.projects.cerasync_back.util.exceptions.UserNotFoundException;

@Service
public class UserService {
    
    @Autowired private UserRepository        repo;
    @Autowired private JwtService            jwtSevice;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder       pswEncoder;

    public AuthResponse register(UserDTO req) throws UserExistsException {
        if (repo.existsByEmail(req.getEmail())) throw new UserExistsException(req.getEmail());
        
        req.setPassword(pswEncoder.encode(req.getPassword()));
        User user = DTOtoObj.UserDTOtoUser(req);
        repo.save(user);
        return AuthResponse.builder().token(jwtSevice.generateToken(user)).build();
    }

    public AuthResponse authenticate(UserDTO req) throws BadCredentialsException, UserNotFoundException {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Unable to authenticate, nvalid credentials", e);
        }
        User user = repo.findByEmail(req.getEmail()).orElseThrow(
            () -> new UserNotFoundException("Unable to authentiacate, user not found"));
        
        return AuthResponse.builder().token(jwtSevice.generateToken(user)).build();
    }

    public EmailCheckResponse isUserExists(EmailCheckDTO req) {
        return EmailCheckResponse.builder().email(req.getEmail()).isExists(repo.existsByEmail(req.getEmail())).build();
    }
}
