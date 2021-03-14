package com.king.photo_app_users.controllers;


import com.king.photo_app_users.models.CreateUserModel;
import com.king.photo_app_users.models.UserModel;
import com.king.photo_app_users.services.UserService;
import com.king.photo_app_users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class Controller {

    @Autowired
    private Environment env;
    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public List<CreateUserModel> status(){
        return userService.findAll();
    }

    @GetMapping("/error")
    public String error(){
        return "Error from user-ws on port: " + env.getProperty("local.server.port");
    }

    @PostMapping(value = "/create-user",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
//    @PostMapping("/create-user")
    public ResponseEntity<CreateUserModel> createUser(@Valid @RequestBody UserModel userDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);

        CreateUserModel returnValue = modelMapper.map(createdUser, CreateUserModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
