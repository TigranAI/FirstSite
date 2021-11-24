package ru.tigran.cardcollector.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.database.entity.User;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = {"user", "cash"})
public class AdminController {

    @GetMapping
    public String admin(Model model){
        User user = (User) model.getAttribute("user");
        if (user == null || user.PrivilegeLevel < 2) return "redirect:/";
        return "admin/index";
    }
}
