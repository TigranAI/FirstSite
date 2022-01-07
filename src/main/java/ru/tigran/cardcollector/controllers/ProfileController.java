/*package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.*;
import ru.tigran.cardcollector.database.repository.*;

@Controller
@RequestMapping("/profile")
@SessionAttributes(names = {"user"})
public class ProfileController {
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private UserLevelRepository userLevelRepository;
    @Autowired
    private UserStickerRepository userStickerRepository;
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping()
    public String profile(Model model){
        if (model.containsAttribute("user")) {
            User user = (User) model.getAttribute("user");
            cashRepository.findById(user.Id).ifPresentOrElse(
                    cash -> user.cash = cash,
                    () -> user.cash = cashRepository.save(new Cash(user.Id)));
            userLevelRepository.findById(user.Id).ifPresentOrElse(
                    userLevel -> user.userLevel = userLevel,
                    () -> user.userLevel = userLevelRepository.save(new UserLevel(user.Id)));
            user.stickers = userStickerRepository.findAllByUserId(user.Id);
            user.stickers.forEach((item)->
                stickerRepository.findById(item.StickerId).ifPresent((sticker)->{
                    item.FilePath = Utilities.getTelegramFile(item.StickerId, "stickers/"+sticker.PackId);
                    item.Animated = sticker.Animated;
                })
            );
            model.addAttribute("title", "WyrmSticker | " + user.Username);
            return "profile/index";
        }
        return "redirect:/";
    }
}*/
