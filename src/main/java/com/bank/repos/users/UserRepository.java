package com.bank.repos.users;

import com.bank.models.users.User;
import org.springframework.stereotype.Repository;
import com.bank.repos.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByOrderByIdDesc();
}