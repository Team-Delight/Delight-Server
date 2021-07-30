package com.team.delightserver.web.repository;

import com.team.delightserver.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialProviderKey(String socialProviderKey);
}