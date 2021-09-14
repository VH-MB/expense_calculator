package com.expensecalculator.modules.user;

import com.expensecalculator.modules.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
class UserController {

    private final UserService userService;

    @GetMapping("/all")
    ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUser());
    }


}
