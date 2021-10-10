package com.expensecalculator.security.web;

import com.expensecalculator.security.constants.SecurityConstants;
import com.expensecalculator.security.jwt.JWTTokenProvider;
import com.expensecalculator.security.payload.request.LoginRequest;
import com.expensecalculator.security.payload.request.SignupRequest;
import com.expensecalculator.security.payload.response.JWTTokenSuccessResponse;
import com.expensecalculator.security.payload.response.MessageResponse;
import com.expensecalculator.security.user.UserService;
import com.expensecalculator.security.validation.ResponseUserErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ResponseUserErrorValidation responseUserErrorValidation;
    private final UserService userService;

    @PostMapping("/sign_in")
    ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseUserErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginRequest.getUsername(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authenticate);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/sign_up")
    ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseUserErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("Person registered successfully"));
    }
}
