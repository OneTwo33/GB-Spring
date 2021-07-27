package ru.onetwo33.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.onetwo33.controller.NotFoundException;
import ru.onetwo33.controller.UserDto;
import ru.onetwo33.persist.User;
import ru.onetwo33.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @PostMapping(produces = "application/json")
    public UserDto create(@RequestBody UserDto userDto) {
        if (userDto.getId() == -1) { // Из ангуляра приходит -1
            userDto.setId(null);
        }
        if (userDto.getId() != null) {
            throw new BadRequestException("User Id should be null");
        }
        userService.save(userDto);
        return userDto;
    }

    @PutMapping(produces = "application/json")
    public void update(@RequestBody UserDto userDto) {
        if (userDto.getId() == null) {
            throw new BadRequestException("User Id shouldn't be null");
        }
        userService.save(userDto);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
