package sde24.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sde24.post.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // email로 처음 가입한 사용자인지 아닌지 체크
    Optional<User> findByEmail(String Email);
}
