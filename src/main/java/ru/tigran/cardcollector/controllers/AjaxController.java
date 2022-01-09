package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.database.repository.UserStickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(names = {"user"})
public class AjaxController {
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private UserStickerRepository userStickerRepository;

    @PostMapping(value = "/sticker")
    public String getStickers(Model model,
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

        if (sortBy != null && !sortBy.equals("")) ListHelper.SortList(stickers, sortBy);
        if (page == null) page = 1;
        int pagesCount = stickers.size() / 12;
        if (stickers.size() % 12 > 0) pagesCount++;

        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("page", page);
        model.addAttribute("author", author);
        model.addAttribute("tier", tier);
        model.addAttribute("emoji", emoji);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 12, 12));
        return "blocks/stickers";
    }

    @PostMapping(value = "/collection")
    public String getCollectionStickers(Model model,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) Integer tier,
                                        @RequestParam(required = false) String emoji,
                                        @RequestParam(required = false) String sortBy) {
        User user = (User) model.getAttribute("user");
        if (user == null) return "redirect:/";
        List<String> stickerIds = userStickerRepository.findAllStickerIdByUserId(user.Id);
        List<Sticker> stickers = stickerRepository.findAllByStickersId(stickerIds);

        List<String> authors = ListHelper.Select(stickers, item -> item.Author).stream().distinct().collect(Collectors.toList());
        ListHelper.FilterListBy(stickers, item -> item.Author, author);
        ListHelper.FilterListBy(stickers, item -> item.Tier, tier);
        List<String> emojis = ListHelper.Select(stickers, item -> item.Emoji).stream().distinct().collect(Collectors.toList());
        ListHelper.FilterListBy(stickers, item -> item.Emoji, emoji);

        if (sortBy != null && !sortBy.equals("")) ListHelper.SortList(stickers, sortBy);
        if (page == null) page = 1;
        int pagesCount = stickers.size() / 12;
        if (stickers.size() % 12 > 0) pagesCount++;

        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("page", page);
        model.addAttribute("author", author);
        model.addAttribute("tier", tier);
        model.addAttribute("emoji", emoji);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 12, 12));
        return "collection/blocks/stickers";
    }

    @PostMapping(value = "/pages")
    public String getPages(Model model, @RequestParam Integer pagesCount, @RequestParam Integer currentPage) {
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("page", currentPage);
        return "blocks/pages";
    }

    @PostMapping(value = "/toast")
    public String getToast(Model model, @RequestParam String message, @RequestParam(required = false) String title) {
        model.addAttribute("message", message);
        model.addAttribute("title", title);
        return "blocks/toast";
    }
}
