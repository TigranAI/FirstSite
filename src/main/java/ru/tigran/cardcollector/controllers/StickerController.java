package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sticker")
@SessionAttributes(names = {"user", "cash"})
public class StickerController {
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private PackRepository packRepository;

    @GetMapping(params = {"hash"})
    public String showSticker(@RequestParam String hash, Model model) {
        Sticker sticker = stickerRepository.findByHash(hash);
        ArrayList<Sticker> stickers = stickerRepository.findByPackId(sticker.PackId);
        for (int i = 0; i < stickers.size(); ++i) {
            if (Objects.equals(stickers.get(i).MD5Hash, hash)) {
                model.addAttribute("previousHash", stickers.get(i != 0 ? i - 1 : stickers.size() - 1).MD5Hash);
                model.addAttribute("nextHash", stickers.get(i != stickers.size() - 1 ? i + 1 : 0).MD5Hash);
                break;
            }
        }
        model.addAttribute("sticker", sticker);
        model.addAttribute("title", "WyrmSticker | " + sticker.Title);
        return "sticker/sticker";
    }

    @GetMapping
    public String showStickers(Model model,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) String author,
                               @RequestParam(required = false) Integer tier,
                               @RequestParam(required = false) String emoji,
                               @RequestParam(required = false) String sortBy) {
        List<Sticker> stickers = stickerRepository.findAll();

        List<String> authors = ListHelper.Select(stickers, item -> item.Author).stream().distinct().collect(Collectors.toList());
        ListHelper.FilterListBy(stickers, item -> item.Author, author);
        ListHelper.FilterListBy(stickers, item -> item.Tier, tier);
        List<String> emojis = ListHelper.Select(stickers, item -> item.Emoji).stream().distinct().collect(Collectors.toList());
        ListHelper.FilterListBy(stickers, item -> item.Emoji, emoji);

        if (sortBy != null) ListHelper.SortList(stickers, sortBy);
        if (page == null) page = 1;
        int pagesCount = stickers.size() / 12;
        if (stickers.size() % 12 > 0) pagesCount++;

        model.addAttribute("page", page);
        model.addAttribute("author", author);
        model.addAttribute("tier", tier);
        model.addAttribute("emoji", emoji);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("authors", authors);
        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 12, 12));
        model.addAttribute("emojis", emojis);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("title", "WyrmSticker | Все стикеры");
        return "sticker/index";
    }
}
