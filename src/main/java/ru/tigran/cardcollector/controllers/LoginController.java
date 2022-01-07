package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import ru.tigran.cardcollector.Config;
import ru.tigran.cardcollector.database.entity.SecretSessionKey;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.SecretSessionKeyRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@SessionAttributes(names = {"user"})
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecretSessionKeyRepository secretSessionKeyRepository;

    @PostMapping()
    public String login(@RequestParam String userId, @RequestParam String secretKey, Model model) {
        Optional<SecretSessionKey> result = secretSessionKeyRepository.findById(secretKey);
        if (result.isEmpty()) return "redirect:/";

        secretSessionKeyRepository.deleteById(secretKey);

        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (userOptional.isEmpty()) return "redirect:/";

        model.addAttribute("user", userOptional.get());
        return "redirect:/collection";
    }

    @GetMapping()
    public String login(Model model) {
        if (model.containsAttribute("user")) return "redirect:/profile";
        String host = Config.get("server.name");
        String port = Config.get("server.port");
        String botName = Config.get("telegram.bot.name");
        model.addAttribute("host", host);
        model.addAttribute("port", port);
        model.addAttribute("botName", botName);
        return "login";
    }
}
