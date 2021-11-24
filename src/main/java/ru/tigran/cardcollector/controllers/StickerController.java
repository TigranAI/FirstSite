package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;

import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/sticker")
@SessionAttributes(names = {"user", "cash"})
public class StickerController {

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private PackRepository packRepository;

    @GetMapping(params = {"hash"})
    public String showSticker(String hash, Model model) {
        Sticker sticker = stickerRepository.findByHash(hash);
        sticker.FilePath = Utilities.getTelegramFile(sticker.Id, "stickers/" + sticker.PackId);
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
        return "sticker/index";
    }
}
