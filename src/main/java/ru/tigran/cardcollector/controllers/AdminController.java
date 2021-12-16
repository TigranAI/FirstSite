package ru.tigran.cardcollector.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.Config;
import ru.tigran.cardcollector.database.entity.User;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = {"user"})
public class AdminController {

    @GetMapping
    public String admin(Model model){
        User user = (User) model.getAttribute("user");
        if (user == null || user.PrivilegeLevel < 2) return "redirect:/";
        String host = Config.get("server.name");
        String port = Config.get("server.port");
        model.addAttribute("host", host);
        model.addAttribute("port", port);
        return "admin/index";
    }
}
