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
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.*;

@Controller
@SessionAttributes(names = {"user"})
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLevelRepository userLevelRepository;
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "WyrmSticker | Главная страница");

        List<User> users = userRepository.findAll();
        Hashtable<Long, User> usersTable = ListHelper.ToHashTable(users, item -> item.Id, item -> item);
        List<UserLevel> userLevels = userLevelRepository.findAll();
        ListHelper.ToList(userLevels, item -> item).forEach(item -> {
            if (usersTable.get(item.UserId).PrivilegeLevel >= 7 || item.TotalExp == 0) userLevels.remove(item);
        });
        userLevels.sort((o1, o2) -> {
            long result = o2.TotalExp - o1.TotalExp;
            if (result > 0) return 1;
            if (result < 0) return -1;
            return 0;
        });
        List<Map.Entry<String, Long>> expTop = ListHelper.ToList(ListHelper.GetRange(userLevels, 0, 5),
                userLevel -> new AbstractMap.SimpleEntry<>(usersTable.get(userLevel.UserId).Username, userLevel.TotalExp));

        ArrayList<Pack> lastPacks = packRepository.findLast(3);
        model.addAttribute("expTop", expTop);
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
