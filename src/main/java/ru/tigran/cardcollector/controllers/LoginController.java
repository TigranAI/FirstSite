package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.Config;
import ru.tigran.cardcollector.database.repository.SessionTokenRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;

import java.net.InetAddress;

@Controller
@RequestMapping("/login")
@SessionAttributes(names = {"user"})
public class LoginController {
    @Autowired
    Environment environment;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionTokenRepository sessionTokenRepository;

    @GetMapping(params = {"token"})
    public String login(String token, Model model){
        try {
            var id = sessionTokenRepository.getIdByToken(token);
            sessionTokenRepository.deleteById(id);
            model.addAttribute("user", userRepository.findById(id).get());
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping()
    public String login(Model model){
        String host = String.format("%s:%s",
                InetAddress.getLoopbackAddress().getHostName(),
                environment.getProperty("server.port"));
        String botName = Config.get("telegram.bot.name");
        String url = String.format("https://t.me/%s?start=create_token=%s", botName, host);
        return "redirect:" + url;
    }
}
