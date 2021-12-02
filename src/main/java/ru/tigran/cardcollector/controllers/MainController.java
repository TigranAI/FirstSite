package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.entity.UserLevel;
import ru.tigran.cardcollector.database.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names = {"user"})
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionTokenRepository sessionTokenRepository;
    @Autowired
    private UserLevelRepository userLevelRepository;
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "WyrmSticker | Главная страница");
        List<User> userTop = userRepository.findTopByExp(5);
        userTop.forEach(item -> {
            Optional<UserLevel> opt = userLevelRepository.findById(item.Id);
            opt.ifPresentOrElse(
                    userLevel -> item.userLevel = userLevel,
                    () -> item.userLevel = userLevelRepository.save(new UserLevel(item.Id)));
        });
        ArrayList<Pack> lastPacks = packRepository.findLast(3);
        lastPacks.forEach(item -> {
            ArrayList<Sticker> stickers = stickerRepository.findByPackId(item.Id);
            System.out.println(item.Id);
            System.out.println(stickers.size());
            Sticker sticker = stickers.get(Utilities.rnd.nextInt(stickers.size()));
            item.StickerPreviewPath = Utilities.getTelegramFile(sticker.getId(), "stickers/"+item.Id);
        });
        model.addAttribute("userTop", userTop);
        model.addAttribute("lastPacks", lastPacks);
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
