package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.UserDto;
import br.com.alura.bookstore.dto.UserFormDto;
import br.com.alura.bookstore.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
@Api(tags = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserDto> getAllUsers(@PageableDefault(size=10) Pageable pageable){
        return userService.findAllUsers(pageable);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserFormDto userFormDto, UriComponentsBuilder uriComponentsBuilder){
        UserDto registered = userService.saveUser(userFormDto);
        URI usersUri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(registered.getId()).toUri();
        return ResponseEntity.created(usersUri).body(registered);
    }



}
