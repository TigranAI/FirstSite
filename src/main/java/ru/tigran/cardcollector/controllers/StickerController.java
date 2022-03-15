package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;
import ru.tigran.cardcollector.models.FiltersDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/sticker")
@SessionAttributes(names = {"user", "filters", "stickers"})
public class StickerController {
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping(params = {"hash"})
    public String showSticker(@RequestParam Long id, Model model) {
        List<Sticker> searchResults = (List<Sticker>) model.getAttribute("stickers");
        Sticker sticker = null;
        if (searchResults != null){
            int index = ListHelper.FindIndexOf(searchResults, item -> item.getId() == id);
            if (index != -1) {
                sticker = searchResults.get(index);
                if (searchResults.size() != 1) {
                    model.addAttribute("previous",
                            searchResults.get(index == 0 ? searchResults.size() - 1 : index - 1));
                    model.addAttribute("next",
                            searchResults.get(index == searchResults.size() - 1 ? 0 : index + 1));
                }
            }
        }

        if (sticker == null){
            Optional<Sticker> result = stickerRepository.findById(id);
            if (result.isPresent()) sticker = result.get();
            else return "redirect:/error";
        }

        model.addAttribute("sticker", sticker);
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
        return "sticker/index";
    }

    @PostMapping
    public String updateStickers(Model model, FiltersDTO filters) {
        PrepareContent(model, filters);
        return "sticker/index :: stickers_page";
    }

    private void PrepareContent(Model model, FiltersDTO filters) {
        Stream<Sticker> stickers = stickerRepository.findAll().stream();

        List<String> authors = getAuthors(stickers);

        if (filters.getAuthor() != null)
            stickers = stickers.filter(item -> item.getAuthor().equals(filters.getAuthor()));

        if (filters.getTier() != null)
            stickers = stickers.filter(item -> item.getTier() == filters.getTier());

        List<String> emojis = getEmojis(stickers);

        if (filters.getEmoji() != null)
                stickers = stickers.filter(item -> item.getEmoji().equals(filters.getEmoji()));

        if (filters.getSortBy() != null)
            stickers = SortList(stickers, filters.getSortBy());

        int page = filters.getPage() == null ? 1 : filters.getPage();
        long pagesCount = stickers.count() / 24;
        if (stickers.count() % 24 > 0) pagesCount++;

        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 24, 24));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("filters", filters);
    }

    private List<String> getEmojis(Stream<Sticker> stickers) {
        return stickers
                .map(Sticker::getEmoji)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> getAuthors(Stream<Sticker> stickers) {
        return stickers
                    .map(Sticker::getAuthor)
                    .distinct()
                    .collect(Collectors.toList());
    }

    private Stream<Sticker> SortList(Stream<Sticker> list, String sortParam) {
        switch (sortParam) {
            case "author":
                return list.sorted(Comparator.comparing(Sticker::getAuthor, String::compareToIgnoreCase));
            case "tier":
                return list.sorted(Comparator.comparingInt(Sticker::getTier));
            case "tier_desc":
                return list.sorted((o1, o2) -> o2.getTier() - o1.getTier());
            case "title":
                return list.sorted(Comparator.comparing(Sticker::getTitle, String::compareToIgnoreCase));
            default:
                return list;
        }
    }
}
