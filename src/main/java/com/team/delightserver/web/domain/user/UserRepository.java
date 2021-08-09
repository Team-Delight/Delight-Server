package com.team.delightserver.web.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialProviderKey(String socialProviderKey);
}