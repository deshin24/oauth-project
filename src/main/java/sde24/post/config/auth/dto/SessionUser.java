package sde24.post.config.auth.dto;

import lombok.Getter;
import sde24.post.entity.Role;
import sde24.post.entity.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // 인증된 사용자 정보를 저장할 Dto
    // 정상적으로 세션에 저장하고 꺼내오기 위해 Serializable class 를 implements 함

    private String name;
    private String email;
    private Role role;


    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
