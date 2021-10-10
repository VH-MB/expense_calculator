package com.expensecalculator.security.user;

import com.expensecalculator.security.payload.request.SignupRequest;
import com.expensecalculator.security.user.dto.UserDTO;
import com.expensecalculator.security.user.enums.ERole;
import com.expensecalculator.security.user.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger LOG = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setFirstName(userIn.getFirstName());
        user.setLastName(userIn.getLastName());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException(user.getUsername());
        }

    }

//    public User updateUser(UserDTO userDTO, Principal principal) {
//        User user = getUserByPrincipal(principal);
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//
//        return userRepository.save(user);
//    }
//
//    public User getCurrentUser(Principal principal){
//        return getUserByPrincipal(principal);
//    }
//
//    private User getUserByPrincipal(Principal principal) {
//        String username = principal.getName();
//        return userQueryRepository.findUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//    }
}
