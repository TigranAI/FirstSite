package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
        for (int i = 0; i < stickers.size(); ++i){
            if (Objects.equals(stickers.get(i).MD5Hash, hash)){
                model.addAttribute("previousHash", stickers.get(i != 0 ? i-1 : stickers.size() - 1).MD5Hash);
                model.addAttribute("nextHash", stickers.get(i != stickers.size() - 1 ? i+1 : 0).MD5Hash);
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
                               @RequestParam(required = false) String sortBy){
        List<Sticker> stickers = stickerRepository.findAll();
        if (author != null) stickers.removeIf(item -> !item.Author.equals(author));
        if (tier != null) stickers.removeIf(item -> !item.Tier.equals(tier));
        if (emoji != null) stickers.removeIf(item -> !item.Emoji.contains(emoji));
        if (sortBy != null) switch (sortBy) {
            case "author":
                stickers.sort(Comparator.comparing(o -> o.Author, String::compareToIgnoreCase));
                break;
            case "tier":
                stickers.sort(Comparator.comparingInt(o -> o.Tier));
                break;
            case "tier_desc":
                stickers.sort((o1, o2) -> o2.Tier - o1.Tier);
                break;
            case "title":
                stickers.sort(Comparator.comparing(o -> o.Title, String::compareToIgnoreCase));
                break;
        }
        if (page == null) page = 1;
        int pagesCount = stickers.size() / 9;
        if (stickers.size() % 9 > 0) pagesCount++;
        stickers = stickers.stream().skip((page -1) * 9L).limit(9).collect(Collectors.toList());
        List<String> authors = packRepository.findDistinctAuthors();
        List<String> emojis = stickerRepository.findDistinctEmojis();
        model.addAttribute("page", page);
        model.addAttribute("author", author);
        model.addAttribute("tier", tier);
        model.addAttribute("emoji", emoji);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("authors", authors);
        model.addAttribute("stickers", stickers);
        model.addAttribute("emojis", emojis);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("title", "WyrmSticker | Все стикеры");
        return "sticker/index";
    }

    @PostMapping
    public String getStickers(Model model,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) String author,
                               @RequestParam(required = false) Integer tier,
                               @RequestParam(required = false) String emoji,
                               @RequestParam(required = false) String sortBy){
        List<Sticker> stickers = stickerRepository.findAll();
        if (author != null && !author.equals("")) stickers.removeIf(item -> !item.Author.equals(author));
        if (tier != null) stickers.removeIf(item -> !item.Tier.equals(tier));
        if (emoji != null && !emoji.equals("")) stickers.removeIf(item -> !item.Emoji.contains(emoji));
        if (sortBy != null && !sortBy.equals("")) switch (sortBy) {
            case "author":
                stickers.sort(Comparator.comparing(o -> o.Author, String::compareToIgnoreCase));
                break;
            case "tier":
                stickers.sort(Comparator.comparingInt(o -> o.Tier));
                break;
            case "tier_desc":
                stickers.sort((o1, o2) -> o2.Tier - o1.Tier);
                break;
            case "title":
                stickers.sort(Comparator.comparing(o -> o.Title, String::compareToIgnoreCase));
                break;
        }
        if (page == null) page = 1;
        int pagesCount = stickers.size() / 9;
        if (stickers.size() % 9 > 0) pagesCount++;
        stickers = stickers.stream().skip((page -1) * 9L).limit(9).collect(Collectors.toList());
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("stickers", stickers);
        return "sticker/blocks/stickers";
    }

    @PostMapping(value = "/pages")
    public String getPages(Model model, @RequestParam Integer pagesCount, @RequestParam Integer currentPage){
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("page", currentPage);
        return "sticker/blocks/pages";
    }
}
