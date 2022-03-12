package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.ArrayList;

@Controller
@SessionAttributes(names = {"user"})
public class MainController {
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "WyrmSticker | Главная страница");
        ArrayList<Sticker> stickers = stickerRepository.findAll();
        ArrayList<Pack> lastPacks = packRepository.findLast(3);
        model.addAttribute("lastPacks", lastPacks);
        model.addAttribute("sticker1", ListHelper.Random(stickers));
        model.addAttribute("sticker2", ListHelper.Random(stickers));
        model.addAttribute("sticker3", ListHelper.Random(stickers));
        model.addAttribute("sticker4", ListHelper.Random(stickers));
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "WyrmSticker | О нас");
        return "about";
    }

    @GetMapping("/logout")
    public String logout(WebRequest request, SessionStatus status) {
        status.setComplete();
        request.removeAttribute("user", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("title", "WyrmSticker | Страница не найдена");
        return "error";
    }
}
