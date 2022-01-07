package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.entity.UserSticker;
import ru.tigran.cardcollector.database.repository.UserStickerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/collection")
@SessionAttributes(names = {"user"})
public class CollectionController {

    @Autowired
    private UserStickerRepository userStickerRepository;

    @GetMapping
    public String collection(Model model,
                             @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) Integer tier,
                             @RequestParam(required = false) String emoji,
                             @RequestParam(required = false) String sortBy) {
        User user = (User) model.getAttribute("user");
        if (user == null) return "redirect:login";

        List<UserSticker> stickers = userStickerRepository.findAllByUserId(user.Id);
        if (author != null) stickers.removeIf(item -> !item.sticker.Author.equals(author));
        if (tier != null) stickers.removeIf(item -> !item.sticker.Tier.equals(tier));
        if (emoji != null) stickers.removeIf(item -> !item.sticker.Emoji.contains(emoji));
        if (sortBy != null) switch (sortBy) {
            case "author":
                stickers.sort(Comparator.comparing(o -> o.sticker.Author, String::compareToIgnoreCase));
                break;
            case "tier":
                stickers.sort(Comparator.comparingInt(o -> o.sticker.Tier));
                break;
            case "tier_desc":
                stickers.sort((o1, o2) -> o2.sticker.Tier - o1.sticker.Tier);
                break;
            case "title":
                stickers.sort(Comparator.comparing(o -> o.sticker.Title, String::compareToIgnoreCase));
                break;
        }
        if (page == null) page = 1;
        stickers = stickers.stream().skip((page -1) * 9L).limit(9).collect(Collectors.toList());
        model.addAttribute("stickers", stickers);
        model.addAttribute("title", "WyrmSticker | Коллекция");
        return "collection/index";
    }

    @PostMapping
    public String getStickers(Model model,
                              @RequestParam(required = false) String author,
                              @RequestParam(required = false) Integer tier,
                              @RequestParam(required = false) String emoji,
                              @RequestParam(required = false) String sortBy) {
        return "redirect:/";
    }
}
