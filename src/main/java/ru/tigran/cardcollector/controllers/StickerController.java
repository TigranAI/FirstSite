package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;

@Controller
@RequestMapping("/sticker")
@SessionAttributes(names = {"user", "cash"})
public class StickerController {

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private PackRepository packRepository;

    @GetMapping(params = {"hash"})
    public String showSticker(String hash, Model model) {
        return "index";
    }
}
