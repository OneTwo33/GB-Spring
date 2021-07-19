package ru.onetwo33.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.onetwo33.controller.UserListParams;
import ru.onetwo33.persist.User;
import ru.onetwo33.persist.UserRepository;
import ru.onetwo33.persist.UserSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findWithFilter(UserListParams userListParams) {
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

        return userRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(userListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(userListParams.getSize()).orElse(3),
                        Sort.by(Optional.ofNullable(userListParams.getSortField()).orElse("id"))));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
