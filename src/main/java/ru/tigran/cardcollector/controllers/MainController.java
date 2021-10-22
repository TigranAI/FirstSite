package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.SessionTokenRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;

import java.util.Optional;

@Controller
@SessionAttributes("user")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionTokenRepository sessionTokenRepository;

    @GetMapping("/")
    public String index(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("title", "Домашняя страница");
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/login", params = {"token"})
    public String login(String token, Model model){
        try {
            var id = sessionTokenRepository.getIdByToken(token);
            model.addAttribute("user", userRepository.findById(id));
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        if (model.getAttribute("user") instanceof Optional optional && optional.isPresent()) {
            if (optional.get() instanceof User user) {
                System.out.println(user.getUsername());
                return "profile";
            }
        }
        return "redirect:/login";
    }
}
