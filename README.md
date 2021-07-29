# post-project

## Google OAuth를 이용한 로그인 구현
#### SecurityConfig.java
- 권한별(ROLE_USER, ROLE_ADMIN) 설정을 진행한다
- 로그인 성공시 CustomOAuth2UserService에서 후속 조치를 진행하도록 설정한다

#### CustomOAuth2UserService.java
- OAuth2UserService 의 구현체인 DefaultOAuth2UserService의 loadUser메서드를 사용하여 oAuth2UserRequest의 정보를 가지고온다 (Google에서 받아오는 정보)
- Google에서 받아온 정보와 DB를 동기화한다
- 세션으로 사용할 user 정보를 담아(SessionUser.java) 세션에 세팅한다


#### 과정 이미지 첨부
<img width="500" alt="google oauth 과정" src="https://user-images.githubusercontent.com/88015037/127441499-e20b87c3-ebc5-4e0c-bf95-5b4d3d71faa2.png">
