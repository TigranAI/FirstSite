package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/pack")
@SessionAttributes(names = {"user", "cash"})
public class PackController {

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private PackRepository packRepository;

    @GetMapping(params = {"id"})
    public String showStickerPack(Integer id, Model model) {
        Optional<Pack> result = packRepository.findById(id);
        if (result.isEmpty()) return "redirect:/";
        ArrayList<Sticker> stickers = id == 1
                ? stickerRepository.findAll()
                : stickerRepository.findByPackId(id);
        for (Sticker sticker : stickers)
            sticker.FilePath = Utilities.getTelegramFile(sticker.Id, "stickers/" + sticker.PackId);
        Pack pack = result.get();
        model.addAttribute("pack", pack);
        model.addAttribute("stickers", stickers);
        model.addAttribute("title", "WyrmSticker | " + pack.Author);
        return "pack/single";
    }

    @GetMapping()
    public String showAllPacks(Model model) {
        ArrayList<Pack> packs = packRepository.findAll();
        for (Pack p : packs){
            ArrayList<Sticker> stickers = stickerRepository.findByPackId(p.Id);
            Sticker sticker = stickers.get(Utilities.rnd.nextInt(stickers.size()));
            p.StickerPreviewPath = Utilities.getTelegramFile(sticker.Id, "stickers/" + p.Id);
        }
        model.addAttribute("packs", packs);
        model.addAttribute("title", "WyrmSticker | Все паки");
        return "pack/index";
    }
}
