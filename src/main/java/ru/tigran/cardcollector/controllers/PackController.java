package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.Optional;

@Controller
@RequestMapping("/pack")
@SessionAttributes(names = {"user", "pack"})
public class PackController {
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private PackRepository packRepository;

    @GetMapping()
    public String showAllPacks(Model model) {
        model.addAttribute("packs", packRepository.findAllSkipId1());
        return "pack/index";
    }

    @GetMapping(params = {"id"})
    public String showStickerPack(Integer id, Model model) {
        Optional<Pack> result = packRepository.findById(id);
        if (result.isEmpty()) return "redirect:/";

        Pack pack = result.get();

        model.addAttribute("pack", pack);
        model.addAttribute("stickers", id == 1 ? stickerRepository.findAll() : pack.getStickers());
        return "pack/single";
    }

    @GetMapping("/sticker")
    public String showSticker(@RequestParam Long id, Model model) {
        Optional<Sticker> result = stickerRepository.findById(id);
        if (result.isEmpty()) return "redirect:/error";

        Sticker sticker = result.get();
        Pack pack = sticker.getPack();

        int index = ListHelper.FindIndexOf(pack.getStickers(), item -> item.getId() == id);

        model.addAttribute("sticker", sticker);
        if (!model.containsAttribute("pack")) model.addAttribute("pack", pack);
        model.addAttribute("previous",
                pack.getStickers().get(index == 0 ? pack.getStickers().size() - 1 : index - 1));
        model.addAttribute("next",
                pack.getStickers().get(index == pack.getStickers().size() - 1 ? 0 : index + 1));
        return "pack/sticker";
    }
}
