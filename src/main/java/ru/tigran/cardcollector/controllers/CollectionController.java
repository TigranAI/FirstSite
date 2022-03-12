package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.*;
import ru.tigran.cardcollector.database.repository.CashRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.database.repository.UserLevelRepository;
import ru.tigran.cardcollector.database.repository.UserStickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;
import ru.tigran.cardcollector.models.FiltersDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/collection")
@SessionAttributes(names = {"user", "cash", "level", "stickers", "filters"})
public class CollectionController {
    @Autowired
    private UserStickerRepository userStickerRepository;
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private UserLevelRepository userLevelRepository;

    @GetMapping("/sticker")
    public String showSticker(@RequestParam String hash, Model model) {
        List<UserSticker> searchResults = (List<UserSticker>) model.getAttribute("stickers");
        Sticker sticker;
        if (searchResults == null) sticker = stickerRepository.findByHash(hash);
        else {
            int index = ListHelper.FindIndexOf(searchResults, item -> Objects.equals(item.sticker.MD5Hash, hash));
            if (index == -1) sticker = stickerRepository.findByHash(hash);
            else {
                sticker = searchResults.get(index).sticker;
                model.addAttribute("previousHash", searchResults.get(index == 0 ? searchResults.size() - 1 : index - 1).sticker.MD5Hash);
                model.addAttribute("nextHash", searchResults.get(index == searchResults.size() - 1 ? 0 : index + 1).sticker.MD5Hash);
            }
        }
        model.addAttribute("sticker", sticker);
        model.addAttribute("title", "WyrmSticker | " + sticker.Title);
        return "collection/sticker";
    }

    @GetMapping("/back")
    public String backToSearchResults(Model model) {
        FiltersDTO filters = (FiltersDTO) model.getAttribute("filters");
        if (filters == null) {
            model.addAttribute("filters", new FiltersDTO());
            return "redirect:/collection";
        }
        return filters.asUrl("redirect:/collection?");
    }

    @GetMapping
    public String showStickers(Model model, FiltersDTO filters) {
        User user = (User) model.getAttribute("user");
        if (user == null) return "redirect:login";

        if (!model.containsAttribute("cash")) {
            Optional<Cash> cash = cashRepository.findById(user.Id);
            cash.ifPresent(item -> model.addAttribute("cash", item));
        }
        if (!model.containsAttribute("level")) {
            Optional<UserLevel> level = userLevelRepository.findById(user.Id);
            level.ifPresent(item -> model.addAttribute("level", item));
        }

        PrepareContent(model, filters, user.Id);

        model.addAttribute("title", "WyrmSticker | Коллекция");
        return "collection/index";
    }

    @PostMapping
    public String updateStickers(Model model, FiltersDTO filters) {
        User user = (User) model.getAttribute("user");
        PrepareContent(model, filters, user.Id);
        return "collection/index :: collection_page";
    }

    private void PrepareContent(Model model, FiltersDTO filters, long userId) {
        List<UserSticker> stickers = userStickerRepository.findAllByUserId(userId);

        List<String> authors = ListHelper.Select(stickers, item -> item.sticker.Author).stream().distinct().collect(Collectors.toList());
        if (filters.getAuthor() != null) ListHelper.FilterListBy(stickers, item -> item.sticker.Author, filters.getAuthor());
        if (filters.getTier() != null) ListHelper.FilterListBy(stickers, item -> item.sticker.Tier, filters.getTier());
        List<String> emojis = ListHelper.Select(stickers, item -> item.sticker.Emoji).stream().distinct().collect(Collectors.toList());
        if (filters.getEmoji() != null) ListHelper.FilterListBy(stickers, item -> item.sticker.Emoji, filters.getEmoji());

        if (filters.getSortBy() != null) SortList(stickers, filters.getSortBy());

        int page = filters.getPage() == null ? 1 : filters.getPage();
        int pagesCount = stickers.size() / 24;
        if (stickers.size() % 24 > 0) pagesCount++;

        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 24, 24));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("filters", filters);
    }

    private void SortList(List<UserSticker> list, String sortParam) {
        switch (sortParam) {
            case "author":
                list.sort(Comparator.comparing(o -> o.sticker.Author, String::compareToIgnoreCase));
                break;
            case "tier":
                list.sort(Comparator.comparingInt(o -> o.sticker.Tier));
                break;
            case "tier_desc":
                list.sort((o1, o2) -> o2.sticker.Tier - o1.sticker.Tier);
                break;
            case "title":
                list.sort(Comparator.comparing(o -> o.sticker.Title, String::compareToIgnoreCase));
                break;
        }
    }
}
