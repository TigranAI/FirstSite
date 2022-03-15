package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.*;
import ru.tigran.cardcollector.database.repository.StickerRepository;
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
@SessionAttributes(names = {"user", "stickers", "filters"})
public class CollectionController {
    @Autowired
    private UserStickerRepository userStickerRepository;
    @Autowired
    private StickerRepository stickerRepository;

    @GetMapping("/sticker")
    public String showSticker(@RequestParam Long id, Model model) {
        Sticker sticker = null;
        List<UserSticker> searchResults = (List<UserSticker>) model.getAttribute("stickers");

        if (searchResults != null) {
            int index = ListHelper.FindIndexOf(searchResults, item -> Objects.equals(item.getSticker().getId(), id));
            if (index != -1) {
                sticker = searchResults.get(index).getSticker();
                if (searchResults.size() != 1) {
                    model.addAttribute("previous",
                            searchResults.get(index == 0 ? searchResults.size() - 1 : index - 1).getSticker());
                    model.addAttribute("next",
                            searchResults.get(index == searchResults.size() - 1 ? 0 : index + 1).getSticker());
                }
            }
        }

        if (sticker == null) {
            Optional<Sticker> result = stickerRepository.findById(id);
            if (result.isPresent()) sticker = result.get();
            else return "redirect:/error";
        }

        model.addAttribute("sticker", sticker);
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

        PrepareContent(model, filters, user);
        return "collection/index";
    }

    @PostMapping
    public String updateStickers(Model model, FiltersDTO filters) {
        User user = (User) model.getAttribute("user");
        PrepareContent(model, filters, user);
        return "collection/index :: collection_page";
    }

    private void PrepareContent(Model model, FiltersDTO filters, User user) {
        List<UserSticker> userStickers = userStickerRepository.findAllByUser(user);

        List<String> authors = getAuthors(userStickers);

        if (filters.getAuthor() != null)
            userStickers.removeIf(item -> !item.getSticker().getAuthor().equals(filters.getAuthor()));

        if (filters.getTier() != null)
            userStickers.removeIf(item -> item.getSticker().getTier() != filters.getTier());

        List<String> emojis = getEmojis(userStickers);

        if (filters.getEmoji() != null)
            userStickers.removeIf(item -> !item.getSticker().getEmoji().equals(filters.getEmoji()));

        if (filters.getSortBy() != null)
            SortList(userStickers, filters.getSortBy());

        int page = filters.getPage() == null ? 1 : filters.getPage();
        Integer pagesCount = userStickers.size() / 24;
        if (userStickers.size() % 24 > 0) pagesCount++;

        model.addAttribute("stickers", ListHelper.GetRange(userStickers, (page - 1) * 24, 24));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("authors", authors);
        model.addAttribute("emojis", emojis);
        model.addAttribute("filters", filters);
    }

    private List<String> getEmojis(List<UserSticker> userStickers) {
        return userStickers.stream()
                .map(UserSticker::getSticker)
                .map(Sticker::getEmoji)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> getAuthors(List<UserSticker> userStickers) {
        return userStickers.stream()
                .map(UserSticker::getSticker)
                .map(Sticker::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }

    private void SortList(List<UserSticker> list, String sortParam) {
        switch (sortParam) {
            case "author":
                list.sort(Comparator.comparing(o -> o.getSticker().getAuthor(), String::compareToIgnoreCase));
                break;
            case "tier":
                list.sort(Comparator.comparingInt(o -> o.getSticker().getTier()));
                break;
            case "tier_desc":
                list.sort((o1, o2) -> o2.getSticker().getTier() - o1.getSticker().getTier());
                break;
            case "title":
                list.sort(Comparator.comparing(o -> o.getSticker().getTitle(), String::compareToIgnoreCase));
                break;
        }
    }
}
