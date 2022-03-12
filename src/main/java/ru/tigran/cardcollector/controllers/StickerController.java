package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;
import ru.tigran.cardcollector.models.FiltersDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sticker")
@SessionAttributes(names = {"user", "filters", "stickers"})
public class StickerController {
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping(params = {"hash"})
    public String showSticker(@RequestParam String hash, Model model) {
        List<Sticker> searchResults = (List<Sticker>) model.getAttribute("stickers");
        Sticker sticker;
        if (searchResults == null) sticker = stickerRepository.findByHash(hash);
        else {
            int index = ListHelper.FindIndexOf(searchResults, item -> Objects.equals(item.MD5Hash, hash));
            if (index == -1) sticker = stickerRepository.findByHash(hash);
            else {
                sticker = searchResults.get(index);
                model.addAttribute("previousHash", searchResults.get(index == 0 ? searchResults.size() - 1 : index - 1).MD5Hash);
                model.addAttribute("nextHash", searchResults.get(index == searchResults.size() - 1 ? 0 : index + 1).MD5Hash);
            }
        }
        model.addAttribute("sticker", sticker);
        model.addAttribute("title", "WyrmSticker | " + sticker.Title);
        return "sticker/sticker";
    }

    @GetMapping("/back")
    public String backToSearchResults(Model model) {
        FiltersDTO filters = (FiltersDTO) model.getAttribute("filters");
        if (filters == null) {
            model.addAttribute("filters", new FiltersDTO());
            return "redirect:/sticker";
        }
        return filters.asUrl("redirect:/sticker?");
    }

    @GetMapping
    public String showStickers(Model model, FiltersDTO filters) {
        PrepareContent(model, filters);
        model.addAttribute("title", "WyrmSticker | Все стикеры");
        return "sticker/index";
    }

    @PostMapping
    public String updateStickers(Model model, FiltersDTO filters) {
        PrepareContent(model, filters);
        return "sticker/index :: stickers_page";
    }

    private void PrepareContent(Model model, FiltersDTO filters) {
        List<Sticker> stickers = stickerRepository.findAll();

        List<String> authors = ListHelper.Select(stickers, item -> item.Author).stream().distinct().collect(Collectors.toList());
        if (filters.getAuthor() != null) ListHelper.FilterListBy(stickers, item -> item.Author, filters.getAuthor());
        if (filters.getTier() != null) ListHelper.FilterListBy(stickers, item -> item.Tier, filters.getTier());
        List<String> emojis = ListHelper.Select(stickers, item -> item.Emoji).stream().distinct().collect(Collectors.toList());
        if (filters.getEmoji() != null) ListHelper.FilterListBy(stickers, item -> item.Emoji, filters.getEmoji());

        if (filters.getSortBy() != null) ListHelper.SortList(stickers, filters.getSortBy());

        int page = filters.getPage() == null ? 1 : filters.getPage();
        int pagesCount = stickers.size() / 24;
        if (stickers.size() % 24 > 0) pagesCount++;

        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 24, 24));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("filters", filters);
    }
}
