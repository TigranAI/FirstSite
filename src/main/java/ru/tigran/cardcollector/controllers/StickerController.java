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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        System.out.println("here");
        PrepareContent(model, filters);
        return "sticker/index :: stickers_page";
    }

    private void PrepareContent(Model model, FiltersDTO filters) {
        List<Sticker> stickers = stickerRepository.findAll();

        List<String> authors = getAuthors(stickers);

        if (filters.getAuthor() != null)
            stickers.removeIf(item -> !item.getAuthor().equals(filters.getAuthor()));

        if (filters.getTier() != null)
            stickers.removeIf(item -> !Objects.equals(item.getTier(), filters.getTier()));

        List<String> emojis = getEmojis(stickers);

        if (filters.getEmoji() != null)
            stickers.removeIf(item -> !item.getEmoji().equals(filters.getEmoji()));

        if (filters.getSortBy() != null)
            SortList(stickers, filters.getSortBy());

        int page = filters.getPage() == null ? 1 : filters.getPage();
        Integer pagesCount = stickers.size() / 24;
        if (stickers.size() % 24 > 0) pagesCount++;

        model.addAttribute("stickers", ListHelper.GetRange(stickers, (page - 1) * 24, 24));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("filters", filters);
    }

    private List<String> getEmojis(List<Sticker> stickers) {
        return stickers.stream()
                .map(Sticker::getEmoji)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> getAuthors(List<Sticker> stickers) {
        return stickers.stream()
                    .map(Sticker::getAuthor)
                    .distinct()
                    .collect(Collectors.toList());
    }

    private void SortList(List<Sticker> list, String sortParam) {
        switch (sortParam) {
            case "author":
                list.sort(Comparator.comparing(Sticker::getAuthor, String::compareToIgnoreCase));
                break;
            case "tier":
                list.sort(Comparator.comparingInt(Sticker::getTier));
                break;
            case "tier_desc":
                list.sort((o1, o2) -> o2.getTier() - o1.getTier());
                break;
            case "title":
                list.sort(Comparator.comparing(Sticker::getTitle, String::compareToIgnoreCase));
                break;
        }
    }
}
