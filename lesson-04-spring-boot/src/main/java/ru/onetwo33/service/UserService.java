package ru.onetwo33.service;

import org.springframework.data.domain.Page;
import ru.onetwo33.controller.UserListParams;
import ru.onetwo33.persist.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Page<User> findWithFilter(UserListParams userListParams);

    Optional<User> findById(Long id);

    void save(User user);

    void deleteById(Long id);
}
