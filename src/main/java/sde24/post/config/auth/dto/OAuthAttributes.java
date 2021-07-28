package sde24.post.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import sde24.post.entity.Role;
import sde24.post.entity.User;

import java.util.Map;

@Getter
public class OAuthAttributes {
    // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담는 클래스

    private Map<String, Object> attributes;
    private String userNameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String userNameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.userNameAttributeKey = userNameAttributeKey;
        this.name = name;
        this.email = email;
    }

    // 사용자 정보가 Map이므로 값을 하나하나 매핑
    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .userNameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
