package ru.tigran.cardcollector.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/toast")
public class ToastController {
    @PostMapping
    public String getToast(@RequestParam String toastId) {
        return "/toast/" + toastId;
    }
}
