package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.SessionTokenRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;

@Controller
@SessionAttributes(names = {"user", "cash"})
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionTokenRepository sessionTokenRepository;

    @GetMapping()
    public String index(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("title", "WyrmSticker | Главная страница");
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "WyrmSticker | О нас");
        return "about";
    }

    @GetMapping("/logout")
    public String logout(WebRequest request, SessionStatus status) {
        status.setComplete();
        request.removeAttribute("user", WebRequest.SCOPE_SESSION);
        request.removeAttribute("cash", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }
}
