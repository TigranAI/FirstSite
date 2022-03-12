package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        List<Pack> packs = packRepository.findAll();
        packs.forEach(item ->{
            if(item.StickerPreview == null || item.StickerPreview.equals("")){
                List<Sticker> stickers = stickerRepository.findByPackId(item.Id);
                Sticker rndSticker = stickers.get(Utilities.rnd.nextInt(stickers.size()));
                item.StickerPreview = rndSticker.Id;
                item.Animated = rndSticker.Animated;
            }
        });
        model.addAttribute("packs", packs);
        model.addAttribute("title", "WyrmSticker | Все паки");
        return "pack/index";
    }

    @GetMapping(params = {"id"})
    public String showStickerPack(Integer id, Model model) {
        Optional<Pack> result = packRepository.findById(id);
        if (result.isEmpty()) return "redirect:/";
        ArrayList<Sticker> stickers = id == 1
                ? stickerRepository.findAll()
                : stickerRepository.findByPackId(id);
        Pack pack = result.get();
        model.addAttribute("pack", pack);
        model.addAttribute("stickers", stickers);
        model.addAttribute("title", "WyrmSticker | " + pack.Author);
        return "pack/single";
    }

    @GetMapping("/sticker")
    public String showSticker(@RequestParam String hash, Model model) {
        Pack pack = (Pack) model.getAttribute("pack");
        if (pack == null) {
            Sticker sticker = stickerRepository.findByHash(hash);
            Optional<Pack> result = packRepository.findById(sticker.PackId);
            if (result.isEmpty()) return "redirect:/";
            pack = result.get();
            model.addAttribute("pack", pack);
        }

        List<Sticker> stickers = stickerRepository.findByPackId(pack.Id);
        int index = ListHelper.FindIndexOf(stickers, sticker -> sticker.MD5Hash.equals(hash));
        if (index == -1) return "redirect:/";

        model.addAttribute("sticker", stickers.get(index));
        model.addAttribute("previousHash", stickers.get(index == 0 ? stickers.size() - 1 : index - 1).MD5Hash);
        model.addAttribute("nextHash", stickers.get(index == stickers.size() - 1 ? 0 : index + 1).MD5Hash);
        model.addAttribute("title", "WyrmSticker | " + pack.Author);
        return "pack/sticker";
    }


}
