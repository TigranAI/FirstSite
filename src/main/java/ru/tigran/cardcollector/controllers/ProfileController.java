package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.SessionTokenRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;

import java.util.Optional;

@Controller
@SessionAttributes("user")
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionTokenRepository sessionTokenRepository;

    @GetMapping()
    public String profile(Model model){
        if (model.getAttribute("user") instanceof Optional optional && optional.isPresent()) {
            if (optional.get() instanceof User user) {
                model.addAttribute("title", "WyrmSticker | " + user.getUsername());
                return "profile";
            }
        }
        return "redirect:tg://resolve?domain=TigranCardCollectorBot&start=create_token";
    }
}
