package sde24.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sde24.post.config.auth.SessionUser;
import sde24.post.repository.UserRepository;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        // CustomOAuth2UserService 에서 세팅한 user
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
