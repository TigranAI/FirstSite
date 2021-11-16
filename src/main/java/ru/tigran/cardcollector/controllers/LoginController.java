package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import ru.tigran.cardcollector.database.repository.CashRepository;
import ru.tigran.cardcollector.database.repository.SessionTokenRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;

@Controller
@RequestMapping("/login")
@SessionAttributes(names = {"user", "cash"})
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionTokenRepository sessionTokenRepository;
    @Autowired
    private CashRepository cashRepository;

    @GetMapping(params = {"token"})
    public String login(String token, Model model){
        try {
            var id = sessionTokenRepository.getIdByToken(token);
            sessionTokenRepository.deleteById(id);
            model.addAttribute("user", userRepository.findById(id).get());
            model.addAttribute("cash", cashRepository.findById(id).get());
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
}
