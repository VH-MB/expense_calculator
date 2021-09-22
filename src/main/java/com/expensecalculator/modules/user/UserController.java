package com.expensecalculator.modules.user;

import com.expensecalculator.modules.user.dto.UserDto;
import com.expensecalculator.security.payload.response.MessageResponse;
import com.expensecalculator.security.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
class UserController {

    private final UserService userService;
    private final UserFacade userFacade;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public UserController(final UserService userService,
                          final UserFacade userFacade,
                          final ResponseErrorValidation responseErrorValidation) {
        this.userService = userService;
        this.userFacade = userFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    @GetMapping("/all")
    ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAllUser()
                        .stream()
                        .map(userFacade::userToUserDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/find/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        User user = userService.findUserById(Long.parseLong(userId));
        return ResponseEntity.status(HttpStatus.OK).body(userFacade.userToUserDto(user));
    }

    @PostMapping("/add/{eventId}")
    ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto,
                                      @PathVariable("eventId") String eventId,
                                      BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        User user = userService.createdUser(userDto, Long.parseLong(eventId));
        return ResponseEntity.status(HttpStatus.CREATED).body(userFacade.userToUserDto(user));
    }

    @DeleteMapping("/remove/{userId}")
    ResponseEntity<MessageResponse> deleteUserById(@PathVariable("userId") String userId) {
        userService.remove(Long.parseLong(userId));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("User was deleted"));
    }

}

