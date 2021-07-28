package sde24.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sde24.post.entity.Role;
import sde24.post.entity.User;
import sde24.post.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BasicTest {

    @Autowired UserRepository userRepository;


    @Test
    public void baseTimeEntityTest(){
        // given
        LocalDateTime now = LocalDateTime.now();
        userRepository.save(User.builder()
                .email("user@email.com")
                .role(Role.USER)
                .name("회원1").build());
        // when
        List<User> userList = userRepository.findAll();

        // then
        User user = userList.get(0);

        System.out.println("createDate   >>> " + user.getCreatedDate());
        System.out.println("modifiedDate >>> " + user.getModifiedDate());

        Assertions.assertThat(user.getCreatedDate()).isAfter(now);
        Assertions.assertThat(user.getModifiedDate()).isAfter(now);
    }
}
