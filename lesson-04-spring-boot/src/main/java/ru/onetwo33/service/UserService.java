package ru.onetwo33.service;

import org.springframework.data.domain.Page;
import ru.onetwo33.controller.UserDto;
import ru.onetwo33.controller.UserListParams;
import ru.onetwo33.persist.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto userDto);

    void deleteById(Long id);
}
