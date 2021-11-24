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

@Controller
@RequestMapping("/profile")
@SessionAttributes(names = {"user", "cash"})
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionTokenRepository sessionTokenRepository;

    @GetMapping()
    public String profile(Model model){
        if (model.getAttribute("user") instanceof User user) {
            model.addAttribute("title", "WyrmSticker | " + user.Username);
            return "profile/index";
        }
        return "redirect:/";
    }
}
