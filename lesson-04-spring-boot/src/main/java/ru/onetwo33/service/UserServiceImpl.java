package ru.onetwo33.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.onetwo33.controller.NotFoundException;
import ru.onetwo33.controller.RoleDto;
import ru.onetwo33.controller.UserDto;
import ru.onetwo33.controller.UserListParams;
import ru.onetwo33.persist.Role;
import ru.onetwo33.persist.User;
import ru.onetwo33.persist.UserRepository;
import ru.onetwo33.persist.UserSpecification;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(),
                        user.getUsername(),
                        user.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> findWithFilter(UserListParams userListParams) {
        Specification<User> spec = Specification.where(null);

        if (userListParams.getUsernameFilter() != null && !userListParams.getUsernameFilter().isEmpty()) {
            spec = spec.and(UserSpecification.usernamePrefix(userListParams.getUsernameFilter()));
        }
        if (userListParams.getMinAge() != null) {
            spec = spec.and(UserSpecification.minAge(userListParams.getMinAge()));
        }
        if (userListParams.getMaxAge() != null) {
            spec = spec.and(UserSpecification.maxAge(userListParams.getMaxAge()));
        }

        if ("desc".equals(userListParams.getDirection())) {
            return userRepository.findAll(spec,
                    PageRequest.of(Optional.ofNullable(userListParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(userListParams.getSize()).orElse(10),
                            Sort.by(Sort.Direction.DESC ,Optional.ofNullable(userListParams.getSortField()).orElse("id"))))
                    .map(user -> new UserDto(user.getId(), user.getUsername(), user.getAge()));
        } else {
            return userRepository.findAll(spec,
                    PageRequest.of(Optional.ofNullable(userListParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(userListParams.getSize()).orElse(10),
                            Sort.by(Sort.Direction.ASC ,Optional.ofNullable(userListParams.getSortField()).orElse("id"))))
                    .map(user -> new UserDto(user.getId(), user.getUsername(), user.getAge()));
        }
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getAge(), mapRolesDto(user)));
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();

        if (userDto.getPassword().equals("") || userDto.getPassword() == null) {
            User existing = userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            user.setPassword(existing.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(2L, "ROLE_GUEST"));
            user.setRoles(roles);
        } else {
            user.setRoles(userDto.getRoles().stream()
                    .map(roleDto -> new Role(roleDto.getId(), roleDto.getName()))
                    .collect(Collectors.toSet()));
        }

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static Set<RoleDto> mapRolesDto(User user) {
        return user.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }
}
