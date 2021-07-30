# oauth-project

## Google OAuth를 이용한 로그인 구현
#### SecurityConfig.java
- 권한별(ROLE_USER, ROLE_ADMIN) 설정을 진행한다
- 로그인 성공시 CustomOAuth2UserService에서 후속 조치를 진행하도록 설정한다

#### CustomOAuth2UserService.java
- OAuth2UserService 의 구현체인 DefaultOAuth2UserService의 loadUser메서드를 사용하여 oAuth2UserRequest의 정보를 가지고온다 (Google에서 받아오는 정보)
- Google에서 받아온 정보와 DB를 동기화한다
- 세션으로 사용할 user 정보를 담아(SessionUser.java) 세션에 세팅한다


<br><br><br><br>

#### 과정 이미지 첨부
<img width="500" alt="google oauth 과정" src="https://user-images.githubusercontent.com/88015037/127441499-e20b87c3-ebc5-4e0c-bf95-5b4d3d71faa2.png">
1. 사용자가 애플리케이션에 접근 리소스 서버(구글)의 서비스 이용 시도.<br>   
2. 소셜 로그인 인증 요청 <br>  
3. 인증 시 Client Id, Client Secret 전송. <br>
4. Scope List(사용희망하는 서비스 리스트) 확인. <br>  
5. 확인 후 authorization code 발급.  <br>    
6. Client는 id, secret, code를 모두 리소스 서버(구글)에 전송. <br>  
7. 리소스 서버(구글)는 값들을 비교해서 일치하면  Access Token을 발급. <br> 
8. 최종적으로 Access Token을 이용하여 리소스 서버(구글)의 api를 호출.   
