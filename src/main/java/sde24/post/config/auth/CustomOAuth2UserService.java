package sde24.post.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sde24.post.config.auth.dto.OAuthAttributes;
import sde24.post.config.auth.dto.SessionUser;
import sde24.post.entity.User;
import sde24.post.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    // 시용자 정보 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원

    private final UserRepository repository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        // OAuth2UserService 의 구현체, loadUser메서드를 사용하여 oAuth2UserRequest의 정보를 빼낸다
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(oAuth2UserRequest);


        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeName = oAuth2UserRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attribute = OAuthAttributes.
                ofGoogle(userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attribute);
        httpSession.setAttribute("user", new SessionUser(user)); // 세션에 사용자 정보 저장

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attribute.getAttributes(), attribute.getUserNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attribute) {
        User user = repository.findByEmail(attribute.getEmail())
                .map(u -> u.update(attribute.getName()))
                .orElse(attribute.toEntity());

        return repository.save(user);
    }
}
