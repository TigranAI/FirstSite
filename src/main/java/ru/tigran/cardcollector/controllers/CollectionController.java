package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.database.repository.UserStickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/collection")
@SessionAttributes(names = {"user"})
public class CollectionController {
    @Autowired
    private UserStickerRepository userStickerRepository;
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping
    public String collection(Model model,
                             @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) Integer tier,
                             @RequestParam(required = false) String emoji,
                             @RequestParam(required = false) String sortBy) {
        User user = (User) model.getAttribute("user");
        if (user == null) return "redirect:login";
        List<String> stickerIds = userStickerRepository.findAllStickerIdByUserId(user.Id);
        List<Sticker> stickers = stickerRepository.findAllByStickersId(stickerIds);
        ListHelper.FilterList(stickers, author, tier, emoji);
        if (sortBy != null) ListHelper.SortList(stickers, sortBy);
        if (page == null) page = 1;
        int pagesCount = stickers.size() / 12;
        if (stickers.size() % 12 > 0) pagesCount++;

        List<String> authors = ListHelper.Select(stickers, item -> item.Author).stream().distinct().collect(Collectors.toList());
        List<String> emojis = ListHelper.Select(stickers, item -> item.Emoji).stream().distinct().collect(Collectors.toList());

        model.addAttribute("page", page);
        model.addAttribute("author", author);
        model.addAttribute("tier", tier);
        model.addAttribute("emoji", emoji);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("authors", authors);
        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page -1) * 12, 12));
        model.addAttribute("emojis", emojis);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("title", "WyrmSticker | Коллекция");
        return "collection/index";
    }
}
